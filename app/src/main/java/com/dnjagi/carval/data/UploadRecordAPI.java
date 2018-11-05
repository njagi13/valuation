package com.dnjagi.carval.data;

import android.os.Handler;
import android.os.Looper;
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
            ArrayList<ImagePathRecord> all = ImagePathRecord.findAllRecords(ImagePathRecord.class);
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
                            ImagePathRecord imagePathRecord = unsentImages.get(i);
                            imagePathRecord.FileStatus = eFileStatus.SENT;
                            imagePathRecord.save();

                            ArrayList<ImagePathRecord> tt = ImagePathRecord.findAllRecords(ImagePathRecord.class);

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


                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<ImagePathRecord> postedImagePathRecord = myPosBase.GetUnsentUploadRecords(uploadRecord.UploadRecordID.toString());
                                myPosBase.UpDateUnsentUploadRecords(uploadRecord.UploadRecordID.toString());
//                                for (int i = 0; i < postedImagePathRecord.size(); i++)
//                                {
//                                    ImagePathRecord imagePathRecord = ImagePathRecord.findById(ImagePathRecord.class,postedImagePathRecord.get(i).getId());
//                                    imagePathRecord.FileStatus = eFileStatus.PENDING_POST;
//                                    imagePathRecord.FileName = postedImagePathRecord.get(i).FileName;
//                                    imagePathRecord.save();
//                                }
                                // confirm
                                ArrayList<ImagePathRecord> arechanged = ImagePathRecord.findAllRecords(ImagePathRecord.class);
                                int y = 0;

                            }
                        });

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
