package com.dnjagi.carval.data;

//import com.dnjagi.carval.Db.InventoryDAO;

import com.dnjagi.carval.database.SugarRecord;
import com.dnjagi.carval.enums.eFileStatus;

import java.util.ArrayList;

/**
 * Created by Waithera on 12/1/2017.
 */

public class MyPosBase extends SugarRecord {

    boolean isSuccess = false;

    public static ArrayList<UserRecord> GetUserDetails() {
        ArrayList<UserRecord> result = (ArrayList<UserRecord>) UserRecord.findWithQuery(UserRecord.class, "Select * from USER_RECORD");
        return result;
    }


    public static ImagePathRecord GetUnsentUploadRecordByID(String uploadRecordID) {
        ImagePathRecord result = (ImagePathRecord) SugarRecord.find(ImagePathRecord.class, "UPLOAD_RECORD_ID = ?", uploadRecordID);
        return result;
    }

    public static ArrayList<ImagePathRecord> GetReadyToSendUploads() {
        ArrayList<ImagePathRecord> result = (ArrayList<ImagePathRecord>) SugarRecord.find(ImagePathRecord.class, "UPLOAD_RECORD_ID = ?", String.valueOf(eFileStatus.PENDING_POST));
        return result;
    }
}


