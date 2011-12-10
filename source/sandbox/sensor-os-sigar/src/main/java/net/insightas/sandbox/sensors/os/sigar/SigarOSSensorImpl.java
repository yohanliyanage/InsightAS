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

package net.insightas.sandbox.sensors.os.sigar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SysInfo;

import net.insightas.sandbox.sensors.api.Sensor;
import net.insightas.sandbox.sensors.constants.SensorAttributeConstants;
import net.insightas.sandbox.sensors.dto.FileSystemInfo;
import net.insightas.sandbox.sensors.dto.NICInfo;
import net.insightas.sandbox.sensors.dto.ProcessorInfo;
import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorFailureException;


/**
 * Sigar based Operating System Sensor Implementation.
 * <p>
 * For more information regarding Sigar API, see {@link http://www.hyperic.com/support/docs/sigar/}.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class SigarOSSensorImpl implements Sensor {

    private static final Log LOG = LogFactory.getLog(SigarOSSensorImpl.class);
    
   /**
    * Sigar Instance.
    */
    private Sigar sigar;
    
    
    // References to permanent sensor data for performance improvements.
    private SensorData osData;
    private SensorData cpuInfoData;
    private SensorData networkData;
    
    /* (non-Javadoc)
     * @see net.insightas.sandbox.sensors.api.Sensor#collect(java.util.Properties)
     */
    public SensorData collect(Properties settings) throws SensorFailureException {

            SensorData sensorData = new SensorData();
            
            initializeSigar();
            
            try {
                // OS Information
                sensorData.addAllSensorData(collectOSInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect OS information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
         
            try {
                // Uptime
                sensorData.add(SensorAttributeConstants.OSConstants.UPTIME_SECONDS, sigar.getUptime().getUptime());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect Uptime information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            try {
                // CPU Information
                sensorData.addAllSensorData(collectCPUInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect CPU information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            try {
                // CPU Usage
                sensorData.addAllSensorData(collectCPUUsageInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect CPU Usage information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            try {
                // Memory
                sensorData.addAllSensorData(collectMemoryInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect memory information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            try {
                // File Systems
                sensorData.addAllSensorData(collectFSInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect file system information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            try {
                // Network
                sensorData.addAllSensorData(collectNetworkInfo());
            } catch (SigarException e) {
                LOG.error("Sigar failed to collect network information : " + e.getMessage(), e);
                throw new SensorFailureException("Sigar Failed : " + e.getMessage(), e);
            }
            
            return sensorData;
        
    }

    /**
     * @throws SigarException
     */
    private SensorData collectNetworkInfo() throws SigarException {
        
        if (networkData == null) {
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("Collecting Network Information");
            }
            
            SensorData data = new SensorData();
            
            data.add(SensorAttributeConstants.NetworkConstants.FQDN, sigar.getFQDN());
            
            List<NICInfo> interfaces = new ArrayList<NICInfo>();
            
            for (String nic : sigar.getNetInterfaceList()) {
                
                NICInfo info = new NICInfo();
                
                NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(nic);
                
                info.setName(nic);
                info.setIpAddress(cfg.getAddress());
                info.setMacAddress(cfg.getHwaddr());
                info.setType(cfg.getType());

                interfaces.add(info);
            }
            
            data.add(SensorAttributeConstants.NetworkConstants.NIC_DATA, interfaces);
            
            networkData = data;
        }
        
        return networkData;
    }

    /**
     * Collects File System Information.
     * @throws SigarException
     */
    private SensorData collectFSInfo() throws SigarException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Collecting File System Information");
        }
        
        SensorData data = new SensorData();
        
        FileSystem[] fileSystems = sigar.getFileSystemList();
        
        List<FileSystemInfo> fsInfoList = new ArrayList<FileSystemInfo>();
        
        for (FileSystem fs : fileSystems) {
            
            FileSystemInfo info = new FileSystemInfo();
            
            info.setDiskName(fs.getDevName());
            info.setLocation(fs.getDirName());
            info.setType(fs.getSysTypeName());
            
            FileSystemUsage fsu = sigar.getFileSystemUsage(fs.getDirName());
            
            info.setTotalMB(fsu.getTotal() / 1024);
            info.setUsedMB(fsu.getUsed() / 1024);
            
            fsInfoList.add(info);
        }
        
        data.add(SensorAttributeConstants.FileSystemConstants.FS_DATA, fsInfoList);
        
        return data;
    }

    /**
     * Collects and returns Memory Information.
     * @throws SigarException
     */
    private SensorData collectMemoryInfo() throws SigarException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Collecting Memory Utilization Information");
        }
        SensorData data = new SensorData();
        
        Mem mem = sigar.getMem();
        
        data.add(SensorAttributeConstants.MemoryConstants.TOTAL_MEMORY, (mem.getTotal() / (1024 * 1024)));
        data.add(SensorAttributeConstants.MemoryConstants.FREE_MEMORY, (mem.getFree() / (1024 * 1024)));
        data.add(SensorAttributeConstants.MemoryConstants.MEM_ACTUAL_USED, (mem.getActualUsed() / (1024 * 1024)));
        
        return data;
    }

    /**
     * Collects and returns CPU Usage Information for this instant.
     * @throws SigarException
     */
    private SensorData collectCPUUsageInfo() throws SigarException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Collecting CPU Usage Information");
        }
        SensorData data = new SensorData();
        
        CpuPerc cpuPerc = sigar.getCpuPerc();
        
        data.add(SensorAttributeConstants.CPUConstants.CPU_IDLE, (cpuPerc.getIdle()  * 100));
        data.add(SensorAttributeConstants.CPUConstants.CPU_USAGE_USER, (cpuPerc.getUser()  * 100));
        data.add(SensorAttributeConstants.CPUConstants.CPU_USAGE_SYSTEM, (cpuPerc.getSys()  * 100));
        data.add(SensorAttributeConstants.CPUConstants.CPU_USAGE_TOTAL, (cpuPerc.getCombined()  * 100));
        
        return data;
    }

    /**
     * @throws SigarException
     */
    private SensorData collectCPUInfo() throws SigarException {
        
        if (cpuInfoData == null) {

            if (LOG.isDebugEnabled()) {
                LOG.debug("Collecting CPU Information");
            }
            
            SensorData data = new SensorData();
            
            // Note : We are using a Set to avoid duplicates. Duplicate entries are possible
            // with Sigar API on multi-core processors. With the equals implementation on the
            // ProcessorInfo class, using a Set will ensure that we will have multiple entries only
            // if the processor information is different.
            
            // However, using this approach will provide us only a single ProcessorInfo line even if there
            // are multiple identical processors. However, since we are interested only on display of the information
            // it is assumed that it will not be a concern.
            
            Set<ProcessorInfo> processors = new HashSet<ProcessorInfo>();
            
            // CPU Info
            CpuInfo[] cpuInfoArray = sigar.getCpuInfoList();

            for (int i = 0; i < cpuInfoArray.length; i++) {
                CpuInfo cpuInfo = cpuInfoArray[i];
                
                ProcessorInfo processor = new ProcessorInfo();
                processor.setVendor(cpuInfo.getVendor());
                processor.setModel(cpuInfo.getModel());
                processor.setClockSpeedMHz(cpuInfo.getMhz());
                processor.setCores(cpuInfo.getTotalCores());
                processor.setCacheSizeKB(cpuInfo.getCacheSize());
                processors.add(processor);
            }
            
            data.add(SensorAttributeConstants.CPUConstants.CPU_INFO_DATA, new ArrayList<ProcessorInfo>(processors));
            cpuInfoData = data;
        }

        return cpuInfoData;
    }

    /**
     * @throws SigarException
     */
    private SensorData collectOSInfo() throws SigarException {
        
        if (osData == null) {
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("Collecting Operating System Information...");
            }
            
            SensorData data = new SensorData();
            
            // Get OS Info from Sigar
            SysInfo sysInfo = new SysInfo();
            sysInfo.gather(sigar);
            
            data.add(SensorAttributeConstants.OSConstants.OS_NAME, sysInfo.getName());
            data.add(SensorAttributeConstants.OSConstants.OS_VENDOR, sysInfo.getVendor());
            data.add(SensorAttributeConstants.OSConstants.OS_VERSION, sysInfo.getVersion());
            data.add(SensorAttributeConstants.OSConstants.OS_ARCHITECTURE, sysInfo.getArch());
            data.add(SensorAttributeConstants.OSConstants.OS_DESCRIPTION, sysInfo.getDescription());
            data.add(SensorAttributeConstants.OSConstants.OS_PATCH_INFO, sysInfo.getPatchLevel());
            
            osData = data;
        }
        
        return osData;
    }

    /**
     * Initializes Sigar.
     */
    private void initializeSigar() {
        
        if (sigar == null) {
            LOG.info("Initializing Sigar...");
            System.setProperty(SigarConstants.SIGAR_PATH, new File(SigarConstants.SIGAR_PATH_DEFAULT).getAbsolutePath());
            sigar = new Sigar();
        }
    }
    
}
 