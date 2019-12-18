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

  private String inputPathFile;   //It is the input of the file that wil be converted.
  private String outputPathFile;  //It is the output of the file that wil be converted.
  private String outputFileName;  //Name the file that will be converted.

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
}
