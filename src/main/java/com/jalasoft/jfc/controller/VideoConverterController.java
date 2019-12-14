/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *  Manage VideoConvert Requests.
 *
 * @version 0.1
 *
 * @author Enrique Carrizales
 */
@RestController
@RequestMapping(path = "/convertVideo")
public class VideoConverterController {

    private static final String UPLOADED_FOLDER =
            "src/main/java/com/jalasoft/jfc/resources/"; //Constant upload file.

    /**
     *
     * @return
     */
    @PostMapping
    public String videoConvert(
            @RequestParam("file")MultipartFile file,
            @RequestParam String fFmpeg
            ){
        return "";
    }
}
