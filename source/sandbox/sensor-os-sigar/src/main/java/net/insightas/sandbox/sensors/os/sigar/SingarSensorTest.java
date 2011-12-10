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

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;


/**
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class SingarSensorTest {
    
    public static void main(String[] args) throws SigarException {
    
        System.setProperty("org.hyperic.sigar.path", new File("./lib").getAbsolutePath());
        Sigar sigar = new Sigar();

        CpuInfo[] cpuInfoArray = sigar.getCpuInfoList();

        for (int i = 0; i < cpuInfoArray.length; i++) {
            CpuInfo cpuInfo = cpuInfoArray[i];
            System.out.println("CPU " + (i+1) + " : " + cpuInfo.getVendor() + " " + cpuInfo.getModel());
            System.out.println("\tClock Speed : " + cpuInfo.getMhz() + " MHz");
            System.out.println("\tTotal Cores : " + cpuInfo.getTotalCores());
            System.out.println("\tCache Size : " + cpuInfo.getCacheSize() + " KB");
        }
        
        CpuPerc cpuPerc = sigar.getCpuPerc();
        System.out.println("CPU Idle : " + (cpuPerc.getIdle()  * 100));
        System.out.println("CPU User : " + (cpuPerc.getUser()  * 100));
        System.out.println("CPU System : " + (cpuPerc.getSys()  * 100));
        System.out.println("CPU Combined : " + (cpuPerc.getCombined())  * 100);
        
        FileSystem[] fileSystems = sigar.getFileSystemList();
        
        for (FileSystem fs : fileSystems) {
            System.out.println("Dev Name : " + fs.getDevName());
            System.out.println("Dir Name : " + fs.getDirName());
            System.out.println("Type : " + fs.getSysTypeName());
            
            FileSystemUsage fsu = sigar.getFileSystemUsage(fs.getDirName());
            System.out.println("Size MB : " + (fsu.getTotal() / 1024));
            System.out.println("Used MB : " + (fsu.getUsed() / 1024));
            System.out.println();
        }
        
        
    }
    
}
