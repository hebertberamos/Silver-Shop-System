package com.web.hevepratas.util;

import com.web.hevepratas.exceptions.AuthorizationException;
import com.web.hevepratas.exceptions.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogUtil  {

    public static void logNotAuthorized(String userEmail, String logMessage, String className) {
        log.warn(generateMessage(logMessage, userEmail, className, null));

        throw new AuthorizationException("Você não tem autorização para prosseguir com esta requisição.");
    }

    public static void logExceptionError(Exception exception, String userEmail, String logMessage, String className, String exceptionMessage) {
        log.error(generateMessage(logMessage, userEmail, className, exception));

        throw new InternalServerException(exceptionMessage);
    }

    public static void logMessage(String userEmail, String logMessage, String className) {
        log.info(generateMessage(logMessage, userEmail, className, null));
    }


    private static String generateMessage(String logMessage, String userEmail, String className, Exception exception) {
        StringBuilder message = new StringBuilder();

        message.append(logMessage + "\n");
        message.append("The error occurred as the following user '" + userEmail + "' \n");
        message.append("Occurred in " + className + "\n");

        if(exception != null) {
            message.append("Exception localized message: " + exception.getLocalizedMessage());
            message.append("\nException message: " + exception.getMessage());
        }

        return message.toString();
    }
}
