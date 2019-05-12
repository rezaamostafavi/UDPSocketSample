package utils;

import udp.UDPServer;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * Created by rezaa on 5/11/2019.
 */
public class MyLogs {

    private Logger LOGGER;

    public MyLogs(String name) {
        LOGGER = Logger.getLogger(name);
        try {
            String pattern = System.getProperty("user.dir") + "/logs/voip.log";
            File file = new File(pattern);
            file.getParentFile().mkdirs();
            FileHandler fileHandler = new FileHandler(pattern);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            LOGGER.addHandler(fileHandler);
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creating SimpleFormatter
        this.LOGGER = LOGGER;
    }

    private static FileHandler rotate(String pattern, int limit, int count, boolean append) throws IOException {
        if (pattern == null) {
            LogManager m = LogManager.getLogManager();
            String p = FileHandler.class.getName();
            pattern = m.getProperty(p + ".pattern");
            if (pattern == null) {
                pattern = "%h/java%u.log";
            }
            System.out.println(pattern);
        }

        new FileHandler(pattern, 0, count, false).close(); //Trigger rotate.
        return new FileHandler(pattern, limit, count, append);
    }

    public static MyLogs getLogger(String name) {
        return new MyLogs(name);
    }

    public void info(String message) {
        LOGGER.info(message);
    }

    public void warning(String message) {
        LOGGER.warning(message);
    }

    public void log(Level level, String message) {
        LOGGER.log(level, message);
    }


}
