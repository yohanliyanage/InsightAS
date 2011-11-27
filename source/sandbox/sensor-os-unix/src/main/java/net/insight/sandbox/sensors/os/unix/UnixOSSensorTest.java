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

package net.insight.sandbox.sensors.os.unix;

import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorFailureException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Unix Based Operating System Sensor Test.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class UnixOSSensorTest {

    
    private static final Log LOG = LogFactory.getLog(UnixOSSensorTest.class);
    
    public static void main(String[] args) throws SensorFailureException {
        
        UnixDiskSpaceSensor diskSensor = new UnixDiskSpaceSensor();
        SensorData diskData = diskSensor.collect();

        LOG.info("[UnixOSSensor] Disk Data Collected");
    }

}
