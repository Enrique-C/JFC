/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.audio.AudioFormat;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.video.VideoFormat;

public class CommandAudioFormat implements ICommandStrategy {

    // Contents command value.
    private String commandValue;

    /**
     * Creates a new CommandAudioFormat object.
     * @param commandValue contains a value.
     */
    public CommandAudioFormat(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return String of a command.
     */
    @Override
    public String command() throws CommandValueException {
        for (AudioFormat audioFormat : AudioFormat.values()) {
            if (audioFormat.getAudioFormat().equals(commandValue)) {
                return commandValue;
            }
        }
        throw new CommandValueException(ErrorMessageJfc.FORMTAT_INVALID.getErrorMessageJfc(), this.getClass().getName());
    }
}
