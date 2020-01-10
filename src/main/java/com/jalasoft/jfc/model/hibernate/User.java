package com.jalasoft.jfc.model.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 *  IConverter interface defines behavior to Converters.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
// @Entity serve to make a table out of this class.
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    // Content id value.
    private Integer id;

    // Content user value.
    private String user;

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
        return user;
    }

    /**
     * Sets user value.
     * @param user value.
     */
    public void setUser(String user) {
        this.user = user;
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
