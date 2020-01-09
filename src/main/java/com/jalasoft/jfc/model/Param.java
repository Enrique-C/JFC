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

  // It is the folder name where the files will be saved.
  private String folderName;

  // Name the file that will be converted.
  private String outputName;

  // It is a Md5 code of a file.
  private String md5;

  // Contains a confirmation of metadata.
  private boolean isMetadata;

  // Contains a file format.
  private String fileFormat;

  /**
   * Gets the checksum value.
   * @return md5 String value.
   */
  public String getMd5() {
    return md5;
  }

  /**
   * Gets inputPathFile value.
   * @return inputPathFile String value.
   */
  public String getInputPathFile() {
    return inputPathFile;
  }

  /**
   * Gets input file name value.
   * @return folderName.
   */
  public String getFolderName() {
    return folderName;
  }

  /**
   * Gets outputPathFile value.
   * @return inputPathFile String value.
   */
  public String getOutputPathFile() {
    return outputPathFile;
  }

  /**
   * Gets outputFileName value.
   * @return outputFileName String value.
   */
  public String getOutputName() {
    return outputName;
  }

  /**
   * Gets confirmation of a metadata.
   * @return boolean value.
   */
  public boolean isMetadata() {
    return isMetadata;
  }

  /**
   * Gets the file format value.
   * @return fileFormat String value.
   */
  public String getFileFormat() {
    return fileFormat;
  }

  /**
   * setInputPathFile sets inputPathFile value.
   * @param inputPathFile for setting in this.inputPathFile.
   */
  public void setInputPathFile(String inputPathFile) {
    this.inputPathFile = inputPathFile;
  }

  /**
   * setFolderName sets folder name.
   * @param folderName
   */
  public void setFolderName(String folderName) {
    this.folderName = folderName;
  }

  /**
   * Sets outputPathFile value.
   * @param outputPathFile for setting in this.outputPathFile.
   */
  public void setOutputPathFile(String outputPathFile) {
    this.outputPathFile = outputPathFile;
  }

  /**
   * Sets outputFileName value.
   * @param outputName for setting this.outputFileName.
   */
  public void setOutputName(String outputName) {
    this.outputName = outputName;
  }

  /**
   * Sets md5 value.
   * @param md5 for setting this.md5.
   */
  public void setMd5(String md5) {
    this.md5 = md5;
  }

  /**
   * Sets metadata value.
   * @param metadata receives a metadata confirmation.
   */
  public void isMetadata(boolean metadata) {
    isMetadata = metadata;
  }

  /**
   * Sets the file format value.
   * @param fileFormat for setting this.fileFormat.
   */
  public void setFileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
  }
}
