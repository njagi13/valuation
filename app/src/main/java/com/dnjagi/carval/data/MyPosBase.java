package com.dnjagi.carval.data;

//import com.dnjagi.carval.Db.InventoryDAO;

import com.dnjagi.carval.database.SugarRecord;
import com.dnjagi.carval.Model.DatabaseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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


