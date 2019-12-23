/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.exception.CommandValueException;

public class CommandFFMpeg implements ICommandStrategy {

    //Content ffmpeg value;
    String commandValue = "ffmpeg ";
    @Override
    public String command() throws CommandValueException {
        return commandValue;
    }
}
