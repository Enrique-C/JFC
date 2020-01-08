/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.LibreOffice;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.pptx.LibreOfficeCommand;

/**
 * Builds a command Headdless.
 *
 * @version 0.1 08 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class CommandHeadless implements ICommandStrategy {

    /**
     * Builds a convert command.
     * @return command convert.
     */
    @Override
    public String command() throws CommandValueException {
        return this.SPACE + LibreOfficeCommand.HEADLESS.getCommand();
    }
}
