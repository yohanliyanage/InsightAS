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

import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.*;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Properties;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.naming.CommunicationException;
import javax.naming.InitialContext;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.insightas.sandbox.sensors.constants.SensorAttributeConstants;
import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorConnectionFailureException;
import net.insightas.sandbox.sensors.exception.SensorFailureException;
import net.insightas.sandbox.sensors.jboss423.jmx.JBossJMXConstants.JNDIConstants;
import net.insightas.sandbox.sensors.util.JMXUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

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
        
        SensorData sensorData = new SensorData();
        
        sensorData.add(SensorAttributeConstants.ServerConstants.HOST_NAME, JMXUtil.getStringAttribute(server, mbServerInfo, "HostName"));
        sensorData.add(SensorAttributeConstants.ServerConstants.HOST_ADDRESS,
                JMXUtil.getStringAttribute(server, mbServerInfo, "HostAddress"));
        sensorData.add(SensorAttributeConstants.ServerConstants.CPU_COUNT,
                JMXUtil.getStringAttribute(server, mbServerInfo, "AvailableProcessors"));
         
        sensorData.add(SensorAttributeConstants.JavaConstants.JVM_NAME, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVMName"));
        sensorData.add(SensorAttributeConstants.JavaConstants.JAVA_VERSION, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVersion"));
        sensorData.add(SensorAttributeConstants.JavaConstants.JAVA_VENDOR, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVendor"));
        sensorData.add(SensorAttributeConstants.JavaConstants.JVM_MAX_MEMORY, JMXUtil.getStringAttribute(server, mbServerInfo, "MaxMemory"));
        sensorData.add(SensorAttributeConstants.JavaConstants.JVM_USED_MEMORY,
                JMXUtil.getStringAttribute(server, mbServerInfo, "TotalMemory"));
        
        sensorData.add(SensorAttributeConstants.OSConstants.OS_NAME, JMXUtil.getStringAttribute(server, mbServerInfo, "OSName"));
        sensorData.add(SensorAttributeConstants.OSConstants.OS_VERSION, JMXUtil.getStringAttribute(server, mbServerInfo, "OSVersion"));
        sensorData.add(SensorAttributeConstants.OSConstants.OS_ARCHITECTURE, JMXUtil.getStringAttribute(server, mbServerInfo, "OSArch"));
        
        // // Gather Heap Information
        String strHeapXML = (JMXUtil.invokeOperation(server, mbServerInfo, "listMemoryPools", new Object[] { Boolean.FALSE },
                new String[] { "boolean" })).toString();
        
        sensorData.addAllSensorData(extractHeapInfo(strHeapXML));

        LOG.info("Sensor Data Collected : " + sensorData);
    }
    
    /**
     * Parses the Heap XML and extracts Heap Information.
     * 
     * @param strHeapXML
     * @param javaMemInfo
     */
    private static SensorData extractHeapInfo(String strHeapXML) throws SensorFailureException {
        
        SensorData sensorData = new SensorData();
        
        // Replace <br> with semi-colon for easier string processing later.
        strHeapXML = strHeapXML.replaceAll("<br>", " ; ");
        
        // Parse DOM Document
        DOMResult result;
        
        try {
            // Parse my (HTML) URL into a well-formed document
            XMLReader reader = new Parser();
            reader.setFeature(Parser.namespacesFeature, false);
            reader.setFeature(Parser.namespacePrefixesFeature, false);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            result = new DOMResult();
            
            transformer.transform(new SAXSource(reader, new InputSource(new StringReader(strHeapXML))), result);
            
        } catch (SAXException e) {
            LOG.error("SAX Exception when parsing Sensor XML - " + e.getMessage());
            throw new SensorFailureException("SAX Exception when parsing Sensor XML", e);
        } catch (TransformerException e) {
            LOG.error("XML Transformation Exception when parsing Sensor XML - " + e.getMessage());
            throw new SensorFailureException("XML Transformation Exception when parsing Sensor XML", e);
        }
        
        try {
            
            // Generate XPath for each segment
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            String strCodeCache = (String) xpath.compile("/html/body/blockquote/blockquote[1]").evaluate(result.getNode(),
                    XPathConstants.STRING);
            String strEden = (String) xpath.compile("/html/body/blockquote/blockquote[2]").evaluate(result.getNode(), XPathConstants.STRING);
            String strSurvivor = (String) xpath.compile("/html/body/blockquote/blockquote[3]").evaluate(result.getNode(),
                    XPathConstants.STRING);
            String strOldGen = (String) xpath.compile("/html/body/blockquote/blockquote[4]").evaluate(result.getNode(),
                    XPathConstants.STRING);
            String strPermGen = (String) xpath.compile("/html/body/blockquote/blockquote[5]").evaluate(result.getNode(),
                    XPathConstants.STRING);
            
            // Extract Code Cache
            extractStatFromString(strCodeCache, sensorData, new String[] { CODE_CACHE_PEAK_USED, CODE_CACHE_PEAK_COMMITTED,
                    CODE_CACHE_PEAK_MAX, CODE_CACHE_CUR_USED, CODE_CACHE_CUR_COMMITTED, CODE_CACHE_CUR_MAX });
            
            // Extract Eden Space
            extractStatFromString(strEden, sensorData, new String[] { EDEN_PEAK_USED, EDEN_PEAK_COMMITTED, EDEN_PEAK_MAX, EDEN_CUR_USED,
                    EDEN_CUR_COMMITTED, EDEN_CUR_MAX });
            
            // Extract Survivor Space
            extractStatFromString(strSurvivor, sensorData, new String[] { SURVIVOR_PEAK_USED, SURVIVOR_PEAK_COMMITTED, SURVIVOR_PEAK_MAX,
                    SURVIVOR_CUR_USED, SURVIVOR_CUR_COMMITTED, SURVIVOR_CUR_MAX });
            
            // Extract Old Gen
            extractStatFromString(strOldGen, sensorData, new String[] { OLD_GEN_PEAK_USED, OLD_GEN_PEAK_COMMITTED, OLD_GEN_PEAK_MAX,
                    OLD_GEN_CUR_USED, OLD_GEN_CUR_COMMITTED, OLD_GEN_CUR_MAX });
            
            // Extract Perm Gen
            extractStatFromString(strPermGen, sensorData, new String[] { PERM_GEN_PEAK_USED, PERM_GEN_PEAK_COMMITTED, PERM_GEN_PEAK_MAX,
                    PERM_GEN_CUR_USED, PERM_GEN_CUR_COMMITTED, PERM_GEN_CUR_MAX });
            
        } catch (XPathExpressionException e) {
            LOG.error("XPathExpressionException when parsing Sensor XML - " + e.getMessage());
            throw new SensorFailureException("XPathExpressionException when parsing Sensor XML", e);
        }
        
        return sensorData;
        
    }
    
    /**
     * @param strRawResponse
     * @return
     */
    private static void extractStatFromString(String strRawResponse, SensorData sensorData, String[] attributes) {
        
        // Verify that all attributes are passed in.
        // Following should be passed in order : Peak Used, Peak Committed, Peak Max, Current Used, Current Committed, Current Max.
        
        if (attributes.length != 6) {
            LOG.warn("Extract Heap Stats Failed. All six attribute names are not provided. Attributes : " + Arrays.toString(attributes));
            throw new IllegalArgumentException(
                    "Invalid attributes argument (String[]). All six attributes for the given memory segment is expected.");
        }
        
        // Replace comma with colon for easier segmenting
        strRawResponse = strRawResponse.replaceAll(",", ":");
        
        // Split Raw Tokens (Peak & Current) by splitting using semi-colon
        String[] rawTokens = strRawResponse.split(";");
        
        // Get Peak Segments by splitting on colon
        String[] peakSegments = rawTokens[0].split(":");
        
        // Get Current Segments by splitting on colon
        String[] currentSgements = rawTokens[1].split(":");
        
        // Extract & Populate Sensor Data
        sensorData.add(attributes[0], peakSegments[4].trim()); // Peak Used
        sensorData.add(attributes[1], peakSegments[6].trim()); // Peak Committed
        sensorData.add(attributes[2], peakSegments[8].trim()); // Peak Max
        
        sensorData.add(attributes[3], currentSgements[4].trim()); // Current Used
        sensorData.add(attributes[4], currentSgements[6].trim()); // Current Committed
        sensorData.add(attributes[5], currentSgements[8].trim()); // Current Max
        
    }
    
}
