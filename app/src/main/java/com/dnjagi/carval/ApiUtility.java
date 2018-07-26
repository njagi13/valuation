package com.dnjagi.carval;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Interface.IPosServicesInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtility implements IPosServicesInterface {
    private IPosServicesInterface service;

    public ApiUtility() {
        GlobalVarible globalVars = new GlobalVarible();
        String API_BASE_URL = globalVars.url;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        service = retrofit.create(IPosServicesInterface.class);
    }


//    @Override
//    public Call<InventoryRecordAPI> inventorySingle(String ParentId, String StoreId, String ClassId, String sku, String token) {
//        return service.inventorySingle(ParentId, StoreId, ClassId, sku, token);
//    }


    @Override
    public Call<ResponseBody> postImage(MultipartBody.Part image, RequestBody name) {
        return service.postImage(image, name);
    }
}