package com.sww.mystandard.http.params;

import android.text.TextUtils;
import com.sww.mystandard.external.LiveCommonConfig;
import okhttp3.Request;

public class CommonParams {

    public static void initHeaderParams(Request.Builder builder) {
        if (!TextUtils.isEmpty(LiveCommonConfig.getAccessToken()))
            builder.addHeader("access_token", LiveCommonConfig.getAccessToken());
    }

}
