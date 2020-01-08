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
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.utility.PathJfc;

import java.io.File;

/**
 * Validates the LibreOffice path.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class CommandLibreOfficePath implements ICommandStrategy {

    // Contents LibreOffice Path value.
    private  String libreOfficePath;

    /**
     * Assigns the LibreOffice Path.
     */
    public CommandLibreOfficePath() {
        libreOfficePath = PathJfc.getLibreOfficePath();
    }

    /**
     * Generates a command.
     * @return exe of LibreOffice path.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            File file = new File(libreOfficePath);

            if (file.exists()) {
                return libreOfficePath;
            }
            throw new CommandValueException(ErrorMessageJfc.LIBREOFFICE_NOT_EXIST.getErrorMessageJfc(), this
                    .getClass().getName());
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
