/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

public enum AudioFormat {
    WAV(".wav"),
    MP3(".mp3");

    // Command variable
    private String format;

    /**
     * Builds an Enum.
     * @param format String value
     */
    AudioFormat(String format) {
        this.format = format;
    }

    /**
     * Gets command value.
     * @return String value.
     */
    public String getAudioFormat(){
        return format;
    }
}
