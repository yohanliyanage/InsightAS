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

package net.insightas.sandbox.sensors.util;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JMX Utility.
 * <p>
 * Provids utility methods for working with JMX MBeans.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class JMXUtil {
    
    private static final Log LOG = LogFactory.getLog(JMXUtil.class);
    
    /**
     * Returns value as an Object after reading a JMX MBean attribute.
     * 
     * @param server MBean Server Connection
     * @param objName Object Name of MBean
     * @param attribute attribute to read
     * @return value, as an Object, or null if the attribute cannot be read
     */
    public static Object getObjectAttribute(MBeanServerConnection server, ObjectName objName, String attribute) {
        
        try {
            return server.getAttribute(objName, attribute);
        } catch (AttributeNotFoundException e) {
            LOG.warn("MBean Attribute [" + attribute + "] not found on ObjectName [" + objName + "]");
        } catch (InstanceNotFoundException e) {
            LOG.warn("MBean Instance not found for ObjectName [" + objName + "]");
        } catch (MBeanException e) {
            LOG.warn("MBean failed on attribute [" + attribute + "] lookup on ObjectName [" + objName + "]", e);
        } catch (ReflectionException e) {
            LOG.warn("Reflective field access failure when accessing attribute [" + attribute + "] lookup on ObjectName [" + objName + "]",
                    e);
        } catch (IOException e) {
            LOG.warn("IOException when accessing attribute [" + attribute + "] lookup on ObjectName [" + objName + "]", e);
        }
        
        return null;
    }
    
    /**
     * Returns value as a String after reading a JMX MBean attribute.
     * 
     * @param server MBean Server Connection
     * @param objName Object Name of MBean
     * @param attribute attribute to read
     * @return value, as a String, or null if the attribute cannot be read
     */
    public static String getStringAttribute(MBeanServerConnection server, ObjectName objName, String attribute) {
        Object value = getObjectAttribute(server, objName, attribute);
        return (value != null) ? value.toString() : null;
    }

    /**
     * Returns value as a Integer after reading a JMX MBean attribute.
     * 
     * @param server MBean Server Connection
     * @param objName Object Name of MBean
     * @param attribute attribute to read
     * @return value, as a Integer, or null if the attribute cannot be read
     * @throws NumberFormatException if the value is not a number
     */
    public static Integer getIntegerAttribute(MBeanServerConnection server, ObjectName objName, String attribute) throws NumberFormatException {
        Object value = getObjectAttribute(server, objName, attribute);

        if (value == null) {
            return null;
        }
        
        if (value instanceof Integer) {
            return (Integer) value;
        }
        else {
            // Value is not an integer. Attempt parsing it.
            return Integer.valueOf(value.toString());
        }
    }
    

    /**
     * Returns value as a Long after reading a JMX MBean attribute.
     * 
     * @param server MBean Server Connection
     * @param objName Object Name of MBean
     * @param attribute attribute to read
     * @return value, as a Long, or null if the attribute cannot be read
     * @throws NumberFormatException if the value is not a number
     */
    public static Long getLongAttribute(MBeanServerConnection server, ObjectName objName, String attribute) throws NumberFormatException {
        Object value = getObjectAttribute(server, objName, attribute);

        if (value == null) {
            return null;
        }
        
        if (value instanceof Long) {
            return (Long) value;
        }
        else {
            // Value is not a Long. Attempt parsing it.
            return Long.valueOf(value.toString());
        }
    }
    

    /**
     * Returns value as a Boolean after reading a JMX MBean attribute.
     * 
     * @param server MBean Server Connection
     * @param objName Object Name of MBean
     * @param attribute attribute to read
     * @return value, as a Boolean, or null if the attribute cannot be read
     */
    public static Boolean getBooleanAttribute(MBeanServerConnection server, ObjectName objName, String attribute)  {
        Object value = getObjectAttribute(server, objName, attribute);

        if (value == null) {
            return null;
        }
        
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        else {
            // Value is not a Long. Attempt parsing it.
            return Boolean.valueOf(value.toString());
        }
    }
}
