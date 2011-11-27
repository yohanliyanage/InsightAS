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

package net.insightas.sandbox.sensors.jboss423.jmx;

/**
 * @author Yohan Liyanage (yohan at computer dot org)
 * @deprecated No longer used.
 */
@Deprecated
public class HeapStatDTO {
    
    private Long peakUsed;
    private Long peakCommitted;
    private Long peakMax;
    
    private Long currentUsed;
    private Long currentCommitted;
    private Long currentMax;
    
    /**
     * @return the peakUsed
     */
    public Long getPeakUsed() {
        return peakUsed;
    }
    
    /**
     * @param peakUsed the peakUsed to set
     */
    public void setPeakUsed(Long peakUsed) {
        this.peakUsed = peakUsed;
    }
    
    /**
     * @return the peakCommitted
     */
    public Long getPeakCommitted() {
        return peakCommitted;
    }
    
    /**
     * @param peakCommitted the peakCommitted to set
     */
    public void setPeakCommitted(Long peakCommitted) {
        this.peakCommitted = peakCommitted;
    }
    
    /**
     * @return the peakMax
     */
    public Long getPeakMax() {
        return peakMax;
    }
    
    /**
     * @param peakMax the peakMax to set
     */
    public void setPeakMax(Long peakMax) {
        this.peakMax = peakMax;
    }
    
    /**
     * @return the currentUsed
     */
    public Long getCurrentUsed() {
        return currentUsed;
    }
    
    /**
     * @param currentUsed the currentUsed to set
     */
    public void setCurrentUsed(Long currentUsed) {
        this.currentUsed = currentUsed;
    }
    
    /**
     * @return the currentCommitted
     */
    public Long getCurrentCommitted() {
        return currentCommitted;
    }
    
    /**
     * @param currentCommitted the currentCommitted to set
     */
    public void setCurrentCommitted(Long currentCommitted) {
        this.currentCommitted = currentCommitted;
    }
    
    /**
     * @return the currentMax
     */
    public Long getCurrentMax() {
        return currentMax;
    }
    
    /**
     * @param currentMax the currentMax to set
     */
    public void setCurrentMax(Long currentMax) {
        this.currentMax = currentMax;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HeapStatDTO [peakUsed=" + peakUsed + ", peakCommitted=" + peakCommitted + ", peakMax=" + peakMax + ", currentUsed="
                + currentUsed + ", currentCommitted=" + currentCommitted + ", currentMax=" + currentMax + "]";
    }
    
    
}
