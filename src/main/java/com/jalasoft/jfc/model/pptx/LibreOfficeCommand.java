/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

/**
 * Used for specifying LibreOffice commands.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public enum LibreOfficeCommand {
    HEADLESS("--headless"),
    CONVERT("convert-to"),
    PDF("pdf"),
    OUTDIR("--outdir");

    // Command variable.
    private String command;

    /**
     * Sets command value.
     * @param command String value
     */
    LibreOfficeCommand(String command) {
        this.command = command;
    }

    /**
     * Gets command value.
     * @return String value.
     */
    public String getCommand(){
        return command;
    }
}
