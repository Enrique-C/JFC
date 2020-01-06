/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.imagick;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

/**
 * This class validates thumbnail value.
 *
 * @version 0.1 20 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandThumbnail implements ICommandStrategy {

    // Contents command value.
    private boolean commandValue;

    // Constant size Thumbnail.
    private final String THUMBNAIL_SIZE = "128";

    /**
     * Allows to instantiate this class.
     * @param commandValue
     */
    public CommandThumbnail(boolean commandValue){
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return concatenated command.
     */
    public String command() {
            if (commandValue) {
                return SPACE + ImageMagickCommand.THUMBNAIL.getCommand() + SPACE + THUMBNAIL_SIZE +
                        ImageMagickCommand.ASTERISK.getCommand();
            }
        return "";
    }
}
