/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import java.io.File;

/**
 * This class deletes a folder.
 *
 * @version 0.1 06 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class FolderRemover {

    /**
     * Removes a folder.
     * @param pathFile contains a file path.
     */
    public static void removeFolder(String pathFile) {
        File file = new File(pathFile);

        String[]entries = file.list();

        if (entries != null) {
            for (String s : entries) {
                File currentFile = new File(file.getPath(), s);
                currentFile.delete();
            }
        }
        file.delete();
    }
}
