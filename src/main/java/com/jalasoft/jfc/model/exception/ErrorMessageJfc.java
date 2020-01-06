/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.exception;

/**
 * Used for specify types of messages of throw new exceptions.
 *
 * @version 0.1 06 Jan 2020
 *
 * @author Alan Escalera
 */
public enum ErrorMessageJfc {
    INVALID_PATH("Invalid input file path value\n"),
    FILE_NO_FOUND("File not found\n"),
    PAGES_NULL("Pages to convert value is NULL "),
    INVALID_SCALE("Invalid input file path value\n"),

    COMMAND_NULL("Command value is NULL "),
    COMMAND_INVALID("command value is invalid\n");
    // Command variable.
    private String message;

    /**
     * Sets the message value.
     * @param message String value.
     */
    ErrorMessageJfc(String message) {
        this.message = message;
    }

    /**
     * Gets the message value.
     * @return String value.
     */
    public String getErrorMessageJfc(){
        return message;
    }
}