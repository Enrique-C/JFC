/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.result;

/**
 * This class is used to return the information about the file converted.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author Alan Escalera
 */
public class Response {

    // Name of the file.
    String name;

    // Status of the conversion.
    String status;


    /**
     * Gets the name of the file.
     * @return the name of the file.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Status of the conversion.
     * @return the status of the conversion.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the name of file.
     * @param name receives the name of the file.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the status of the conversion.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
