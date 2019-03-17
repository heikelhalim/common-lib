package com.insider.travel.japan.wabisabi.common;

import com.insider.travel.japan.wabisabi.common.utils.CommonMessage;
import com.insider.travel.japan.wabisabi.common.utils.LogMessage;

/**
 * 引数で指定されたLogMessageからエラーコードとエラーメッセージを取得し保持します。<br>
 * 生成したWabisabiExceptionの次のメソッドで保持しているエラーコード、エラーメッセージを取得してください。<br>
 * <pre>
 * 　getMessageCode()
 * 　getMessage()
 * </pre>
 * <pre>
 * 使用例）
 * try {
 *  connection = new URL("http://" + uri.getHost()).openConnection(proxy);
 * } catch (MalformedURLException ex) {
 *  throw new WabisabiException(ex, AgentMessages.ERROR_MALFORMED_URL, "プロトコル", "文字列解析", "XML");
 * } catch (IOException ex) {
 *  throw new WabisabiException(ex, AgentMessages.ERROR_PROXY_CONNECTION, uri);
 * } catch (Exception ex) {
 *  throw new WabisabiException(ex);  //  LogMessageはCommonMessages.ERROR_UNEXPECTED（予期せぬエラー）となります。
 *                                   //  あまり使用してほしくはないです。個別にエラー設定をしてほしいです。
 * }
 * 
 * ※キャッチしたところで、
 * LOGGER.error(e.getMessage(), e);
 * とやってログに出力してください。
 * </pre>
 * 
 * 
 */

public class WabisabiException extends RuntimeException {
    
    private final String messageCode;
	
        /**
         * 引数に指定されたExceptionとlogMessageでWabisabiExceptionを生成します。<br>
         * @param cause An exception which occurred.
         * @param logMessage ログメッセージ
         * @param replacements ログメッセージから取得したエラーメッセージへの置換文字列 
         */
	public WabisabiException(Throwable cause, LogMessage logMessage, String...replacements) {
		super(logMessage.getMessage(replacements), cause);
                messageCode = logMessage.getMessageCode();
	}

        /**
         * 引数に指定されたExceptionでWabisabiExceptionを生成します。<br>
         * LogMessageはCommonMessages.ERROR_UNEXPECTED（予期せぬエラー）となります。<br>
         * @param cause An exception which occurred.
         */
        public WabisabiException(Throwable cause) {
		super(CommonMessage.ERROR_UNEXPECTED.getMessage(), cause);
                messageCode = CommonMessage.ERROR_UNEXPECTED.getMessageCode();
	}

        /**
         * 引数に指定されたLogMessageでWabisabiExceptionを生成します。<br>
         * @param logMessage ログメッセージ
         * @param replacements ログメッセージから取得したエラーメッセージへの置換文字列 
         */
        public WabisabiException(LogMessage logMessage, String...replacements) {
		super(logMessage.getMessage(replacements));
                messageCode = logMessage.getMessageCode();
	}

        public String getMessageCode() {
		return messageCode;
	}
    
}
