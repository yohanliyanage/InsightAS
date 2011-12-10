/*
 * Copyright 2011 Yohan Liyanage.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package net.insight.sandbox.sensors.os.unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import net.insightas.sandbox.sensors.api.Sensor;
import net.insightas.sandbox.sensors.constants.SensorAttributeConstants;
import net.insightas.sandbox.sensors.dto.FileSystemInfo;
import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorFailureException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Unix Disk Space Sensor.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class UnixDiskSpaceSensor implements Sensor {
    
    private static final Log LOG = LogFactory.getLog(UnixDiskSpaceSensor.class);
    
    /**
     * Collects and returns Unix Disk Space Information.
     * @return SensorData for Disk Space.
     */
    public SensorData collect(Properties settings) throws SensorFailureException {

        LOG.info("[UnixDiskSpaceSensor] Collecting Disk Space Data...");
        
        SensorData data = new SensorData();
        data.add(SensorAttributeConstants.FileSystemConstants.FS_DATA, collectDiskSpaceStatistics());
        return data;
    }

    /**
     * Internal method to collect disk space statistics.
     * 
     * @return a list of DiskSpaceInfo objects, each representing a partition / disk in the system. 
     * @throws SensorFailureException if application fails to parse information.
     */
    private List<FileSystemInfo> collectDiskSpaceStatistics() throws SensorFailureException {
        
        BufferedReader bufReader = null;
        
        try {
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("[UnixDiskSpaceSensor] Invoking System Command : 'df -km'");
            }
            
            Process p = Runtime.getRuntime().exec(new String[] {"df", "-km"});
            
            // Read Process Output
            bufReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            // Note on Output :
            //      df -km output is in the following format for all common *nix implementations, including Linux and Mac OS X
            //              Disk      Disk Size (MB)      Used (MB)   Available (MB)     Use (%)      Mount Point
            //      The following code parses this output to extract the details into the DTO.
            
            
            // TODO : This implementation fails if the Disk Capacity Header is smaller than the actual size data (because we are using headers to determine the boundaries). 
            // Need to come up with a better implementation of this.
            
            List<FileSystemInfo> diskList = new ArrayList<FileSystemInfo>();
            int lineCount = 0;
            
            String line = null;
            
            // Initialize Indexes
            int[] tokenStartIndices = null;
            
            while ( (line = bufReader.readLine()) != null) {
                
                lineCount++;
                
                // Ignore First Line. It's the headers
                if (lineCount == 1) {
                    
                    // Identify starting indexes for each section
                    tokenStartIndices = calibrateIndices(line);
                    continue;
                }
                
                // Process Line, Extract Information, Add to List
                diskList.add(processDiskInfoLine(line, tokenStartIndices));
            }
            
            // Wait until process exits
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                LOG.warn("Interrupted while waiting for the df process to exit", e);
            }
            
            return diskList;
            
        } catch (IOException e) {
            LOG.warn("Failed to read Disk Space information due to IOException", e);
            throw new  SensorFailureException("DiskSpace Sensor Failed due to IOException", e);
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    LOG.warn("Unable to close InputStream from external process (df).", e);
                }
            }
        }
    }

    /**
     * Parses the Header Line to calibrate and identify the starting index of each section in output.
     * 
     * @param line header line
     * @return
     */
    private int[] calibrateIndices(String line) {
        
        int[] indices = new int[6];
        
        Scanner scanner = new Scanner(line);
        scanner.next();
        // Disk Name always begins at zero
        indices[0] = 0;
        
        // Disk Size
        scanner.next();
        indices[1] = scanner.match().start();
        
        // Used
        indices[2] = scanner.match().end() + 1;

        // Available
        scanner.next();
        indices[3] = scanner.match().end() + 1;
        
        // Percentage
        scanner.next();
        indices[4] = scanner.match().end() + 1;

        // Mount Point
        scanner.next();
        indices[5] = scanner.match().end() + 1;

        return indices;
    }

    /**
     * Processes a df -k output line to extract data and populate to a {@link FileSystemInfo} instance.
     * 
     * @param line df -k output line 
     * @param tokenStartIndices token start position indexes
     * @return {@link FileSystemInfo} instance
     */
    private FileSystemInfo processDiskInfoLine(String line, int[] tokenStartIndices) {
        
        try {
            
            FileSystemInfo info = new FileSystemInfo();
            
            info.setDiskName(line.substring(tokenStartIndices[0], tokenStartIndices[1]).trim());
            info.setTotalMB(Long.valueOf(line.substring(tokenStartIndices[1], tokenStartIndices[2]).trim()));
            info.setUsedMB(Long.valueOf(line.substring(tokenStartIndices[2], tokenStartIndices[3]).trim()));
            info.setLocation(line.substring(tokenStartIndices[5]));
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("[UnixDiskSpaceSensor] Collected Disk Info : " + info);
            }
            
            return info;
            
        } catch (NumberFormatException e) {
            LOG.warn("Failed to parse Disk Space Information from df output line : " + line, e);
            return null;
        }
    }
}
