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

import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_CUR_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_CUR_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_CUR_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_PEAK_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_PEAK_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.CODE_CACHE_PEAK_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_CUR_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_CUR_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_CUR_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_PEAK_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_PEAK_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.EDEN_PEAK_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_CUR_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_CUR_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_CUR_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_PEAK_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_PEAK_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.OLD_GEN_PEAK_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_CUR_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_CUR_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_CUR_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_PEAK_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_PEAK_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.PERM_GEN_PEAK_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_CUR_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_CUR_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_CUR_USED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_PEAK_COMMITTED;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_PEAK_MAX;
import static net.insightas.sandbox.sensors.constants.SensorAttributeConstants.JavaConstants.SURVIVOR_PEAK_USED;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Properties;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.naming.CommunicationException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import net.insightas.sandbox.sensors.api.Sensor;
import net.insightas.sandbox.sensors.constants.SensorAttributeConstants;
import net.insightas.sandbox.sensors.constants.SensorSettingsConstants;
import net.insightas.sandbox.sensors.dto.SensorData;
import net.insightas.sandbox.sensors.exception.SensorConnectionFailureException;
import net.insightas.sandbox.sensors.exception.SensorFailureException;
import net.insightas.sandbox.sensors.jboss423.jmx.JBossJMXConstants.JNDIConstants;
import net.insightas.sandbox.sensors.jboss423.jmx.JBossJMXConstants.MBeanConstants;
import net.insightas.sandbox.sensors.util.JMXUtil;


/**
 * JBoss 4.2.3.GA Sensor Implementation.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class JBossSensorImpl implements Sensor {

    private static final Log LOG = LogFactory.getLog(JBossSensorImpl.class);
    
    /* (non-Javadoc)
     * @see net.insightas.sandbox.sensors.api.Sensor#collect()
     */
    @Override
    public SensorData collect(Properties settings) throws SensorFailureException {
        
        LOG.info("[JBossSensorImpl] Collecting Sensor Data via JBoss JMX Adapter : " + settings.get(SensorSettingsConstants.JNDI_URL));

        if (LOG.isDebugEnabled()) {
            LOG.debug("Preparing JNDI Connection...");
        }
        
        // Prepare for JNDI
        Properties props = new Properties();
        props.put(JNDIConstants.JAVA_NAMING_FACTORY_INITIAL, JNDIConstants.JBOSS_JAVA_NAMING_FACTORY_INITIAL_VALUE);
        props.put(JNDIConstants.JAVA_NAMING_PROVIDER_URL, settings.get(SensorSettingsConstants.JNDI_URL));
        props.put(JNDIConstants.JAVA_NAMING_FACTORY_URL_PKGS, JNDIConstants.JBOSS_JAVA_NAMING_FACTORY_URL_PKGS_VALUE);
        
        SensorData sensorData = null;
        
        try {
            InitialContext ctx = new InitialContext(props);
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("Connecting to MBean Server on JBoss Server at " + settings.get(SensorSettingsConstants.JNDI_URL) + "...");
            }
            
            // Connect to MBean Server
            MBeanServerConnection server;
            
            try {
                server = (MBeanServerConnection) ctx.lookup(MBeanConstants.JBOSS_JMX_ADAPTER);
            } catch (CommunicationException e) {
                LOG.warn("Unable to establish connection to MBean Server [" + MBeanConstants.JBOSS_JMX_ADAPTER 
                        + "]  on " + settings.get(SensorSettingsConstants.JNDI_URL), e);
                throw new SensorConnectionFailureException("Cannot connect to MBean Server [" + MBeanConstants.JBOSS_JMX_ADAPTER 
                        + "] on " + settings.get(SensorSettingsConstants.JNDI_URL), e);
            }
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("Connection established to remote MBean server at " + settings.get(SensorSettingsConstants.JNDI_URL));
            }
            
            // ServerInfo MBean
            final ObjectName mbServerInfo = new ObjectName(MBeanConstants.JBOSS_SERVER_INFO_OBJ_NAME);
            
            sensorData = new SensorData();
            
            sensorData.add(SensorAttributeConstants.ServerConstants.HOST_NAME, JMXUtil.getStringAttribute(server, mbServerInfo, "HostName"));
            sensorData.add(SensorAttributeConstants.ServerConstants.HOST_ADDRESS, JMXUtil.getStringAttribute(server, mbServerInfo, "HostAddress"));
             
            sensorData.add(SensorAttributeConstants.JavaConstants.JVM_NAME, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVMName"));
            sensorData.add(SensorAttributeConstants.JavaConstants.JAVA_VERSION, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVersion"));
            sensorData.add(SensorAttributeConstants.JavaConstants.JAVA_VENDOR, JMXUtil.getStringAttribute(server, mbServerInfo, "JavaVendor"));
            sensorData.add(SensorAttributeConstants.JavaConstants.JVM_MAX_MEMORY, JMXUtil.getStringAttribute(server, mbServerInfo, "MaxMemory"));
            sensorData.add(SensorAttributeConstants.JavaConstants.JVM_USED_MEMORY,
                    JMXUtil.getStringAttribute(server, mbServerInfo, "TotalMemory"));
            
            // // Gather Heap Information
            String strHeapXML = (JMXUtil.invokeOperation(server, mbServerInfo, "listMemoryPools", new Object[] { Boolean.FALSE },
                    new String[] { "boolean" })).toString();
            
            sensorData.addAllSensorData(extractHeapInfo(strHeapXML));

            LOG.info("[JBossSensorImpl] Sensor Data Collected : " + sensorData);
        } catch (MalformedObjectNameException e) {
            LOG.error("[JBossSensorImpl] MalformedObjectName during JMX Data Collection on ServerInfo MBean Name : " + MBeanConstants.JBOSS_SERVER_INFO_OBJ_NAME, e);
            throw new SensorConnectionFailureException("Unable to obtain data - Connectivity to MBean Failed. ServerInfo MBean Not Found", e);
        } catch (NamingException e) {
            LOG.error("[JBossSensorImpl] JNDI Naming Exception : " + e.getMessage(), e);
            throw new SensorConnectionFailureException("Unable to collect data due to JNDI Naming Failure : " + e.getMessage() , e);
        }
        
        return sensorData;
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
