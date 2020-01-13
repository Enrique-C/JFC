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

    // Content audioCodec value.
    private String audioCodec;

    // Content audioSampleRate value.
    private int audioSampleRate;

    // Content audioBitRate value.
    private int audioBitRate;

    // Content audioChannel value.
    private int audioChannel;

    /**
     * Gets an audio codec.
     * @return an audio codec value.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Gets getAudioSampleRate value.
     * @return an audio sample rate value.
     */
    public int getAudioSampleRate() {
        return audioSampleRate;
    }

    /**
     * Gets getAudioChannel value.
     * @return an audio channel value.
     */
    public int getAudioChannel() {
        return audioChannel;
    }

    /**
     * Gets audioBitRate value.
     * @return an audio bitRate value.
     */
    public int getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Sets audioCodec value.
     * @param audioCodec contains an audio codec.
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Sets audioSampleRate value.
     * @param audioSampleRate contains an audio sample rate value.
     */
    public void setAudioSampleRate(int audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

    /**
     * Sets audioChannel value.
     * @param audioChannel contains and audio channel value.
     */
    public void setAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }

    /**
     * Sets audioBitRate value.
     * @param audioBitRate contains an audio bitRate.
     */
    public void setAudioBitRate(int audioBitRate) {
        this.audioBitRate = audioBitRate;
    }
}
