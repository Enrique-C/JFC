package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * Class changes Aspect Ratio.
 *
 * @version 0.1 02 Ene 2020.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoBitRate implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * Creates a new CommandVideoBitRate object.
     *
     * @param commandValue, receive a value.
     */
    public CommandVideoBitRate(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            if (commandValue != "") {
                System.out.println(this.SPACE + VideoCommand.VIDEO_BITRATE.getCommand() + this.SPACE + commandValue);
                return this.SPACE + VideoCommand.VIDEO_BITRATE.getCommand() + this.SPACE + commandValue;
            }
            throw new CommandValueException("Can not change the Video Bit rate", this.getClass().getName());
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
