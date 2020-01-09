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
    String pptxFormat;

    // Contains a boolean value for building the Thumbnail.
    private boolean isThumbnail;

    /**
     * Gets getPptxFormat value.
     * @return String value.
     */
    public String getPptxFormat() {
        return pptxFormat;
    }

    /**
     * Gets isThumbnail value.
     * @return boolean value.
     */
    public boolean isThumbnail() {
        return isThumbnail;
    }

    /**
     * Sets pptxFormat value.
     * @param pptxFormat for setting pptxFormat.
     */
    public void setPptxFormat(String pptxFormat) {
        this.pptxFormat = pptxFormat;
    }

    /**
     * Sets isThumbnail.
     * @param isThumbnail receives boolean value.
     */
    public void isThumbnail(boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }
}
