package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.video.VideoFormat;

/**
 * Verify a valid image format.
 *
 * @version 0.1 02 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoFormat implements ICommandStrategy {

    // Contents command value.
    private String commandValue;

    /**
     * Creates a new CommandVideoFormat object.
     * @param commandValue contains a value.
     */
    public CommandVideoFormat(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return String of a command.
     */
    public String command() throws CommandValueException {
        for (VideoFormat video : VideoFormat.values()) {
            if (video.getVideoFormat().equals(commandValue)) {
                return commandValue;
            }
        }
        throw new CommandValueException(ErrorMessageJfc.FORMTAT_INVALID.getErrorMessageJfc(), this.getClass().getName());
    }
}
