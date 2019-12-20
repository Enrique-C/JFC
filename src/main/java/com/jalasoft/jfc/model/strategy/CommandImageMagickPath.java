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
 * Validates ImageMagick path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandImageMagickPath implements ICommandStrategy {

    // Content command value.
    private final String IMAGE_MAGIC_PATH = "thirdparty/ImageMagick/magick.exe";

    /**
     * Generates a command.
     * @return exe of ImageMagick path.
     */
    @Override
    public String command() {
        File file = new File(IMAGE_MAGIC_PATH);
        if (file.exists()) {
            return IMAGE_MAGIC_PATH + this.SPACE;
        }
        return null;
    }
}
