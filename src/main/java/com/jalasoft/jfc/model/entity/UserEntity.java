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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines UserEntity .
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Content id value.
    private Integer id;

    // Content user value.
    private String userName;

    // Content password value.
    private String password;

    /**
     * Gets id value.
     * @return id value.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id value.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets user value.
     * @return user value.
     */
    public String getUser() {
        return userName;
    }

    /**
     * Sets user value.
     * @param userName value.
     */
    public void setUser(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password value.
     * @return password value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password value.
     * @param password value.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
