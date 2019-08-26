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
import com.dnjagi.carval.data.SuspensionSystemRecord;
import com.dnjagi.carval.utility.Utilities;


public class SuspensionSystemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public SuspensionSystemFragment() {
        // Required empty public constructor
    }


    public static SuspensionSystemFragment newInstance(String param1, String param2) {
        SuspensionSystemFragment fragment = new SuspensionSystemFragment();
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

    private Switch ssbusheswornout_switch, sssuspensionwornout_switch, sscenterboltbroken_switch, ssballjointwornout_switch, ssspringbroken_switch, sscollspringbroken_switch, ssspringanchor_switch;
    private EditText ssinput_comments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Suspension System Inspection");
        View view = inflater.inflate(R.layout.fragment_suspension_system, container, false);


        ssbusheswornout_switch = (Switch) view.findViewById(R.id.ssbusheswornout_switch);
        sssuspensionwornout_switch = (Switch) view.findViewById(R.id.sssuspensionwornout_switch);
        sscenterboltbroken_switch = (Switch) view.findViewById(R.id.sscenterboltbroken_switch);
        ssballjointwornout_switch = (Switch) view.findViewById(R.id.ssballjointwornout_switch);
        ssspringbroken_switch = (Switch) view.findViewById(R.id.ssspringbroken_switch);
        sscollspringbroken_switch = (Switch) view.findViewById(R.id.sscollspringbroken_switch);
        ssspringanchor_switch = (Switch) view.findViewById(R.id.ssspringanchor_switch);
        ssinput_comments = (EditText) view.findViewById(R.id.ssinput_comments);
        if (GlobalVarible.uploadRecord != null) {
            if (GlobalVarible.uploadRecord.SuspensionSystemRecordModel != null) {
                ssbusheswornout_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.BushesWornOut);
                sssuspensionwornout_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.SuspensionShockAbsorbersWornOut);
                sscenterboltbroken_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.CenterBoltCenterRuberBroken);
                ssballjointwornout_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.SuspensionBallJointWornOut);
                ssspringbroken_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.LeafSpringBrokenOrWeakOrMisaligned);
                sscollspringbroken_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.CoilSpringsBroken);
                ssspringanchor_switch.setChecked(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.SpringAnchorPointsOk);
                ssinput_comments.setText(GlobalVarible.uploadRecord.SuspensionSystemRecordModel.CommentsOnSuspension);
            }
        }

        Button btnPrevious = view.findViewById(R.id.ssbuttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new TransmissionSystemFragment();
                    replaceFragment(fragment,"BrakingSystemFragment");
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        Button btnNext = view.findViewById(R.id.ssbuttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SuspensionSystemRecord suspensionSystemRecord = new SuspensionSystemRecord();
                    suspensionSystemRecord.BushesWornOut = ssbusheswornout_switch.isChecked();
                    suspensionSystemRecord.SuspensionShockAbsorbersWornOut = sssuspensionwornout_switch.isChecked();
                    suspensionSystemRecord.CenterBoltCenterRuberBroken = sscenterboltbroken_switch.isChecked();
                    suspensionSystemRecord.SuspensionBallJointWornOut = ssballjointwornout_switch.isChecked();
                    suspensionSystemRecord.LeafSpringBrokenOrWeakOrMisaligned = ssspringbroken_switch.isChecked();
                    suspensionSystemRecord.CoilSpringsBroken = sscollspringbroken_switch.isChecked();
                    suspensionSystemRecord.SpringAnchorPointsOk = ssspringanchor_switch.isChecked();
                    suspensionSystemRecord.CommentsOnSuspension = ssinput_comments.getText().toString();
                    suspensionSystemRecord.UploadRecordID = GlobalVarible.uploadRecord.UploadRecordID;
                    GlobalVarible.uploadRecord.SuspensionSystemRecordModel = suspensionSystemRecord;


                    Fragment fragment = new BrakingSystemFragment();
                    replaceFragment(fragment,"BrakingSystemFragment");
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
