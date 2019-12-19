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
 * This enum is used for specify types of image formats.
 *
 * @version 0.1 16 Dic 2019
 *
 * @author Alan Escalera
 */
public enum ImageFormat {
    JPEG(".jpg"),
    PNG(".png"),
    GIF(".gif"),
    TIFF(".tif");

    // Command variable
    private String format;

    /**
     * This method is for setting command value.
     * @param format String value
     */
    ImageFormat(String format) {
        this.format = format;
    }

    /**
     * This method gets command value.
     * @return String value.
     */
    public String getImageFormat(){
        return format;
    }
}
