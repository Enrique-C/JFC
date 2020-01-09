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
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;

import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.utility.FileServiceController;
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

    // Constant backslash.
    final String BACKSLASH = "/";

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
        PdfParam pdfParam = (PdfParam) param;
        PdfConverter pdfConverter = new PdfConverter();
        FileResponse fileResponse = new FileResponse();

        StringBuilder stringCommand = new StringBuilder();
        stringCommand.append(generatePdf(param));
        runCommand(stringCommand.toString());
        convertedName = getOriginalName(pdfParam);

        if (pdfParam.isThumbnail()){
            setWhenPptxToPdf(pdfParam);
            stringCommand = new StringBuilder();
            stringCommand.append(pdfConverter.generateThumbnail(pdfParam));
            runCommand(stringCommand.toString());
        }

        zipFile(pdfParam);

        fileResponse.setName(param.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(zipPath);
        return fileResponse;
    }

    /**
     * Sets When converts pptx to pdf.
     * @param pdfParam pdf parameters.
     * @throws IOException when there is an invalid file path.
     */
    private void setWhenPptxToPdf(PdfParam pdfParam) throws IOException {
        if (pdfParam.getFileFormat().equals(PDF_EXTENSION)) {
            pdfParam.setInputPathFile(getNewInputPath(pdfParam));

            PDDocument doc = PDDocument.load(new File(pdfParam.getInputPathFile()));

            int quantityPages = doc.getNumberOfPages();
            
            pdfParam.setQuantityOfPage(quantityPages);
        }
    }

    /**
     * Gets the new input path to convert to rename and convert to images.
     * @param pdfParam pdf parameters.
     * @return the new input path.
     */
    private String getNewInputPath(PdfParam pdfParam) {
        String newInputPath = pdfParam.getOutputPathFile() + pdfParam.getFolderName() + BACKSLASH + convertedName;
        return  newInputPath;
    }

    /**
     * Gets the original name of the file converted.
     * @param pdfParam pdf parameters.
     * @return the original name with its extension.
     */
    private String getOriginalName(PdfParam pdfParam) {
        File fileOriginalName = new File(pdfParam.getInputPathFile());
        String regex = "[.][^.]+$";
        final String REPLACE_REGEX = "";

        String name = fileOriginalName.getName().replaceFirst(regex,REPLACE_REGEX) + PDF_EXTENSION;
        if (!pdfParam.getOutputName().isEmpty() && !pdfParam.getOutputName().equals(null)) {
            File converted = new File(pdfParam.getOutputPathFile()+ pdfParam.getFolderName() + BACKSLASH
                    + name);

            File fileToRename = new File(pdfParam.getOutputPathFile()+ pdfParam.getFolderName() + BACKSLASH +
                    pdfParam.getOutputName()+ PDF_EXTENSION);
            converted.renameTo(fileToRename);

            name = fileToRename.getName();
        }else{
            pdfParam.setOutputName(fileOriginalName.getName().replaceFirst(regex,REPLACE_REGEX));
            name = pdfParam.getOutputName() + PDF_EXTENSION;
        }
        return name;
    }

    /**
     * Generates a command to convert an pdf file.
     * @param param receives image params.
     * @throws CommandValueException when there is an invalid command.
     */
    private String generatePdf ( Param param) throws CommandValueException {
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

        File[] files = new File(param.getOutputPathFile() + BACKSLASH + param.getFolderName() +
                BACKSLASH).listFiles();

        File fileZip = new File( PathJfc.getPublicFilePath() + param.getFolderName() + ZIP_TAG);

        zipPath = fileZip.getAbsolutePath();
        zip.zipFolderFile(files, fileZip);
    }
}
