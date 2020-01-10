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
public class FilesMd5 {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

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
