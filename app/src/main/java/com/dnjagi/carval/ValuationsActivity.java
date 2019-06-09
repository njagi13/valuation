package com.dnjagi.carval;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Interface.IMotovalServicesInterface;
import com.dnjagi.carval.Model.ValuationModel;
import com.dnjagi.carval.adapter.ValuationAdapter;
import com.dnjagi.carval.data.ApiClient;
import com.dnjagi.carval.utility.Utilities;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValuationsActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] listItem;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuations);
        Toolbar toolbar = findViewById(R.id.toolbar);
        View view = (View) findViewById(R.id.btnShow);
        context = getBaseContext();
        setSupportActionBar(toolbar);
        final Activity activity = this;
        IMotovalServicesInterface iMotovalServicesInterface =
                ApiClient.createService(IMotovalServicesInterface.class, GlobalVarible.token);
        Call<ArrayList<ValuationModel>> req = iMotovalServicesInterface.valuationList(GlobalVarible.email);
        req.enqueue(new Callback<ArrayList<ValuationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ValuationModel>> call, Response<ArrayList<ValuationModel>> response) {
                if (response.isSuccessful()) {
                    listView = (ListView) findViewById(R.id.listView);
                    textView = (TextView) findViewById(R.id.regNoTextView);
                    ArrayList<ValuationModel> reesponseData = response.body();
                    final ValuationAdapter adapter = new ValuationAdapter(activity, android.R.layout.simple_list_item_1, reesponseData);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            // TODO Auto-generated method stub
                            String value = adapter.getItem(position).RegistrationNumber;

                            Snackbar.make(view,value, Snackbar.LENGTH_SHORT)
                                    .show();

                        }
                    });
                } else {
                    Utilities.LogException(new Exception("Error posting Valuation at UploadRecordAPI.CreateValuation()"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ValuationModel>> call, Throwable t) {
                // something went completely south (like no internet connection)
                Utilities.LogException(new Exception("Error posting image!"));
            }
        });


    }

}


