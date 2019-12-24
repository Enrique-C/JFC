/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.exception;

/**
 * Class of manage exception of commands value.
 *
 * @version 0.1 20 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandValueException extends Exception {

    // Returns a message error.
    String message;

    // Method where error is generated.
    String method;
    /**
     * Manages Value Exceptions.
     * @param method where error is generated.
     * @param message of error.
     */
    public CommandValueException(String message, String method) {
        this.message = message;
        this.method = method;
    }

    /**
     * Gets error message.
     * @return message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
