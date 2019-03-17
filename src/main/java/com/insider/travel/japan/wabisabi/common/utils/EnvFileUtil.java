package com.insider.travel.japan.wabisabi.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 */
public final class EnvFileUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvFileUtil.class);

    /**
     * ファイルの文字コード。
     */
    private static final String FILE_CHARSET = "UTF-8";

    /**
     * 検査実施に関するプロパティファイルの情報を保持する。
     */
    private static final Map<String, String> PROP_MAP = new HashMap<>();

    // 設定ファイルの項目
    public static final String KEY_OF_ACCESSPOINT = "service.accesspoint";
    public static final String KEY_OF_PROXY_HOST = "proxy.host";
    public static final String KEY_OF_PROXY_PORT = "proxy.port";
    public static final String KEY_OF_PROXY_USER = "proxy.username";
    public static final String KEY_OF_PROXY_PASS = "proxy.password";
    public static final String KEY_OF_VM_OPTS = "jvm.options";

    private EnvFileUtil() {
    }

    /**
     * アクセスポイントを取得する。
     *
     * @return アクセスポイント
     */
    public static String getAccesspoint() {
        String val = null;
        if (PROP_MAP.containsKey(KEY_OF_ACCESSPOINT)) {
            val = PROP_MAP.get(KEY_OF_ACCESSPOINT);
        }
        return val;
    }


    /**
     * プロキシホストを取得する
     *
     * @return プロキシホスト
     */
    public static String getProxyHost() {
        String val = null;
        if (PROP_MAP.containsKey(KEY_OF_PROXY_HOST)) {
            val = PROP_MAP.get(KEY_OF_PROXY_HOST);
        }
        return val;
    }

    /**
     * プロキシポートを取得する
     *
     * @return プロキシポート
     */
    public static String getProxyPort() {
        String val = null;
        if (PROP_MAP.containsKey(KEY_OF_PROXY_PORT)) {
            val = PROP_MAP.get(KEY_OF_PROXY_PORT);
        }
        return val;
    }

    /**
     * プロキシユーザを取得する
     *
     * @return プロキシユーザ
     */
    public static String getProxyUser() {
        String val = null;
        if (PROP_MAP.containsKey(KEY_OF_PROXY_USER)) {
            val = PROP_MAP.get(KEY_OF_PROXY_USER);
        }
        return val;
    }

    /**
     * プロキシパスワードを取得する
     *
     * @return プロキシパスワード
     */
    public static String getProxyPass() {
        String val = null;
        if (PROP_MAP.containsKey(KEY_OF_PROXY_PASS)) {
            val = PROP_MAP.get(KEY_OF_PROXY_PASS);
        }
        return val;
    }

    /**
     * プロキシの設定が有効か（コメント解除されている）を返却する。
     *
     * @return trueのばあい有効
     */
    public static boolean isAvailableProxy() {
        return PROP_MAP.containsKey(KEY_OF_PROXY_HOST)
                && PROP_MAP.containsKey(KEY_OF_PROXY_PORT);
    }

    /**
     * プロキシの認証設定が有効か（コメント解除されている）を返却する。 プロキシユーザが有効であれば、有効とみなす。
     *
     * @return trueのばあい有効
     */
    public static boolean isAvailableProxyAuth() {
        return PROP_MAP.containsKey(KEY_OF_PROXY_USER);
    }

    /**
     * JVMのオプションに指定された値を文字列のListで返す。
     *
     * @return JVMのオプションに指定された値を文字列のList
     */
    public static List<String> getAllVMOpts() {
        List<String> options = new ArrayList<>();
        final String optDelimiter = " ";
        String val;
        if (PROP_MAP.containsKey(KEY_OF_VM_OPTS)) {
            val = PROP_MAP.get(KEY_OF_VM_OPTS);

            String[] vals = val.split(optDelimiter);
            options.addAll(Arrays.asList(vals));
        }
        return options;
    }

    /**
     * リソースの内容を格納したMapを返す。
     * ユニットテストなどで使用する以外は ReadOnly にしてください。
     *
     * @return リソースの内容を格納したMap
     */
    public static Map<String, String> getBundleData() {
        return PROP_MAP;
    }
    
}
