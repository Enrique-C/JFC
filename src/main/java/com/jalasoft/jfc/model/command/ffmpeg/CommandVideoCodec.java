package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ValidCommands;
import com.jalasoft.jfc.model.video.VideoCommand;

import java.io.IOException;

/**
 * Changes Aspect Ratio.
 *
 * @version 0.1 02 Ene 2020.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoCodec implements ICommandStrategy {

    // Contents command value.
    private String commandValue;

    // Contents file name of video codec.
    private String pathVideoCodec = "\\videoCodec.dat";

    /**
     * Creates a new CommandVideoCodec object.
     * @param commandValue receives a value.
     */
    public CommandVideoCodec(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return concatenated command.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            if (ValidCommands.getsValidCommand(PathJfc.getPublicVideoCommandsPath() + pathVideoCodec, commandValue)) {
                return this.SPACE + VideoCommand.VIDEO_CODEC.getCommand() + this.SPACE + commandValue;
            }
            throw new CommandValueException(ErrorMessageJfc.VIDEOCODEC_NOT_CHANGE.getErrorMessageJfc(), this.
                    getClass().getName());
        } catch (CommandValueException | IOException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
