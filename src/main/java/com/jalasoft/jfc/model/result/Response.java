/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.result;

public class Response {

    // Name of the file.
    String Name;
    // Status of the conversion.
    String Status;

    public void setName(String name) {
        Name = name;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
