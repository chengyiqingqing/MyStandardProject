package com.sww.mystandard.external;

public class LiveCommonConfig {

    private static LoginInfoListener loginInfoListener;

    public static String getAccessToken() {
        if (loginInfoListener != null) return loginInfoListener.getToken();
        return "";
    }

}
