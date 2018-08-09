package com.dnjagi.carval.Interface;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 7/25/2018.
 */

public interface IPosServicesInterface {
    @Multipart
    @POST("/api/Assessment/PostAssessment")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("uploadId") RequestBody uploadId);
}
