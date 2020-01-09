/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.audio.AudioFfmpegCommand;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * Generates an audio command.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandAudioBitRate implements ICommandStrategy {

    // Contents format value.
    private String audioFormat;

    // Contents bitRate value.
    private byte audioBitRate;



    /**
     * Creates a new CommandAudio object.
     * @param audioBitRate receives values.
     */
    public CommandAudioBitRate(String audioFormat, byte audioBitRate) {
        this.audioFormat = audioFormat;
        this.audioBitRate = audioBitRate;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        final byte MP3_BITRATE_MAX = (byte)320;
        final byte MP3_BITRATE_MIN = (byte)320;
        final byte EMPTY_BITRATE = (byte)0;

        if (audioBitRate == EMPTY_BITRATE) {
            audioBitRate = MP3_BITRATE_MIN;
        }

        String resultFormat = audioFormat;

        switch (resultFormat) {
            case ".mp3":
                if (!(audioBitRate >= MP3_BITRATE_MIN && audioBitRate <= MP3_BITRATE_MAX)) {
                    throw new CommandValueException(ErrorMessageJfc.BIT_RATE_EXCEEDED.getErrorMessageJfc(), this.
                            getClass().getName());
                }
                break;
            case ".wma":
                //Implements this with codec CBR
                break;
        }
        return this.SPACE + VideoCommand.AUDIO_BITRATE.getCommand() + this.SPACE + audioBitRate
                + AudioFfmpegCommand.KBPS.getFfmpegCommand();
    }
}
