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

package net.insightas.sandbox.sensors.constants;

/**
 * Default Sensor Attribute Constants.
 * <p>
 * The constants defined here are Strings. They denote various sensor attributes supported by InsightAS by default. You may extend the
 * attributes by using your own constant class in addition to this. Since sensor attribute names are Strings, you can use any unique string,
 * whether it is defined in this class or not. For your own constants, you may need to modify the dashboard configuration files to show it
 * in the section you want in the UI.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public final class SensorAttributeConstants {
    
    /**
     * Server Specific Constants (Hardware / Network).
     * 
     * @author Yohan Liyanage (yohan at computer dot org)
     * 
     */
    public static final class ServerConstants {
        
        /**
         * Host Name.
         */
        public static final String HOST_NAME = "HOST_NAME";
        
        /**
         * Host Address.
         */
        public static final String HOST_ADDRESS = "HOST_ADDRESS";
        
        /**
         * CPU Count.
         */
        public static final String CPU_COUNT = "CPU_COUNT";
        
        /**
         * Private Constructor. No instantiation.
         */
        private ServerConstants() {
            super();
        }
        
    }
    
    /**
     * Java Specific Constants.
     * 
     * @author Yohan Liyanage (yohan at computer dot org)
     * 
     */
    public static final class JavaConstants {
        
        /**
         * JVM Name.
         */
        public static final String JVM_NAME = "JVM_NAME";
        
        /**
         * Vendor of Java.
         */
        public static final String JAVA_VENDOR = "JAVA_VENDOR";
        
        /**
         * Version of Java.
         */
        public static final String JAVA_VERSION = "JAVA_VERSION";
        
        /**
         * JVM Maximum Memory Allowed (XMX).
         */
        public static final String JVM_MAX_MEMORY = "JVM_MAX_MEMORY";
        
        /**
         * JVM Used Memory.
         */
        public static final String JVM_USED_MEMORY = "JVM_USED_MEMORY";
        
        /**
         * Code Cache (Non Heap) Current Maximum.
         */
        public static final String CODE_CACHE_CUR_MAX = "CODE_CACHE_CUR_MAX";
        
        /**
         * Code Cache (Non Heap) Current Committed.
         */
        public static final String CODE_CACHE_CUR_COMMITTED = "CODE_CACHE_CUR_COMMITTED";
        
        /**
         * Code Cache (Non Heap) Current Used.
         */
        public static final String CODE_CACHE_CUR_USED = "CODE_CACHE_CUR_USED";
        
        /**
         * Code Cache (Non Heap) Peak Maximum.
         */
        public static final String CODE_CACHE_PEAK_MAX = "CODE_CACHE_PEAK_MAX";
        
        /**
         * Code Cache (Non Heap) Peak Committed.
         */
        public static final String CODE_CACHE_PEAK_COMMITTED = "CODE_CACHE_PEAK_COMMITTED";
        
        /**
         * Code Cache (Non Heap) Peak Used.
         */
        public static final String CODE_CACHE_PEAK_USED = "CODE_CACHE_PEAK_USED";
        
        /**
         * Eden Space (Heap) Current Maximum.
         */
        public static final String EDEN_CUR_MAX = "EDEN_CUR_MAX";
        
        /**
         * Eden Space (Heap) Current Committed.
         */
        public static final String EDEN_CUR_COMMITTED = "EDEN_CUR_COMMITTED";
        
        /**
         * Eden Space (Heap) Current Used.
         */
        public static final String EDEN_CUR_USED = "EDEN_CUR_USED";
        
        /**
         * Eden Space (Heap) Peak Maximum.
         */
        public static final String EDEN_PEAK_MAX = "EDEN_PEAK_MAX";
        
        /**
         * Eden Space (Heap) Peak Committed.
         */
        public static final String EDEN_PEAK_COMMITTED = "EDEN_PEAK_COMMITTED";
        
        /**
         * Eden Space (Heap) Peak Used.
         */
        public static final String EDEN_PEAK_USED = "EDEN_PEAK_USED";
        
        /**
         * Survivor Space (Heap) Current Maximum.
         */
        public static final String SURVIVOR_CUR_MAX = "SURVIVOR_CUR_MAX";
        
        /**
         * Survivor Space (Heap) Current Committed.
         */
        public static final String SURVIVOR_CUR_COMMITTED = "SURVIVOR_CUR_COMMITTED";
        
        /**
         * Survivor Space (Heap) Current Used.
         */
        public static final String SURVIVOR_CUR_USED = "SURVIVOR_CUR_USED";
        
        /**
         * Survivor Space (Heap) Peak Maximum.
         */
        public static final String SURVIVOR_PEAK_MAX = "SURVIVOR_PEAK_MAX";
        
        /**
         * Survivor Space (Heap) Peak Committed.
         */
        public static final String SURVIVOR_PEAK_COMMITTED = "SURVIVOR_PEAK_COMMITTED";
        
        /**
         * Survivor Space (Heap) Peak Used.
         */
        public static final String SURVIVOR_PEAK_USED = "SURVIVOR_PEAK_USED";
        
        /**
         * Old Generation (Heap) Current Maximum.
         */
        public static final String OLD_GEN_CUR_MAX = "OLD_GEN_CUR_MAX";
        
        /**
         * Old Generation (Heap) Current Committed.
         */
        public static final String OLD_GEN_CUR_COMMITTED = "OLD_GEN_CUR_COMMITTED";
        
        /**
         * Old Generation (Heap) Current Used.
         */
        public static final String OLD_GEN_CUR_USED = "OLD_GEN_CUR_USED";
        
        /**
         * Old Generation (Heap) Peak Maximum.
         */
        public static final String OLD_GEN_PEAK_MAX = "OLD_GEN_PEAK_MAX";
        
        /**
         * Old Generation (Heap) Peak Committed.
         */
        public static final String OLD_GEN_PEAK_COMMITTED = "OLD_GEN_PEAK_COMMITTED";
        
        /**
         * Old Generation (Heap) Peak Used.
         */
        public static final String OLD_GEN_PEAK_USED = "OLD_GEN_PEAK_USED";
        
        /**
         * Permanent Generation (Heap) Current Maximum.
         */
        public static final String PERM_GEN_CUR_MAX = "PERM_GEN_CUR_MAX";
        
        /**
         * Permanent Generation (Heap) Current Committed.
         */
        public static final String PERM_GEN_CUR_COMMITTED = "PERM_GEN_CUR_COMMITTED";
        
        /**
         * Permanent Generation (Heap) Current Used.
         */
        public static final String PERM_GEN_CUR_USED = "PERM_GEN_CUR_USED";
        
        /**
         * Permanent Generation (Heap) Peak Maximum.
         */
        public static final String PERM_GEN_PEAK_MAX = "PERM_GEN_PEAK_MAX";
        
        /**
         * Permanent Generation (Heap) Peak Committed.
         */
        public static final String PERM_GEN_PEAK_COMMITTED = "PERM_GEN_PEAK_COMMITTED";
        
        /**
         * Permanent Generation (Heap) Peak Used.
         */
        public static final String PERM_GEN_PEAK_USED = "PERM_GEN_PEAK_USED";
        
        /**
         * Private Constructor. No instantiation.
         */
        private JavaConstants() {
            super();
        }
        
    }
    
    /**
     * Operating System Constants.
     * 
     * @author Yohan Liyanage (yohan at computer dot org)
     * 
     */
    public static final class OSConstants {
        
        /**
         * Operating System Name.
         */
        public static final String OS_NAME = "OS_NAME";
        
        /**
         * Operating System Version.
         */
        public static final String OS_VERSION = "OS_VERSION";
        
        /**
         * Operating System Architecture.
         */
        public static final String OS_ARCHITECTURE = "OS_ARCHITECTURE";
        
        /**
         * Private Constructor. No instantiation.
         */
        private OSConstants() {
            super();
        }
    }
    
    public static final class CPUConstants {
        /**
         * Current CPU Utilization (Percentage).
         */
        public static final String CURRENT_CPU_UTILIZATION = "CURRENT_CPU_UTILIZATION";
        
    }
    
    public static final class DiskConstants {

        /**
         * Current Disk Utilization (List of DiskSpaceInfo objects).
         */
        public static final String DISK_UTLIZATION_DATA = "DISK_UTILIZATION_DATA";
        
    }
    
    public static final class MemoryConstants {
        /**
         * Current Memory Utilization (Percentage).
         */
        public static final String MEMORY_UTILIZATION = "MEMORY_UTILIZATION";
    }
    
    /**
     * Private Constructor. No instantiation.
     */
    private SensorAttributeConstants() {
        super();
    }
}
