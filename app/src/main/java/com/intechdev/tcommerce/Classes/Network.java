package com.intechdev.tcommerce.Classes;

import android.webkit.WebSettings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network {
    public static OkHttpClient getOkHttpClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(6 , TimeUnit.MINUTES)
                .readTimeout(6 , TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }
}
