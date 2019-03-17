package com.insider.travel.japan.wabisabi.common.log;

import com.insider.travel.japan.wabisabi.common.log.LogParameters.Item;
import java.lang.reflect.Field;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/**
 * {@link LogParameters}のサブクラスを対象に、{@link Loggable}アノテーションが設定されたフィールドの解析を行います。
 * {@link LogParameters}のサブクラスはインナークラスなどのネストされたクラスには対応していません。
 */

public final class LoggableParser {
    
    private LoggableParser() {
    }

    /**
     * 引数のインスタンスの解析を行います。
     * 
     * @param logParameters 解析したいインスタンス
     */
    public static void parse(final LogParameters logParameters) {

        if (logParameters == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        try {
            Field[] fieldList = logParameters.getClass().getDeclaredFields();
            
            for (Field f : fieldList) {
                f.setAccessible(true);
                // RetentionPolicy.RUNTIME以外はここがnullになります。
                if (f.getAnnotation(Loggable.class) != null) {
                    Loggable element = f.getAnnotation(Loggable.class);
                    String fieldValue = f.get(logParameters) == null ? null : (String) f.get(logParameters);
                    // 常に出力する場合は出力リストに追加
                    logParameters.put(f.getName(),
                            new Item(element.order(), selectName(f.getName(), element), fieldValue, element.always()));
                }
            }
            
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            throw new IllegalStateException("Parse error", ex);
        }
    }

    /**
     * ロケールや、{@link Loggable}アノテーションの設定に応じた項目名を取り出します。
     * @param defaultName デフォルトの項目名（フィールド名を想定）
     * @param loggable アノテーションの情報
     * @return 項目名
     */
    private static String selectName(String defaultName, Loggable loggable) {
        // 日本語のロケールか
        if (Locale.getDefault().getLanguage().equals(Locale.JAPANESE.getLanguage())) {
            // 日本語の設定が無ければデフォルト、あればその値
            if (StringUtils.isEmpty(loggable.name_ja())) {
                return defaultName;
            } else {
                return loggable.name_ja();
            }
        } else if (StringUtils.isEmpty(loggable.name())) {
            return defaultName;
        } else {
            return loggable.name();
        }
    }
    
}
