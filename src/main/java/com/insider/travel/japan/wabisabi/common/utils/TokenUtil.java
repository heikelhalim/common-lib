package com.insider.travel.japan.wabisabi.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * Create token class
 */
public final class TokenUtil {
    
    private static final int TOKEN_LENGTH = 16; //16*2=32バイト
    private static final String ALGORITHM = "SHA1PRNG";

    private TokenUtil() {
    }

    /**
     * 32バイトのCSRFトークンを作成し返却します。
     * @return トークン
     */
    public static String getToken() {

        byte[] token = new byte[TOKEN_LENGTH];
        StringBuffer buf = new StringBuffer();
        SecureRandom random = null;

        try {
            random = SecureRandom.getInstance(ALGORITHM);
            random.nextBytes(token);

            for (int i = 0; i < token.length; i++) {
                buf.append(String.format("%02x", token[i]));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }
    
}
