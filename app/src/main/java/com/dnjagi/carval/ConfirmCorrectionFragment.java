package com.dnjagi.carval;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.UploadRecordAPI;
import com.dnjagi.carval.utility.Utilities;


public class ConfirmCorrectionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextView ccinput_comments;
    private CheckBox confirm_submission;
    private Button btnSubmit;

    public ConfirmCorrectionFragment() {
        // Required empty public constructor
    }


    public static ConfirmCorrectionFragment newInstance() {
        ConfirmCorrectionFragment fragment = new ConfirmCorrectionFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_correction, container, false);
        Button btnPrevious = view.findViewById(R.id.ccbuttonBack);
        btnSubmit = view.findViewById(R.id.btnSubmitCorrection);
        confirm_submission = (CheckBox) view.findViewById(R.id.confirm_submission);
        ccinput_comments = (TextView) view.findViewById(R.id.ccinput_comments);
        ccinput_comments.setText(GlobalVarible.uploadRecord.Comments);

        confirm_submission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnSubmit.setEnabled(true);
                }
                else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostValuation();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new BrakingSystemFragment();
                    replaceFragment(fragment);

                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
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

    private void PostValuation() {
        UploadRecordAPI uploadRecordAPI = new UploadRecordAPI(getContext());
        uploadRecordAPI.CreateValuation(GlobalVarible.uploadRecord);
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
