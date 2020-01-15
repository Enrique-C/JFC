/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.Main;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.apache.pdfbox.io.IOUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Used for testing PdfConverterController.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Juan Martinez.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class PdfConverterControllerTest {

    // mockMvc variable.
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void pdfConverter_uploadPdfFileAndItsSpecifications_returnCreated201() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String md5 = "md5";
        String outputName = "outputName";
        String rotate = "rotate";
        String scale = "scale";
        String isThumbnail = "isThumbnail";
        String  isMetadata = "isMetadata";
        String imageFormat = "imageFormat";
        String width = "width";
        String height = "height";
        String pagesToConvert = "pagesToConvert";

        String md5Value = "8bd6509aba6eafe623392995b08c7047";
        String outputNameValue = "pdfTest";
        int rotateValue = 0;
        String scaleValue = "%";
        boolean isThumbnailValue = true;
        boolean isMetadataValue = true;
        String imageFormatValue = ".png";
        int widthValue = 0;
        int heightValue = 0;
        String pagesToConvertValue = "1,2";

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/pdfConverter/").file(file)
        .param(md5,md5Value).param(outputName, outputNameValue).param(rotate, String.valueOf(rotateValue))
        .param(scale, scaleValue).param(isThumbnail, String.valueOf(isThumbnailValue))
        .param(isMetadata, String.valueOf(isMetadataValue)).param(imageFormat, imageFormatValue)
        .param(width, String.valueOf(widthValue)).param(height, String.valueOf(heightValue))
        .param(pagesToConvert, pagesToConvertValue).characterEncoding("UTF-8")).andExpect(status().isCreated());
    }

    @Test
    public void pdfConverter_uploadPdfFileAndItsSpecificationsWithInvalidMd5Value_returnNotAcceptable()
            throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String md5 = "md5";
        String outputName = "outputName";
        String rotate = "rotate";
        String scale = "scale";
        String isThumbnail = "isThumbnail";
        String  isMetadata = "isMetadata";
        String imageFormat = "imageFormat";
        String width = "width";
        String height = "height";
        String pagesToConvert = "pagesToConvert";

        String md5Value = "8bd6509aba6eafe623392995b08c7047Invalid";
        String outputNameValue = "pdfTest";
        int rotateValue = 0;
        String scaleValue = "%";
        boolean isThumbnailValue = true;
        boolean isMetadataValue = true;
        String imageFormatValue = ".png";
        int widthValue = 0;
        int heightValue = 0;
        String pagesToConvertValue = "1,2";

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/pdfConverter/").file(file)
        .param(md5,md5Value).param(outputName, outputNameValue).param(rotate, String.valueOf(rotateValue))
        .param(scale, scaleValue).param(isThumbnail, String.valueOf(isThumbnailValue))
        .param(isMetadata, String.valueOf(isMetadataValue)).param(imageFormat, imageFormatValue)
        .param(width, String.valueOf(widthValue)).param(height, String.valueOf(heightValue))
        .param(pagesToConvert, pagesToConvertValue).characterEncoding("UTF-8")).andExpect(status().isNotAcceptable());
    }

    @Test
    public void pdfConverter_uploadPdfFileAndItsSpecificationsWithEmptyMd5AndNullFileName_returnNotFound404()
            throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String md5 = "md5";
        String outputName = "outputName";
        String rotate = "rotate";
        String scale = "scale";
        String isThumbnail = "isThumbnail";
        String  isMetadata = "isMetadata";
        String imageFormat = "imageFormat";
        String width = "width";
        String height = "height";
        String pagesToConvert = "pagesToConvert";

        String md5Value = " ";
        String outputNameValue = "pdfTest";
        int rotateValue = 0;
        String scaleValue = "%";
        boolean isThumbnailValue = true;
        boolean isMetadataValue = true;
        String imageFormatValue = ".jpg";
        int widthValue = 0;
        int heightValue = 0;
        String pagesToConvertValue = "1,2";

        MockMultipartFile file = new MockMultipartFile("file", null,
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/pdfConverter/").file(file)
        .param(md5,md5Value).param(outputName, outputNameValue).param(rotate, String.valueOf(rotateValue))
        .param(scale, scaleValue).param(isThumbnail, String.valueOf(isThumbnailValue))
        .param(isMetadata, String.valueOf(isMetadataValue)).param(imageFormat, imageFormatValue)
        .param(width, String.valueOf(widthValue)).param(height, String.valueOf(heightValue))
        .param(pagesToConvert, pagesToConvertValue).characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
    }

    @Test
    public void pdfConverter_uploadPdfFileAndItsSpecificationsWithInvalidImageFormat_returnBadRequest400()
            throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String md5 = "md5";
        String outputName = "outputName";
        String rotate = "rotate";
        String scale = "scale";
        String isThumbnail = "isThumbnail";
        String  isMetadata = "isMetadata";
        String imageFormat = "imageFormat";
        String width = "width";
        String height = "height";
        String pagesToConvert = "pagesToConvert";

        String md5Value = "8bd6509aba6eafe623392995b08c7047";
        String outputNameValue = "pdfTest";
        int rotateValue = 0;
        String scaleValue = "%";
        boolean isThumbnailValue = true;
        boolean isMetadataValue = true;
        String formatValue = ".exe";
        int widthValue = 0;
        int heightValue = 0;
        String pagesToConvertValue = "1,2";

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/pdfConverter/").file(file)
        .param(md5,md5Value).param(outputName, outputNameValue).param(rotate, String.valueOf(rotateValue))
        .param(scale, scaleValue).param(isThumbnail, String.valueOf(isThumbnailValue))
        .param(isMetadata, String.valueOf(isMetadataValue)).param(imageFormat, formatValue)
        .param(width, String.valueOf(widthValue)).param(height, String.valueOf(heightValue))
        .param(pagesToConvert, pagesToConvertValue).characterEncoding("UTF-8"))
        .andExpect(status().isBadRequest());
    }
}
