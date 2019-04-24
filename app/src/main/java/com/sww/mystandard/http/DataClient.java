package com.sww.mystandard.http;

import android.net.Uri;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * @author ShaoWenWen
 * @date 2019/4/24
 */
public class DataClient<T> {

    private volatile static OkHttpClient okHttpClient;
    private final Class<T> serviceClass;
    private T dataService;


    public DataClient(Class<T> serviceClass,String hostUrl){
        this.serviceClass = serviceClass;
        initClient(hostUrl);
    }

    private void initClient(String hostUrl) {
//        dataService = getRetrofit(hostUrl).create(serviceClass);
    }

    private Retrofit getRetrofit(String hostUrl){
        if (okHttpClient == null) {
//            okHttpClient = g
        }
        // TODO: 待返回 by ShaoWenWen 2019/4/24
        return null;
    }

    private OkHttpClient getOkHttpClient(){
        Interceptor networkInterceptor = getNetworkInterceptor();
        // TODO: 待返回 by ShaoWenWen 2019/4/24
        return null;
    }

    private Interceptor getNetworkInterceptor() {
        return chain -> {
            Request request = chain.request();
            Uri uri = Uri.parse(request.url().toString());
            Map<String, String> paramsMap = parseParams(request, uri);
            Request.Builder builder = request.newBuilder();
            initHeaderData(builder);
            return chain.proceed(builder.build());
        };
    }

    private void initHeaderData(Request.Builder builder) {

    }

    private Map<String, String> parseParams(Request request, Uri uri) throws IOException{
        Map<String,String> paramsMap = new HashMap<>();
        RequestBody requestBody = request.body();
        if (requestBody != null && requestBody.contentLength() > 0) {
            if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                for (int i = 0; i < formBody.size(); i++) {
                    paramsMap.put(formBody.name(i), formBody.value(i));
                }
            }
        } else {
            String query = uri.getQuery();
            if (!TextUtils.isEmpty(query)){
                String[] splitArray = query.split("&");
                for (String str : splitArray){
                    String[] splitParam = str.split("=");
                    if (splitParam.length == 2){
                        paramsMap.put(splitParam[0],splitParam[1]);
                    }
                }
            }
        }
        return paramsMap;
    }



}
