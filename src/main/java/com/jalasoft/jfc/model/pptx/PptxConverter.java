/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.IConverter;
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
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.utility.FolderRemover;
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
public class PptxConverter implements IConverter {

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
     * @param param receives param params.
     * @return Conversion status.
     * @throws CommandValueException when there is an invalid command.
     * @throws ConvertException when there is an invalid conversion.
     * @throws ZipJfcException when there is an invalid file path.
     * @throws IOException when there is an invalid input.
     */
    public FileResponse convert(Param param) throws CommandValueException, ConvertException, ZipJfcException, IOException {
        if (param == null) {
            throw new ConvertException("Parameter param is null", this.getClass().getName());
        }
        PptxParam pptxParam = (PptxParam) param;
        FileResponse fileResponse = new FileResponse();

        StringBuilder stringCommand = new StringBuilder();
        stringCommand.append(generatePdf(pptxParam));
        runCommand(stringCommand.toString());

        isPdfConversion(pptxParam);

        fileResponse.setName(pptxParam.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(zipPath);
        return fileResponse;
    }

    /**
     * Verifies if the conversion is just to Pdf.
     * @param pptxParam receives pptx params.
     * @throws ZipJfcException when there is an invalid file path.
     * @throws CommandValueException when there is an invalid command.
     * @throws ConvertException when there is an invalid conversion.
     * @throws IOException when there is an invalid input.
     */
    private void isPdfConversion(PptxParam pptxParam) throws ZipJfcException, CommandValueException, ConvertException
            , IOException {
       setFileName(pptxParam);
        if (pptxParam.getFileFormat().equals(PDF_EXTENSION)) {
            isMetadataTrue(pptxParam);
            isThumbnail(pptxParam);
            zipFile(pptxParam);
            FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
        } else {
            pptxParam.setInputPathFile(getNewInputPath(pptxParam));
        }
    }

    /**
     * Verifies if the conversion requires metadata.
     * @param pptxParam receives pptx params.
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
     * @param pptxParam receives pptx params.
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
     * @param pptxParam receives pptx params.
     * @return command concatenated.
     * @throws IOException when there is an invalid input.
     * @throws CommandValueException when there is an invalid command.
     */
    private String generateThumbnail(PptxParam pptxParam) throws IOException, CommandValueException {
        pptxParam.setInputPathFile(getNewInputPath(pptxParam));
        PDDocument doc = PDDocument.load(new File(pptxParam.getInputPathFile()));
        int quantityPages = doc.getNumberOfPages();
        doc.close();
        final String THUMBNAIL_KEY = "Thumb";

        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pptxParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pptxParam.getPagesToConvertThumbnail(), quantityPages));
        commandsList.add(new CommandThumbnail(pptxParam.getIsThumbnail()));
        commandsList.add(new CommandOutputFilePath(pptxParam.getOutputPathFile(), pptxParam.getFolderName()));
        commandsList.add(new CommandOutputFileName(pptxParam.getOutputName() + THUMBNAIL_KEY,
                pptxParam.getInputName() + THUMBNAIL_KEY));
        commandsList.add(new CommandImageFormat(pptxParam.getThumbnailFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * Gets the new input path to convert, rename and convert to images.
     * @param pptxParam receives pptx params.
     * @return the new input path.
     */
    private String getNewInputPath(PptxParam pptxParam) {
        String newInputPath = pptxParam.getOutputPathFile() + pptxParam.getFolderName() + SLASH + convertedName;
        return  newInputPath;
    }

    /**
     * Gets the original name of converted file.
     * @param pptxParam receives pptx params.
     * @return original name with file extension.
     * @throws CommandValueException when there is an invalid command.
     */
    private void setFileName(PptxParam pptxParam) throws CommandValueException {
        final String REGEX_SPECIAL_CHARACTERS = "[^a-zA-Z0-9.]";
        final String REGEX_REPLACE = "";

        isNameOutputNull(pptxParam);
        pptxParam.setOutputName(pptxParam.getOutputName().replaceAll(REGEX_SPECIAL_CHARACTERS, REGEX_REPLACE));
        if (!pptxParam.getOutputName().isEmpty()) {

            File converted = new File(pptxParam.getOutputPathFile() + pptxParam.getFolderName() + SLASH
                    + pptxParam.getInputName() + PDF_EXTENSION);

            File fileToRename = new File(pptxParam.getOutputPathFile() + pptxParam.getFolderName()
                    + SLASH + pptxParam.getOutputName() + PDF_EXTENSION);
            converted.renameTo(fileToRename);
            convertedName = pptxParam.getOutputName() + PDF_EXTENSION;

        } else {
            convertedName = pptxParam.getInputName() + PDF_EXTENSION;
        }
    }

    private void isNameOutputNull(PptxParam pptxParam) throws CommandValueException {
        if(pptxParam.getOutputName() == null){
            FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
            throw new CommandValueException(ErrorMessageJfc.OUTPUT_NAME_NULL.getErrorMessageJfc(), this.getClass()
                    .getName());
        }
    }

    /**
     * Generates a command to convert a pdf file.
     * @param pptxParam receives pptx params.
     * @throws CommandValueException when there is an invalid command.
     */
    private String generatePdf (PptxParam pptxParam) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandLibreOfficePath());
        commandsList.add(new CommandHeadless());
        commandsList.add(new CommandPdfConverter());
        commandsList.add(new CommandInputFilePath(pptxParam.getInputPathFile()));
        commandsList.add(new CommandOutDir());
        commandsList.add(new CommandOutputFilePath(pptxParam.getOutputPathFile(), pptxParam.getFolderName()));
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
            process.destroy();
        } catch (InterruptedException | IOException e) {
            throw new ConvertException(e.getMessage(), this.getClass().getName());
        }
    }

    /**
     * Zips a list of files.
     * @param pptxparam receives pptx params.
     * @throws ZipJfcException when there is an invalid file path.
     */
    private void zipFile(PptxParam pptxparam) throws ZipJfcException {
        ZipFolder zip = new ZipFolder();

        final String ZIP_TAG = ".zip";

        File[] files = new File(pptxparam.getOutputPathFile() + SLASH + pptxparam.getFolderName() +
                SLASH).listFiles();

        File fileZip = new File( PathJfc.getPublicFilePath() + pptxparam.getFolderName() + ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();
        zip.zipFolderFile(files, fileZip);
    }
}
