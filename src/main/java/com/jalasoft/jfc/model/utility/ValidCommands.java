package com.jalasoft.jfc.model.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidCommands {

    /**
     * Returns true or false after compare to valid commands.
     * @param file, file is path of command values.
     * @param commandValue video command value.
     * @throws IOException when is an invalid path.
     */
    public static boolean getsValidCommand(String file, String commandValue) throws IOException {
        String line;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        line = b.readLine();
        String[] parts = line.split(",");

        for (short i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
            System.out.println(commandValue);
            if (commandValue.equals(parts[i].toString())) {
                b.close();
                return true;
            }
        }
        b.close();
        return false;
    }
}
