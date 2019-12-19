/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.exception;

public class ConvertException extends Exception {

    // Returns a message error
    String message;

    /**
     * @param param parameter of file
     * @param message of error
     */
    public ConvertException(String message, String param) {
        this.message = message;
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
