package com.dnjagi.carval.data;

import android.text.TextUtils;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Model.AuthenticationInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    static GlobalVarible globalVars = new GlobalVarible();

    public static final String BASE_URL = globalVars.url;

    private static Retrofit retrofit = null;

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    //set post timeout
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS);
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    okhttp3.Request newRequest  = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer " + GlobalVarible.token)
//                            .build();
//                    return chain.proceed(newRequest);
//                }
//            }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
