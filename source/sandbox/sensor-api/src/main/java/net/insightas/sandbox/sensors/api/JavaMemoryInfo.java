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
    
    private Long codeCacheCurrentMax;
    private Long codeCacheCurrentCommitted;
    private Long codeCacheCurrentUsed;
    
    private Long codeCachePeakMax;
    private Long codeCachePeakCommitted;
    private Long codeCachePeakUsed;
    
    private Long edenCurrentMax;
    private Long edenCurrentCommitted;
    private Long edenCurrentUsed;
    
    private Long edenPeakMax;
    private Long edenPeakCommitted;
    private Long edenPeakUsed;
    
    private Long survivorCurrentMax;
    private Long survivorCurrentCommitted;
    private Long survivorCurrentUsed;
    
    private Long survivorPeakMax;
    private Long survivorPeakCommitted;
    private Long survivorPeakUsed;
    
    private Long oldGenCurrentMax;
    private Long oldGenCurrentCommitted;
    private Long oldGenCurrentUsed;
    
    private Long oldGenPeakMax;
    private Long oldGenPeakCommitted;
    private Long oldGenPeakUsed;
    
    private Long permGenCurrentMax;
    private Long permGenCurrentCommitted;
    private Long permGenCurrentUsed;
    
    private Long permGenPeakMax;
    private Long permGenPeakCommitted;
    private Long permGenPeakUsed;
    
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
     * @return the codeCacheCurrentMax
     */
    public Long getCodeCacheCurrentMax() {
        return codeCacheCurrentMax;
    }
    
    /**
     * @param codeCacheCurrentMax the codeCacheCurrentMax to set
     */
    public void setCodeCacheCurrentMax(Long codeCacheCurrentMax) {
        this.codeCacheCurrentMax = codeCacheCurrentMax;
    }
    
    /**
     * @return the codeCacheCurrentCommitted
     */
    public Long getCodeCacheCurrentCommitted() {
        return codeCacheCurrentCommitted;
    }
    
    /**
     * @param codeCacheCurrentCommitted the codeCacheCurrentCommitted to set
     */
    public void setCodeCacheCurrentCommitted(Long codeCacheCurrentCommitted) {
        this.codeCacheCurrentCommitted = codeCacheCurrentCommitted;
    }
    
    /**
     * @return the codeCacheCurrentUsed
     */
    public Long getCodeCacheCurrentUsed() {
        return codeCacheCurrentUsed;
    }
    
    /**
     * @param codeCacheCurrentUsed the codeCacheCurrentUsed to set
     */
    public void setCodeCacheCurrentUsed(Long codeCacheCurrentUsed) {
        this.codeCacheCurrentUsed = codeCacheCurrentUsed;
    }
    
    /**
     * @return the codeCachePeakMax
     */
    public Long getCodeCachePeakMax() {
        return codeCachePeakMax;
    }
    
    /**
     * @param codeCachePeakMax the codeCachePeakMax to set
     */
    public void setCodeCachePeakMax(Long codeCachePeakMax) {
        this.codeCachePeakMax = codeCachePeakMax;
    }
    
    /**
     * @return the codeCachePeakCommitted
     */
    public Long getCodeCachePeakCommitted() {
        return codeCachePeakCommitted;
    }
    
    /**
     * @param codeCachePeakCommitted the codeCachePeakCommitted to set
     */
    public void setCodeCachePeakCommitted(Long codeCachePeakCommitted) {
        this.codeCachePeakCommitted = codeCachePeakCommitted;
    }
    
    /**
     * @return the codeCachePeakUsed
     */
    public Long getCodeCachePeakUsed() {
        return codeCachePeakUsed;
    }
    
    /**
     * @param codeCachePeakUsed the codeCachePeakUsed to set
     */
    public void setCodeCachePeakUsed(Long codeCachePeakUsed) {
        this.codeCachePeakUsed = codeCachePeakUsed;
    }
    
    /**
     * @return the edenCurrentMax
     */
    public Long getEdenCurrentMax() {
        return edenCurrentMax;
    }
    
    /**
     * @param edenCurrentMax the edenCurrentMax to set
     */
    public void setEdenCurrentMax(Long edenCurrentMax) {
        this.edenCurrentMax = edenCurrentMax;
    }
    
    /**
     * @return the edenCurrentCommitted
     */
    public Long getEdenCurrentCommitted() {
        return edenCurrentCommitted;
    }
    
    /**
     * @param edenCurrentCommitted the edenCurrentCommitted to set
     */
    public void setEdenCurrentCommitted(Long edenCurrentCommitted) {
        this.edenCurrentCommitted = edenCurrentCommitted;
    }
    
    /**
     * @return the edenCurrentUsed
     */
    public Long getEdenCurrentUsed() {
        return edenCurrentUsed;
    }
    
    /**
     * @param edenCurrentUsed the edenCurrentUsed to set
     */
    public void setEdenCurrentUsed(Long edenCurrentUsed) {
        this.edenCurrentUsed = edenCurrentUsed;
    }
    
    /**
     * @return the edenPeakMax
     */
    public Long getEdenPeakMax() {
        return edenPeakMax;
    }
    
    /**
     * @param edenPeakMax the edenPeakMax to set
     */
    public void setEdenPeakMax(Long edenPeakMax) {
        this.edenPeakMax = edenPeakMax;
    }
    
    /**
     * @return the edenPeakCommitted
     */
    public Long getEdenPeakCommitted() {
        return edenPeakCommitted;
    }
    
    /**
     * @param edenPeakCommitted the edenPeakCommitted to set
     */
    public void setEdenPeakCommitted(Long edenPeakCommitted) {
        this.edenPeakCommitted = edenPeakCommitted;
    }
    
    /**
     * @return the edenPeakUsed
     */
    public Long getEdenPeakUsed() {
        return edenPeakUsed;
    }
    
    /**
     * @param edenPeakUsed the edenPeakUsed to set
     */
    public void setEdenPeakUsed(Long edenPeakUsed) {
        this.edenPeakUsed = edenPeakUsed;
    }
    
    /**
     * @return the survivorCurrentMax
     */
    public Long getSurvivorCurrentMax() {
        return survivorCurrentMax;
    }
    
    /**
     * @param survivorCurrentMax the survivorCurrentMax to set
     */
    public void setSurvivorCurrentMax(Long survivorCurrentMax) {
        this.survivorCurrentMax = survivorCurrentMax;
    }
    
    /**
     * @return the survivorCurrentCommitted
     */
    public Long getSurvivorCurrentCommitted() {
        return survivorCurrentCommitted;
    }
    
    /**
     * @param survivorCurrentCommitted the survivorCurrentCommitted to set
     */
    public void setSurvivorCurrentCommitted(Long survivorCurrentCommitted) {
        this.survivorCurrentCommitted = survivorCurrentCommitted;
    }
    
    /**
     * @return the survivorCurrentUsed
     */
    public Long getSurvivorCurrentUsed() {
        return survivorCurrentUsed;
    }
    
    /**
     * @param survivorCurrentUsed the survivorCurrentUsed to set
     */
    public void setSurvivorCurrentUsed(Long survivorCurrentUsed) {
        this.survivorCurrentUsed = survivorCurrentUsed;
    }
    
    /**
     * @return the survivorPeakMax
     */
    public Long getSurvivorPeakMax() {
        return survivorPeakMax;
    }
    
    /**
     * @param survivorPeakMax the survivorPeakMax to set
     */
    public void setSurvivorPeakMax(Long survivorPeakMax) {
        this.survivorPeakMax = survivorPeakMax;
    }
    
    /**
     * @return the survivorPeakCommitted
     */
    public Long getSurvivorPeakCommitted() {
        return survivorPeakCommitted;
    }
    
    /**
     * @param survivorPeakCommitted the survivorPeakCommitted to set
     */
    public void setSurvivorPeakCommitted(Long survivorPeakCommitted) {
        this.survivorPeakCommitted = survivorPeakCommitted;
    }
    
    /**
     * @return the survivorPeakUsed
     */
    public Long getSurvivorPeakUsed() {
        return survivorPeakUsed;
    }
    
    /**
     * @param survivorPeakUsed the survivorPeakUsed to set
     */
    public void setSurvivorPeakUsed(Long survivorPeakUsed) {
        this.survivorPeakUsed = survivorPeakUsed;
    }
    
    /**
     * @return the oldGenCurrentMax
     */
    public Long getOldGenCurrentMax() {
        return oldGenCurrentMax;
    }
    
    /**
     * @param oldGenCurrentMax the oldGenCurrentMax to set
     */
    public void setOldGenCurrentMax(Long oldGenCurrentMax) {
        this.oldGenCurrentMax = oldGenCurrentMax;
    }
    
    /**
     * @return the oldGenCurrentCommitted
     */
    public Long getOldGenCurrentCommitted() {
        return oldGenCurrentCommitted;
    }
    
    /**
     * @param oldGenCurrentCommitted the oldGenCurrentCommitted to set
     */
    public void setOldGenCurrentCommitted(Long oldGenCurrentCommitted) {
        this.oldGenCurrentCommitted = oldGenCurrentCommitted;
    }
    
    /**
     * @return the oldGenCurrentUsed
     */
    public Long getOldGenCurrentUsed() {
        return oldGenCurrentUsed;
    }
    
    /**
     * @param oldGenCurrentUsed the oldGenCurrentUsed to set
     */
    public void setOldGenCurrentUsed(Long oldGenCurrentUsed) {
        this.oldGenCurrentUsed = oldGenCurrentUsed;
    }
    
    /**
     * @return the oldGenPeakMax
     */
    public Long getOldGenPeakMax() {
        return oldGenPeakMax;
    }
    
    /**
     * @param oldGenPeakMax the oldGenPeakMax to set
     */
    public void setOldGenPeakMax(Long oldGenPeakMax) {
        this.oldGenPeakMax = oldGenPeakMax;
    }
    
    /**
     * @return the oldGenPeakCommitted
     */
    public Long getOldGenPeakCommitted() {
        return oldGenPeakCommitted;
    }
    
    /**
     * @param oldGenPeakCommitted the oldGenPeakCommitted to set
     */
    public void setOldGenPeakCommitted(Long oldGenPeakCommitted) {
        this.oldGenPeakCommitted = oldGenPeakCommitted;
    }
    
    /**
     * @return the oldGenPeakUsed
     */
    public Long getOldGenPeakUsed() {
        return oldGenPeakUsed;
    }
    
    /**
     * @param oldGenPeakUsed the oldGenPeakUsed to set
     */
    public void setOldGenPeakUsed(Long oldGenPeakUsed) {
        this.oldGenPeakUsed = oldGenPeakUsed;
    }
    
    /**
     * @return the permGenCurrentMax
     */
    public Long getPermGenCurrentMax() {
        return permGenCurrentMax;
    }
    
    /**
     * @param permGenCurrentMax the permGenCurrentMax to set
     */
    public void setPermGenCurrentMax(Long permGenCurrentMax) {
        this.permGenCurrentMax = permGenCurrentMax;
    }
    
    /**
     * @return the permGenCurrentCommitted
     */
    public Long getPermGenCurrentCommitted() {
        return permGenCurrentCommitted;
    }
    
    /**
     * @param permGenCurrentCommitted the permGenCurrentCommitted to set
     */
    public void setPermGenCurrentCommitted(Long permGenCurrentCommitted) {
        this.permGenCurrentCommitted = permGenCurrentCommitted;
    }
    
    /**
     * @return the permGenCurrentUsed
     */
    public Long getPermGenCurrentUsed() {
        return permGenCurrentUsed;
    }
    
    /**
     * @param permGenCurrentUsed the permGenCurrentUsed to set
     */
    public void setPermGenCurrentUsed(Long permGenCurrentUsed) {
        this.permGenCurrentUsed = permGenCurrentUsed;
    }
    
    /**
     * @return the permGenPeakMax
     */
    public Long getPermGenPeakMax() {
        return permGenPeakMax;
    }
    
    /**
     * @param permGenPeakMax the permGenPeakMax to set
     */
    public void setPermGenPeakMax(Long permGenPeakMax) {
        this.permGenPeakMax = permGenPeakMax;
    }
    
    /**
     * @return the permGenPeakCommitted
     */
    public Long getPermGenPeakCommitted() {
        return permGenPeakCommitted;
    }
    
    /**
     * @param permGenPeakCommitted the permGenPeakCommitted to set
     */
    public void setPermGenPeakCommitted(Long permGenPeakCommitted) {
        this.permGenPeakCommitted = permGenPeakCommitted;
    }
    
    /**
     * @return the permGenPeakUsed
     */
    public Long getPermGenPeakUsed() {
        return permGenPeakUsed;
    }
    
    /**
     * @param permGenPeakUsed the permGenPeakUsed to set
     */
    public void setPermGenPeakUsed(Long permGenPeakUsed) {
        this.permGenPeakUsed = permGenPeakUsed;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "JavaMemoryInfo [jvmMaxMemory=" + jvmMaxMemory + ", jvmTotalUsedMemory=" + jvmTotalUsedMemory + ", codeCacheCurrentMax="
                + codeCacheCurrentMax + ", codeCacheCurrentCommitted=" + codeCacheCurrentCommitted + ", codeCacheCurrentUsed="
                + codeCacheCurrentUsed + ", codeCachePeakMax=" + codeCachePeakMax + ", codeCachePeakCommitted=" + codeCachePeakCommitted
                + ", codeCachePeakUsed=" + codeCachePeakUsed + ", edenCurrentMax=" + edenCurrentMax + ", edenCurrentCommitted="
                + edenCurrentCommitted + ", edenCurrentUsed=" + edenCurrentUsed + ", edenPeakMax=" + edenPeakMax + ", edenPeakCommitted="
                + edenPeakCommitted + ", edenPeakUsed=" + edenPeakUsed + ", survivorCurrentMax=" + survivorCurrentMax
                + ", survivorCurrentCommitted=" + survivorCurrentCommitted + ", survivorCurrentUsed=" + survivorCurrentUsed
                + ", survivorPeakMax=" + survivorPeakMax + ", survivorPeakCommitted=" + survivorPeakCommitted + ", survivorPeakUsed="
                + survivorPeakUsed + ", oldGenCurrentMax=" + oldGenCurrentMax + ", oldGenCurrentCommitted=" + oldGenCurrentCommitted
                + ", oldGenCurrentUsed=" + oldGenCurrentUsed + ", oldGenPeakMax=" + oldGenPeakMax + ", oldGenPeakCommitted="
                + oldGenPeakCommitted + ", oldGenPeakUsed=" + oldGenPeakUsed + ", permGenCurrentMax=" + permGenCurrentMax
                + ", permGenCurrentCommitted=" + permGenCurrentCommitted + ", permGenCurrentUsed=" + permGenCurrentUsed
                + ", permGenPeakMax=" + permGenPeakMax + ", permGenPeakCommitted=" + permGenPeakCommitted + ", permGenPeakUsed="
                + permGenPeakUsed + "]";
    }
    
    
}
