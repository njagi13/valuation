package com.dnjagi.carval.Interface;

import com.dnjagi.carval.data.CompanyRecord;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.data.ValuationTypeRecord;

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
    Call<ResponseBody> PostImage(@Part MultipartBody.Part image, @Part("uploadId") RequestBody uploadId);

    @GET("/api/Assessment/GetUserValuations")
    Call<ArrayList<UploadRecord>> ValuationList(
            @Query("email") String email
    );

    @GET("/api/Assessment/GetValuationTypes")
    Call<ArrayList<ValuationTypeRecord>> GetValuationTypes();

    @GET("/api/Assessment/GetCompanies")
    Call<ArrayList<CompanyRecord>> GetCompanies();
}
