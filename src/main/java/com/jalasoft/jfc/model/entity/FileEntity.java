/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Defines UserRepository.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Content id value.
    private Integer id;

    // Content md5 value.
    private String md5;

    // Content filePath value.
    private String filePath;

    /**
     * Gets md5 value.
     * @return md5 value.
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Sets md5 value.
     * @param md5 value.
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * Gets filePath value.
     * @return filePath value.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets filePath value.
     * @param filePath value.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets id value.
     * @return id value.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id value.
     * @param id value.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
