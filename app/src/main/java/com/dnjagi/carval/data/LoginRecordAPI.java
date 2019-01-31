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


            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        IsLogged[0] = true;
                    } else {
                        Utilities.LogException(new Exception("Error posting Valuation at UploadRecordAPI.CreateValuation()"));
                        IsLogged[0] = false;
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    IsLogged[0] = false;
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e) {
            Utilities.LogException(e);
        }
        return IsLogged[0];
    }
}
