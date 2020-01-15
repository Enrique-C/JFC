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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class AudioConverterControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void audioConverter_WhenNullMultipartFileNameIsUploaded_BadRequest() throws Exception {
        String srcFilePath = "src/test/resources/audio.wav";
        String relativeMappingPath = "/api/v1/audioConverter/";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        ResultMatcher badRequest = MockMvcResultMatchers.status().isBadRequest();
        MockMultipartFile file = new MockMultipartFile("file", null,
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file))
                .andExpect(badRequest);
    }

    @Test
    public void audioConverter_WhenFinishAConversion_Status200() throws Exception {
        String srcFilePath = "src/test/resources/audio.wav";
        String relativeMappingPath = "/api/v1/audioConverter/";
        String md5Param = "md5";
        String md5 = "2559480156e9cddf65ed3125521b9922";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(md5Param, md5))
                .andExpect(status().isOk());
    }

    @Test
    public void audioConverter_WhenAAudioParamIsNull_Status400() throws Exception {
        String srcFilePath = "src/test/resources/audio.wav";
        String relativeMappingPath = "/api/v1/audioConverter/";
        String md5Param = "md5";
        String md5 = "2559480156e9cddf65ed3125521b9922";
        String audioSampleRateParam = "sampleRate";
        String audiSampleRate = "55100";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(md5Param, md5).param(audioSampleRateParam, audiSampleRate))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void audioConverter_WhenMd5IsWrong_Status406() throws Exception {
        String srcFilePath = "src/test/resources/audio.wav";
        String relativeMappingPath = "/api/v1/audioConverter/";
        String md5Param = "md5";
        String md5 = "2559480156e9cddf65ed3125521b9922WRONG";

        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                null, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(relativeMappingPath).file(file)
                .param(md5Param, md5))
                .andExpect(status().isNotAcceptable());
    }
}
