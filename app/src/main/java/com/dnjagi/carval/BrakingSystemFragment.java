package com.dnjagi.carval;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.dnjagi.carval.data.BrakingSystemRecord;
import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.utility.Utilities;


public class BrakingSystemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BrakingSystemFragment() {
        // Required empty public constructor
    }


    public static BrakingSystemFragment newInstance(String param1, String param2) {
        BrakingSystemFragment fragment = new BrakingSystemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Switch bsparkingbreak_switch, bsfootbrakespongy_switch, bsbrakepedalfreeplay_switch, bsabnormalnoise_switch, bsABS_switch, bsbrakeseffective_switch, bsfluidleaks_switch;
    private EditText bsinput_comments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Braking System Inspection");
        View view = inflater.inflate(R.layout.fragment_braking_system, container, false);

        bsparkingbreak_switch = (Switch) view.findViewById(R.id.bsparkingbreak_switch);
        bsfootbrakespongy_switch = (Switch) view.findViewById(R.id.bsfootbrakespongy_switch);
        bsbrakepedalfreeplay_switch = (Switch) view.findViewById(R.id.bsbrakepedalfreeplay_switch);
        bsabnormalnoise_switch = (Switch) view.findViewById(R.id.bsabnormalnoise_switch);
        bsABS_switch = (Switch) view.findViewById(R.id.bsABS_switch);
        bsbrakeseffective_switch = (Switch) view.findViewById(R.id.bsbrakeseffective_switch);
        bsfluidleaks_switch = (Switch) view.findViewById(R.id.bsfluidleaks_switch);
        bsinput_comments = (EditText) view.findViewById(R.id.bsinput_comments);

        if (GlobalVarible.uploadRecord != null) {
            if (GlobalVarible.uploadRecord.BrakingSystemRecordModel != null) {
                bsparkingbreak_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.ParkingBrakeEffective);
                bsfootbrakespongy_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.FootBrakeSpongy);
                bsbrakepedalfreeplay_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.BrakePedalFreePlayndTravelNormal);
                bsabnormalnoise_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.NoiseAndVibrationInBrakes);
                bsABS_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.ABSOperateProperly);
                bsbrakeseffective_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.BrakesEffective);
                bsfluidleaks_switch.setChecked(GlobalVarible.uploadRecord.BrakingSystemRecordModel.FluidLeaksInBrakeSystem);
                bsinput_comments.setText(GlobalVarible.uploadRecord.BrakingSystemRecordModel.CommentsOnBraking);
            }
        }

        Button btnPrevious = view.findViewById(R.id.bsbuttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new SuspensionSystemFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        Button btnNext = view.findViewById(R.id.bsbuttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BrakingSystemRecord brakingSystemRecord = new BrakingSystemRecord();
                    brakingSystemRecord.ParkingBrakeEffective = bsparkingbreak_switch.isChecked();
                    brakingSystemRecord.FootBrakeSpongy = bsfootbrakespongy_switch.isChecked();
                    brakingSystemRecord.BrakePedalFreePlayndTravelNormal = bsbrakepedalfreeplay_switch.isChecked();
                    brakingSystemRecord.NoiseAndVibrationInBrakes = bsabnormalnoise_switch.isChecked();
                    brakingSystemRecord.ABSOperateProperly = bsABS_switch.isChecked();
                    brakingSystemRecord.BrakesEffective = bsbrakeseffective_switch.isChecked();
                    brakingSystemRecord.FluidLeaksInBrakeSystem = bsfluidleaks_switch.isChecked();
                    brakingSystemRecord.CommentsOnBraking = bsinput_comments.getText().toString();
                    brakingSystemRecord.UploadRecordID = GlobalVarible.uploadRecord.UploadRecordID;
                    GlobalVarible.uploadRecord.BrakingSystemRecordModel = brakingSystemRecord;
                    Fragment fragment = new CameraViewFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
