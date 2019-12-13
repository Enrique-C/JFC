/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package com.jalasoft.jfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
        Run Sprint Boot to Web Application
 *
 * @version
        0.1    * @author Enrique Carrizales */
@SpringBootApplication
public class Main {

    /**
     * Main method executes SprintApplication
     * @param args receives String array
     */
    public static void main(String[] args) {
        System.out.println("Wellcome to JFC");
        SpringApplication.run(Main.class, args);
    }
}