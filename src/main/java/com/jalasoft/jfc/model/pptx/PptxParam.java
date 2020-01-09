/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.Param;

/**
 * Manages params of an pptx.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 * */
public class PptxParam extends Param {

    // Contents a format to convert.
    private String thumbnailFormat;

    // Contains a boolean value for building the Thumbnail.
    private boolean isThumbnail;

    // Content number of pages to convert
    private String pagesToConvertThumbnail;

    /**
     * Gets Thumbnail Format value.
     * @return String value.
     */
    public String getThumbnailFormat() {
        return thumbnailFormat;
    }

    /**
     * Gets isThumbnail value.
     * @return boolean value.
     */
    public boolean getIsThumbnail() {
        return isThumbnail;
    }

    /**
     * Gets number of pages to be converted to thumbnail.
     * @return pagesToConvert.
     */
    public String getPagesToConvertThumbnail() {
        return pagesToConvertThumbnail;
    }

    /**
     * Sets pptxFormat value.
     * @param pptxFormat for setting pptxFormat.
     */
    public void setThumbnailFormat(String pptxFormat) {
        this.thumbnailFormat = pptxFormat;
    }

    /**
     * Sets isThumbnail.
     * @param isThumbnail receives boolean value.
     */
    public void setIsThumbnail(boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

    /**
     * Sets number of pages to be converted to thumbnail.
     * @param pagesToConvertThumbnail value.
     */
    public void setPagesToConvertThumbnail(String pagesToConvertThumbnail) {
        this.pagesToConvertThumbnail = pagesToConvertThumbnail;
    }
}
