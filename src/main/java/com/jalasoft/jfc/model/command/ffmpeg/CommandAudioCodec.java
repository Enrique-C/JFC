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

    /**
     * Creates a new CommandAudio object.
     * @param audioCodec receives value.
     */
    public CommandAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
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
            return this.SPACE + AudioFfmpegCommand.AUDIO_CODEC.getFfmpegCommand() + this.SPACE + audioCodec;
        }
    }
}
