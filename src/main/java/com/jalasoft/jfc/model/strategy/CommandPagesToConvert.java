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

import java.util.regex.Pattern;

/**
 * This class verify a valid image format.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandPagesToConvert implements ICommandStrategy {

    /**
     * Builds a command.
     * @param numberOfPage receives a param.
     * @return String of a command.
     */
    public String command(String numberOfPage){
        final Pattern pattern = Pattern.compile("[0-9]\\d*||[0-9][-][0-9]\\d*$");
        if (!pattern.matcher(numberOfPage).matches()){
            return null;
        }
        return SPACE + ImageMagickCommand.OPEN_BRACKET.getCommand() + numberOfPage +
        ImageMagickCommand.CLOSE_BRACKET.getCommand();
    }
}
