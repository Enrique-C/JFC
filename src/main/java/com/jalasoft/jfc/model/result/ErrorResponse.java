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
 * This class is used to return an error on the conversion.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author Alan Escalera
 */
public class ErrorResponse extends Response {

    // Error of the conversion.
    private String error;

    /**
     * Gets the type of error.
     * @return the error message.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the type of error.
     * @param error receives the type of error.
     */
    public void setError(String error) {
        this.error = error;
    }
}
