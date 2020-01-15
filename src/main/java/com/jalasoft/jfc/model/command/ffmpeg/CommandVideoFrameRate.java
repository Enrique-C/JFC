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
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ValidCommands;
import com.jalasoft.jfc.model.video.VideoCommand;

import java.io.IOException;

/**
 * Changes the Frame Rate of a video.
 *
 * @version 0.1 20 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoFrameRate implements ICommandStrategy {

    // Contents command value.
    private String commandValue;

    // Contents file name of frame rate command.
    private String pathFileFrameRateCommands = "\\videoFrameRate.dat";

    /**
     * Creates a new CommandVideoFrameRate object.
     * @param commandValue, receive a value.
     */
    public CommandVideoFrameRate(String commandValue) {
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
            if (commandValue.isEmpty() || commandValue.equals(" ")) {
                return VideoCommand.EMPTY.getCommand();
            }
            else {
                if (ValidCommands.getsValidCommand(PathJfc.getPublicVideoCommandsPath() + pathFileFrameRateCommands, commandValue)) {
                    return this.SPACE + VideoCommand.FRAME_RATE.getCommand() + SPACE + commandValue;
                }
                throw new CommandValueException(ErrorMessageJfc.FRAMERATE_NOT_CHANGE.getErrorMessageJfc(), this.getClass()
                        .getName());
            }
        } catch (CommandValueException | IOException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
