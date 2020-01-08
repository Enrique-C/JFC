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
import com.jalasoft.jfc.model.utility.PathJfc;

import com.jalasoft.jfc.model.video.VideoConverter;
import com.jalasoft.jfc.model.video.VideoParam;

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
        PptxParam pptxParam = getParamsPptx();

        String zipPptx = pptxConverter.convert(pptxParam).getDownload();
        final int EMPTY_BYTES = 0;

        File zipFile = new File(zipPptx);
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;

        assertTrue(expected);
    }

    private PptxParam getParamsPptx() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/grabacion.mp4";

        String md5 = "c4d2c40bd1218da61651f28da6ad8838";
        String outputPath = "src/test/resources/";
        String outputName = "video_Test";
        String videoFormat = ".avi";
        boolean isThumbnail = false;
        boolean isMetadata = false;

        PptxParam pptxParam = new PptxParam();

        pptxParam.setMd5(md5);
        pptxParam.setInputPathFile(fileUploadedPath);
        pptxParam.setOutputPathFile(outputPath);
        pptxParam.setOutputName(outputName);

        pptxParam.setFolderName(md5);

        return pptxParam;
    }
}
