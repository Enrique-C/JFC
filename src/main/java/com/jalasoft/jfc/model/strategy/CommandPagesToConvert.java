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
 * This class verify a valid image format.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandPagesToConvert implements ICommandStrategy {

    // Content command value
    private String commandValue;

    /**
     * It builds the class.
     * @param commandValue contains a value.
     */
    public CommandPagesToConvert(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return String of a command.
     */
    public String command(){
        final Pattern pattern = Pattern.compile("[0-9]\\d*||[0-9][-][0-9]\\d*$");
        if (!pattern.matcher(commandValue).matches()){
            return null;
        }
        return SPACE + PdfCommand.OPEN_BRACKET.getCommand() + commandValue +
        PdfCommand.CLOSE_BRACKET.getCommand();
    }
}
