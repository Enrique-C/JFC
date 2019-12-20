/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

/**
*
*  This class contend the attributes in common of Class PdfParam, VideoParam, ImageParam.
*
* @version 0.1  13 Dic 2019
*
* @author Alan Escalera
*/
public class Param {

  // It is the input of the file that wil be converted.
  private String inputPathFile;

  // It is the output of the file that wil be converted.
  private String outputPathFile;

  // Name the file that will be converted.
  private String outputFileName;

  // It is a Md5 code of a file.
  private String md5;

  /**
   * getMd5 gets the checksum value.
   * @return md5 String value.
   */
  public String getMd5() {
    return md5;
  }

  /**
   * getInputPathFile gets inputPathFile value.
   * @return inputPathFile String value.
   */
  public String getInputPathFile() {
    return inputPathFile;
  }

  /**
   * getOutputPathFile gets outputPathFile value.
   * @return inputPathFile String value.
   */
  public String getOutputPathFile() {
    return outputPathFile;
  }

  /**
   * getOutputFileName gets outputFileName value.
   * @return outputFileName String value.
   */
  public String getOutputFileName() {
    return outputFileName;
  }

  /**
   * setInputPathFile sets inputPathFile value.
   * @param inputPathFile for setting in this.inputPathFile.
   */
  public void setInputPathFile(String inputPathFile) {
    this.inputPathFile = inputPathFile;
  }

  /**
   * setOutputPathFile sets outputPathFile value.
   * @param outputPathFile for setting in this.outputPathFile.
   */
  public void setOutputPathFile(String outputPathFile) {
    this.outputPathFile = outputPathFile;
  }

  /**
   * setOutputFileName sets outputFileName value.
   * @param outputFileName for setting this.outputFileName.
   */
  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  /**
   * setMd5 sets md5 value.
   * @param md5 for settint this.md5.
   */
  public void setMd5(String md5) {
    this.md5 = md5;
  }
}
