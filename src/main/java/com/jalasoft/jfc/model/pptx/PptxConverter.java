/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.LibreOffice.CommandHeadless;
import com.jalasoft.jfc.model.command.LibreOffice.CommandLibreOfficePath;
import com.jalasoft.jfc.model.command.LibreOffice.CommandOutDir;
import com.jalasoft.jfc.model.command.LibreOffice.CommandPdfConverter;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.imagick.CommandImageAlpha;
import com.jalasoft.jfc.model.command.imagick.CommandImageBackground;
import com.jalasoft.jfc.model.command.imagick.CommandImageConverter;
import com.jalasoft.jfc.model.command.imagick.CommandImageDensity;
import com.jalasoft.jfc.model.command.imagick.CommandImageFormat;
import com.jalasoft.jfc.model.command.imagick.CommandImageMagickPath;
import com.jalasoft.jfc.model.command.imagick.CommandPagesToConvert;
import com.jalasoft.jfc.model.command.imagick.CommandThumbnail;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.utility.ZipFolder;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts pptx to pdf.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class PptxConverter {

    // List of commands.
    List<ICommandStrategy> commandsList;

    // Variable of CommandStrategy.
    ContextStrategy contextStrategy;

    // Assigns the zip's Path variable.
    private String zipPath;

    // Assigns the converted name.
    private String convertedName;

    // Constant slash.
    final String SLASH = "/";

    // Constant pdf extension.
    final String PDF_EXTENSION = ".pdf";

    /**
     * Changes an Pptx format to pdf or Image.
     * @param param pptx parameters.
     * @return Conversion status.
     * @throws CommandValueException when there is an invalid command.
     * @throws ConvertException when there is an invalid conversion.
     * @throws ZipJfcException when there is an invalid file path.
     */
    public FileResponse convert(Param param) throws CommandValueException, ConvertException, ZipJfcException,
            IOException {
        if (param == null) {
            throw new ConvertException("Parameter param is null", this.getClass().getName());
        }
        PptxParam pptxParam = (PptxParam) param;
        FileResponse fileResponse = new FileResponse();

        StringBuilder stringCommand = new StringBuilder();
        stringCommand.append(generatePdf(param));
        runCommand(stringCommand.toString());

        isPdfConversion(pptxParam);

        fileResponse.setName(pptxParam.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(zipPath);
        return fileResponse;
    }

    /**
     * Verifies if the conversion is just to Pdf.
     * @param pptxParam pptx parameters.
     * @throws ZipJfcException when there is an invalid file path.
     * @throws CommandValueException when there is an invalid command.
     * @throws ConvertException when there is an invalid conversion.
     * @throws IOException when there is an invalid input.
     */
    private void isPdfConversion(PptxParam pptxParam) throws ZipJfcException, CommandValueException, ConvertException
            , IOException {
        if (pptxParam.getFileFormat().equals(PDF_EXTENSION)) {
            isMetadataTrue(pptxParam);
            convertedName = getOriginalName(pptxParam);
            isThumbnail(pptxParam);
            zipFile(pptxParam);
        }
    }

    /**
     * Verifies if the conversion requires metadata.
     * @param pptxParam pptx parameters.
     * @throws ConvertException when there is an invalid conversion.
     */
    private void isMetadataTrue(PptxParam pptxParam) throws ConvertException {
        if (pptxParam.isMetadata()) {
            MetadataConverter metadataConverter = new MetadataConverter();
            metadataConverter.convert(pptxParam);
        }
    }

    /**
     * Verifies if the conversion requires thumbnail.
     * @param pptxParam pptx parameters.
     * @throws IOException when there is an invalid input.
     * @throws CommandValueException when there is an invalid command.
     * @throws ConvertException when there is an invalid conversion.
     */
    private void isThumbnail(PptxParam pptxParam) throws IOException, CommandValueException, ConvertException {
        if (pptxParam.getIsThumbnail()){
            StringBuilder stringCommand = new StringBuilder();
            stringCommand.append(generateThumbnail(pptxParam));
            runCommand(stringCommand.toString());
        }
    }

    /**
     * Generates thumbnail string command.
     * @param pptxParam pptx parameters.
     * @return command concatenated.
     * @throws IOException when there is an invalid input.
     * @throws CommandValueException when there is an invalid command.
     */
    private String generateThumbnail(PptxParam pptxParam) throws IOException, CommandValueException {
        pptxParam.setInputPathFile(getNewInputPath(pptxParam));
        PDDocument doc = PDDocument.load(new File(pptxParam.getInputPathFile()));
        int quantityPages = doc.getNumberOfPages();

        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pptxParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pptxParam.getPagesToConvert(), quantityPages));
        commandsList.add(new CommandThumbnail(pptxParam.getIsThumbnail()));
        commandsList.add(new CommandOutputFilePath(pptxParam.getOutputPathFile(), pptxParam.getFolderName()));
        commandsList.add(new CommandOutputFileName(pptxParam.getOutputName() + "_t",
                pptxParam.getFolderName() + "_t"));
        commandsList.add(new CommandImageFormat(pptxParam.getThumbnailFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * Gets the new input path to convert to rename and convert to images.
     * @param pptxParam pdf parameters.
     * @return the new input path.
     */
    private String getNewInputPath(PptxParam pptxParam) {
        String newInputPath = pptxParam.getOutputPathFile() + pptxParam.getFolderName() + SLASH + convertedName;
        return  newInputPath;
    }

    /**
     * Gets the original name of converted file.
     * @param pptxParam pdf parameters.
     * @return the original name with its extension.
     */
    private String getOriginalName(PptxParam pptxParam) {
        File fileOriginalName = new File(pptxParam.getInputPathFile());
        String regex = "[.][^.]+$";
        final String REPLACE_REGEX = "";

        String name = fileOriginalName.getName().replaceFirst(regex,REPLACE_REGEX) + PDF_EXTENSION;
        if (!pptxParam.getOutputName().isEmpty() && !pptxParam.getOutputName().equals(null)) {
            File converted = new File(pptxParam.getOutputPathFile() + pptxParam.getFolderName() + SLASH + name);

            File fileToRename = new File(pptxParam.getOutputPathFile() + pptxParam.getFolderName() + SLASH +
                    pptxParam.getOutputName() + PDF_EXTENSION);
            converted.renameTo(fileToRename);

            name = fileToRename.getName();
        } else {
            pptxParam.setOutputName(fileOriginalName.getName().replaceFirst(regex, REPLACE_REGEX));
            name = pptxParam.getOutputName() + PDF_EXTENSION;
        }
        return name;
    }

    /**
     * Generates a command to convert an pdf file.
     * @param param receives image params.
     * @throws CommandValueException when there is an invalid command.
     */
    private String generatePdf (Param param) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandLibreOfficePath());
        commandsList.add(new CommandHeadless());
        commandsList.add(new CommandPdfConverter());
        commandsList.add(new CommandInputFilePath(param.getInputPathFile()));
        commandsList.add(new CommandOutDir());
        commandsList.add(new CommandOutputFilePath(param.getOutputPathFile(), param.getFolderName()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @throws ConvertException when the conversion was not completed.
     */
    private void runCommand (String stringCommand) throws ConvertException {
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new ConvertException(e.getMessage(), this.getClass().getName());
        }
    }

    /**
     * Zips a list of files.
     * @param param receives Param.
     * @throws ZipJfcException when there is an invalid file path.
     */
    private void zipFile(Param param) throws ZipJfcException {
        ZipFolder zip = new ZipFolder();

        final String ZIP_TAG = ".zip";

        File[] files = new File(param.getOutputPathFile() + SLASH + param.getFolderName() +
                SLASH).listFiles();

        File fileZip = new File( PathJfc.getPublicFilePath() + param.getFolderName() + ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();
        zip.zipFolderFile(files, fileZip);
    }
}
