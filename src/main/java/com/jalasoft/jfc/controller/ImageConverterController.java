/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.jfc.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 *
 *  Manage ImageConvert Requests.
 *
 * @version 0.1.
 *
 * @author Enrique Carrizales.
 */
@RestController
@RequestMapping(path = "/convertImage")
public class ImageConverterController {

    private static final String UPLOADED_FOLDER =
            "src/main/java/com/jalasoft/jfc/resources/";  //Constant upload file

    /**
     * convertImage method receives a image to covert.
     * @param file contains the image file.
     * @param param1 contains a image param.
     * @param param2 contains a image param.
     * @param param3 contains a image param.
     * @param param4 contains a image param.
     * @param param5 contains a image param.
     * @param param6 contains a image param.
     * @param param7 contains a image param.
     * @param param8 contains a image param.
     * @return get the path of the upload file.
     */
    @PostMapping()
    public String convertImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam String param1,
            @RequestParam String param2,
            @RequestParam String param3,
            @RequestParam String param4,
            @RequestParam String param5,
            @RequestParam String param6,
            @RequestParam String param7,
            @RequestParam String param8) {

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return UPLOADED_FOLDER;
    }
}
