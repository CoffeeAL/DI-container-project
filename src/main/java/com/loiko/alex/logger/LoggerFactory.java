package com.loiko.alex.logger;

import com.loiko.alex.Runner;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public final class LoggerFactory {

    private static final Logger logger = Logger.getLogger(Runner.class.getName());
    private static FileHandler fileHandler;
    private static final SimpleFormatter simpleFormatter = new SimpleFormatter();
    private static final String LOG_FILE = "runner-log.txt";

    static {
        try {
            fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}