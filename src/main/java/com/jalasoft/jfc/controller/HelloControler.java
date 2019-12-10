/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.jalasoft.jfc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enrique Carrizales
 */
@RestController
public class HelloControler {

    /**
     * Mapping method /hello
     * @return String message
     */
    @RequestMapping("/hello")
    public String home() {
        return "HolaMundo";
    }
}
