package com.dnjagi.carval.data;

//import com.dnjagi.carval.Db.InventoryDAO;

import com.dnjagi.carval.database.SugarRecord;

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




}


