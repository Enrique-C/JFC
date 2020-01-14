package com.jalasoft.jfc.model.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Writes and verifies the name of file.
 *
 * @version 0.1 05 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class FileServiceController {

    /**
     * Writes the file uploaded.
     * @param pathFile receive the file path.
     * @param file receive the file uploaded.
     * @return the path of the file uploaded.
     * @throws IOException when is an invalid path.
     */
    public static String writeFile(String pathFile, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(pathFile);
        Files.write(path, bytes);
        return path.toString();
    }

    /**
     * Assigns a new name if it changed.
     * @param file receive the file uploaded.
     * @return Original name without special characters.
     */
    public static String getName(MultipartFile file) {
        final String REGEX_REMOVE_EXTENSION = "[.][^.]+$";
        final String REGEX_REPLACE = "";
        String nameFile = file.getOriginalFilename().replaceFirst(REGEX_REMOVE_EXTENSION, REGEX_REPLACE);

        return nameFile;
    }
}
