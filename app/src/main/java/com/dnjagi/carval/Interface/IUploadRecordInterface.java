package com.dnjagi.carval.Interface;

import com.dnjagi.carval.data.UploadRecord;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 8/30/2018.
 */

public interface IUploadRecordInterface {
    @Headers("Accept: application/json")
    @POST("api/Assessment/CreateValuation")
    Call<UploadRecord> postUploadRecordInformation(@Body UploadRecord model);
}
