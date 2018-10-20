package com.dnjagi.carval.data;

import android.util.Log;
import com.dnjagi.carval.Interface.IPosServicesInterface;
import com.dnjagi.carval.Interface.IUploadRecordInterface;
import com.dnjagi.carval.dataObject.uploadDataObj;
import com.dnjagi.carval.enums.eFileStatus;
import com.dnjagi.carval.utility.Utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 7/30/2018.
 */

public class UploadRecordAPI extends APIBase<uploadDataObj> {
    final MyPosBase myPosBase = new MyPosBase();

    public void PostImages() {
        try {
            IPosServicesInterface inventoryInterface =
                    ApiClient.getClient().create(IPosServicesInterface.class);
            String filePath = "";
            ArrayList<ImagePathRecord> unsentImages = myPosBase.GetReadyToSendUploads();
            if (unsentImages != null && unsentImages.size() > 0) {
                for (int i = 0; i < unsentImages.size(); i++) {
                    filePath = unsentImages.get(i).ImagePath;
                    String uploadRecordID = unsentImages.get(i).UploadRecordID;
                    File file = new File(filePath);
                    if (file.exists()) {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

                        //THIS IS THE UPLOAD ID
                        String json = UUID.randomUUID().toString();
                        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), json);
                        retrofit2.Call<okhttp3.ResponseBody> req = inventoryInterface.postImage(body, name);
                        Response<ResponseBody> response = req.execute();
                        if (response.isSuccessful()) {
//todo:  Update Table on sent Rec
                            int res = 1;
                        } else {
                            Utilities.LogException(new Exception("Error posting record!"));
                        }
                    }
                }
            }


        } catch (Exception ex) {
            Utilities.LogException(ex);
        }
    }

    public void CreateValuation(final UploadRecord uploadRecord) {
        try {
            IUploadRecordInterface iUploadRecordInterface =
                    ApiClient.getClient().create(IUploadRecordInterface.class);
            Call<UploadRecord> req = iUploadRecordInterface.postUploadRecordInformation(uploadRecord);
            req.enqueue(new Callback<UploadRecord>() {
                @Override
                public void onResponse(Call<UploadRecord> call, Response<UploadRecord> response) {
                    if (response.isSuccessful()) {
                        // CHANGE STATUS TO READY FOR SUBMISSION FOR IMAGES TO BE SENT TO SERVER
                        ImagePathRecord postedImagePathRecord = myPosBase.GetUnsentUploadRecordByID(uploadRecord.UploadRecordID.toString());
                        postedImagePathRecord.FileStatus = eFileStatus.PENDING_SUBMISSION;
                        postedImagePathRecord.save();
                    } else {
                        Utilities.LogException(new Exception("Error posting Valuation at UploadRecordAPI.CreateValuation()"));
                    }
                }

                @Override
                public void onFailure(Call<UploadRecord> call, Throwable t) {
                    // something went completely south (like no internet connection)
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e) {
            Utilities.LogException(e);
        }
    }


}
