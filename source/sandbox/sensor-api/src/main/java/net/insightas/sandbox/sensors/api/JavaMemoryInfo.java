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
 * Java Memory (Heap / Non-Heap) Information.
 * <p>
 * Contains Information about Server's JVM Memory. This is returned from JVM Memory Sensors.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class JavaMemoryInfo implements Serializable {
    
    private static final long serialVersionUID = 1965067145015851335L;
    
    private Long jvmMaxMemory;
    private Long jvmTotalUsedMemory;
    
    private Long codeCacheMax;
    private Long codeCacheCommitted;
    private Long codeCacheUsed;
    
    private Long edenMax;
    private Long edenCommitted;
    private Long edenUsed;
    
    private Long survivorMax;
    private Long survivorCommitted;
    private Long survivorUsed;
    
    private Long oldGenMax;
    private Long oldGenCommitted;
    private Long oldGenUsed;
    
    private Long permGenMax;
    private Long permGenCommitted;
    private Long permGenUsed;
    
    /**
     * @return the jvmMaxMemory
     */
    public Long getJvmMaxMemory() {
        return jvmMaxMemory;
    }
    
    /**
     * @param jvmMaxMemory the jvmMaxMemory to set
     */
    public void setJvmMaxMemory(Long jvmMaxMemory) {
        this.jvmMaxMemory = jvmMaxMemory;
    }
    
    /**
     * @return the jvmTotalUsedMemory
     */
    public Long getJvmTotalUsedMemory() {
        return jvmTotalUsedMemory;
    }
    
    /**
     * @param jvmTotalUsedMemory the jvmTotalUsedMemory to set
     */
    public void setJvmTotalUsedMemory(Long jvmTotalUsedMemory) {
        this.jvmTotalUsedMemory = jvmTotalUsedMemory;
    }
    
    /**
     * @return the codeCacheMax
     */
    public Long getCodeCacheMax() {
        return codeCacheMax;
    }
    
    /**
     * @param codeCacheMax the codeCacheMax to set
     */
    public void setCodeCacheMax(Long codeCacheMax) {
        this.codeCacheMax = codeCacheMax;
    }
    
    /**
     * @return the codeCacheCommitted
     */
    public Long getCodeCacheCommitted() {
        return codeCacheCommitted;
    }
    
    /**
     * @param codeCacheCommitted the codeCacheCommitted to set
     */
    public void setCodeCacheCommitted(Long codeCacheCommitted) {
        this.codeCacheCommitted = codeCacheCommitted;
    }
    
    /**
     * @return the codeCacheUsed
     */
    public Long getCodeCacheUsed() {
        return codeCacheUsed;
    }
    
    /**
     * @param codeCacheUsed the codeCacheUsed to set
     */
    public void setCodeCacheUsed(Long codeCacheUsed) {
        this.codeCacheUsed = codeCacheUsed;
    }
    
    /**
     * @return the edenMax
     */
    public Long getEdenMax() {
        return edenMax;
    }
    
    /**
     * @param edenMax the edenMax to set
     */
    public void setEdenMax(Long edenMax) {
        this.edenMax = edenMax;
    }
    
    /**
     * @return the edenCommitted
     */
    public Long getEdenCommitted() {
        return edenCommitted;
    }
    
    /**
     * @param edenCommitted the edenCommitted to set
     */
    public void setEdenCommitted(Long edenCommitted) {
        this.edenCommitted = edenCommitted;
    }
    
    /**
     * @return the edenUsed
     */
    public Long getEdenUsed() {
        return edenUsed;
    }
    
    /**
     * @param edenUsed the edenUsed to set
     */
    public void setEdenUsed(Long edenUsed) {
        this.edenUsed = edenUsed;
    }
    
    /**
     * @return the survivorMax
     */
    public Long getSurvivorMax() {
        return survivorMax;
    }
    
    /**
     * @param survivorMax the survivorMax to set
     */
    public void setSurvivorMax(Long survivorMax) {
        this.survivorMax = survivorMax;
    }
    
    /**
     * @return the survivorCommitted
     */
    public Long getSurvivorCommitted() {
        return survivorCommitted;
    }
    
    /**
     * @param survivorCommitted the survivorCommitted to set
     */
    public void setSurvivorCommitted(Long survivorCommitted) {
        this.survivorCommitted = survivorCommitted;
    }
    
    /**
     * @return the survivorUsed
     */
    public Long getSurvivorUsed() {
        return survivorUsed;
    }
    
    /**
     * @param survivorUsed the survivorUsed to set
     */
    public void setSurvivorUsed(Long survivorUsed) {
        this.survivorUsed = survivorUsed;
    }
    
    /**
     * @return the oldGenMax
     */
    public Long getOldGenMax() {
        return oldGenMax;
    }
    
    /**
     * @param oldGenMax the oldGenMax to set
     */
    public void setOldGenMax(Long oldGenMax) {
        this.oldGenMax = oldGenMax;
    }
    
    /**
     * @return the oldGenCommitted
     */
    public Long getOldGenCommitted() {
        return oldGenCommitted;
    }
    
    /**
     * @param oldGenCommitted the oldGenCommitted to set
     */
    public void setOldGenCommitted(Long oldGenCommitted) {
        this.oldGenCommitted = oldGenCommitted;
    }
    
    /**
     * @return the oldGenUsed
     */
    public Long getOldGenUsed() {
        return oldGenUsed;
    }
    
    /**
     * @param oldGenUsed the oldGenUsed to set
     */
    public void setOldGenUsed(Long oldGenUsed) {
        this.oldGenUsed = oldGenUsed;
    }
    
    /**
     * @return the permGenMax
     */
    public Long getPermGenMax() {
        return permGenMax;
    }
    
    /**
     * @param permGenMax the permGenMax to set
     */
    public void setPermGenMax(Long permGenMax) {
        this.permGenMax = permGenMax;
    }
    
    /**
     * @return the permGenCommitted
     */
    public Long getPermGenCommitted() {
        return permGenCommitted;
    }
    
    /**
     * @param permGenCommitted the permGenCommitted to set
     */
    public void setPermGenCommitted(Long permGenCommitted) {
        this.permGenCommitted = permGenCommitted;
    }
    
    /**
     * @return the permGenUsed
     */
    public Long getPermGenUsed() {
        return permGenUsed;
    }
    
    /**
     * @param permGenUsed the permGenUsed to set
     */
    public void setPermGenUsed(Long permGenUsed) {
        this.permGenUsed = permGenUsed;
    }
    
}
