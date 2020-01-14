/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.exception.Md5Exception;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Evaluates the md5 of the client and the file uploaded.
 *
 * @version 1.0 18 Dic 2019
 *
 * @author Alan Escalera
 */
public class Md5Checksum {

    private static final Logger logger = Logger.getLogger(Md5Checksum.class.getName());

    /**
     * Returns a boolean after to compare checksums.
     * @param file receives the path to get md5.
     * @param md5Client receives the md5 of the client.
     * @return a String of md5 after to compare.
     * @throws Md5Exception when is a invalid file.
     */
    public static String getMd5(String file, String md5Client) throws Md5Exception {
        String checksum;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
            if (checksum.equals(md5Client.trim())) {
                return checksum;
            }
            throw new Md5Exception(ErrorMessageJfc.MD5_ERROR.getErrorMessageJfc(), md5Client);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new Md5Exception("This is a invalid file", "Md5Checksum");
        }
    }

    /**
     * Returns a boolean after to compare checksums.
     * @param file receives the path to get md5.
     * @return a String of md5 after to compare.
     * @throws Md5Exception when is a invalid file.
     */
    public static String getMd5(String file) throws Md5Exception {
        final String EMPTY_VALUE = "";
        String checksum = EMPTY_VALUE;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            throw new Md5Exception("file not found", e.getMessage());
        }
        return checksum;
    }
}
