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

import com.dnjagi.carval.data.EngineSystemRecord;
import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.utility.Utilities;


public class EngineSystemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EngineSystemFragment() {
        // Required empty public constructor
    }


    public static EngineSystemFragment newInstance(String param1, String param2) {
        EngineSystemFragment fragment = new EngineSystemFragment();
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


    private Switch enginestartsproperly_switch, mountingswornout_switch, valvemechanism_switch, drivebelts_switch, oilleaks_switch,
            rpm_switch, noiseinengine_switch, waterpump_switch, radiatorcap_switch, enginecoolantlevel_switch;
    private EditText input_comments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Engine System Inspection");
        View view = inflater.inflate(R.layout.fragment_engine_system, container, false);

        enginestartsproperly_switch = (Switch) view.findViewById(R.id.enginestartsproperly_switch);
        mountingswornout_switch = (Switch) view.findViewById(R.id.mountingswornout_switch);
        valvemechanism_switch = (Switch) view.findViewById(R.id.valvemechanism_switch);
        drivebelts_switch = (Switch) view.findViewById(R.id.drivebelts_switch);
        oilleaks_switch = (Switch) view.findViewById(R.id.oilleaks_switch);
        rpm_switch = (Switch) view.findViewById(R.id.rpm_switch);
        noiseinengine_switch = (Switch) view.findViewById(R.id.noiseinengine_switch);
        waterpump_switch = (Switch) view.findViewById(R.id.waterpump_switch);
        radiatorcap_switch = (Switch) view.findViewById(R.id.radiatorcap_switch);
        enginecoolantlevel_switch = (Switch) view.findViewById(R.id.enginecoolantlevel_switch);
        input_comments = (EditText) view.findViewById(R.id.input_comments);


        if (GlobalVarible.uploadRecord != null) {
            if (GlobalVarible.uploadRecord.EngineSystemRecordModel != null) {
                enginestartsproperly_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.StartsAndIdlesProperly);
                mountingswornout_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.MountingsWornOut);
                valvemechanism_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.ValveMechanismNoisy);
                drivebelts_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.DriveBeltsLooseOrWornOut);
                oilleaks_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.EngineLeaks);
                rpm_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.EngineOperatesWellAtVariousRPM);
                noiseinengine_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.EngineAbnormalVibration);
                waterpump_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.WaterPumpOperateWell);
                radiatorcap_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.RadiatorCapOperateWell);
                enginecoolantlevel_switch.setChecked(GlobalVarible.uploadRecord.EngineSystemRecordModel.CoolantLevelCorrect);
                input_comments.setText(GlobalVarible.uploadRecord.EngineSystemRecordModel.CommentsOnEngine);
            }
        }

               Button btnPrevious = view.findViewById(R.id.buttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new VisualInspectionFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        Button btnNext = view.findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EngineSystemRecord engineSystemRecord = new EngineSystemRecord();
                    engineSystemRecord.StartsAndIdlesProperly = enginestartsproperly_switch.isChecked();
                    engineSystemRecord.MountingsWornOut = mountingswornout_switch.isChecked();
                    engineSystemRecord.ValveMechanismNoisy = valvemechanism_switch.isChecked();
                    engineSystemRecord.DriveBeltsLooseOrWornOut = drivebelts_switch.isChecked();
                    engineSystemRecord.EngineLeaks = oilleaks_switch.isChecked();
                    engineSystemRecord.EngineOperatesWellAtVariousRPM = rpm_switch.isChecked();
                    engineSystemRecord.EngineAbnormalVibration = noiseinengine_switch.isChecked();
                    engineSystemRecord.WaterPumpOperateWell = waterpump_switch.isChecked();
                    engineSystemRecord.RadiatorCapOperateWell = radiatorcap_switch.isChecked();
                    engineSystemRecord.CoolantLevelCorrect = enginecoolantlevel_switch.isChecked();
                    engineSystemRecord.CommentsOnEngine = input_comments.getText().toString();

                    engineSystemRecord.UploadRecordID = GlobalVarible.uploadRecord.UploadRecordID;
                    GlobalVarible.uploadRecord.EngineSystemRecordModel = engineSystemRecord;

                    Fragment fragment = new TransmissionSystemFragment();
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
