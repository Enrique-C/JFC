/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import com.jalasoft.jfc.model.exception.ZipJfcException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/**
 * This class is used compress files into zip file.
 *
 * @version 0.1 23 Dic 2019
 *
 * @author Juan Martinez
 */
public class ZipFolder {

    /**
     * This method adds existent file to zip file.
     * @param currentFile is the existent file.
     * @param zipOutputStream is the stream content.
     * @throws IOException is thrown when there are some error.
     */
    private void addCurrentFile(File currentFile, ZipOutputStream zipOutputStream) throws IOException {
        int quantity = 128;
        byte [] bufferBytes = new byte[quantity];
        ZipEntry entry = new ZipEntry(currentFile.getName());
        FileInputStream fileInputStream = new FileInputStream(currentFile);
        zipOutputStream.putNextEntry(entry);
        int read = 0;
        while ((read = fileInputStream.read(bufferBytes)) != -1) {
            zipOutputStream.write(bufferBytes, 0, read);
        }
        zipOutputStream.closeEntry();
        fileInputStream.close();
    }

    /**
     * This method compress files.
     * @param files content a list of files.
     * @param pathZipFileName content path name of zip file.
     */
    public void zipFolderFile(final File [] files, final File pathZipFileName) throws ZipJfcException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathZipFileName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            for (File currentFile : files) {
                if (!currentFile.isDirectory()) {
                    addCurrentFile(currentFile, zipOutputStream);
                }
            }
            zipOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new ZipJfcException(e.getMessage(), this.getClass().getName());
        } catch (IOException ex){
            throw new ZipJfcException(ex.getMessage(), this.getClass().getName());
        }

    }
}
