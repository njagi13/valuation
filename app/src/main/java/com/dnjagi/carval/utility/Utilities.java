package com.dnjagi.carval.utility;

import com.dnjagi.carval.data.LogRecord;

import java.util.Date;

/**
 * Created by user on 7/30/2018.
 */

public class Utilities {
    public static String IMEI = "";
    public  static  void LogException(Exception ex)
    {
        LogRecord lr = new LogRecord();
        lr.EventDate = new Date();

        if(ex.getMessage().equals( "Unable to resolve host \"api.my-points-rewardz.com\": No address associated with hostname"))
        {
            lr.ErrorMessage = ex.getMessage() + "STACKTRACE MSG ***"+  ex.getStackTrace();
        }
        else
        {
            lr.ErrorMessage = ex.getMessage() + ex.getStackTrace();
        }

        lr.sent = 0;
        lr.save();
    }
}
