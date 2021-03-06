package com.dnjagi.carval;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.utility.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class CarValuationFragment extends Fragment
        implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;

    public CarValuationFragment() {
        // Required empty public constructor
    }


    public static CarValuationFragment newInstance() {
        CarValuationFragment fragment = new CarValuationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Calendar myCalendar = Calendar.getInstance();
    EditText dateOfRegText, dateOfPolicyExpiryText;

    private EditText input_make, input_model, input_regnumber, input_color, input_odom, input_yearOfManf, input_policyNo,
            input_policyexpirydate, input_chasisnumber, input_enginenumber, input_engrating, input_fname, input_lname,input_mname,
            input_phone1, input_phone2, input_email;

    UploadRecord mUploadRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Vehicle Details");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_valuation, container, false);
        mUploadRecord = new UploadRecord();
        input_make = (EditText) view.findViewById(R.id.input_make);
        input_model = (EditText) view.findViewById(R.id.input_model);
        input_regnumber = (EditText) view.findViewById(R.id.input_regnumber);
        input_color = (EditText) view.findViewById(R.id.input_color);
        input_odom = (EditText) view.findViewById(R.id.input_odom);
        input_yearOfManf = (EditText) view.findViewById(R.id.input_yearOfManf);
        input_policyNo = (EditText) view.findViewById(R.id.input_policyNo);
        input_chasisnumber = (EditText) view.findViewById(R.id.input_chasisnumber);
        input_policyexpirydate = (EditText) view.findViewById(R.id.input_policyexpirydate);
        input_enginenumber = (EditText) view.findViewById(R.id.input_enginenumber);
        input_engrating = (EditText) view.findViewById(R.id.input_engrating);
        input_fname = (EditText) view.findViewById(R.id.input_fname);
        input_lname = (EditText) view.findViewById(R.id.input_lname);
        input_mname = (EditText) view.findViewById(R.id.input_mname);
        input_phone1 = (EditText) view.findViewById(R.id.input_phone1);
        input_phone2 = (EditText) view.findViewById(R.id.input_phone2);
        input_email = (EditText) view.findViewById(R.id.input_email);
        dateOfRegText = (EditText) view.findViewById(R.id.input_dateofreg);
        dateOfPolicyExpiryText = (EditText) view.findViewById(R.id.input_policyexpirydate);

        if (GlobalVarible.uploadRecord != null) {
            if (GlobalVarible.uploadRecord.UploadRecordID != null) {

                input_make.setText(GlobalVarible.uploadRecord.Make);
                input_model.setText(GlobalVarible.uploadRecord.CarModel);
                input_regnumber.setText(GlobalVarible.uploadRecord.RegistrationNumber);
                input_color.setText(GlobalVarible.uploadRecord.Color);
                input_odom.setText(GlobalVarible.uploadRecord.OdomReading);
                input_yearOfManf.setText(GlobalVarible.uploadRecord.YearOfManufacture);
                input_policyNo.setText(GlobalVarible.uploadRecord.PolicyNumber);
                input_chasisnumber.setText(GlobalVarible.uploadRecord.ChassisNumber);
                input_enginenumber.setText(GlobalVarible.uploadRecord.EngineNumber);
                input_engrating.setText(GlobalVarible.uploadRecord.EngineRating);
                input_fname.setText(GlobalVarible.uploadRecord.FirstName);
                input_mname.setText(GlobalVarible.uploadRecord.MiddleName);
                input_lname.setText(GlobalVarible.uploadRecord.LastName);
                input_phone1.setText(GlobalVarible.uploadRecord.PhoneNumber1);
                input_phone2.setText(GlobalVarible.uploadRecord.PhoneNumber2);
                dateOfRegText.setText(GlobalVarible.uploadRecord.DateOfReg);
                dateOfPolicyExpiryText.setText(GlobalVarible.uploadRecord.InsExpiryDate);
                input_email.setText(GlobalVarible.uploadRecord.Email);
            }
        }

        Button btnNext = (Button) view.findViewById(R.id.buttonNext);
        Button btnCancel = (Button) view.findViewById(R.id.buttonCancelInvoice);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateOfReg();
            }

        };

        dateOfRegText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateOfExpiry();
            }

        };

        dateOfPolicyExpiryText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnNext.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return view;
    }

    private void updateDateOfReg() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOfRegText.setText(sdf.format(myCalendar.getTime()));
    }


    private void updateDateOfExpiry() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfPolicyExpiryText.setText(sdf.format(myCalendar.getTime()));
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

    @Override
    public void onClick(View v) {
        try {

            switch (v.getId()) {
                case R.id.buttonNext:
                    Fragment fragment = null;

                    mUploadRecord.Make = input_make.getText().toString();
                    mUploadRecord.CarModel = input_model.getText().toString();
                    mUploadRecord.RegistrationNumber = input_regnumber.getText().toString();
                    mUploadRecord.Color = input_color.getText().toString();
                    mUploadRecord.OdomReading = input_odom.getText().toString();
                    mUploadRecord.YearOfManufacture = input_yearOfManf.getText().toString();
                    mUploadRecord.PolicyNumber = input_policyNo.getText().toString();
                    mUploadRecord.InsCompany = input_policyNo.getText().toString();
                    mUploadRecord.InsExpiryDate = dateOfPolicyExpiryText.getText().toString();
                    mUploadRecord.ChassisNumber = input_chasisnumber.getText().toString();
                    mUploadRecord.EngineNumber = input_enginenumber.getText().toString();
                    mUploadRecord.EngineRating = input_engrating.getText().toString();
                    mUploadRecord.FirstName = input_fname.getText().toString();
                    mUploadRecord.LastName = input_lname.getText().toString();
                    mUploadRecord.MiddleName = input_mname.getText().toString();
                    mUploadRecord.PhoneNumber1 = input_phone1.getText().toString();
                    mUploadRecord.PhoneNumber2 = input_phone2.getText().toString();
                    mUploadRecord.Email = input_email.getText().toString();
                    mUploadRecord.DateOfReg = dateOfRegText.getText().toString();
                    mUploadRecord.UploadRecordID = UUID.randomUUID();
                    SharedPreferences preferences = getActivity().getSharedPreferences(GlobalVarible.LoggedIn, getActivity().getApplicationContext().MODE_PRIVATE);
                    mUploadRecord.ValuerEmail = preferences.getString("Email", null);
                    if (GlobalVarible.uploadRecord == null) {
                        setupGlobalUploadRecord(mUploadRecord);
                    }

                    //save locally
                    //  mUploadRecord.save();
                    // long tt = UploadRecord.count(UploadRecord.class);
                    fragment = new VisualInspectionFragment();
                    replaceFragment(fragment , "VisualInspectionFragment");
                    break;
                case R.id.buttonCancelInvoice:
                    GlobalVarible.uploadRecord = new UploadRecord();
                    fragment = new ValuationTypeFragment();
                    replaceFragment(fragment, "ValuationTypeFragment");
                    break;
            }
        } catch (Exception ex) {
            Utilities.LogException(ex);
        }

    }

    private void setupGlobalUploadRecord(UploadRecord mUploadRecord) {
        try {
            GlobalVarible.uploadRecord = new UploadRecord();
            GlobalVarible.uploadRecord.Make = mUploadRecord.Make;
            GlobalVarible.uploadRecord.CarModel = mUploadRecord.CarModel;
            GlobalVarible.uploadRecord.RegistrationNumber = mUploadRecord.RegistrationNumber;
            GlobalVarible.uploadRecord.Color = mUploadRecord.Color;
            GlobalVarible.uploadRecord.OdomReading = mUploadRecord.OdomReading;
            GlobalVarible.uploadRecord.YearOfManufacture = mUploadRecord.YearOfManufacture;
            GlobalVarible.uploadRecord.PolicyNumber = mUploadRecord.PolicyNumber;
            GlobalVarible.uploadRecord.InsExpiryDate = mUploadRecord.InsExpiryDate;
            GlobalVarible.uploadRecord.ChassisNumber = mUploadRecord.ChassisNumber;
            GlobalVarible.uploadRecord.EngineNumber = mUploadRecord.EngineNumber;
            GlobalVarible.uploadRecord.EngineRating = mUploadRecord.EngineRating;
            GlobalVarible.uploadRecord.FirstName = mUploadRecord.FirstName;
            GlobalVarible.uploadRecord.LastName = mUploadRecord.LastName;
            GlobalVarible.uploadRecord.PhoneNumber1 = mUploadRecord.PhoneNumber1;
            GlobalVarible.uploadRecord.PhoneNumber2 = mUploadRecord.PhoneNumber2;
            GlobalVarible.uploadRecord.Email = mUploadRecord.Email;
            GlobalVarible.uploadRecord.DateOfReg = mUploadRecord.DateOfReg;
            GlobalVarible.uploadRecord.UploadRecordID = mUploadRecord.UploadRecordID;
            GlobalVarible.uploadRecord.ValuerEmail = mUploadRecord.ValuerEmail;
            GlobalVarible.uploadRecord.InsCompany = mUploadRecord.InsCompany;
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
