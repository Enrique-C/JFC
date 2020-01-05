/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoBitRate;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoCodec;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.command.ffmpeg.CommandFFMpegPath;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is used for convert videos.
 *
 * @author Juan Martinez
 *
 * @version 0.1 13 Dic 2019
 */
public class VideoConverter implements IConverter {

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     */
    private void runCommand(String stringCommand){
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It converts a Video to other format.
     * @param param value of command.
     * @return FileResult object or null value.
     * @throws CommandValueException when is a invalid command.
     */
    public FileResponse convert(Param param) throws CommandValueException, ConvertException, IOException {
        FileResponse fileResponse = new FileResponse();
        VideoParam videoParam = (VideoParam)param;

        StringBuilder stringCommand;
        stringCommand = new StringBuilder();
        stringCommand.append(videoConvert(videoParam));
        runCommand(stringCommand.toString());

        if (videoParam.getThumbnail()) {
            stringCommand = new StringBuilder();
            stringCommand.append(getThumbnail(videoParam));
            runCommand(stringCommand.toString());
            System.out.println(stringCommand);
        }

        if (param.isMetadata()) {
            MetadataConverter metadataConverter = new MetadataConverter();
            metadataConverter.convert(param);
        }

        fileResponse.setName(videoParam.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(videoParam.getOutputPathFile()+videoParam.getOutputName());
        return fileResponse;
    }

    /**
     * It is for getting the string command.
     * @param param value of command.
     * @return command concatenated.
     * @throws CommandValueException when is a invalid command.
     */
    public String videoConvert(Param param) throws CommandValueException {
        VideoParam videoParam = (VideoParam) param;
        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoConverter());
            list.add(new CommandVideoAspectRatio(videoParam.getAspectRatio()));
            list.add(new CommandVideoScale(videoParam.getWidth(), videoParam.getHeight()));
            list.add(new CommandVideoFrameRate(videoParam.getFrameRate()));
            list.add(new CommandVideoRotate(videoParam.getRotate()));
            list.add(new CommandVideoCodec(videoParam.getVideoCodec()));
            list.add(new CommandVideoBitRate(videoParam.getVideoBitRate()));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile(), videoParam.getFolderName()));
            list.add(new CommandOutputFileName(videoParam.getOutputName(), videoParam.getFolderName()));
            ContextStrategy contextStrategy = new ContextStrategy(list);
            String result = contextStrategy.buildCommand();
            System.out.println(result);
            return result;

        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    /**
     * It is for getting the string thumbnail command.
     * @param param value of command.
     * Response it mean the result of the conversion.
     * @throws CommandValueException when is a invalid command.
     */
    public String getThumbnail(Param param) throws CommandValueException {
        VideoParam videoParam = (VideoParam) param;
        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoThumbNail(videoParam.getThumbnail()));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile(), videoParam.getFolderName()));
            list.add(new CommandOutputFileName("thumbnail.gif", videoParam.getFolderName()));
            ContextStrategy contextStrategy = new ContextStrategy(list);
            String result = contextStrategy.buildCommand();
            return result;
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
