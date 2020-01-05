/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  This class evaluate the md5 of the client and the file uploaded.
 *
 * @version 1.0 18 Dic 2019
 *
 * @author Alan Escalera
 */
public class Md5Checksum {

    private static final Logger logger = Logger.getLogger(Md5Checksum.class.getName());

    /**
     *  This method return a boolean after to compare checksums.
     * @param file receive the path of a file to get md5.
     * @return a boolean after to compare.
     * @throws IOException when is a invalid file.
     */
    public static boolean getMd5(String file, String md5Client) throws IOException {
        String checksum;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new IOException("This is a invalid file");
        }
        return checksum.equals(md5Client);
    }
}
