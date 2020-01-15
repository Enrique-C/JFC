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
 * Tests Pptx converter controller.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Alan Escalera.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class PptxConverterControllerTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void pttxConverterToPdf_WhenFinishTheConversion_Status201() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToPdf/";

        String md5Param = "md5";
        String pagesToConvertThumbNailParam = "pagesToConvertThumbNail";
        String thumbnailFormatParam = "thumbnailFormat";
        String isThumbnailParam = "isThumbnail";
        String outputNameParam = "outputName";

        String isThumbnail = "true";
        String thumbnailFormat = ".png";
        String outputName = "";
        String pagesToConvertThumbNail = "1-5";
        String md5 = "86f655c0e849a9220f3355db2dd1df63";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file).param(md5Param, md5)
        .param(pagesToConvertThumbNailParam, pagesToConvertThumbNail).param(outputNameParam, outputName)
        .param(thumbnailFormatParam, thumbnailFormat).param(isThumbnailParam, isThumbnail)).andExpect(status()
        .isCreated());
    }

    @Test
    public void pttxConverterToPdf_WhenFinishTheConversion_Status400() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToPdf/";

        String md5Param = "md5";
        String pagesToConvertThumbNailParam = "pagesToConvertThumbNail";
        String thumbnailFormatParam = "thumbnailFormat";
        String isThumbnailParam = "isThumbnail";
        String outputNameParam = "outputName";

        String isThumbnail = "true";
        String thumbnailFormat = ".mp3";
        String outputName = "";
        String pagesToConvertThumbNail = "1-5";
        String md5 = "86f655c0e849a9220f3355db2dd1df63";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file).param(md5Param, md5)
        .param(pagesToConvertThumbNailParam, pagesToConvertThumbNail).param(outputNameParam, outputName)
        .param(thumbnailFormatParam, thumbnailFormat).param(isThumbnailParam, isThumbnail)).andExpect(status()
        .isBadRequest());
    }

    @Test
    public void pttxConverterToPdf_WhenMD5IsInvalid_Status406() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToPdf/";

        String md5Param = "md5";
        String md5 = "whioutmd5";
        String outputNameParam = "outputName";
        String outputName = "";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(outputNameParam, outputName).param(md5Param, md5)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void pttxConverterToPdf_WhenFileIsNotFound_Status404() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToPdf/";

        String md5Param = "md5";
        String md5 = "whioutmd5";
        String outputNameParam = "outputName";
        String outputName = "";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", null,
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(outputNameParam, outputName).param(md5Param, md5)).andExpect(status().isNotFound());
    }

    @Test
    public void pttxConverterToImage_WhenFinishTheConversion_Status201() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToImage/";

        String fileFormatParam = "imageFormat";
        String md5Param = "md5";
        String isThumbnailParam = "isThumbnail";
        String outputNameParam = "outputName";
        String pagesToConvertParam = "pagesToConvert";

        String fileFormat = ".png";
        String isThumbnail = "true";
        String outputName = "";
        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String pagesToConvert = "1-3";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file).param(md5Param, md5)
        .param(pagesToConvertParam, pagesToConvert).param(outputNameParam, outputName)
        .param(fileFormatParam, fileFormat).param(isThumbnailParam, isThumbnail)).andExpect(status()
        .isCreated());
    }

    @Test
    public void pttxConverterToImage_WhenMD5IsInvalid_Status406() throws Exception {
        String srcFilePath = "src/test/resources/Designpatters.pptx";
        String relativeMappingPath = "/api/v1/pptxConverterToImage/";

        String md5Param = "md5";
        String md5 = "86f655c0e849a9220f3355db2dd1df63";
        String outputNameParam = "outputName";
        String outputName = "";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(outputNameParam, outputName).param(md5Param, md5)).andExpect(status().isNotAcceptable());
    }
}
