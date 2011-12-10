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

package net.insightas.sandbox.sensors.os.sigar;

import static org.junit.Assert.*;

import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorFailureException;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class SigarOSSensorImplTest {
    
    private SigarOSSensorImpl impl;
    
    @Before
    public void setup() {
        impl = new SigarOSSensorImpl();
    }
    
    @Test
    public void testCollect() throws SensorFailureException {
        long time = System.currentTimeMillis();
        SensorData sd = impl.collect(null);
        System.out.println("R1 Time " + (time - System.currentTimeMillis()));
        
        time = System.currentTimeMillis();
        sd = impl.collect(null);
        System.out.println("R2 Time " + (time - System.currentTimeMillis()));
        

        time = System.currentTimeMillis();
        sd = impl.collect(null);
        System.out.println("R3 Time " + (time - System.currentTimeMillis()));

        time = System.currentTimeMillis();
        sd = impl.collect(null);
        System.out.println("R4 Time " + (time - System.currentTimeMillis()));

        assertNotNull(sd);
    }
    
}
