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

    // Contents an audio format value.
    private String audioFormat;

    // Contents an audio bitRate value.
    private short audioBitRate;

    // Contents an audio codec value.
    private String audioCodec;

    /**
     * Creates a new CommandAudio object.
     * @param audioFormat receives value.
     * @param audioBitRate receives value.
     */
    public CommandAudioBitRate(String audioFormat, short audioBitRate, String audioCodec) {
        this.audioFormat = audioFormat;
        this.audioBitRate = audioBitRate;
        this.audioCodec = audioCodec;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        final short MP3_BITRATE_MAX = 320;
        final short WMA_BITRATE_MAX = 192;
        final short MP3_BITRATE_MIN = 32;
        final short WMA_BITRATE_MIN = 48;
        final short EMPTY_BITRATE = 0;

        final String CBR_CODEC = "cbr";

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
                if (audioCodec.equals(CBR_CODEC)) {
                    if (!(audioBitRate >= WMA_BITRATE_MIN && audioBitRate <= WMA_BITRATE_MAX)) {
                        throw new CommandValueException(ErrorMessageJfc.BIT_RATE_EXCEEDED.getErrorMessageJfc(), this.
                                getClass().getName());
                    }
                }
                break;
        }
        return this.SPACE + VideoCommand.AUDIO_BITRATE.getCommand() + this.SPACE + audioBitRate
                + AudioFfmpegCommand.KBPS.getFfmpegCommand();
    }
}
