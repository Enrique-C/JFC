/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.image.ImageFormat;

/**
 * This class verify a valid image format.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandImageFormat implements ICommandStrategy {

    /**
     * Builds a command.
     * @param imageFormat receives a param.
     * @return String of a command.
     */
    public String command(String imageFormat){
        for (ImageFormat image : ImageFormat.values()) {
            if (image.getImageFormat().equals(imageFormat)){
                return imageFormat;
            }
        }
        return null;
    }
}
