/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandAudioBitRate;
import com.jalasoft.jfc.model.command.ffmpeg.CommandAudioFormat;
import com.jalasoft.jfc.model.command.ffmpeg.CommandFFMpegPath;
import com.jalasoft.jfc.model.command.ffmpeg.CommandVideoFormat;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.utility.FolderRemover;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ZipFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts an audio format to another audio format.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class AudioConverter implements IConverter {

    // Absolute path of zip folder.
    String zipPath;

    // List of audio command.
    List<ICommandStrategy> commandAudioList = new ArrayList<>();

    /**
     * Changes an Audio format to another one.
     * @param param it receives file params.
     * @return FileResponse object.
     * @throws ConvertException when the conversion failed.
     * @throws CommandValueException when is an invalid command.
     * @throws IOException when there is a wrong input.
     * @throws ZipJfcException when zip process was wrong.
     */
    @Override
    public FileResponse convert(Param param) throws ConvertException, CommandValueException, ZipJfcException {
        if (param == null) {
            throw new ConvertException(ErrorMessageJfc.AUDIO_CONVERT_ERROR_MESSAGE.getErrorMessageJfc(), this.getClass().getName());
        }

        AudioParam audioParam = (AudioParam) param;
        FileResponse fileResponse = new FileResponse();

        String commandString;

        generateAudio(audioParam);

        ContextStrategy commandAudioContext = new ContextStrategy(commandAudioList);

        try {
            commandString = commandAudioContext.buildCommand();

            Process audioConvertProcess = Runtime.getRuntime().exec(commandString);
            audioConvertProcess.waitFor();

            if (param.isMetadata()) {
                MetadataConverter metadataConverter = new MetadataConverter();
                metadataConverter.convert(param);
            }

            zipFile(audioParam);

            FolderRemover.removeFolder(audioParam.getOutputPathFile() + audioParam.getFolderName());

            fileResponse.setName(audioParam.getOutputName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
            fileResponse.setDownload(zipPath);
        } catch (Exception e) {
            throw new ConvertException("Error converting Audio: " + e.getMessage(), this.getClass().getName());
        }
        return fileResponse;
    }

    /**
     * Generates commands to convert an audio to another audio.
     * @param audioParam receives audio params.
     */
    private void generateAudio(AudioParam audioParam) {
        commandAudioList.add(new CommandFFMpegPath());
        commandAudioList.add(new CommandInputFilePath(audioParam.getInputPathFile()));
        commandAudioList.add(new CommandAudioBitRate(audioParam.getAudioBitRate()));
        commandAudioList.add(new CommandOutputFilePath(audioParam.getOutputPathFile(), audioParam.getFolderName()));
        commandAudioList.add(new CommandOutputFileName(audioParam.getOutputName(), audioParam.getFolderName()));
        commandAudioList.add(new CommandAudioFormat(audioParam.getAudioFormat()));
    }

    /**
     * Zips a folder of audio.
     * @param audioParam receives audio params.
     * @throws ZipJfcException when is a invalid zip path.
     */
    private void zipFile(AudioParam audioParam) throws ZipJfcException {
        ZipFolder zip = new ZipFolder();

        final String BACKSLASH = "/";
        final String ZIP_TAG = ".zip";

        File[] files = new File(audioParam.getOutputPathFile() + BACKSLASH + audioParam.getFolderName() +
                BACKSLASH).listFiles();

        File fileZip = new File(PathJfc.getPublicFilePath() + BACKSLASH + audioParam.getFolderName() +
                ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();

        zip.zipFolderFile(files, fileZip);
    }
}
