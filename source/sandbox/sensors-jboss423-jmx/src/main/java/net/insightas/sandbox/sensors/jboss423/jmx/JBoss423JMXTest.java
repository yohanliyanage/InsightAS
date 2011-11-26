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

import java.io.StringReader;
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
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.insightas.sandbox.sensors.api.JavaMemoryInfo;
import net.insightas.sandbox.sensors.api.ServerInfo;
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
        
        LOG.info("Server Info : " + serverInfo);
        
        // // Gather Heap Information
        String strHeapXML = (JMXUtil.invokeOperation(server, mbServerInfo, "listMemoryPools", new Object[] { Boolean.FALSE },
                new String[] { "boolean" })).toString();
        
        JavaMemoryInfo javaMemInfo = new JavaMemoryInfo();
        javaMemInfo.setJvmMaxMemory(JMXUtil.getLongAttribute(server, mbServerInfo, "MaxMemory"));
        javaMemInfo.setJvmTotalUsedMemory(JMXUtil.getLongAttribute(server, mbServerInfo, "TotalMemory"));
        
        extractHeapInfo(strHeapXML, javaMemInfo);
        
        LOG.info("Java Memory Info : " + javaMemInfo);
    }
    
    /**
     * Parses the Heap XML and extracts Heap Information.
     * 
     * @param strHeapXML
     * @param javaMemInfo
     */
    private static void extractHeapInfo(String strHeapXML, JavaMemoryInfo javaMemInfo) throws SensorFailureException {
        
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
            
            XPathExpression xpCodeCache = xpath.compile("/html/body/blockquote/blockquote[1]");
            XPathExpression xpEden = xpath.compile("/html/body/blockquote/blockquote[2]");
            XPathExpression xpSurvivor = xpath.compile("/html/body/blockquote/blockquote[3]");
            XPathExpression xpOldGen = xpath.compile("/html/body/blockquote/blockquote[4]");
            XPathExpression xpPermGen = xpath.compile("/html/body/blockquote/blockquote[5]");
            
            HeapStatDTO codeCacheStats = extractStatFromString((String) xpCodeCache.evaluate(result.getNode(), XPathConstants.STRING));
            HeapStatDTO edenStats = extractStatFromString((String) xpEden.evaluate(result.getNode(), XPathConstants.STRING));
            HeapStatDTO survivorStats = extractStatFromString((String) xpSurvivor.evaluate(result.getNode(), XPathConstants.STRING));
            HeapStatDTO oldGenStats = extractStatFromString((String) xpOldGen.evaluate(result.getNode(), XPathConstants.STRING));
            HeapStatDTO permGenStats = extractStatFromString((String) xpPermGen.evaluate(result.getNode(), XPathConstants.STRING));
            
            javaMemInfo.setCodeCacheCurrentCommitted(codeCacheStats.getCurrentCommitted());
            javaMemInfo.setCodeCacheCurrentMax(codeCacheStats.getCurrentMax());
            javaMemInfo.setCodeCacheCurrentUsed(codeCacheStats.getCurrentUsed());
            javaMemInfo.setCodeCachePeakCommitted(codeCacheStats.getPeakCommitted());
            javaMemInfo.setCodeCachePeakMax(codeCacheStats.getPeakMax());
            javaMemInfo.setCodeCachePeakUsed(codeCacheStats.getPeakUsed());
            
            javaMemInfo.setEdenCurrentCommitted(edenStats.getCurrentCommitted());
            javaMemInfo.setEdenCurrentMax(edenStats.getCurrentMax());
            javaMemInfo.setEdenCurrentUsed(edenStats.getCurrentUsed());
            javaMemInfo.setEdenPeakCommitted(edenStats.getPeakCommitted());
            javaMemInfo.setEdenPeakMax(edenStats.getPeakMax());
            javaMemInfo.setEdenPeakUsed(edenStats.getPeakUsed());
            
            javaMemInfo.setSurvivorCurrentCommitted(survivorStats.getCurrentCommitted());
            javaMemInfo.setSurvivorCurrentMax(survivorStats.getCurrentMax());
            javaMemInfo.setSurvivorCurrentUsed(survivorStats.getCurrentUsed());
            javaMemInfo.setSurvivorPeakCommitted(survivorStats.getPeakCommitted());
            javaMemInfo.setSurvivorPeakMax(survivorStats.getPeakMax());
            javaMemInfo.setSurvivorPeakUsed(survivorStats.getPeakUsed());
            
            javaMemInfo.setOldGenCurrentCommitted(oldGenStats.getCurrentCommitted());
            javaMemInfo.setOldGenCurrentMax(oldGenStats.getCurrentMax());
            javaMemInfo.setOldGenCurrentUsed(oldGenStats.getCurrentUsed());
            javaMemInfo.setOldGenPeakCommitted(oldGenStats.getPeakCommitted());
            javaMemInfo.setOldGenPeakMax(oldGenStats.getPeakMax());
            javaMemInfo.setOldGenPeakUsed(oldGenStats.getPeakUsed());
            
            javaMemInfo.setPermGenCurrentCommitted(permGenStats.getCurrentCommitted());
            javaMemInfo.setPermGenCurrentMax(permGenStats.getCurrentMax());
            javaMemInfo.setPermGenCurrentUsed(permGenStats.getCurrentUsed());
            javaMemInfo.setPermGenPeakCommitted(permGenStats.getPeakCommitted());
            javaMemInfo.setPermGenPeakMax(permGenStats.getPeakMax());
            javaMemInfo.setPermGenPeakUsed(permGenStats.getPeakUsed());
            
        } catch (XPathExpressionException e) {
            LOG.error("XPathExpressionException when parsing Sensor XML - " + e.getMessage());
            throw new SensorFailureException("XPathExpressionException when parsing Sensor XML", e);
        }
        
    }

    /**
     * @param strCodeCache
     * @return
     */
    private static HeapStatDTO extractStatFromString(String strCodeCache) {

        HeapStatDTO stat = new HeapStatDTO();
        
        // Replace comma with colon for easier segmenting
        strCodeCache = strCodeCache.replaceAll(",", ":");
        
        // Split segments
        String[] segments = strCodeCache.split(";");
        String[] peakSegments = segments[0].split(":");
        String[] currentSgements = segments[1].split(":");
        
        stat.setPeakUsed(Long.valueOf(peakSegments[4].trim()));
        stat.setPeakCommitted(Long.valueOf(peakSegments[6].trim()));
        stat.setPeakMax(Long.valueOf(peakSegments[8].trim()));
        
        stat.setCurrentUsed(Long.valueOf(currentSgements[4].trim()));
        stat.setCurrentCommitted(Long.valueOf(currentSgements[6].trim()));
        stat.setCurrentMax(Long.valueOf(currentSgements[8].trim()));
        
        return stat;
    }
    
}
