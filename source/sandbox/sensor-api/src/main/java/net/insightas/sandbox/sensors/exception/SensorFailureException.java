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
 * Generic Sensor Failure Exception.
 * 
 * @author Yohan Liyanage (yohan at computer dot org)
 *
 */
public class SensorFailureException extends Exception {

    private static final long serialVersionUID = -812875172271235566L;

    /**
     * Constructs a Sensor Failure Exception without a message / cause.
     */
    public SensorFailureException() {
        super();
    }

    /**
     * Constructs a SensorFailureException with the given message and cause.
     * @param message message
     * @param cause cause
     */
    public SensorFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a SensorFailureException with the given message.
     * @param message message
     */
    public SensorFailureException(String message) {
        super(message);
    }

    /**
     * Constructs a SensorFailureException with the given cause.
     * @param cause cause
     */
    public SensorFailureException(Throwable cause) {
        super(cause);
    }

    
}
