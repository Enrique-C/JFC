/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.FolderRemover;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Executes PptxConvert's unit tests.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class PptxConverterTest {

    @Test
    public void Convert_PptxTo_pdf() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        PptxConverter pptxConverter = new PptxConverter();
        PptxParam pptxParam = getParamsPptxToPdf();

        String zipPptx = pptxConverter.convert(pptxParam).getDownload();
        final int EMPTY_BYTES = 0;

        File zipFile = new File(zipPptx);
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test(expected = ConvertException.class)
    public void Convert_WhenPptxParamIsNull_ConvertException() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        PptxConverter pptxConverter = new PptxConverter();
        PptxParam pptxParam = null;
        PdfConverter pdfConverter = new PdfConverter();
        String zipPdfFile = pdfConverter.convert(pptxParam).getDownload();
    }

    @Test
    public void Convert_PptxTo_image() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        PptxConverter pptxConverter = new PptxConverter();
        PdfConverter pdfConverter = new PdfConverter();
        PptxParam pptxParam = getParamsPptxToImage();

        pptxConverter.convert(pptxParam);
        PdfParam pdfParam = getParamsPdfToImage(pptxParam);
        String zipPptx = pdfConverter.convert(pdfParam).getDownload();
        final int EMPTY_BYTES = 0;

        File zipFile = new File(zipPptx);
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test(expected = CommandValueException.class)
    public void Convert_WhenOuputNameIsNull_CommandValueException() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        PptxConverter pptxConverter = new PptxConverter();
        PptxParam pptxParam = getParamsPptxToPdfWithOutputNameNull();
        String zipPptx = pptxConverter.convert(pptxParam).getDownload();
    }

    private PptxParam getParamsPptxToPdf() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/Designpatters.pptx";

        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String outputPath = "src/test/resources/";
        String inputName = "Designpatters";
        String outputName = "pruebapptxtopdf";
        String fileFormat = ".pdf";
        String thumbnailFormat = ".png";
        String pages = "";
        boolean thumbnail = true;
        PptxParam pptxParam = new PptxParam();

        pptxParam.setMd5(md5);
        pptxParam.setInputPathFile(fileUploadedPath);
        pptxParam.setOutputPathFile(outputPath);
        pptxParam.setInputName(inputName);
        pptxParam.setFolderName(md5);
        pptxParam.setOutputName(outputName);
        pptxParam.setFileFormat(fileFormat);
        pptxParam.setPagesToConvertThumbnail(pages);
        pptxParam.setIsThumbnail(thumbnail);
        pptxParam.setThumbnailFormat(thumbnailFormat);
        pptxParam.isMetadata(true);

        return pptxParam;
    }

    private PptxParam getParamsPptxToPdfWithOutputNameNull() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/Designpatters.pptx";

        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String outputPath = "src/test/resources/";
        String inputName = "Designpatters";
        String outputName = null;
        String fileFormat = ".pdf";
        String thumbnailFormat = ".png";
        String pages = "";
        boolean thumbnail = true;
        PptxParam pptxParam = new PptxParam();

        pptxParam.setMd5(md5);
        pptxParam.setInputPathFile(fileUploadedPath);
        pptxParam.setOutputPathFile(outputPath);
        pptxParam.setInputName(inputName);
        pptxParam.setFolderName(md5);
        pptxParam.setOutputName(outputName);
        pptxParam.setFileFormat(fileFormat);
        pptxParam.setPagesToConvertThumbnail(pages);
        pptxParam.setIsThumbnail(thumbnail);
        pptxParam.setThumbnailFormat(thumbnailFormat);
        pptxParam.isMetadata(true);

        return pptxParam;
    }

    private PptxParam getParamsPptxToImage() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/Designpatters.pptx";
        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String outputPath = "src/test/resources/";
        String inputName = "Designpatters";
        String outputName = "pruebapptxtoimage";
        String fileFormat = ".jpg";

        PptxParam pptxParam = new PptxParam();

        pptxParam.setInputPathFile(fileUploadedPath);
        pptxParam.setFileFormat(fileFormat);
        pptxParam.setMd5(md5);
        pptxParam.setFolderName(md5);
        pptxParam.setInputName(inputName);
        pptxParam.setOutputName(outputName);
        pptxParam.setOutputPathFile(outputPath);

        return pptxParam;
    }

    private PdfParam getParamsPdfToImage(PptxParam pptxParam) {
        PathJfc pathJfc = new PathJfc();
        PdfParam pdfParam = new PdfParam();

        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String outputPath = "src/test/resources/";
        String inputName = "Designpatters";
        String outputName = "pruebapptxtoimage";
        String fileFormat = ".jpg";
        String thumbnailFormat = ".png";
        String pages = "1-3";
        boolean thumbnail = true;
        boolean isMetadata = false;
        int width = 0;
        String scale = "%";
        int height = 0;
        int rotate = 0;

        pdfParam.setMd5(md5);
        pdfParam.setInputPathFile(pptxParam.getInputPathFile());
        pdfParam.setOutputPathFile(outputPath);
        pdfParam.setInputName(inputName);
        pdfParam.setOutputName(outputName);
        pdfParam.setImageFormat(thumbnailFormat);
        pdfParam.setFileFormat(fileFormat);
        pdfParam.setPagesToConvert(pages);
        pdfParam.setThumbnail(thumbnail);
        pdfParam.isMetadata(isMetadata);
        pdfParam.setWidth(width);
        pdfParam.setScale(scale);
        pdfParam.setHeight(height);
        pdfParam.setRotate(rotate);
        pdfParam.setFolderName(md5);

        return pdfParam;
    }
}
