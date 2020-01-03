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
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.video.VideoCommand;

import java.io.File;
import java.io.IOException;

/**
 * This Class has Command FFmpeg path.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandFFMpegPath implements ICommandStrategy {

    // Content ffmpeg command value.
    private  String FFMpegPath;

    /**
     * This initialize PathJfc and gets the FfMpeg Path.
     * @throws CommandValueException when is a invalid file.
     */
    public CommandFFMpegPath() throws CommandValueException {
        FFMpegPath = PathJfc.getFfmpegPath();
    }

    /**
     * Generates a command.
     * @return exe of FFMpeg path.
     * @throws CommandValueException when is a invalid file.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            File file = new File(FFMpegPath);

            if (file.exists()) {
                return this.SPACE + FFMpegPath + this.SPACE + VideoCommand.INFILE.getCommand();
            }
            throw new CommandValueException("FFMepg doesn't exist\n", this.getClass().getName());
        } catch (CommandValueException cve){
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
