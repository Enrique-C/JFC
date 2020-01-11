/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Defines UserEntity.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Content id value.
    @Column
    private Integer id;

    // Content user value.
    @Column
    private String userName;

    // Content password value.
    @Column
    private String password;

    // Content token value.
    @Column
    private String token;

    // Content rol value.
    @Column
    private String rol;

    // Content email value.
    @Column
    private String email;

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

    /**
     * Gets token value.
     * @return token value.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token value.
     * @param token value.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets rol value.
     * @return rol.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Sets email address.
     * @param email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets rol value.
     * @param rol value.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Gets email address.
     * @return email.
     */
    public String getEmail() {
        return email;
    }
}
