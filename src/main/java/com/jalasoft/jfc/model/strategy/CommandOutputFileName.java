/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

/**
 * This class verify if outputFileName value.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandOutputFileName implements ICommandStrategy {

    /**
     * Builds command.
     * @param outputFileName receives a param.
     * @return String of a command.
     */
    public String command(String outputFileName){
        String regexRule = "[^a-zA-Z0-9]";
        String replaceValue = "";
        if (outputFileName != null){
            outputFileName = outputFileName.replaceAll(regexRule, replaceValue);
            return outputFileName;
        }
        return null;
    }
}
