/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;


import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoBitRate;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoCodec;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoFormat;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoFrameRate;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoScale;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.FileResponse;

import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandFFMpegPath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoAspectRatio;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoThumbNail;
import com.jalasoft.jfc.model.exception.CommandValueException;

import com.jalasoft.jfc.model.utility.FolderRemover;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ZipFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses for convert videos.
 *
 * @author Juan Martinez
 *
 * @version 0.1 13 Dic 2019
 */
public class VideoConverter implements IConverter {

    // Assigns the zip's Path.
    private String zipPath;

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     */
    private void runCommand(String stringCommand) {
        try {
            Process process = Runtime.getRuntime().exec(stringCommand + VideoCommand.LOG_LEVEL_QUIET
                    .getCommand());
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
     * @throws ConvertException when converts fail.
     * @throws ZipJfcException when  a zipFile is invalid.
     */
    @Override
    public FileResponse convert(Param param) throws ConvertException, CommandValueException, ZipJfcException {
        FileResponse fileResponse = new FileResponse();
        VideoParam videoParam = (VideoParam) param;

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

        zipFile(videoParam);

        FolderRemover.removeFolder(videoParam.getOutputPathFile() + videoParam.getFolderName());

        fileResponse.setName(videoParam.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(zipPath);
        return fileResponse;
    }

    /**
     * Gets the string command.
     * @param param value of command.
     * @return command concatenated.
     * @throws CommandValueException when is a invalid command.
     */
    public String videoConvert(Param param) throws CommandValueException {
        VideoParam videoParam = (VideoParam)param;
        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandFFMpegPath());
            list.add(new CommandInputFilePath(videoParam.getInputPathFile()));
            list.add(new CommandVideoCodec(videoParam.getVideoCodec()));
            list.add(new CommandVideoAspectRatio(videoParam.getAspectRatio()));
            list.add(new CommandVideoScale(videoParam.getWidth(), videoParam.getHeight()));
            list.add(new CommandVideoFrameRate(videoParam.getFrameRate()));
            list.add(new CommandVideoBitRate(videoParam.getVideoBitRate()));
            list.add(new CommandOutputFilePath(videoParam.getOutputPathFile(), videoParam.getFolderName()));
            list.add(new CommandOutputFileName(videoParam.getOutputName(), videoParam.getFolderName()));
            list.add(new CommandVideoFormat(videoParam.getVideoFormat()));
            ContextStrategy contextStrategy = new ContextStrategy(list);
            String result = contextStrategy.buildCommand();
            return result;

        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    /**
     * Gets the string thumbnail command.
     * @param param value of command.
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

    /**
     * Zips a list of files.
     * @param videoParam receives videoParam.
     * @throws ZipJfcException when is a invalid file path.
     */
    private void zipFile(VideoParam videoParam) throws ZipJfcException {
        ZipFolder zip = new ZipFolder();

        final String BACKSLASH = "/";
        final String ZIP_TAG = ".zip";

        File[] files = new File(videoParam.getOutputPathFile() + BACKSLASH + videoParam.getFolderName() +
                "/").listFiles();

        File fileZip = new File(PathJfc.getPublicFilePath() + videoParam.getFolderName() + ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();
        zip.zipFolderFile(files, fileZip);

    }
}
