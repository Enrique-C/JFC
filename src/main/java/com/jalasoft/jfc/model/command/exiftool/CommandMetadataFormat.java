/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.exiftool;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.image.ImageFormat;

/**
 * This class generates a command.
 *
 * @version 0.1 03 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandMetadataFormat implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    // Content format value.
    private final String METADATA_FORMAT = ".xmp";

    /**
     * Creates a new CommandMetadataFormat object.
     */
    public CommandMetadataFormat() {
        this.commandValue = METADATA_FORMAT;
    }

    /**
     * Generates a extension file.
     * @return a command String.
     * @throws CommandValueException generates a error message.
     */
    @Override
    public String command() throws CommandValueException {
        return commandValue;
    }
}
