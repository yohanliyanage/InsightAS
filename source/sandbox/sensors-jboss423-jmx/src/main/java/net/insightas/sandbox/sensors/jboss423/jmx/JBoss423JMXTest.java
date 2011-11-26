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

import java.util.Properties;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.naming.CommunicationException;
import javax.naming.InitialContext;

import net.insightas.sandbox.sensors.api.JavaMemoryInfo;
import net.insightas.sandbox.sensors.api.ServerInfo;
import net.insightas.sandbox.sensors.exception.SensorConnectionFailureException;
import net.insightas.sandbox.sensors.jboss423.jmx.JBossJMXConstants.JNDIConstants;
import net.insightas.sandbox.sensors.util.JMXUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a test class for JBoss 423 JMX Sensor Testing.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class JBoss423JMXTest {
    
    /**
     * 
     */
    private static final String JBOSS_JMX_ADAPTER = "jmx/invoker/RMIAdaptor";
    
    private static final Log LOG = LogFactory.getLog(JBoss423JMXTest.class);
    
    private static final String JBOSS_JNDI_URL = "jnp://127.0.0.1:1099";
    
    public static void main(String[] args) throws Exception {
        
        LOG.info("Test");
        if (LOG.isDebugEnabled()) {
            LOG.debug("Preparing JNDI Connection...");
        }
        
        // Prepare for JNDI
        Properties props = new Properties();
        props.put(JNDIConstants.JAVA_NAMING_FACTORY_INITIAL, JNDIConstants.JBOSS_JAVA_NAMING_FACTORY_INITIAL_VALUE);
        props.put(JNDIConstants.JAVA_NAMING_PROVIDER_URL, JBOSS_JNDI_URL);
        props.put(JNDIConstants.JAVA_NAMING_FACTORY_URL_PKGS, JNDIConstants.JBOSS_JAVA_NAMING_FACTORY_URL_PKGS_VALUE);
        
        InitialContext ctx = new InitialContext(props);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Connecting to MBean Server on JBoss Server at " + JBOSS_JNDI_URL + "...");
        }
        
        // Connect to MBean Server
        MBeanServerConnection server;
        
        try {
            server = (MBeanServerConnection) ctx.lookup(JBOSS_JMX_ADAPTER);
        } catch (CommunicationException e) {
            LOG.warn("Unable to establish connection to MBean Server [" + JBOSS_JMX_ADAPTER + "]  on " + JBOSS_JNDI_URL, e);
            throw new SensorConnectionFailureException("Cannot connect to MBean Server [" + JBOSS_JMX_ADAPTER + "] on " + JBOSS_JNDI_URL, e);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Connection established to remote MBean server at " + JBOSS_JNDI_URL);
        }
        
        // ServerInfo MBean
        final ObjectName mbServerInfo = new ObjectName("jboss.system:type=ServerInfo");
        
        // Gather Server Information
        ServerInfo serverInfo = new ServerInfo();
        
        serverInfo.setHostAddress(JMXUtil.getStringAttribute(server, mbServerInfo, "HostAddress"));
        serverInfo.setHostName(JMXUtil.getStringAttribute(server, mbServerInfo, "HostName"));
        serverInfo.setJavaVendor(JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVendor"));
        serverInfo.setJavaVersion(JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVersion"));
        serverInfo.setJvmName(JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVMName"));
        serverInfo.setOsArchitecture(JMXUtil.getStringAttribute(server, mbServerInfo, "OSArch"));
        serverInfo.setOsName(JMXUtil.getStringAttribute(server, mbServerInfo, "OSName"));
        serverInfo.setOsVersion(JMXUtil.getStringAttribute(server, mbServerInfo, "OSVersion"));
        serverInfo.setCpuCount(JMXUtil.getStringAttribute(server, mbServerInfo, "AvailableProcessors"));
        
        System.out.println(serverInfo);
        
        // // Gather Heap Information
        String strHeapXML = (JMXUtil.invokeOperation(server, mbServerInfo, "listMemoryPools", new Object[] { Boolean.FALSE },
                new String[] { "boolean" })).toString();
        
        JavaMemoryInfo javaMemInfo = new JavaMemoryInfo();
        javaMemInfo.setJvmMaxMemory(JMXUtil.getLongAttribute(server, mbServerInfo, "MaxMemory"));
        javaMemInfo.setJvmTotalUsedMemory(JMXUtil.getLongAttribute(server, mbServerInfo, "TotalMemory"));
        
        extractHeapInfo(strHeapXML, javaMemInfo);
    }
    
    /**
     * Parses the Heap XML and extracts Heap Information.
     * 
     * @param strHeapXML
     * @param javaMemInfo
     */
    private static void extractHeapInfo(String strHeapXML, JavaMemoryInfo javaMemInfo) {
        System.out.println(strHeapXML);
    }
    
}
