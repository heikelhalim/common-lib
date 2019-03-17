package com.insider.travel.japan.wabisabi.common.utils;

/**
 *
 * String escape class
 */
public final class StringEscapeUtil {
    
    public static final String REGEX_CRLF = "(\\r\\n|\\r|\\n)";
	public static final String REGEX_TAB = "¥¥t";

    private StringEscapeUtil() {
    }
    
	/**
	 * 改行コードを文字列"\n"にエスケープします。
	 * 
	 * @param src エスケープしたい文字列
	 * @return エスケープ後の文字列
	 */
	public static String escapeCRLF(final String src) {
		if (src != null) {
			return src.replaceAll(REGEX_CRLF, "\\\\n");
		} else {
			return "";
		}
	}

	/**
	 * タブを文字列"\t"にエスケープします。
	 * 
	 * @param src エスケープしたい文字列
	 * @return エスケープ後の文字列
	 */
	public static String escapeTab(final String src) {
		if (src != null) {
			return src.replaceAll(REGEX_TAB, "\\\\t");
		} else {
			return "";
		}
	}

	/**
	 * ログ出力用の文字列にエスケープします。
	 * 
	 * @param src エスケープしたい文字列
	 * @return エスケープ後の文字列
	 */
	public static String escapeForLog(final String src) {
		if (src != null) {
			return StringEscapeUtil.escapeCRLF(StringEscapeUtil.escapeTab(src));
		} else {
			return "";
		}
	}
    
}
