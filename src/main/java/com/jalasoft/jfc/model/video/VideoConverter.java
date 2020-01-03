/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.command.ffmpeg.CommandFFMpegPath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandFFMpeg;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoAspectRatio;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoScale;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoConverter;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoThumbNail;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoRotate;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoFrameRate;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;

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
    public FileResponse convert(Param param) {

        VideoParam videoParam = (VideoParam) param;
        FileResponse fileResponse = new FileResponse();

        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandFFMpeg());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoAspectRatio(Integer.toString(videoParam.getAspectRatio())));
            list.add(new CommandVideoScale(videoParam.getWidth(), videoParam.getHeight()));
            list.add(new CommandVideoConverter());
            list.add(new CommandVideoThumbNail(Integer.parseInt(videoParam.getThumbnail())));
            list.add(new CommandVideoRotate(videoParam.getRotate()));
            list.add(new CommandVideoFrameRate(videoParam.getFrameRate()));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile(), videoParam.getFolderName()));
            list.add(new CommandOutputFileName(videoParam.getOutputName(), videoParam.getFolderName()));
            String stringCommand = getCommand(list);
            Process process = Runtime.getRuntime().exec(stringCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

            }
            process.waitFor();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } finally {
            fileResponse.setName(videoParam.getOutputName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
            fileResponse.setDownload(videoParam.getOutputPathFile()+videoParam.getOutputName());
            return fileResponse;
        }
    }

    /**
     * This method is for getting the string command.
     *
     * @param commandList
     * @return command concatenated.
     * @throws CommandValueException
     */
    public String getCommand(List<ICommandStrategy> commandList) throws CommandValueException, IOException {
        ContextStrategy contextStrategy = new ContextStrategy(commandList);
        String result = contextStrategy.buildCommand();
        return result;
    }
}
