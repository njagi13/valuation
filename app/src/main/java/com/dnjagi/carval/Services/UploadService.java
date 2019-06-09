package com.dnjagi.carval.Services;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.UploadRecordAPI;
import com.dnjagi.carval.database.SugarContext;
import com.dnjagi.carval.utility.Utilities;

import java.util.Date;

import static com.dnjagi.carval.MainActivity.appContext;

public class UploadService extends IntentService {
    static boolean IsRunning;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (appContext != null) {
            SugarContext.init(appContext);
        } else {
            appContext = getApplicationContext();
            if (appContext != null) {
                SugarContext.init(appContext);
            }
        }
        IsRunning = true;
        backgroundThreadFunc();
    }

    public UploadService() {
        // Used to name the worker thread, important only for debugging.
        super("upload-service");
    }

    Date nextPollInventory = null;
    // poll after 20 secs
    public final long MS_TO_MINS = 1000 * 20;

    public void pollPendingValuationImages() {
        try {
            Date now = new Date();
            if (GlobalVarible.hasActiveInternetConnection(getApplicationContext()) && nextPollInventory == null || (now.getTime() > (nextPollInventory.getTime() + (MS_TO_MINS)))) {
                nextPollInventory = now;
                UploadRecordAPI uploadRecordAPI = new UploadRecordAPI();
                //disable posting images in service
               // uploadRecordAPI.PostImages();
            }
        } catch (Exception e) {
            Utilities.LogException(e);
        }
    }



    void backgroundThreadFunc() {
        while (IsRunning == true) {
            pollPendingValuationImages();
        }
    }

}
