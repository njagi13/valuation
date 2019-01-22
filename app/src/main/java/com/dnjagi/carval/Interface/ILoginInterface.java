package com.dnjagi.carval.Interface;


import com.dnjagi.carval.data.LoginRecord;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginInterface {
    @Headers("Accept: application/json")
    @POST("api/Account/Login")
    Call<ResponseBody> loginUser(@Query("email") String email,
                                 @Query("password") String password);
}
