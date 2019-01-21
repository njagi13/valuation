package com.dnjagi.carval.data;

import android.util.Log;
import android.widget.Toast;

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
                    filePath = unsentImages.get(i).ImagePath + unsentImages.get(i).FileName + ".png";
                    String uploadRecordID = unsentImages.get(i).UploadRecordID;
                    File file = new File(filePath);
                    if (file.exists()) {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
                        //THIS IS THE UPLOAD ID
                        String json = UUID.randomUUID().toString();
                        //lock image for posting to disable multipost of same item
                        ImagePathRecord sentRec = ImagePathRecord.findById(ImagePathRecord.class, unsentImages.get(i).getId());
                        sentRec.FileStatus = eFileStatus.LOCKED;
                        sentRec.save();

                        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), json);
                        retrofit2.Call<okhttp3.ResponseBody> req = inventoryInterface.postImage(body, name);
                        Response<ResponseBody> response = req.execute();
                        if (response.isSuccessful()) {
                            //UPDATE STATUS AFTER POST  ////
                            unsentImages.get(i).FileStatus = eFileStatus.SENT;
                            unsentImages.get(i).save();
                            ArrayList<ImagePathRecord> tt = ImagePathRecord.findAllRecords(ImagePathRecord.class);
                            Log.e("list", tt.toString());
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

    public void postImagesByUploadId(String uploadRecordId) {
        try {
            IPosServicesInterface inventoryInterface =
                    ApiClient.getClient().create(IPosServicesInterface.class);
            String filePath = "";
            ArrayList<ImagePathRecord> unsentImages = myPosBase.GetImageUploadsByUploadRecordId(uploadRecordId);
            if (unsentImages != null && unsentImages.size() > 0) {
                for (int i = 0; i < unsentImages.size(); i++) {
                    filePath = unsentImages.get(i).ImagePath + unsentImages.get(i).FileName + ".png";
                    String uploadRecordID = unsentImages.get(i).UploadRecordID;
                    File file = new File(filePath);
                    if (file.exists()) {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
                        //THIS IS THE UPLOAD ID
                        String json = UUID.randomUUID().toString();
                        //lock image for posting to disable multipost of same item
                        ImagePathRecord sentRec = ImagePathRecord.findById(ImagePathRecord.class, unsentImages.get(i).getId());
                        sentRec.FileStatus = eFileStatus.LOCKED;
                        sentRec.save();

                        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), json);
                        retrofit2.Call<okhttp3.ResponseBody> req = inventoryInterface.postImage(body, name);
                        req.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {

                                } else {
                                    Utilities.LogException(new Exception("Error posting Valuation at UploadRecordAPI.CreateValuation()"));
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                // something went completely south (like no internet connection)
                                Utilities.LogException(new Exception("Error posting image!"));
                            }
                        });
                    }
                }
                //todo: SAVE STATUS AS SENT WHEN USING A BACKEND SERVICE
             //    myPosBase.UpDateUploadStatus(uploadRecordId, eFileStatus.SENT);

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

                        ArrayList<ImagePathRecord> postedImagePathRecord = myPosBase.GetUnsentUploadRecords(uploadRecord.UploadRecordID.toString());
                        myPosBase.UpDateUploadStatus(uploadRecord.UploadRecordID.toString(), eFileStatus.PENDING_POST);
                        //todo: post the images asynchronously // change to backend service later
                        UploadRecordAPI uploadRecordAPI = new UploadRecordAPI();
                        uploadRecordAPI.postImagesByUploadId(uploadRecord.UploadRecordID.toString());
                        // confirm
                        ArrayList<ImagePathRecord> arechanged = ImagePathRecord.findAllRecords(ImagePathRecord.class);
                        int y = 0;

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
