/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc;

import com.jalasoft.jfc.model.strategy.CommandImageMagickPath;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 *
 *  Run Sprint Boot to Web Application
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Enrique Carrizales
 */
@SpringBootApplication
public class Main {

    /**
     * Main method executes SprintApplication
     * @param args receives String array
     */
    public static void main(String[] args) {
        System.out.println("Welcome to JFC");
        SpringApplication.run(Main.class, args);
    }
}
