package com.web.hevepratas.servicies.configs;

import lombok.Setter;

@Setter
public class LogMessage {

    private String initialMessage;
    private String authenticatedUser;
    private String className;
    private Exception exception;

    public LogMessage (String initialMessage, String authenticatedUser, String className, Exception exception) {
        this.initialMessage = initialMessage;
        this.authenticatedUser = authenticatedUser;
        this.className = className;
        this.exception = exception;
    }

    public String generateMessage() {
        StringBuilder message = new StringBuilder();

        message.append(initialMessage + "\n");
        message.append("The error occurred as the following user '" + authenticatedUser + "' \n");
        message.append("Occurred in " + className + "\n");

        if(exception != null) {
            message.append("Exception localized message: " + exception.getLocalizedMessage());
            message.append("\nException message: " + exception.getMessage());
        }

        return message.toString();
    }
}
