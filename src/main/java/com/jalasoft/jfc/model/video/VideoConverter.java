/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.FileResult;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.strategy.CommandFFMpegPath;
import com.jalasoft.jfc.model.strategy.CommandInputFilePath;
import com.jalasoft.jfc.model.strategy.CommandVideoAspectRatio;
import com.jalasoft.jfc.model.strategy.CommandVideoScale;
import com.jalasoft.jfc.model.strategy.CommandVideoConverter;
import com.jalasoft.jfc.model.strategy.CommandVideoThumbNail;
import com.jalasoft.jfc.model.strategy.CommandVideoRotate;
import com.jalasoft.jfc.model.strategy.CommandVideoFrameRate;
import com.jalasoft.jfc.model.strategy.CommandOutputFilePath;
import com.jalasoft.jfc.model.strategy.CommandOutputFileName;
import com.jalasoft.jfc.model.strategy.ContextStrategy;
import com.jalasoft.jfc.model.strategy.ICommandStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This Class is used for convert videos.
 *
 * @author Juan Martinez
 *
 * @version 0.1 13 Dic 2019
 */
public class VideoConverter implements IConverter {

    private final static Logger LOGGER = Logger.getLogger(VideoConverter.class.getName());

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     */
    private int runCommand(String stringCommand){
        int returnValue = -1;
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
            returnValue = process.exitValue();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * This method converts a Video to other format.
     * @param param
     * @return FileResult object or null value.
     */
    public FileResult convert(Param param) throws CommandValueException, IOException {
        FileResult fileResult = new FileResult();
        String stringCommand = getCommand(param);
        System.out.println(stringCommand);
        int value = runCommand(stringCommand);
        return fileResult;
    }

    /**
     * This method is for getting the string command.
     *
     * @param param
     * @return command concatenated.
     * @throws CommandValueException
     */
    public String getCommand(Param param) throws CommandValueException {
        VideoParam videoParam = (VideoParam) param;
        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoAspectRatio(videoParam.getAspectRatio()));
            list.add(new CommandVideoScale(videoParam.getWidth(), videoParam.getHeight()));
            list.add(new CommandVideoConverter());
            list.add(new CommandVideoThumbNail(videoParam.getThumbnail()));
            list.add(new CommandVideoRotate(videoParam.getRotate()));
            list.add(new CommandVideoFrameRate(videoParam.getFrameRate()));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile(), videoParam.getInputFileName()));
            list.add(new CommandOutputFileName(videoParam.getOutputFileName(), videoParam.getInputFileName()));
            ContextStrategy contextStrategy = new ContextStrategy(list);
            String result = contextStrategy.buildCommand();
            return result;
        } catch (CommandValueException | IOException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
