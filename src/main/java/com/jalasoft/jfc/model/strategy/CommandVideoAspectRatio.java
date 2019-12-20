/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * This class changes aspect ratio of a video.
 *
 * @version 0.1 20 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoAspectRatio implements ICommandStrategy{


    public String command(String value) {
        return this.SPACE + VideoCommand.INFILE + this.SPACE + value + this.SPACE;
    }

    @Override
    public String command() {
        return this.SPACE;
    }
}
