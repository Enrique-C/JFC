/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.pdf.ImageMagickCommand;
import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * This Class change Command Video Scale of a video.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoScale implements ICommandStrategy {

    // Content width value.
    private int width;

    // Content height value.
    private int height;

    /**
     * Creates a new CommandVideoScale object.
     *
     * @param width, receive a value.
     * @param height, receive a value.
     */
    public CommandVideoScale(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * This method builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() {
        if (width > 0 && height > 0) {
            return SPACE + VideoCommand.SCALE.getCommand() + SPACE + width + VideoCommand.COLON.getCommand() + height;
        }
        return null;
    }
}
