package com.insider.travel.japan.wabisabi.common.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ZULEY
 */
public final class StringUtil {
    
    private StringUtil() {
    }

    /**
     * regex の正規表現で引っかかる文字列を target から取得する
     * @param regex 取得したい対象の正規表現
     * @param target 取得元の文字列
     * @return 正規表現で引っかかる文字列 
     */
    public static String extractMatchString(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    /**
     * regex の正規表現で引っかかる文字列が target に存在するか
     * @param regex 取得したい対象の正規表現
     * @param target 取得元の文字列
     * @return 存在する場合 true
     */
    public static boolean matches(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

	/**
	 * 文字列リストの連結を行う。
	 * 
	 * @param list 連結対象の文字列リスト。nullは渡さないこと
	 * @param head 文字列を括る先頭文字
	 * @param delimita 区切り文字
	 * @param tail 文字列を括る終端文字
	 * @return 連結文字列
	 */
    public static String joinList(List<String> list, String head, String delimita, String tail) {
		// MEMO stream API で処理するりも早いのでよStringBuilderで処理している
		StringBuilder sb  = new StringBuilder();

		sb.append(head);
		int i = 0;
		for (String str : list) {
			if (i != 0) {
				sb.append(delimita);
			}
			sb.append(str);
			i++;
		}
		sb.append(tail);

		return sb.toString();

	}
    
}
