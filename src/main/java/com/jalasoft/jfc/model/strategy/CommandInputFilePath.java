/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import java.io.File;

/**
 * Validates an input file path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandInputFilePath implements ICommandStrategy {

    /**
     * Generates a command.
     * @param value receives a param.
     * @return input path.
     */
    @Override
    public String command(String value) {
        File file = new File(value);
        if (file.exists()) {
            return value;
        }
        return null;
    }
}
