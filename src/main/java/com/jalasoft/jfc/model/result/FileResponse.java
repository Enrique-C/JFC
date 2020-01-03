/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.result;

/**
 * This class is used to return the success of the conversion.
 *
 * @version 0.1 17 Dic 2019
 *
 * @author Juan Martinez
 */
public class FileResponse extends Response{

    // Download link of the file.
    String download;

    /**
     * Gets the download path.
     * @return the download paht of the file.
     */
    public String getDownload() {
        return download;
    }

    /**
     * Sets the path to download.
     * @param download receives the path to download.
     */
    public void setDownload(String download) {
        this.download = download;
    }

}
