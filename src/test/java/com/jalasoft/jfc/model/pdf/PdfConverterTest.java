/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Executes pdfConvert's unit tests.
 *
 * @version 0.1 06 Jan 2020.
 *
 * @author Juan Martinez.
 */
public class PdfConverterTest {

    @Test
    public void convert_PdfFileToImage() throws ConvertException, ZipJfcException, CommandValueException, IOException {
        PdfConverter pdfConverter = new PdfConverter();
        PdfParam pdfParam = generatePdfParamObject();
        String zipPdfFile = pdfConverter.convert(pdfParam).getDownload();
        final int EMPTY_BYTES = 0;
        File zipFileGenerated = new File(zipPdfFile);
        boolean expected = zipFileGenerated.exists() && zipFileGenerated.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test
    public void convert_PdfFileToImageWithThumbnail() throws ConvertException, ZipJfcException, CommandValueException,
            IOException {
        PdfConverter pdfConverter = new PdfConverter();
        PdfParam pdfParam = generatePdfParamObjectThumbnail();
        String zipPdfFile = pdfConverter.convert(pdfParam).getDownload();
        final int EMPTY_BYTES = 0;
        File zipFileGenerated = new File(zipPdfFile);
        boolean expected = zipFileGenerated.exists() && zipFileGenerated.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test
    public void convert_PdfFileToImageWithMetadata() throws ConvertException, ZipJfcException, CommandValueException,
            IOException {
        PdfConverter pdfConverter = new PdfConverter();
        PdfParam pdfParam = generatePdfParamObjectMetadata();
        String zipPdfFile = pdfConverter.convert(pdfParam).getDownload();
        final int EMPTY_BYTES = 0;
        File zipFileGenerated = new File(zipPdfFile);
        boolean expected = zipFileGenerated.exists() && zipFileGenerated.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test(expected = ConvertException.class)
    public void convertPdfFileToImageWithNullObjectValue() throws ConvertException, ZipJfcException,
            CommandValueException, IOException {
            PdfConverter pdfConverter = new PdfConverter();
            PathJfc pathJfc = new PathJfc();
            PdfParam pdfParam = null;
            String zipPdfFile = pdfConverter.convert(pdfParam).getDownload();
    }

    /**
     * Generates complete pdf param object.
     * @return PdfParam object.
     */
    private PdfParam generatePdfParamObject() {
        PathJfc pathJfc = new PathJfc();
        String pathOfUploadedFile = "src/test/resources/pdf.pdf";
        String outputPath = "src/test/resources/";
        String outputName = "fileConverted";
        String imageFormat = ".jpg";
        String pagesToConvert = "2";
        int quantityOfPages = 4;
        int width = 0;
        int height = 0;
        String scale = "%";
        int rotate = 0;
        String md5 = "8bd6509aba6eafe623392995b08c7047";
        boolean isThumbnail = false;
        boolean isMetadata = false;

        PdfParam pdfParam = new PdfParam();
        pdfParam.setMd5(md5);
        pdfParam.setInputPathFile(pathOfUploadedFile);
        pdfParam.setOutputPathFile(outputPath);
        pdfParam.setPagesToConvert(pagesToConvert);
        pdfParam.setQuantityOfPage(quantityOfPages);
        pdfParam.setHeight(height);
        pdfParam.setWidth(width);
        pdfParam.setScale(scale);
        pdfParam.setRotate(rotate);
        pdfParam.setImageFormat(imageFormat);
        pdfParam.setOutputName(outputName);
        pdfParam.setThumbnail(isThumbnail);
        pdfParam.setFolderName(md5);
        pdfParam.isMetadata(isMetadata);
        return pdfParam;
    }

    /**
     * Generates pdf param object with thumbnail.
     * @return PdfParam object.
     */
    private PdfParam generatePdfParamObjectThumbnail() {
        PathJfc pathJfc = new PathJfc();
        String pathOfUploadedFile = "src/test/resources/pdf.pdf";
        String outputPath = "src/test/resources/";
        String outputName = "filethumbnail";
        String imageFormat = ".jpg";
        String pagesToConvert = "2";
        int quantityOfPages = 4;
        int width = 0;
        int height = 0;
        String scale = "%";
        int rotate = 0;
        String md5 = "8bd6509aba6eafe623392995b08c7047";
        boolean isThumbnail = true;
        boolean isMetadata = false;

        PdfParam pdfParam = new PdfParam();
        pdfParam.setMd5(md5);
        pdfParam.setInputPathFile(pathOfUploadedFile);
        pdfParam.setOutputPathFile(outputPath);
        pdfParam.setPagesToConvert(pagesToConvert);
        pdfParam.setQuantityOfPage(quantityOfPages);
        pdfParam.setHeight(height);
        pdfParam.setWidth(width);
        pdfParam.setScale(scale);
        pdfParam.setRotate(rotate);
        pdfParam.setImageFormat(imageFormat);
        pdfParam.setOutputName(outputName);
        pdfParam.setThumbnail(isThumbnail);
        pdfParam.setFolderName(md5);
        pdfParam.isMetadata(isMetadata);
        return pdfParam;
    }

    /**
     * Generates pdf param object with metadata.
     * @return PdfParam object.
     */
    private PdfParam generatePdfParamObjectMetadata() {
        PathJfc pathJfc = new PathJfc();
        String pathOfUploadedFile = "src/test/resources/pdf.pdf";
        String outputPath = "src/test/resources/";
        String outputName = "filemetadata";
        String imageFormat = ".png";
        String pagesToConvert = "2";
        int quantityOfPages = 4;
        int width = 0;
        int height = 0;
        String scale = "%";
        int rotate = 0;
        String md5 = "8bd6509aba6eafe623392995b08c7047";
        boolean isThumbnail = false;
        boolean isMetadata = true;

        PdfParam pdfParam = new PdfParam();
        pdfParam.setMd5(md5);
        pdfParam.setInputPathFile(pathOfUploadedFile);
        pdfParam.setOutputPathFile(outputPath);
        pdfParam.setPagesToConvert(pagesToConvert);
        pdfParam.setQuantityOfPage(quantityOfPages);
        pdfParam.setHeight(height);
        pdfParam.setWidth(width);
        pdfParam.setScale(scale);
        pdfParam.setRotate(rotate);
        pdfParam.setImageFormat(imageFormat);
        pdfParam.setOutputName(outputName);
        pdfParam.setThumbnail(isThumbnail);
        pdfParam.setFolderName(md5);
        pdfParam.isMetadata(isMetadata);
        return pdfParam;
    }

    private PdfParam generatePdfParamObjectNull() {
        PathJfc pathJfc = new PathJfc();
        String pathOfUploadedFile = null;
        String outputPath = null;
        String outputName = null;
        String imageFormat = ".png";
        String pagesToConvert = "2";
        int quantityOfPages = 4;
        int width = 0;
        int height = 0;
        String scale = "%";
        int rotate = 0;
        String md5 = null;
        boolean isThumbnail = false;
        boolean isMetadata = true;

        PdfParam pdfParam = new PdfParam();
        pdfParam.setMd5(md5);
        pdfParam.setInputPathFile(pathOfUploadedFile);
        pdfParam.setOutputPathFile(outputPath);
        pdfParam.setPagesToConvert(pagesToConvert);
        pdfParam.setQuantityOfPage(quantityOfPages);
        pdfParam.setHeight(height);
        pdfParam.setWidth(width);
        pdfParam.setScale(scale);
        pdfParam.setRotate(rotate);
        pdfParam.setImageFormat(imageFormat);
        pdfParam.setOutputName(outputName);
        pdfParam.setThumbnail(isThumbnail);
        pdfParam.setFolderName(md5);
        pdfParam.isMetadata(isMetadata);
        return pdfParam;
    }
}
