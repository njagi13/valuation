package com.dnjagi.carval.Model;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
//space after bearer and token is MUST
        Request.Builder builder = original.newBuilder()
                .addHeader("Authorization", "Bearer " + authToken);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
