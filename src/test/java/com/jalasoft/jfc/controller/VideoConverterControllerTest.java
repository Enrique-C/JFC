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
 * Tests Video converter controller.
 *
 * @version 0.1 14 Jan 2020.
 *
 * @author Oscar Lopez.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class VideoConverterControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void videoConverter_WhenUploadFile_VideoConverted() throws Exception {
        String srcFilePath = "src/test/resources/grabacion.mp4";
        String mappingPath = "/api/v1/videoConverter/";
        String md5 = "c4d2c40bd1218da61651f28da6ad8838";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/videoConverter/").file(file)
                .param("md5", md5).characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    public void videoConverter_WhenFileOriginalFileNameIsNull_Status404() throws Exception {
        String srcFilePath = "src/test/resources/grabacion.mp4";
        String mappingPath = "/api/v1/videoConverter/";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", null,
                MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(mappingPath).file(file))
                .andExpect(status().isNotFound());
    }

    @Test
    public void videoConverter_WhenFileIsUploadAndMd5IsWrong_Status406() throws Exception {
        String srcFilePath = "src/test/resources/grabacion.mp4";
        String mappingPath = "/api/v1/videoConverter/";
        String md5 = "JAJAc4d2c40bd1218da61651f28da6ad8838";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(mappingPath).file(file)
                .param("md5", md5).characterEncoding("UTF-8"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void videoConverter_WhenFileIsUploadAndVideoFormatIsWrong_Status400() throws Exception {
        String srcFilePath = "src/test/resources/grabacion.mp4";
        String mappingPath = "/api/v1/videoConverter/";
        String md5 = "c4d2c40bd1218da61651f28da6ad8838";
        String videoFormat = ".txt";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(mappingPath).file(file)
                .param("md5", md5).characterEncoding("UTF-8")
                .param("videoFormat", videoFormat))
                .andExpect(status().isBadRequest());
    }
}
