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
    MKV(".wav"),
    VID(".mp3");

    // Command variable
    private String format;

    /**
     * This method is for setting command value.
     * @param format String value
     */
    AudioFormat(String format) {
        this.format = format;
    }

    /**
     * This method gets command value.
     * @return String value.
     */
    public String getAudioFormat(){
        return format;
    }
}
