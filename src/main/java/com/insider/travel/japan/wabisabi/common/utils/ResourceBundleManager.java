package com.insider.travel.japan.wabisabi.common.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ロケール（Locale.getDefault()）に合ったResourceBundleを返します。<br>
 * リソースファイルはロケール（Locale.getDefault()）に合わせたファイルが使用されます。<br>
 * 例えば、ロケールが日本の場合は、○○Messages_ja_JP.propertiesを使用します。<br>
 * ロケールに合ったファイルが無い場合は、○○Messages.propertiesを使用します。<br>
 * <br>
 */

public final class ResourceBundleManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleManager.class);

    private ResourceBundleManager() { }

    /**
     * 引数に指定されたbaseNameに一致するResourceBundleを返します。
     * 引数に指定されたresourceBundleがnullでなく、resourceBundleのロケールと
     * 現在のロケールに違いがなければ、引数に指定されたresourceBundleを返します。
     *
     * @param baseName
     *        例えばMessages.propertiesの場合は"Messages"を指定してください。
     * @param resourceBundle
     *        現在のResourceBundleを指定してください。
     * @return a resource bundle for the given base name
     */
    public static ResourceBundle getResourceBundle(String baseName, ResourceBundle resourceBundle) {

        if (null == resourceBundle || resourceBundle.getLocale() != Locale.getDefault()) {
            LOGGER.trace("Got a resource bundle using the specified base name [" + baseName + "] and locale [" + Locale.getDefault() + "].");
            resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());
        }
        return resourceBundle; 

    }
    
}
