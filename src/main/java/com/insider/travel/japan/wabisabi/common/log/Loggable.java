package com.insider.travel.japan.wabisabi.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.commons.lang3.StringUtils;

/**
 * 共通ログ出力用のアノテーションです。
 * {@link LoggableParser}で解析されます。
 * String以外のフィールドには対応していません。またString以外のフィールドに指定した場合はどうなるか検証してないです。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface Loggable {
    
    /**
     * 未設定の値に対して設定する文字列
     */
    public static final String UNDEFINED = "UNDEFINED";
    /**
     * この値が{@code true}の場合、値の無いように関わらず表示する項目になります。
     * @return 表示設定
     */
    boolean always() default true;
    /**
     * 表示順を設定します。数字の小さい方から先に表示されます。
     * @return 表示順
     */
    int order();
    /**
     * デフォルトのロケール用の項目名を設定します。
     * 未設定の場合は、フィールド名が採用されます。
     * @return 項目名
     */
    String name() default StringUtils.EMPTY;
    /**
     * jaロケール用の項目名を設定します。
     * 未設定の場合は、フィールド名が採用されます。
     * @return 項目名
     */
    String name_ja() default StringUtils.EMPTY;
    
}
