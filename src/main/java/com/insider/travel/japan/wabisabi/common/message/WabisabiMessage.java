package com.insider.travel.japan.wabisabi.common.message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ZULEY
 */
public class WabisabiMessage {
    
    /**
     * Tsubomiで使用する電文を定義します。
     */
    public enum Protocols {
		TRF_NOTIFY_MESSAGE;			// 通知メッセージ	
    }

    /**
     * responseCodeを定義します。
     */
    public static final class ResponseCode {
		static final int ERR_CODE_MIN = 1;	// Error Code Min Value
		static final int ERR_CODE_MAX = 100;	// Error Code Max Value
		static final int WRN_CODE_MIN = 101;	// Warning Code Min Value
		static final int WRN_CODE_MAX = 200;	// Warning Code Max Value

        private ResponseCode() {
        }

        // TODO 体系を決める必要あり。とりあえず文字列で定義しているが整理必要。
        public static final String OK = "0";                // OK
		//.Error 1 - 100
        // --------------------------------------------------------------------------
        // 共通エラー 1～10
        public static final String ERR_COM_RELAYSERVER = "1";   // 中継サーバエラー

		public static boolean isOK(String responseCode) {
			return responseCode.equals(OK);
		}

		public static boolean isError(String responseCode) {
			int codeValue = Integer.parseInt(responseCode);
			return ERR_CODE_MIN <= codeValue  && codeValue <= ERR_CODE_MAX;
		}

		public static boolean isWarn(String responseCode) {
			int codeValue = Integer.parseInt(responseCode);
			return WRN_CODE_MIN <= codeValue  && codeValue <= WRN_CODE_MAX;
		}
    
    /**
     * TsubomiMessage内のString型以外の変数名を定義します。
     */
    public static final class Parameter {
        /**
         * notifyLevel
         */
        public static final String NOTIFY_LEVEL = "notifyLevel";

        private Parameter() {
        }
    }

	/**
	 * 通知レベル
	 */
	public enum NotifyLevel {
		TRACE,
		DEBUG,
		INFO,
		WARN,
		ERROR
	}

    /**
     * 結果コード
     */
    private String responseCode;

    /**
     * 結果メッセージ
     */
    private String responseMessage;

	/**
	 * 通知メッセージレベル
	 */
	private NotifyLevel notifyLevel; 

	/**
	 * 通知メッセージ
	 */
	private String notifyMessage; 

    /**
     * 結果コードを返す。
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * 結果コードを設定する。
     * @param responseCode - the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * 通知メッセージタイプを取得する
	 * @return the notifyLevel
	 */
	public NotifyLevel getNotifyLevel() {
		return notifyLevel;
	}

	/**
	 * 通知メッセージタイプを設定する
	 * @param notifyLevel the notifyLevel to set
	 */
	public void setNotifyLevel(NotifyLevel notifyLevel) {
		this.notifyLevel = notifyLevel;
	}

	/**
	 * 通知メッセージを取得する
	 * @return the notifyMessage
	 */
	public String getNotifyMessage() {
		return notifyMessage;
	}

	/**
	 * 通知メッセージを設定する
	 * @param notifyMessage the notifyMessage to set
	 */
	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}

    }
    
    /**
     * メッセージの内容を全出力する
     * @return 
     */
    public String detailParameters() {
        return "WabisabiMessage : " + Stream.of(this.getClass().getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .filter(field -> isNull(field))
                    .map(field -> formatField(field))
                    .collect(Collectors.joining(","));
    }
    
    private boolean isNull(Field field) {
        try {
            return field.get(this) != null;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return false;
        }
    }
    
    private String formatField(Field field) {
        try {
            return field.getName() + ":" + field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return "!unread field!";
        }
    }
    
}
