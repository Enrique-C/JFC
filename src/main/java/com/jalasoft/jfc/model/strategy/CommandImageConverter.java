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
 *
 * Builds a command to convert an image.
 *
 * @version 0.1 18 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandImageConverter implements ICommandStrategy{


    /**
     * Builds a convert command.
     * @return command convert.
     */
    @Override
    public String command() {
        return this.SPACE + ImageMagickCommand.CONVERT;
    }
}
