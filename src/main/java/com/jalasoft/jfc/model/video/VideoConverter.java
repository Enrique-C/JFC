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
import com.jalasoft.jfc.model.strategy.CommandFFMpeg;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * This method convert a video format to another format.
     * @param param
     * @return FileResult object or null value.
     * @throws IOException
     */
    public FileResult convert(Param param) {
        VideoParam videoParam = (VideoParam) param;
        FileResult fileResult = new FileResult();

        //Variable to get a second where take a thumbnail
        final String secondToGetThumbnail = "00:00:2.100";
        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoAspectRatio(videoParam.getAspectRatio()));
            list.add(new CommandVideoRotate(videoParam.getRotate()));
            list.add(new CommandVideoScale(videoParam.getWidth(), videoParam.getHeight()));
            list.add(new CommandVideoFrameRate(videoParam.getFrameRate()));
            //list.add(new CommandVideoThumbNail(secondToGetThumbnail));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile()));
            list.add(new CommandOutputFileName(videoParam.getOutputFileName()));
            String stringCommand = getCommand(list);
            System.out.println(stringCommand);
            Process process = Runtime.getRuntime().exec(stringCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

            }
            process.waitFor();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } finally {
            return fileResult;
        }
    }

    /**
     * This method is for getting the string command.
     *
     * @param commandList
     * @return command concatenated.
     * @throws CommandValueException
     */
    public String getCommand(List<ICommandStrategy> commandList) throws CommandValueException {
        ContextStrategy contextStrategy = new ContextStrategy(commandList);
        String result = contextStrategy.buildCommand();
        return result;
    }
}
