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

public class CommandAudioSampleRate implements ICommandStrategy {

    // Contents sampleRate value.
    private String audioSampleRate;

    /**
     * Creates a new CommandAudio object.
     * @param audioSampleRate receives values.
     */
    public CommandAudioSampleRate(String audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        getSampleRate();

        return this.SPACE + AudioFfmpegCommand.AUDIO_SAMPLE_RATE.getFfmpegCommand() + this.SPACE + audioSampleRate;
    }

    /**
     * Reviews audioSampleRate value.
     * @throws CommandValueException when there is an invalid value.
     */
    private void getSampleRate() throws CommandValueException {
        int audioSampleRate = Integer.parseInt(this.audioSampleRate);

        final int DEFAULT = 44100;

        if (audioSampleRate == 0) {
            audioSampleRate = DEFAULT;
        }
        if (audioSampleRate < DEFAULT) {
            throw new CommandValueException(ErrorMessageJfc.SAMPLE_RATE_EXCEEDED.getErrorMessageJfc() + ": "
                    + audioSampleRate, this.getClass().getName());
        }
    }
}
