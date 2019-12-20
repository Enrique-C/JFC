/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pdf;

/**
 * This enum is used to list Pdf Commands.
 *
 * @author Juan Martinez
 * @version 0.1 18 Dic 2019
 */
public enum ImageMagickCommand {
    CONVERT("convert"),
    DENSITY("-density"),
    SCALE("-scale"),
    QUALITY("-quality"),
    SIZE("-s"),
    TRIM("-trim"),
    RESIZE("-resize"),
    THUMBNAIL("-thumbnail"),
    OPEN_BRACKET("["),
    EN_DASH("-"),
    CLOSE_BRACKET("]"),
    ASTERISK("x"),
    ROTATE("-rotate");

    // Command variable.
    private String command;

    /**
     * This method sets command value.
     *
     * @param command String value
     */
    ImageMagickCommand(String command) {
        this.command = command;
    }

    /**
     * This method gets command value.
     *
     * @return String value.
     */
    public String getCommand() {
        return command;
    }
}
