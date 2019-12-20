/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

/**
 * This class validates width and height.
 *
 * @author Juan Martinez
 *
 * @version 0.1 19 Dic 2019
 */
public class CommandImageResize implements ICommandStrategy {

    // Content width value.
    private int width;

    // Content height value.
    private int height;

    /**
     * Allows to instantiate CommandResize.
     * @param width, height receive a value.
     * @return command concatenated.
     */
    public CommandImageResize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * This method builds a command.
     * @return command concatenated.
     */
    public String command() {
        if (width > 0 && height > 0) {
            return SPACE + ImageMagickCommand.RESIZE.getCommand() +
                    SPACE + width + ImageMagickCommand.ASTERISK + height;
        }
        return null;
    }
}
