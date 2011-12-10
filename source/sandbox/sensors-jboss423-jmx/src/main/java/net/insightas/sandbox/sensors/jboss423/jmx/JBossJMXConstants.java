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

package net.insightas.sandbox.sensors.jboss423.jmx;


/**
 * JBoss JMX Constants.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public final class JBossJMXConstants {


    /**
     * JMX MBean Constants.
     * @author Yohan Liyanage (yohan at computer dot org)
     *
     */
    public static final class MBeanConstants {
        
        /**
         * JMX Adapter MBean Reference.
         */
        public static final String JBOSS_JMX_ADAPTER = "jmx/invoker/RMIAdaptor";
        
        /**
         * ObjectName for ServerInfo MBean.
         */
        public static final String JBOSS_SERVER_INFO_OBJ_NAME = "jboss.system:type=ServerInfo";
    }
    
    /**
     * JNDI Constants.
     * 
     * @author Yohan Liyanage (yohan at computer dot org)
     *
     */
    public static final class JNDIConstants {
        
        /**
         * JNDI Property Key for JBoss JNDI Factory. 
         */
        public static final String JAVA_NAMING_FACTORY_INITIAL = "java.naming.factory.initial";
        
        /**
         * JNDI Property Value for JBoss JNDI Factory.
         */
        public static final String JBOSS_JAVA_NAMING_FACTORY_INITIAL_VALUE = "org.jnp.interfaces.NamingContextFactory";
        
        /**
         *  JNDI Property Key for JBoss JNDI Factory URL Packages. 
         */
        public static final String JAVA_NAMING_FACTORY_URL_PKGS = "java.naming.factory.url.pkgs";

        
        /**
         * JNDI Property Value for JBoss JNDI Factory URL Packages.
         */
        public static final String JBOSS_JAVA_NAMING_FACTORY_URL_PKGS_VALUE = "org.jnp.interfaces";
        
        /**
         * JNDI Property Key for JBoss JNDI Provider URL.
         */
        public static final String JAVA_NAMING_PROVIDER_URL = "java.naming.provider.url";

        /**
         * Private Constructor - No External Instantiation. 
         */
        private JNDIConstants() {
            super();
        }
        
    }
    
    
    /**
     * Private Constructor - No External Instantiation.
     */
    private JBossJMXConstants() {
        super();
    }
    
    
}
