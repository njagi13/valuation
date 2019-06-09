package com.dnjagi.carval;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

public class ValuationsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ValuationsFragment() {
        // Required empty public constructor
    }

    ListView listView;
    TextView textView;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("My Valuations");
        final View view = inflater.inflate(R.layout.fragment_valuations, container, false);
        final Button btn = (Button) view.findViewById(R.id.btnShow1);
        IMotovalServicesInterface iMotovalServicesInterface =
                ApiClient.createService(IMotovalServicesInterface.class, GlobalVarible.token);
        Call<ArrayList<ValuationModel>> req = iMotovalServicesInterface.valuationList(GlobalVarible.email);
        req.enqueue(new Callback<ArrayList<ValuationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ValuationModel>> call, Response<ArrayList<ValuationModel>> response) {
                if (response.isSuccessful()) {
                    listView = (ListView) getView().findViewById(R.id.listView);
                    textView = (TextView) getView().findViewById(R.id.regNoTextView);
                    ArrayList<ValuationModel> reesponseData = response.body();
                    final ValuationAdapter adapter = new ValuationAdapter(getActivity(), R.layout.valuationlist_item, reesponseData);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            String value = adapter.getItem(position).RegistrationNumber;
                            Snackbar.make(btn, value, Snackbar.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Snackbar.make(btn, "Error getting valuations. Please login again.", Snackbar.LENGTH_LONG).show();
                    Utilities.LogException(new Exception("Error getting valuations. Please login again."));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ValuationModel>> call, Throwable t) {
                Snackbar.make(btn, "Error getting valuations. Please login again.", Snackbar.LENGTH_LONG).show();
                Utilities.LogException(new Exception("Error getting valuations" + " " + t.getMessage()));
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

       public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
