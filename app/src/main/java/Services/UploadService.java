package Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.data.UploadRecordAPI;
import com.dnjagi.carval.database.SugarContext;

import java.util.ArrayList;
import java.util.Date;

import static com.dnjagi.carval.MainActivity.appContext;

public class UploadService extends IntentService {
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
        backgroundThreadFunc();
    }

    public UploadService() {
        // Used to name the worker thread, important only for debugging.
        super("upload-service");
    }

    Date nextPollInventory = null;
    // poll after 5 secs
    public final long MS_TO_MINS = 1000 * 5;

    public void pollPendingValuationImages() {
        try {
            Date now = new Date();
            if (GlobalVarible.hasActiveInternetConnection(getApplicationContext()) && nextPollInventory == null || (now.getTime() > (nextPollInventory.getTime() + (MS_TO_MINS)))) {
                nextPollInventory = now;
                UploadRecordAPI uploadRecordAPI = new UploadRecordAPI();
                uploadRecordAPI.PostImages();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean IsRunning;

    void backgroundThreadFunc() {
        while (IsRunning == true) {
            pollPendingValuationImages();
        }
    }

}
