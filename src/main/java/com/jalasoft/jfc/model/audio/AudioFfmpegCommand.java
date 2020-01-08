/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

/**
 * This enum is used for specifying FFmpeg commands.
 *
 * @version 0.1 08 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public enum AudioFfmpegCommand {
    KBPS("k");

    // Command variable
    private String format;

    /**
     * Builds an Enum.
     * @param format String value
     */
    AudioFfmpegCommand(String format) {
        this.format = format;
    }

    /**
     * Gets command value.
     * @return String value.
     */
    public String getFfmpegCommand(){
        return format;
    }
}
