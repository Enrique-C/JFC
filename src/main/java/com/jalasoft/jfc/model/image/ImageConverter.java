/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.strategy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts a image type to another.
 *
 * @version 0.1 11 Dec 2019.
 *
 * @author Oscar Lopez.
 * */
public class ImageConverter implements IConverter {

    // List of image command.
    List<ICommandStrategy> commandImageList = new ArrayList<>();

    // List of thumbnail commands.
    List<ICommandStrategy> commandThumbnailList = new ArrayList<>();

    /**
     * Changes an Image format to another one.
     * @param param Image parameters.
     * @return Conversion status.
     * @throws CommandValueException when is a invalid command.
     * @throws ConvertException when the conversion failed.
     */
    public FileResponse convert(Param param) throws ConvertException, CommandValueException {
        ImageParam imageParam = (ImageParam) param;

        FileResponse fileResult;

        String commandString;

        if (imageParam.isThumbnail()) {
            generateThumbnail(imageParam);
        }

        generateImage(imageParam);
        ContextStrategy commandImageContext = new ContextStrategy(commandImageList);

        try {
            commandString = commandImageContext.buildCommand();

            Runtime.getRuntime().exec(commandString);

            if (!commandThumbnailList.isEmpty()) {
                ContextStrategy commandThumbnailContext = new ContextStrategy(commandThumbnailList);
                commandString = commandThumbnailContext.buildCommand();
                Runtime.getRuntime().exec(commandString);
            }

            fileResult = new FileResponse();
            fileResult.setDownload(imageParam.getOutputPathFile());
        } catch (Exception e) {
            throw new ConvertException("The conversion Image failed", "Command image converter");
        }
        return fileResult;
    }

    /**
     * Generates a command to convert an image to another image.
     * @param imageParam receives image params.
     * @throws CommandValueException when is a invalid command.
     */
    private void generateImage(ImageParam imageParam) throws CommandValueException {
        commandImageList.add(new CommandImageMagickPath());
        commandImageList.add(new CommandImageConverter());
        commandImageList.add(new CommandInputFilePath(imageParam.getInputPathFile()));
        commandImageList.add(new CommandImageRotate(imageParam.getDegreesToRotate()));
        commandImageList.add(new CommandImageResize(imageParam.getImageWidth(), imageParam.getImageHeight()));
        commandImageList.add(new CommandOutputFilePath(imageParam.getOutputPathFile(), imageParam.getInputFileName()));
        commandImageList.add(new CommandOutputFileName(imageParam.getOutputFileName(), imageParam.getInputFileName()));
        commandImageList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }

    /**
     * Generates a command to convert an image to thumbnail.
     * @param imageParam receives image params.
     * @throws CommandValueException when is a invalid command.
     */
    private void generateThumbnail(ImageParam imageParam) throws CommandValueException {
        final String THUMBNAIL_TAG = "thumb01";

        commandThumbnailList.add(new CommandImageMagickPath());
        commandThumbnailList.add(new CommandImageConverter());
        commandThumbnailList.add(new CommandInputFilePath(imageParam.getInputPathFile()));
        commandThumbnailList.add(new CommandThumbnail(imageParam.isThumbnail()));
        commandThumbnailList.add(new CommandOutputFilePath(imageParam.getOutputPathFile(), imageParam.getInputFileName()));
        commandThumbnailList.add(new CommandOutputFileName(THUMBNAIL_TAG, imageParam.getInputFileName()));
        commandThumbnailList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }
}
