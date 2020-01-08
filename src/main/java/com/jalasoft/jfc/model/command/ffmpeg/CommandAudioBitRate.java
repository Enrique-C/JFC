/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

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

    // Contents command value.
    private String commandValue;

    /**
     * Creates a new CommandAudioBitRate object.
     * @param commandValue receive a value.
     */
    public CommandAudioBitRate(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return command concatenated.
     * @throws CommandValueException when is a invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            int MINIMUM_VALUE = 96;
            if (commandValue.isEmpty()) {
                return VideoCommand.EMPTY.getCommand();
            } else {
                if (!commandValue.isEmpty() && Integer.parseInt(commandValue) > MINIMUM_VALUE ) {
                    return this.SPACE + VideoCommand.AUDIO_BITRATE.getCommand() + this.SPACE + commandValue;
                }
                throw new CommandValueException(ErrorMessageJfc.AUDIOBITRATE_NOT_CHANGE.getErrorMessageJfc(), this.
                        getClass().getName());
            }
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
