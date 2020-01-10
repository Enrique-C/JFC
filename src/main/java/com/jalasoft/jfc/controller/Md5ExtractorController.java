/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.utility.PathJfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Extracts md5 of a file.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Juan Martinez.
 */
@Api(value = "Md5ExtractorController", description = "REST API related to Extract Md5")
@RestController
@RequestMapping("/api")
public class Md5ExtractorController {

    /**
     * Extract md5 of a file.
     * @param file contents file value.
     * @return md5.
     * @throws Md5Exception
     */
    @PostMapping("/extractMd5")
    @ApiOperation(value = "File", notes = "Provides values for extracting Md5",
            response = Response.class)
    public ResponseEntity<String> extractMd5(@RequestParam("file") MultipartFile file) throws Md5Exception {
        final String EMPTY_VALUE = "";
        String md5String = EMPTY_VALUE;
        PathJfc pathJfc = new PathJfc();

        try {
            String fileUploaded = FileServiceController.writeFile(pathJfc.getInputFilePath() +
            file.getOriginalFilename(), file);
            md5String = Md5Checksum.getMd5(fileUploaded);
        } catch (Md5Exception | IOException ioe) {
            return new ResponseEntity<String>(ioe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(md5String, HttpStatus.OK);
    }
}
