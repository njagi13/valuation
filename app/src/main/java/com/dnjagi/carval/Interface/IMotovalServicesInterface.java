package com.dnjagi.carval.Interface;

import com.dnjagi.carval.data.UploadRecord;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user on 7/25/2018.
 */

public interface IMotovalServicesInterface {
    @Multipart
    @POST("/api/Assessment/PostAssessment")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("uploadId") RequestBody uploadId);

    @GET("/api/Assessment/GetUserValuations")
    Call<ArrayList<UploadRecord>> valuationList(
            @Query("email") String email
    );
}
