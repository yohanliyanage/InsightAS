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
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SysInfo;


/**
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class SingarSensorTest {
    
    public static void main(String[] args) throws SigarException {
    
        System.setProperty(SigarConstants.SIGAR_PATH, new File(SigarConstants.SIGAR_PATH_DEFAULT).getAbsolutePath());
        Sigar sigar = new Sigar();

        // OS Info
        SysInfo sysInfo = new SysInfo();
        sysInfo.gather(sigar);
        
        System.out.println("OS Name : " + sysInfo.getName());
        System.out.println("OS Vendor : " + sysInfo.getVendor());
        System.out.println("OS Version : " + sysInfo.getVersion());
        System.out.println("OS Architecture : " + sysInfo.getArch());
        System.out.println("Description : " + sysInfo.getDescription());
        System.out.println("Patches : " + sysInfo.getPatchLevel());
        System.out.println();
        
        // CPU Info
        CpuInfo[] cpuInfoArray = sigar.getCpuInfoList();

        for (int i = 0; i < cpuInfoArray.length; i++) {
            CpuInfo cpuInfo = cpuInfoArray[i];
            System.out.println("CPU " + (i+1) + " : " + cpuInfo.getVendor() + " " + cpuInfo.getModel());
            System.out.println("\tClock Speed : " + cpuInfo.getMhz() + " MHz");
            System.out.println("\tTotal Cores : " + cpuInfo.getTotalCores());
            System.out.println("\tCache Size : " + cpuInfo.getCacheSize() + " KB");
        }
        
        
        // CPU Usage
        CpuPerc cpuPerc = sigar.getCpuPerc();
        System.out.println("CPU Idle : " + (cpuPerc.getIdle()  * 100));
        System.out.println("CPU User : " + (cpuPerc.getUser()  * 100));
        System.out.println("CPU System : " + (cpuPerc.getSys()  * 100));
        System.out.println("CPU Combined : " + (cpuPerc.getCombined())  * 100);

        // Memory
        Mem mem = sigar.getMem();
        System.out.println("Mem Total (MB) : " + (mem.getTotal() / (1024 * 1024)));
        System.out.println("Mem Free  (MB) : " + (mem.getFree() / (1024 * 1024)));
        System.out.println("Mem Actual In Use (MB) : " + mem.getActualUsed() / (1024*1024));
        
        // File Systems
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
        
        // Network
        System.out.println("FQDN : " + sigar.getFQDN());
        for (String nic : sigar.getNetInterfaceList()) {
            System.out.println("NIC : " + nic);
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(nic);
            System.out.println("IP Address : " + cfg.getAddress());
            System.out.println("MAC Address : " + cfg.getHwaddr());
            System.out.println("Type : " + cfg.getType());
            System.out.println();
        }
        System.out.println();
        System.out.println("Uptime (seconds) : " + sigar.getUptime().getUptime());
    }
    
}

