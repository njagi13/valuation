package com.dnjagi.carval.data;

import com.dnjagi.carval.Interface.IPosServicesInterface;
import com.dnjagi.carval.dataObject.uploadDataObj;
import com.dnjagi.carval.global.GlobalVarible;
import com.dnjagi.carval.utility.Utilities;

import java.io.File;

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

    public void PostUploadRecord() {
        try {

            IPosServicesInterface inventoryInterface =
                    ApiClient.getClient().create(IPosServicesInterface.class);
            String filePath = GlobalVarible.imgpath;
            File file = new File(filePath);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

            retrofit2.Call<okhttp3.ResponseBody> req = inventoryInterface.postImage(body, name);
            Response<ResponseBody> response = req.execute();
            if (response.isSuccessful()) {
//Update Table on sent Rec
            } else {
                Utilities.LogException(new Exception("Error posting record!"));
            }

        } catch (Exception ex) {
            Utilities.LogException(ex);
        }
    }


}
