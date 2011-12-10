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
public class CpuInfo {
    
    private String vendor;
    private String model;
    private int clockSpeedMHz;
    private int cores;
    private int cacheSizeKB;
    
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
    public int getCacheSizeKB() {
        return cacheSizeKB;
    }
    
    /**
     * @param cacheSizeKB the cacheSizeKB to set
     */
    public void setCacheSizeKB(int cacheSizeKB) {
        this.cacheSizeKB = cacheSizeKB;
    }
    
}
