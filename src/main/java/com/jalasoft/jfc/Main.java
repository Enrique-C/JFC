/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to JFC");

        //esta es la forma de leer application.properties
        Properties properties = new Properties();
        String links = "src/main/resources/application.properties";
        properties.load(new FileInputStream(links));
        String reading = properties.getProperty("test.path.magic");
        System.out.println("probando:  " + reading);

        SpringApplication.run(Main.class, args);





    }
}
