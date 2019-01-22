package com.dnjagi.carval.data;

import android.util.Log;

import com.dnjagi.carval.Interface.ILoginInterface;
import com.dnjagi.carval.utility.Utilities;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRecordAPI {

    public boolean LoginUser(LoginRecord loginRecord) {
        final boolean[] IsLogged = {false};
        try {

            ILoginInterface iLoginInterface =
                    ApiClient.getClient().create(ILoginInterface.class);
            Call<ResponseBody> req = iLoginInterface.loginUser(loginRecord.email , loginRecord.password);

            Response<ResponseBody> response = req.execute();
          if(response.isSuccessful())
          {
              IsLogged[0] = false;
          }
        } catch (Exception e) {
            Utilities.LogException(e);
        }
        return IsLogged[0];
    }
}
