package com.insider.travel.japan.wabisabi.common.utils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 *
 * 
 */
public enum CommonMessage implements LogMessage {
    
    INFO_SAMPLE("I000001"), 
    WARN_SAMPLE("W000001"), 
    ERROR_SAMPLE("E000001"),
    ERROR_UNEXPECTED("E999999");

    private static final String BASE_NAME = "CommonMessage";
    private static ResourceBundle resourceBundle;
    private final String messageCode;
    
    private CommonMessage(String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public String getMessage(String... replacements) {
        resourceBundle = ResourceBundleManager.getResourceBundle(BASE_NAME, resourceBundle);
        return messageCode + " - " + MessageFormat.format(resourceBundle.getString(messageCode), Arrays.toString(replacements));
    }
    
}
