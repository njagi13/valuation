package com.dnjagi.carval.data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Interface.ILoginInterface;
import com.dnjagi.carval.MainActivity;
import com.dnjagi.carval.utility.Utilities;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRecordAPI {

    Context mContext;
    Activity mActivity;

    public LoginRecordAPI(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    boolean IsLogged = false;
    public boolean LoginUser(LoginRecord loginRecord) {
        try {

            ILoginInterface iLoginInterface =
                    ApiClient.getClient().create(ILoginInterface.class);

            byte[] credentials = "clientId:clientSecret".getBytes();
            String basicAuth = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials);
            }
            GlobalVarible.email = loginRecord.email;
            Call<AccessTokenResponse> req = iLoginInterface.loginUser("password", loginRecord.email, loginRecord.password, basicAuth);
            req.enqueue(new Callback<AccessTokenResponse>() {
                @Override
                public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                    System.out.println(response.toString());
                    if (response.isSuccessful()) {
                        AccessTokenResponse res = response.body();
                        SharedPreferences sharedpreferences = mContext.getSharedPreferences(GlobalVarible.LoggedIn, mContext.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Email",  GlobalVarible.email );
                        editor.putString("Token", res.access_token);
                        editor.putString("LoginDateKey", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
                        editor.putLong("ExpiredDate", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
                        editor.commit();
                        GlobalVarible.token = res.access_token;

                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "Login Error!" , Toast.LENGTH_LONG).show();
                        Utilities.LogException(new Exception("Error Login In"));

                    }
                }

                @Override
                public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                    IsLogged = false;
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e) {
            Utilities.LogException(e);
        }
        return IsLogged;
    }
}
