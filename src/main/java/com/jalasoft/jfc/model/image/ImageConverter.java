/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.imagick.CommandImageGrayscale;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.command.imagick.CommandImageConverter;
import com.jalasoft.jfc.model.command.imagick.CommandImageFormat;
import com.jalasoft.jfc.model.command.imagick.CommandImageMagickPath;
import com.jalasoft.jfc.model.command.imagick.CommandImageResize;
import com.jalasoft.jfc.model.command.imagick.CommandImageRotate;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.imagick.CommandThumbnail;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ZipFolder;

import java.io.File;
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

    // Tag thumbnail.
    final String THUMBNAIL_TAG = "thumb";

    // Absolute path of zip folder.
    String zipPath;

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

        FileResponse fileResponse = new FileResponse();

        String commandString;

        if (imageParam.isThumbnail()) {
            generateThumbnail(imageParam);
        }

        generateImage(imageParam);
        ContextStrategy commandImageContext = new ContextStrategy(commandImageList);

        try {
            commandString = commandImageContext.buildCommand();

            Process imageConvertProcess = Runtime.getRuntime().exec(commandString);
            imageConvertProcess.waitFor();


            if (!commandThumbnailList.isEmpty()) {
                ContextStrategy commandThumbnailContext = new ContextStrategy(commandThumbnailList);
                commandString = commandThumbnailContext.buildCommand();
                Process thumbnailConvertProcess = Runtime.getRuntime().exec(commandString);
                thumbnailConvertProcess.waitFor();
            }

            if (param.isMetadata()) {
                MetadataConverter metadataConverter = new MetadataConverter();
                metadataConverter.convert(param);
            }

            zipFile(imageParam);

            fileResponse.setName(imageParam.getOutputName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
            fileResponse.setDownload(zipPath);
        } catch (Exception e) {
            throw new ConvertException("Error converting Image: " + e.getMessage(), this.getClass().getName());
        }
        return fileResponse;
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
        commandImageList.add(new CommandImageGrayscale(imageParam.isGrayscale()));
        commandImageList.add(new CommandImageRotate(imageParam.getDegreesToRotate()));
        commandImageList.add(new CommandImageResize(imageParam.getImageWidth(), imageParam.getImageHeight()));
        commandImageList.add(new CommandOutputFilePath(imageParam.getOutputPathFile(), imageParam.getFolderName()));
        commandImageList.add(new CommandOutputFileName(imageParam.getOutputName(), imageParam.getOutputName()));
        commandImageList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }

    /**
     * Generates a command to convert an image to thumbnail.
     * @param imageParam receives image params.
     * @throws CommandValueException when is a invalid command.
     */
    private void generateThumbnail(ImageParam imageParam) throws CommandValueException {
        commandThumbnailList.add(new CommandImageMagickPath());
        commandThumbnailList.add(new CommandImageConverter());
        commandThumbnailList.add(new CommandInputFilePath(imageParam.getInputPathFile()));
        commandThumbnailList.add(new CommandThumbnail(imageParam.isThumbnail()));
        commandThumbnailList.add(new CommandOutputFilePath(imageParam.getOutputPathFile(), imageParam.getFolderName()));
        commandThumbnailList.add(new CommandOutputFileName(THUMBNAIL_TAG, imageParam.getFolderName()));
        commandThumbnailList.add(new CommandImageFormat(imageParam.getImageFormat()));
    }

    /**
     * Zips a folder of images.
     * @param imageParam receives image params.
     * @throws IOException when is a invalid file path.
     */
    private void zipFile(ImageParam imageParam) throws IOException {
        ZipFolder zip = new ZipFolder();

        final String BACKSLASH = "/";
        final String ZIP_TAG = ".zip";

        File[] files = new File(imageParam.getOutputPathFile() + BACKSLASH + imageParam.getFolderName() +
                BACKSLASH).listFiles();

        File fileZip = new File(PathJfc.getPublicFilePath() + BACKSLASH + imageParam.getFolderName() +
                ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();

        zip.zipFolderFile(files, fileZip);
    }
}
