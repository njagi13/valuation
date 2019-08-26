package com.dnjagi.carval;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Interface.IMotovalServicesInterface;
import com.dnjagi.carval.adapter.ValuationAdapter;
import com.dnjagi.carval.data.ApiClient;
import com.dnjagi.carval.data.CompanyRecord;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.data.ValuationTypeRecord;
import com.dnjagi.carval.utility.Utilities;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ValuationTypeFragment extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;

    public ValuationTypeFragment() {
        // Required empty public constructor
    }


    public static ValuationTypeFragment newInstance(String param1, String param2) {
        ValuationTypeFragment fragment = new ValuationTypeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    private Spinner valuationTypeSpinner;
    private Spinner companySpinner;
    private Button btn;
    private ArrayList<ValuationTypeRecord> _valuationTypes;
    private ArrayList<CompanyRecord> _companyRecords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_valuation_type, container, false);
        btn = (Button) view.findViewById(R.id.btnShow1);
        valuationTypeSpinner = (Spinner) view.findViewById(R.id.valuation_type_spinner);
        companySpinner = (Spinner) view.findViewById(R.id.company_spinner);
        Button btnNext = (Button) view.findViewById(R.id.buttonNext);
        Button btnCancel = (Button) view.findViewById(R.id.buttonCancel);
        btnNext.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        initializeUI();
        return view;
    }

    private void initializeUI() {
//populate valuation type
        final ArrayList<String> valuationTypeRecords = new ArrayList<>();
        IMotovalServicesInterface iMotovalServicesInterface =
                ApiClient.createService(IMotovalServicesInterface.class, GlobalVarible.token);
        Call<ArrayList<ValuationTypeRecord>> req = iMotovalServicesInterface.GetValuationTypes();
        req.enqueue(new Callback<ArrayList<ValuationTypeRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<ValuationTypeRecord>> call, Response<ArrayList<ValuationTypeRecord>> response) {
                if (response.isSuccessful()) {
                    ArrayList<ValuationTypeRecord> reesponseData = response.body();
                    if (reesponseData != null && reesponseData.size() > 0) {
                        _valuationTypes = reesponseData;
                        for (int i = 0; i < reesponseData.size(); i++) {
                            ValuationTypeRecord v = new ValuationTypeRecord();
                            valuationTypeRecords.add(reesponseData.get(i).Description);
                        }

                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, valuationTypeRecords);
                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        valuationTypeSpinner.setAdapter(adapter);
                        valuationTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                GlobalVarible.SelectedValuationType = _valuationTypes.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Snackbar.make(btn, "General Valuation selected.", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        reesponseData = new ArrayList<>();
                        Snackbar.make(btn, "0 Valuation Types", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(btn, "Error getting Valuation Types. Please login again.", Snackbar.LENGTH_LONG).show();
                    Utilities.LogException(new Exception("Error getting Valuation Types. Please login again."));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ValuationTypeRecord>> call, Throwable t) {
                Snackbar.make(btn, "Error getting Valuation Types. Please try again.", Snackbar.LENGTH_LONG).show();
                Utilities.LogException(new Exception("Error getting Valuation Types" + " " + t.getMessage()));
            }
        });

//populate company
        final ArrayList<String> companyRecords = new ArrayList<>();
        Call<ArrayList<CompanyRecord>> companyReq = iMotovalServicesInterface.GetCompanies();
        companyReq.enqueue(new Callback<ArrayList<CompanyRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<CompanyRecord>> call, Response<ArrayList<CompanyRecord>> response) {
                if (response.isSuccessful()) {
                    ArrayList<CompanyRecord> reesponseData = response.body();
                    if (reesponseData != null && reesponseData.size() > 0) {
                        _companyRecords = reesponseData;
                        for (int i = 0; i < reesponseData.size(); i++) {
                            CompanyRecord v = new CompanyRecord();
                            companyRecords.add(reesponseData.get(i).CompanyName);
                            if (reesponseData.get(i).CompanyName.equals("__DEFAULT__")) {
                                companySpinner.setSelection(i);
                            }
                        }

                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, companyRecords);
                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        companySpinner.setAdapter(adapter);
                        for (int i = 0; i < reesponseData.size(); i++) {
                            if (reesponseData.get(i).CompanyName.equals("__DEFAULT__")) {
                                companySpinner.setSelection(i);
                            }
                        }
                        companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                GlobalVarible.SelectedCompany = _companyRecords.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        _companyRecords = new ArrayList<>();
                        Snackbar.make(btn, "0 Companies found", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(btn, "Error getting Companies. Please login again.", Snackbar.LENGTH_LONG).show();
                    Utilities.LogException(new Exception("Error getting Companies. Please login again."));
                }


            }

            @Override
            public void onFailure(Call<ArrayList<CompanyRecord>> call, Throwable t) {
                Snackbar.make(btn, "Error getting Companies. Please try again.", Snackbar.LENGTH_LONG).show();
                Utilities.LogException(new Exception("Error getting Companies" + " " + t.getMessage()));
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
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

    public void onClick(View v) {
        try {

            switch (v.getId()) {
                case R.id.buttonNext:
                    Fragment fragment = null;
//get selected company and valuation Type
                    if(GlobalVarible.SelectedValuationType == null)
                    {
                        Snackbar.make(btn, "Select Valuation Type.", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    fragment = new CarValuationFragment();
                    replaceFragment(fragment, "CarValuationFragment");
                    break;
                case R.id.buttonCancelInvoice:
                    GlobalVarible.uploadRecord = new UploadRecord();
                    fragment = new HomeFragment();
                    replaceFragment(fragment, "HomeFragment");
                    break;
            }
        } catch (Exception ex) {
            Utilities.LogException(ex);
        }

    }

    public void replaceFragment(Fragment someFragment, String fragmentName) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, someFragment).addToBackStack(fragmentName);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
