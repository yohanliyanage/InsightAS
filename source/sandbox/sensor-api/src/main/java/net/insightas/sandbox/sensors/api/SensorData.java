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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Sensor Data Wrapper.
 * <p>
 * This class is used to store a collection of sensor data.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class SensorData {
    
    private Map<String, Object> sensorDataMap;
    
    /**
     * Constructs a SensorData object which has an empty sensor data set.
     */
    public SensorData() {
        super();
        sensorDataMap = new HashMap<String, Object>();
    }
    
    /**
     * Copy Constructor. Copies a given sensor data map to create a new one. Note that this is a shallow copy of the internal data set.
     * 
     * @param original original SensorData object.
     */
    public SensorData(SensorData original) {
        // Create a new HashMap, but inside the hash-map, references are shallow copies.
        this.sensorDataMap = new HashMap<String, Object>(original.sensorDataMap);
    }
    
    /**
     * Adds a Sensor Data attribute.
     * @param attributeName Name of Sensor Attribute
     * @param value Value of Sensor Attribute
     */
    public void add(String attributeName, Object value) {
        
        // Do not add if the value is null
        if (value == null) {
            return;
        }
        
        sensorDataMap.put(attributeName, value);
    }
    
    /**
     * Returns the value for the given attribute.
     * 
     * @param attributeName Name of attribute
     * @return value of attribute if it exists. Otherwise this returns null
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String attributeName) {
        return (T) sensorDataMap.get(attributeName);
    }
    
    /**
     * Removes the given attribute from the dataset if it exists.
     * @param attributeName Name of attribute to remove.
     */
    public void remove(String attributeName) {
        this.sensorDataMap.remove(attributeName);
    }
    
    /**
     * Returns true if this sensor data collection contains a value for the given attribute name.
     * @param attribute
     * @return True if the data-set contains a value for the given attribute. Otherwise false.
     */
    public boolean hasAttribute(String attribute) {
        return this.sensorDataMap.containsKey(attribute);
    }
    
    /**
     * Returns the names of the attributes in this data set.
     * @return Set of attribute names in the data-set, as a Set of Strings.
     */
    public Set<String> getAttributes() {
        return this.sensorDataMap.keySet();
    }
    
    /**
     * Adds all sensor data from the given SensorData object into this SensorData object.
     * @param data target data set to be added to this SensorData instance.
     */
    public void addAllSensorData(SensorData data) {
        this.sensorDataMap.putAll(data.sensorDataMap);
    }
}
