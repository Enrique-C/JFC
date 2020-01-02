/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command;

import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

/**
 * This class allows to use alpha of image.
 *
 * @version 0.1 28 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandImageAlpha implements ICommandStrategy {

    // Content alpha value.
    private final String COMMAND_VALUE = "remove";

    /**
     * Builds a alpha command.
     * @return command concatenated.
     */
    @Override
    public String command() {
        return this.SPACE + ImageMagickCommand.ALPHA.getCommand() + SPACE + COMMAND_VALUE;
    }
}
