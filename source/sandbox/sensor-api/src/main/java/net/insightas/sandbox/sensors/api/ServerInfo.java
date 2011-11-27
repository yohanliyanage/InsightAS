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
 * Server Information.
 * <p>
 * This class contains server information obtained from Sensors.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 * @deprecated Replaced with a Map.
 */
@Deprecated
public class ServerInfo implements Serializable {
    
    private static final long serialVersionUID = 5524787852198805940L;
    
    private String hostName;
    private String hostAddress;
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private String cpuCount;
    private String javaVersion;
    private String javaVendor;
    private String jvmName;
    
    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }
    
    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    /**
     * @return the hostAddress
     */
    public String getHostAddress() {
        return hostAddress;
    }
    
    /**
     * @param hostAddress the hostAddress to set
     */
    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
    
    /**
     * @return the osName
     */
    public String getOsName() {
        return osName;
    }
    
    /**
     * @param osName the osName to set
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }
    
    /**
     * @return the osVersion
     */
    public String getOsVersion() {
        return osVersion;
    }
    
    /**
     * @param osVersion the osVersion to set
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
    
    /**
     * @return the osArchitecture
     */
    public String getOsArchitecture() {
        return osArchitecture;
    }
    
    /**
     * @param osArchitecture the osArchitecture to set
     */
    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
    }
    
    /**
     * @return the cpuCount
     */
    public String getCpuCount() {
        return cpuCount;
    }
    
    /**
     * @param cpuCount the cpuCount to set
     */
    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }
    
    /**
     * @return the javaVersion
     */
    public String getJavaVersion() {
        return javaVersion;
    }
    
    /**
     * @param javaVersion the javaVersion to set
     */
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }
    
    /**
     * @return the javaVendor
     */
    public String getJavaVendor() {
        return javaVendor;
    }
    
    /**
     * @param javaVendor the javaVendor to set
     */
    public void setJavaVendor(String javaVendor) {
        this.javaVendor = javaVendor;
    }
    
    /**
     * @return the jvmName
     */
    public String getJvmName() {
        return jvmName;
    }
    
    /**
     * @param jvmName the jvmName to set
     */
    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ServerInfo [hostName=" + hostName + ", hostAddress=" + hostAddress + ", osName=" + osName + ", osVersion=" + osVersion
                + ", osArchitecture=" + osArchitecture + ", cpuCount=" + cpuCount + ", javaVersion=" + javaVersion + ", javaVendor="
                + javaVendor + ", jvmName=" + jvmName + "]";
    }

    
}
