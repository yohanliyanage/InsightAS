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
 * Network Interface Card Information DTO.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class NICInfo {
    
    private String name;
    private String ipAddress;
    private String macAddress;
    private String type;
    
    /**
     * Retuns the name of the Network Interface.
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the Network Interface.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the IP Address of the Network Interface.
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * Sets the IP Address of the Network Interface.
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    /**
     * Returns the MAC Address.
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }
    
    /**
     * Sets the MAC Address.
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    
    /**
     * Returns the type of the Network Interface (ex. Ethernet).
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the type of the Network Interface (ex. Ethernet).
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NICInfo [name=" + name + ", ipAddress=" + ipAddress + ", macAddress=" + macAddress + ", type=" + type + "]";
    }
    
    
    
}
