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


/**
 * Sigar API Constants.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public final class SigarConstants {
    
    /**
     * Sigar Path System Property Constant.
     */
    public static final String SIGAR_PATH = "org.hyperic.sigar.path";
    
    /**
     * Default Sigar Path.
     */
    public static final String SIGAR_PATH_DEFAULT = "./lib";
    
    /**
     * No instantiation.
     */
    private SigarConstants() {
        // No instantiation
    }
}
