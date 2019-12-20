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
 * This class verify the scale value.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandScale implements ICommandStrategy {

    /**
     * This method builds a command.
     * @param value receives a value.
     * @return command concatenated.
     */
    public String command(String value) {
        final Pattern pattern = Pattern.compile("[0-9]\\d*[%]");
        if (!pattern.matcher(value).matches()){
            return null;
        }
        return SPACE + PdfCommand.SCALE.getCommand() + SPACE + value;
    }
}
