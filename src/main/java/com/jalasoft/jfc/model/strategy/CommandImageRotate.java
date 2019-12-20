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
 * Rotates an image.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Enrique Carrizales
 */
public class CommandImageRotate implements ICommandStrategy{

    /**
     * It Builds a command.
     * @param value receives a value.
     * @return String of a command.
     */
    @Override
    public String command(String value) {
        int intValue = Integer.parseInt(value);
        if (intValue > 0) {
            return this.SPACE + ImageMagickCommand.ROTATE.getCommand() + this.SPACE + value;
        }
        return null;
    }
}
