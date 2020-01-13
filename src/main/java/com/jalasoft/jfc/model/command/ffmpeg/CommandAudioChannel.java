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
public class CommandAudioChannel implements ICommandStrategy {

    // Contents an audio channel value.
    private int audioChannel;

    /**
     * Creates a new CommandAudio object.
     * @param audioChannel receives value.
     */
    public CommandAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        if (audioChannel == 0) {
            return "";
        } else {
            verifyAudioChannel();
            return this.SPACE + AudioFfmpegCommand.AUDIO_CHANNEL.getFfmpegCommand() + this.SPACE + audioChannel;
        }
    }

    /**
     * Verifies if channel is supported.
     * @throws CommandValueException when there is an invalid command.
     */
    private void verifyAudioChannel() throws CommandValueException {
        final int STEREO_CHANNEL = 2;

        if(audioChannel > STEREO_CHANNEL) {
            throw new CommandValueException(ErrorMessageJfc.AUDIO_CHANNEL.getErrorMessageJfc(), this.
                    getClass().getName());
        }
    }
}
