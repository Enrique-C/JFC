/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Tests ImageConverter class.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class ImageConverterTest {

    final long EMPTY_BYTES = 22;
    long convertedImageSize;

    @Test
    public void convert_ImagePNGToJPG_Zip() throws ConvertException, CommandValueException {
        ImageConverter imageConverter = new ImageConverter();
        ImageParam imageParam = generateOnlyImageJPG();

        String zipImagePath = imageConverter.convert(imageParam).getDownload();

        File zipFile = new File(zipImagePath);
        convertedImageSize = zipFile.length();
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test
    public void convert_ImagePNBToJPGAndThumbnailAndMetadata_Zip() throws ConvertException, CommandValueException {
        ImageConverter imageConverter = new ImageConverter();
        ImageParam imageParam = generateImageJPGAndThumbnailAndMetadata();

        String zipImagePath = imageConverter.convert(imageParam).getDownload();

        File zipFile = new File(zipImagePath);
        boolean expected = zipFile.exists() && zipFile.length() > convertedImageSize;

        assertTrue(expected);
    }

    @Test(expected = ConvertException.class)
    public void convert_ImageParamNull_ConvertException() throws ConvertException, CommandValueException {
        ImageConverter imageConverter = new ImageConverter();
        imageConverter.convert(null);
    }

    @Test(expected = ConvertException.class)
    public void convert_ImageParamImageFormatEmpty_ConvertException() throws ConvertException, CommandValueException {
        ImageConverter imageConverter = new ImageConverter();
        ImageParam imageParam = generateOnlyImageJPG();
        imageParam.setImageFormat("");

        imageConverter.convert(imageParam);
    }

    private ImageParam generateOnlyImageJPG() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/fundacionjala.png";

        String md5 = "af738d53637a29f531fa0c2bf1eb1516";
        String outputPath = "src/test/resources/";
        String outputName = "img";
        String imageFormat = ".jpg";
        boolean isThumbnail = false;
        boolean isMetadata = false;
        boolean isGrayscale = false;
        int ImageWidth = 0;
        int ImageHeight = 0;
        float degreesToRotate = 0;

        ImageParam imageParam = new ImageParam();
        imageParam.setMd5(md5);
        imageParam.setInputPathFile(fileUploadedPath);
        imageParam.setOutputPathFile(outputPath);
        imageParam.setImageFormat(imageFormat);
        imageParam.setOutputName(outputName);
        imageParam.isThumbnail(isThumbnail);
        imageParam.isMetadata(isMetadata);
        imageParam.isGrayscale(isGrayscale);
        imageParam.setImageWidth(ImageWidth);
        imageParam.setImageHeight(ImageHeight);
        imageParam.setDegreesToRotate(degreesToRotate);
        imageParam.setFolderName(md5);

        return imageParam;
    }
    
    private ImageParam generateImageJPGAndThumbnailAndMetadata() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/fundacionjala.png";

        String md5 = "af738d53637a29f531fa0c2bf1eb1516";
        String outputPath = "src/test/resources/";
        String outputName = "img";
        String imageFormat = ".jpg";
        boolean isThumbnail = true;
        boolean isMetadata = true;
        boolean isGrayscale = false;
        int ImageWidth = 0;
        int ImageHeight = 0;
        float degreesToRotate = 0;

        ImageParam imageParam = new ImageParam();
        imageParam.setMd5(md5);
        imageParam.setInputPathFile(fileUploadedPath);
        imageParam.setOutputPathFile(outputPath);
        imageParam.setImageFormat(imageFormat);
        imageParam.setOutputName(outputName);
        imageParam.isThumbnail(isThumbnail);
        imageParam.isMetadata(isMetadata);
        imageParam.isGrayscale(isGrayscale);
        imageParam.setImageWidth(ImageWidth);
        imageParam.setImageHeight(ImageHeight);
        imageParam.setDegreesToRotate(degreesToRotate);
        imageParam.setFolderName(md5);

        return imageParam;
    }
}
