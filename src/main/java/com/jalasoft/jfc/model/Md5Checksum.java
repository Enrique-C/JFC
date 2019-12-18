/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *  This class contend the attributes in common of Class PdfParam, VideoParam, ImageParam.
 *
 * @version 1.0 18 Dic 2019
 *
 * @author Alan Escalera
 */
public class Md5Checksum {

    private static final Logger logger = Logger.getLogger(Md5Checksum.class.getName());

    /**
     *  This method return a Md5 checksum from a file.
     * @param file
     * @return String
     */
    public String getMd5(String file){
        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return checksum;
    }
}