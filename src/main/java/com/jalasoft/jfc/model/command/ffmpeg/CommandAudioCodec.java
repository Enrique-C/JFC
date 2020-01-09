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

/**
 * Generates an audio command.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandAudioCodec implements ICommandStrategy {

    // Contents an audio codec value.
    private String audioCodec;

    // Contents an audio format value.
    private String audioFormatOut;

    /**
     * Creates a new CommandAudio object.
     * @param audioCodec receives value.
     * @param audioFormatOut receives value.
     */
    public CommandAudioCodec(String audioCodec, String audioFormatOut) {
        this.audioCodec = audioCodec;
        this.audioFormatOut = audioFormatOut;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        if (audioCodec.isEmpty()) {
            return "";
        } else {
            verifyCodec(audioFormatOut);

            return this.SPACE + AudioFfmpegCommand.AUDIO_CODEC.getFfmpegCommand() + this.SPACE + audioCodec;
        }
    }

    private void verifyCodec(String resultFormat) throws CommandValueException {
        final String MP3_CODEC = "mp3";
        final String AAC_CODEC = "aac";

        switch (resultFormat) {
            case ".mp3":
                if (!audioCodec.equals(MP3_CODEC)) {
                    throw new CommandValueException(ErrorMessageJfc.BIT_RATE_EXCEEDED.getErrorMessageJfc(), this.
                            getClass().getName());
                }
                break;
            case ".wav":
                //Todo
                break;
        }
    }
}
