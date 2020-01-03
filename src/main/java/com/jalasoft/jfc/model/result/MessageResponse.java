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
 * This enum is used for specify types of messages of response.
 *
 * @version 0.1 03 Jan 2020
 *
 * @author Alan Escalera
 */
public enum MessageResponse {
    ERROR400("Error! 400 Bad Request"),
    ERROR404("Error! 404 Not Found"),
    ERROR406("Error! 406 Not Acceptable"),
    SUCCESS200("Success! 200 file converted");

    // Command variable.
    private String message;

    /**
     * This method is for setting message value.
     * @param message String value.
     */
    MessageResponse(String message) {
        this.message = message;
    }

    /**
     * This method gets message value.
     * @return String value.
     */
    public String getMessageResponse(){
        return message;
    }
}
