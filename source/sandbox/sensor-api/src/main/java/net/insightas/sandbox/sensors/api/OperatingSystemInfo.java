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

package net.insightas.sandbox.sensors.api;

import java.io.Serializable;
import java.util.List;

/**
 * Operating System Information.
 * <p>
 * This class contains Operating System level information gathered by the relevant sensors.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class OperatingSystemInfo implements Serializable {
    
    private static final long serialVersionUID = -517609253047922455L;
    
    private String cpuUtilization;
    private String memoryUtilization;
    private List<DiskSpaceInfo> diskInfo;
    
    /**
     * @return the cpuUtilization
     */
    public String getCpuUtilization() {
        return cpuUtilization;
    }
    
    /**
     * @param cpuUtilization the cpuUtilization to set
     */
    public void setCpuUtilization(String cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }
    
    /**
     * @return the memoryUtilization
     */
    public String getMemoryUtilization() {
        return memoryUtilization;
    }
    
    /**
     * @param memoryUtilization the memoryUtilization to set
     */
    public void setMemoryUtilization(String memoryUtilization) {
        this.memoryUtilization = memoryUtilization;
    }
    
    /**
     * @return the diskInfo
     */
    public List<DiskSpaceInfo> getDiskInfo() {
        return diskInfo;
    }
    
    /**
     * @param diskInfo the diskInfo to set
     */
    public void setDiskInfo(List<DiskSpaceInfo> diskInfo) {
        this.diskInfo = diskInfo;
    }
    
}
