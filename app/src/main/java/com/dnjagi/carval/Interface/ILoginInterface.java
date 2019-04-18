package com.dnjagi.carval.Interface;


import com.dnjagi.carval.data.AccessTokenResponse;
import com.dnjagi.carval.data.LoginRecord;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginInterface {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessTokenResponse> loginUser(@Field("grant_type") String grantType,
                                        @Field("username") String username,
                                        @Field("password") String password,
                                        @Header("Authorization") String authorization);
}
