package com.dnjagi.carval;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.TransmissionSystemRecord;
import com.dnjagi.carval.utility.Utilities;


public class TransmissionSystemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Switch tsautomaticgearbox_switch, tstorqueconverter_switch, tsgearsynchromesh_switch,
            tsgearjump_switch, tsclutchslip_switch, tsgearboxmounting_switch, tsdriveshaftcvjointwornout_switch, tsoilleaks_switch, tspropeliershaftbent_switch, tsdifferentialunity_switch;
    private EditText tsinput_comments;

    private OnFragmentInteractionListener mListener;


    public TransmissionSystemFragment() {
        // Required empty public constructor
    }


    public static TransmissionSystemFragment newInstance(String param1, String param2) {
        TransmissionSystemFragment fragment = new TransmissionSystemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        getActivity().setTitle("Transmission System Inspection");
        View view = inflater.inflate(R.layout.fragment_transmission_system, container, false);

        tsautomaticgearbox_switch = (Switch) view.findViewById(R.id.tsautomaticgearbox_switch);
        tstorqueconverter_switch = (Switch) view.findViewById(R.id.tstorqueconverter_switch);
        tsgearsynchromesh_switch = (Switch) view.findViewById(R.id.tsgearsynchromesh_switch);
        tsgearjump_switch = (Switch) view.findViewById(R.id.tsgearjump_switch);
        tsclutchslip_switch = (Switch) view.findViewById(R.id.tsclutchslip_switch);
        tsgearboxmounting_switch = (Switch) view.findViewById(R.id.tsgearboxmounting_switch);
        tsdriveshaftcvjointwornout_switch = (Switch) view.findViewById(R.id.tsdriveshaftcvjointwornout_switch);
        tsoilleaks_switch = (Switch) view.findViewById(R.id.tsoilleaks_switch);
        tspropeliershaftbent_switch = (Switch) view.findViewById(R.id.tspropeliershaftbent_switch);
        tsdifferentialunity_switch = (Switch) view.findViewById(R.id.tsdifferentialunity_switch);
        tsinput_comments = (EditText) view.findViewById(R.id.tsinput_comments);

        if (GlobalVarible.uploadRecord != null) {
            if (GlobalVarible.uploadRecord.TransmissionSystemRecordModel != null) {
                tsautomaticgearbox_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.AutomaticGearboxSmooth);
                tstorqueconverter_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.EvidenceOfTorqueConverterSpin);
                tsgearsynchromesh_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.ManualGearSynchromeshSmooth);
                tsgearjump_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.AnyGearJumpOutOfMesh);
                tsclutchslip_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.ClutchSlip);
                tsgearboxmounting_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.GearBoxMountingsWornOut);
                tsdriveshaftcvjointwornout_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.DriveShaftCVJointsWornOut);
                tsoilleaks_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.FluidLeaksInTransmission);
                tspropeliershaftbent_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.PropellerShaftBentOrWobling);
                tsdifferentialunity_switch.setChecked(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.DifferentialUnityNoisy);
                tsinput_comments.setText(GlobalVarible.uploadRecord.TransmissionSystemRecordModel.CommentsOnTransmission);
            }
        }


        Button btnPrevious = view.findViewById(R.id.tsbuttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new EngineSystemFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        Button btnNext = view.findViewById(R.id.tsbuttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TransmissionSystemRecord transmissionSystemRecord = new TransmissionSystemRecord();
                    transmissionSystemRecord.AutomaticGearboxSmooth = tsautomaticgearbox_switch.isChecked();
                    transmissionSystemRecord.EvidenceOfTorqueConverterSpin = tstorqueconverter_switch.isChecked();
                    transmissionSystemRecord.ManualGearSynchromeshSmooth = tsgearsynchromesh_switch.isChecked();
                    transmissionSystemRecord.AnyGearJumpOutOfMesh = tsgearjump_switch.isChecked();
                    transmissionSystemRecord.ClutchSlip = tsclutchslip_switch.isChecked();
                    transmissionSystemRecord.GearBoxMountingsWornOut = tsgearboxmounting_switch.isChecked();
                    transmissionSystemRecord.DriveShaftCVJointsWornOut = tsdriveshaftcvjointwornout_switch.isChecked();
                    transmissionSystemRecord.FluidLeaksInTransmission = tsoilleaks_switch.isChecked();
                    transmissionSystemRecord.PropellerShaftBentOrWobling = tspropeliershaftbent_switch.isChecked();
                    transmissionSystemRecord.DifferentialUnityNoisy = tsdifferentialunity_switch.isChecked();
                    transmissionSystemRecord.CommentsOnTransmission = tsinput_comments.getText().toString();
                    transmissionSystemRecord.UploadRecordID = GlobalVarible.uploadRecord.UploadRecordID;
                    GlobalVarible.uploadRecord.TransmissionSystemRecordModel = transmissionSystemRecord;

                    Fragment fragment = new SuspensionSystemFragment();
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


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
