/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandVideoThumbNailTest {

    @Test
    public void convert_CommandVideoThumbnailWhenCommandValueIsTrue() throws CommandValueException {
        ICommandStrategy videoThumbnail = new CommandVideoThumbNail(true);
        String expectedResult = " -ss 00:00:02.000 -vf scale=128:128 -t 4 -r 10";
        assertEquals(expectedResult, videoThumbnail.command());
    }

    @Test
    public void convert_CommandVideoThumbnailWhenCommandValueIsFalse() throws CommandValueException {
        ICommandStrategy videoThumbnail = new CommandVideoThumbNail(false);
        String expectedResult = "";
        assertEquals(expectedResult, videoThumbnail.command());
    }
}
