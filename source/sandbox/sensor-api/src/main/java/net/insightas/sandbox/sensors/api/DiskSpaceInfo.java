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

/**
 * Contains Utilization Information about a particular disk.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class DiskSpaceInfo implements Serializable {
    
    private static final long serialVersionUID = 6218143495061921076L;
    
    private String diskName;
    private String mountPoint;
    private Integer utilizedMB;
    private Integer totalMB;
    
    /**
     * @return the diskName
     */
    public String getDiskName() {
        return diskName;
    }
    
    /**
     * @param diskName the diskName to set
     */
    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }
    
    /**
     * @return the mountPoint
     */
    public String getMountPoint() {
        return mountPoint;
    }
    
    /**
     * @param mountPoint the mountPoint to set
     */
    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }
    
    /**
     * @return the utilizedMB
     */
    public Integer getUtilizedMB() {
        return utilizedMB;
    }
    
    /**
     * @param utilizedMB the utilizedMB to set
     */
    public void setUtilizedMB(Integer utilizedMB) {
        this.utilizedMB = utilizedMB;
    }
    
    /**
     * @return the totalMB
     */
    public Integer getTotalMB() {
        return totalMB;
    }
    
    /**
     * @param totalMB the totalMB to set
     */
    public void setTotalMB(Integer totalMB) {
        this.totalMB = totalMB;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DiskSpaceInfo [diskName=" + diskName + ", mountPoint=" + mountPoint + ", utilizedMB=" + utilizedMB + ", totalMB=" + totalMB
                + "]";
    }
    
    
    
}
