package main.ai.util;

import java.util.logging.*;

public class LoggerUtil {
    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        try {
            Handler handler = new FileHandler("application.log");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to set up logger", e);
        }
        return logger;
    }
}