package com.disneybank.controller;

import org.apache.log4j.Logger;

public class LoggerMain{

    public void log(String msg, Logger logger, String logType) {

        if (logType.equals("info") && logger.isInfoEnabled()) {
            // use info for regular transaction messages
            logger.info(msg);
        }
        else if (logType.equals("error")) {
            // use error for bad transactions
            logger.error(msg);
        }
        else {
            // use fatal for crashes/reduced levels of performance
            logger.fatal(msg);
        }
    }
}

