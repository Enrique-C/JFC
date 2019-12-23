package com.jalasoft.jfc.model.utility;

import com.jalasoft.jfc.Main;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathJfc {
    private Properties properties;
    private InputStream inputPath;
    private final String APPLICATION_PROPERTIES = "application.properties";
    private final String PATH_MAGICK = "path.magick";
    private final String PATH_FFMPEG = "path.ffmpeg";

    public PathJfc() throws IOException {
        properties = new Properties();
        inputPath = PathJfc.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
        properties.load(inputPath);
    }
    public String getMagicPath(){
        return  properties.getProperty(PATH_MAGICK);
    }
    public String getFfmpegPath(){
        return  properties.getProperty(PATH_FFMPEG);
    }
}
