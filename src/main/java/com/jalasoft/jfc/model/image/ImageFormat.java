/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.jfc.model.image;

/**
 * Specifies types of image formats.
 *
 * @version 0.1 18 Dic 2019
 *
 * @author Enrique Carrizales
 */
public enum ImageFormat {
    JPG(".jpg"),
    PNG(".png"),
    GIF(".gif"),
    BMP(".bmp"),
    WBMP ("wbmp");

    // Command variable.
    private String command;

    /**
     * Sets command value.
     * @param command String value.
     */
    ImageFormat(String command) {
        this.command = command;
    }

    /**
     * Gets command value.
     * @return String value.
     */
    public String getCommand(){
        return command;
    }
}
