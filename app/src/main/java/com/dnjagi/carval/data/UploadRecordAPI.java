package com.dnjagi.carval.data;

import com.dnjagi.carval.Interface.IPosServicesInterface;
import com.dnjagi.carval.dataObject.uploadDataObj;
import com.dnjagi.carval.global.GlobalVarible;
import com.dnjagi.carval.utility.Utilities;
import com.google.gson.Gson;

import java.io.File;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by user on 7/30/2018.
 */

public class UploadRecordAPI extends APIBase<uploadDataObj> {
    final MyPosBase myPosBase = new MyPosBase();

    public void PostUploadRecord(UploadRecord uploadRecord) {
        try {

            IPosServicesInterface inventoryInterface =
                    ApiClient.getClient().create(IPosServicesInterface.class);
            String filePath = "/storage/emulated/0/Android/data/com.dnjagi.carval/files/upload//KCC7874H/0e5c4a15-6fac-4f14-9325-72c9a5f4b620_20180731102109.png"; // GlobalVarible.imgpath;
            File file = new File(filePath);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

            //THIS IS THE UPLOAD ID
            String json = UUID.randomUUID().toString();
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), json);
            retrofit2.Call<okhttp3.ResponseBody> req = inventoryInterface.postImage(body, name);
            Response<ResponseBody> response = req.execute();
            if (response.isSuccessful()) {
//Update Table on sent Rec
                int res = 1;
            } else {
                Utilities.LogException(new Exception("Error posting record!"));
            }

        } catch (Exception ex) {
            Utilities.LogException(ex);
        }
    }


}
