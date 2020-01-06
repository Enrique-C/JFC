package com.jalasoft.jfc.model.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Compares valid commands.
 *
 * @version 0.1 06 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public class ValidCommands {

    /**
     * Returns true or false after compare to valid commands.
     * @param file is path of command values.
     * @param commandValue video command value.
     * @throws IOException when is an invalid path.
     */
    public static boolean getsValidCommand(String file, String commandValue) throws IOException {
        String line;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        line = bufferedReader.readLine();
        String[] parts = line.split(",");

        for (short index = 0; index < parts.length; index++) {
            if (commandValue.equals(parts[index].toString())) {
                bufferedReader.close();
                return true;
            }
        }
        bufferedReader.close();
        return false;
    }
}
