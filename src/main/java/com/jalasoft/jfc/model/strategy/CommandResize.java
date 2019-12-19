/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.pdf.PdfCommand;

import java.util.regex.Pattern;

/**
 * This class validates width and height.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandResize implements ICommandStrategy {

    /**
     * This method builds a command.
     * @param widthAndHeight receives a param.
     * @return command concatenated.
     */
    public String command(String widthAndHeight) {
        return SPACE;
    }

    /**
     * This method builds a command.
     * @param width, height receive a value.
     * @return command concatenated.
     */
    public String command(int width, int height) {
        if (width > 0 && height > 0){
            return SPACE + PdfCommand.RESIZE.getCommand() +
                   SPACE + width + PdfCommand.ASTERISK + height;
        }
        return null;
    }
}
