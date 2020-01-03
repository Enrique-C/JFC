/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.model.image.ImageParam;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.imagick.CommandImageMagickPath;
import com.jalasoft.jfc.model.command.imagick.CommandImageConverter;
import com.jalasoft.jfc.model.command.imagick.CommandImageDensity;
import com.jalasoft.jfc.model.command.imagick.CommandImageAlpha;
import com.jalasoft.jfc.model.command.imagick.CommandImageBackground;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.imagick.CommandPagesToConvert;
import com.jalasoft.jfc.model.command.imagick.CommandImageResize;
import com.jalasoft.jfc.model.command.common.CommandScale;
import com.jalasoft.jfc.model.command.imagick.CommandThumbnail;
import com.jalasoft.jfc.model.command.imagick.CommandImageRotate;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.imagick.CommandImageFormat;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ZipFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class converts PDF to Image
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfConverter implements IConverter {

    // List of commands.
    List<ICommandStrategy> commandsList;

    // Variable of CommandStrategy.
    ContextStrategy contextStrategy;

    /**
     * This method converts a PDF to Image.
     * @param param
     * @return FileResult object or null value.
     * @throws CommandValueException when is a invalid command.
     * @throws ConvertException when the conversion was not completed.
     */
    public FileResponse convert(Param param) throws CommandValueException, ConvertException, IOException {
        FileResponse fileResponse = new FileResponse();
        PdfParam pdfParam = (PdfParam)param;
        StringBuilder stringCommand = new StringBuilder();
        if (!pdfParam.isThumbnail() && !pdfParam.isMetadata()) {
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
        }
        if (pdfParam.isThumbnail()) {
            stringCommand = new StringBuilder();
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
            stringCommand = new StringBuilder();
            stringCommand.append(generateThumbnail(pdfParam));
            runCommand(stringCommand.toString());
        }
        if (pdfParam.isMetadata()) {
            stringCommand = new StringBuilder();
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
            generateMetadata(pdfParam);
        }
        System.out.println(stringCommand);
        fileResponse.setName(pdfParam.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(pdfParam.getOutputPathFile()+pdfParam.getOutputName());
        zipFile(pdfParam);
        return fileResponse;
    }

    /**
     * This method is for generate image string command.
     * @param pdfParam
     * @return command concatenated.
     * @throws CommandValueException
     */
    private String generateImage(PdfParam pdfParam) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pdfParam.getPagesToConvert(), pdfParam.getQuantityOfPage()));
        commandsList.add(new CommandImageResize(pdfParam.getWidth(), pdfParam.getHeight()));
        commandsList.add(new CommandScale(pdfParam.getScale()));
        commandsList.add(new CommandImageRotate(pdfParam.getRotate()));
        commandsList.add(new CommandOutputFilePath(pdfParam.getOutputPathFile(), pdfParam.getFolderName()));
        commandsList.add(new CommandOutputFileName(pdfParam.getOutputName(), pdfParam.getFolderName()));
        commandsList.add(new CommandImageFormat(pdfParam.getImageFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * This method is for generate thumbnail string command.
     * @param pdfParam
     * @return command concatenated.
     * @throws CommandValueException
     */
    private String generateThumbnail(PdfParam pdfParam) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pdfParam.getPagesToConvert(), pdfParam.getQuantityOfPage()));
        commandsList.add(new CommandThumbnail(pdfParam.isThumbnail()));
        commandsList.add(new CommandOutputFilePath(pdfParam.getOutputPathFile(), pdfParam.getFolderName()));
        commandsList.add(new CommandOutputFileName(pdfParam.getOutputName() + "_t",
                pdfParam.getFolderName() + "_t"));
        commandsList.add(new CommandImageFormat(pdfParam.getImageFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * This method generate metadata of input file.
     * @param pdfParam
     */
    private void generateMetadata(PdfParam pdfParam) {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        // execute XMP or use XMP.
    }

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     * @throws ConvertException when the conversion was not completed.
     */
    private void runCommand(String stringCommand) throws ConvertException {
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new ConvertException(e.getMessage(), this.getClass().getName());
        }
    }

    private void zipFile(PdfParam pdfParam) throws IOException {
        PathJfc pathJfc = new PathJfc();
        final String ZIP = ".zip";
        File[] files = new File(pdfParam.getOutputPathFile() + "/" + pdfParam.getFolderName() +
                "/").listFiles();

        File fileZip = new File( pathJfc.getPublicFilePath() + "/" + pdfParam.getOutputName() + ZIP);

        ZipFolder zip = new ZipFolder();
        zip.zipFolderFile(files, fileZip);
    }
}
