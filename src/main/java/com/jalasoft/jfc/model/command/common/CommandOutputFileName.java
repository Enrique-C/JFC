/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.common;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;

/**
 * This class verify if outputFileName value.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandOutputFileName implements ICommandStrategy {

    // Content a output name.
    private String outputName;

    // Content input file name without extension.
    private String inputName;

    /**
     * Creates a new CommandOutputFileName object.
     * @param outputName contains a value.
     */
    public CommandOutputFileName(String outputName, String inputName) {
        this.outputName = outputName;
        this.inputName = inputName;
    }

    /**
     * Builds command.
     * @return string of command.
     * @throws CommandValueException
     */
    public String command() throws CommandValueException {
        String regexRule = "[^a-zA-Z0-9.]";
        String replaceValue = "";

            if (outputName.equals("_t") || outputName.isEmpty()) {
                outputName = inputName;
            }
            outputName = outputName.replaceAll(regexRule, replaceValue);
        return outputName;
    }
}
