/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

import com.jalasoft.jfc.model.Param;

/**
 * Manages params of an audio.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class AudioParam extends Param {

    // Contains format of an audio.
    private  String audioFormat;

    // Content audioBitRate value.
    private String audioBitRate;

    /**
     * Gets format of an audio.
     * @return audio format String value.
     */
    public String getAudioFormat() {
        return audioFormat;
    }

    /**
     * Gets audioBitRate value.
     * @return audio bitRate String value.
     */
    public String getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Sets audioFormat value.
     * @param audioFormat contains and audio format.
     */
    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    /**
     * Sets audioBitRate value.
     * @param audioBitRate contains an audio bitRate.
     */
    public void setAudioBitRate(String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }
}
