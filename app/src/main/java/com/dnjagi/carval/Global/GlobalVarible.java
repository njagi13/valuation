package com.dnjagi.carval.global;

import com.dnjagi.carval.data.UploadRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 7/25/2018.
 */

public class GlobalVarible {
    public static UploadRecord uploadRecord;
    public static String fileRoot = "root";
    public static String url = "http://09b2a1d5.ngrok.io/";
    public static String imgpath = "";
    public static boolean RefreshGrid = false;
    public static int RequiredImagesCount = 3;

    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
