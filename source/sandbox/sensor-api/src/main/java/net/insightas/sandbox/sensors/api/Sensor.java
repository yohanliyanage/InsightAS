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

import java.util.Properties;

import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorFailureException;


/**
 * Sensor SPI.
 * <p>
 * This Service Provider Interface mandates the specification for all sensors.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public interface Sensor {
    
    /**
     * Collects Sensor Data and returns it.
     * 
     * @return sensor data collected
     * @throws SensorFailureException if sensor fails to collect data
     */
    SensorData collect(Properties settings) throws SensorFailureException;
}
