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

/***
 * @author Enrique Carrizales
 */
@SpringBootApplication
public class Main {
    /**
     * Main method
     * @param args receives String array
     */
    public static void main(String[] args){
        System.out.println("JFC");
        SpringApplication.run(Main.class, args);
    }
}
