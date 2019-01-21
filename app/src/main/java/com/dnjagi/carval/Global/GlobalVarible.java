package com.dnjagi.carval.Global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.utility.Utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 7/25/2018.
 */

public class GlobalVarible {
    public static UploadRecord uploadRecord;
    public static String fileRoot = "root";
    public static String url = "http://d93267c1.ngrok.io/";
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

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Utilities.LogException(new Exception("Error checking internet connection"));
            }
        } else {
            Utilities.LogException(new Exception("No network available!"));
        }
        return false;
    }
}
