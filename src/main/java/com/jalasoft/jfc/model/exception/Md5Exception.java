package com.jalasoft.jfc.model.exception;

/**
 * Class of manage exception of Md5.
 *
 * @version 0.1 03 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class Md5Exception extends Exception {

        // Returns a error message of Md5.
        String message;

        /**
         * Manages Exceptions of Md5.
         * @param Checksum checksum of file.
         * @param message of error.
         */
        public Md5Exception(String message, String Checksum) {
            this.message = message + " " + Checksum;
        }

        /**
         * Gets error message.
         * @return message.
         */
        @Override
        public String getMessage() {
            return message;
        }
}
