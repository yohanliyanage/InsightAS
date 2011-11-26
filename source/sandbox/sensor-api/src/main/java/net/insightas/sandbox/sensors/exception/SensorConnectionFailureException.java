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

package net.insightas.sandbox.sensors.exception;

/**
 * Sensor Connection Failure Exception denotes a failure in a sensor due to a connectivity issue, such as target server / component being
 * inactive or unresponsive.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 * 
 */
public class SensorConnectionFailureException extends SensorFailureException {
    
    private static final long serialVersionUID = 1081959713065813119L;
    
    /**
     * Constructs a Sensor Connection Failure Exception without a message / cause.
     */
    public SensorConnectionFailureException() {
        super();
    }
    
    /**
     * Constructs a Sensor Connection Failure Exception with the given message and cause.
     * 
     * @param message message
     * @param cause cause
     */
    public SensorConnectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a Sensor Connection Failure Exception with the given message.
     * 
     * @param message message
     */
    public SensorConnectionFailureException(String message) {
        super(message);
    }
    
    /**
     * Constructs a Sensor Connection Failure Exception with the given cause.
     * 
     * @param cause cause
     */
    public SensorConnectionFailureException(Throwable cause) {
        super(cause);
    }
    
}
