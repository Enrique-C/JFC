/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.FileResult;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.strategy.*;
import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

import java.io.IOException;
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

    // List of commands.
    List<ICommandStrategy> commandStrategyList = new ArrayList<>();

    /**
     * Changes an Image format to another one.
     * @param param Image parameters.
     * @return Conversion status.
     * @throws IOException when is a invalid file.
     * @throws ConvertException when the conversion failed.
     */
    public FileResult convert(Param param) throws ConvertException, IOException {
        ImageParam imageParam = (ImageParam) param;

        FileResult fileResult;

        String commandString;

        commandStrategyList.add(new CommandImageMagickPath());

        if (imageParam.isThumbnail()) {
            generateThumbnail(imageParam);
        }

        generateImage(imageParam);

        ContextStrategy comContext = new ContextStrategy(commandStrategyList);

        try {
            commandString = comContext.buildCommand();

            Runtime.getRuntime().exec(commandString);
            fileResult = new FileResult();
            fileResult.setPath(imageParam.getOutputPathFile());
        } catch (Exception e) {
            throw new ConvertException("The conversion Image failed", "Command image converter");
        }
        return fileResult;
    }

    /**
     * Generates a command to convert an image to another image.
     * @param imageParam receives image params.
     * @throws IOException when is a invalid file.
     */
    private void generateImage(ImageParam imageParam) throws IOException {
        commandStrategyList.add(new CommandImageAddCommand());

        commonCommandImage(imageParam);

        commandStrategyList.add(new CommandImageRotate(imageParam.getDegreesToRotate()));
        commandStrategyList.add(new CommandImageResize(imageParam.getImageWidth(), imageParam.getImageHeight()));
        commandStrategyList.add(new CommandOutputFilePath(imageParam.getOutputPathFile()));
        commandStrategyList.add(new CommandOutputFileName(imageParam.getOutputFileName()));
        commandStrategyList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }

    /**
     * Generates a command to convert an image to thumbnail.
     * @param imageParam receives image params.
     * @throws IOException when is a invalid file.
     */
    private void generateThumbnail(ImageParam imageParam) throws IOException {
        final String THUMBNAIL_TAG = "thumb01";

        commonCommandImage(imageParam);

        commandStrategyList.add(new CommandThumbnail(imageParam.isThumbnail()));
        commandStrategyList.add(new CommandOutputFilePath(imageParam.getOutputPathFile()));
        commandStrategyList.add(new CommandOutputFileName(THUMBNAIL_TAG));
        commandStrategyList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }

    /**
     * Generates a command common.
     * @param imageParam receives image params.
     * @throws IOException when is a invalid file.
     */
    private void commonCommandImage(ImageParam imageParam) throws IOException {
        commandStrategyList.add(new CommandImageConverter());
        commandStrategyList.add(new CommandInputFilePath(imageParam.getInputPathFile()));
    }
}
