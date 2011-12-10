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

package net.insightas.sandbox.sensors.dto;

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
    private String location;
    private String type;
    private Long usedMB;
    private Long totalMB;
    
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    
    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the usedMB
     */
    public Long getUsedMB() {
        return usedMB;
    }
    
    /**
     * @param usedMB the usedMB to set
     */
    public void setUsedMB(Long usedMB) {
        this.usedMB = usedMB;
    }
    
    /**
     * @return the totalMB
     */
    public Long getTotalMB() {
        return totalMB;
    }
    
    /**
     * @param totalMB the totalMB to set
     */
    public void setTotalMB(Long totalMB) {
        this.totalMB = totalMB;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
