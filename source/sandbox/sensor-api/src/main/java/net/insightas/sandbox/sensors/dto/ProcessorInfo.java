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

/**
 * CPU Information DTO.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class ProcessorInfo {
    
    private String vendor;
    private String model;
    private int clockSpeedMHz;
    private int cores;
    private long cacheSizeKB;
    
    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }
    
    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }
    
    /**
     * @return the clockSpeedMHz
     */
    public int getClockSpeedMHz() {
        return clockSpeedMHz;
    }
    
    /**
     * @param clockSpeedMHz the clockSpeedMHz to set
     */
    public void setClockSpeedMHz(int clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }
    
    /**
     * @return the cores
     */
    public int getCores() {
        return cores;
    }
    
    /**
     * @param cores the cores to set
     */
    public void setCores(int cores) {
        this.cores = cores;
    }
    
    /**
     * @return the cacheSizeKB
     */
    public long getCacheSizeKB() {
        return cacheSizeKB;
    }
    
    /**
     * @param cacheSizeKB the cacheSizeKB to set
     */
    public void setCacheSizeKB(long cacheSizeKB) {
        this.cacheSizeKB = cacheSizeKB;
    }

    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((int)cacheSizeKB);
        result = prime * result + clockSpeedMHz;
        result = prime * result + cores;
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ProcessorInfo other = (ProcessorInfo) obj;
        if (cacheSizeKB != other.cacheSizeKB) return false;
        if (clockSpeedMHz != other.clockSpeedMHz) return false;
        if (cores != other.cores) return false;
        if (model == null) {
            if (other.model != null) return false;
        } else if (!model.equals(other.model)) return false;
        if (vendor == null) {
            if (other.vendor != null) return false;
        } else if (!vendor.equals(other.vendor)) return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CpuInfo [vendor=" + vendor + ", model=" + model + ", clockSpeedMHz=" + clockSpeedMHz + ", cores=" + cores
                + ", cacheSizeKB=" + cacheSizeKB + "]";
    }
    
    
}
