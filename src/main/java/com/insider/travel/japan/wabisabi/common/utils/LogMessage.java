package com.insider.travel.japan.wabisabi.common.utils;

/**
 *
 * 
 */
public interface LogMessage {
    
    /**
     * ログ出力メッセージリソースファイルのキー（コード）を返します。<br>
     * 例）E110003<br>
     * @return メッセージコード
     */
    String getMessageCode();

    /**
     * ログ出力メッセージリソースファイルの値（メッセージ）を返します。<br>
     * 置換文字がある場合は引数に与えてください。<br>
     * 例）○○が起動しました。<br>
     * @param replacements　置換文字
     * @return メッセージ
     */
    String getMessage(String... replacements);
    
}
