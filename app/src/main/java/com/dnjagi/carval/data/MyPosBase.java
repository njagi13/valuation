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


    public static ImagePathRecord GetUnsentUploadRecordByID(int recordID) {
        ImagePathRecord result = (ImagePathRecord) SugarRecord.findById(ImagePathRecord.class, recordID);
        return result;
    }


    public static ArrayList<ImagePathRecord> GetUnsentUploadRecords(String uploadRecordID) {
        ArrayList<ImagePathRecord> result = (ArrayList<ImagePathRecord>) SugarRecord.find(ImagePathRecord.class, "UPLOAD_RECORD_ID = ?", uploadRecordID);
        return result;
    }

    public static void UpDateUploadStatus(String uploadRecordID , int postStatus) {
        SugarRecord.executeQuery("UPDATE IMAGE_PATH_RECORD SET FILE_STATUS ="+ postStatus +" WHERE  UPLOAD_RECORD_ID = ?", uploadRecordID);
    }



    public static ArrayList<ImagePathRecord> GetUnsentUploads() {
        ArrayList<ImagePathRecord> result = (ArrayList<ImagePathRecord>) SugarRecord.find(ImagePathRecord.class, "FILE_STATUS = ?", String.valueOf(eFileStatus.PENDING_SUBMISSION));
        return result;
    }

    public static ArrayList<ImagePathRecord> GetReadyToSendUploads() {
        ArrayList<ImagePathRecord> result = (ArrayList<ImagePathRecord>) SugarRecord.find(ImagePathRecord.class, "FILE_STATUS = ?", String.valueOf(eFileStatus.PENDING_POST));
        return result;
    }

    public static ArrayList<ImagePathRecord> GetImageUploadsByUploadRecordId(String uploadID) {
        ArrayList<ImagePathRecord> result = (ArrayList<ImagePathRecord>) SugarRecord.find(ImagePathRecord.class, "UPLOAD_RECORD_ID = ?", uploadID);
        return result;
    }


}


