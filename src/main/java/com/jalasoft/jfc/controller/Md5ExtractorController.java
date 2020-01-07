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
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
public class Md5ExtractorController {

    @PostMapping("/extractMd5")
    public String extractMd5(@RequestParam("file") MultipartFile file) throws Md5Exception {
        String md5String = "";
        PathJfc pathJfc = new PathJfc();

        try {
            String fileUploaded = FileServiceController.writeFile(pathJfc.getInputFilePath() +
            file.getOriginalFilename(), file);
            md5String = Md5Checksum.getMd5(fileUploaded);
        } catch (IOException ioe) {
            throw new Md5Exception(ioe.getMessage(), this.getClass().getName());
        }
        return md5String;
    }
}
