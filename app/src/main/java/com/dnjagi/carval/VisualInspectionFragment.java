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
import android.widget.Switch;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.VisualInspectionRecordModel;
import com.dnjagi.carval.utility.Utilities;


public class VisualInspectionFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private Switch headlightsOk_switch, tail_lights_ok_switch, turn_signal_lights_ok_switch, break_lights_switch,
            axels_and_wheels_ok_switch, drivetrain_ok_switch, muffler_ok_switch, break_inspection_ok_switch,
            parking_break_inspection_switch, steering_mechanism_switch, horn_switch, bumper_switch,
            all_doors_ok_switch, front_seat_adjustment_switch, seat_belts_ok_switch, ac_heat_ok_switch,
            windshield_ok_switch, rear_windows_ok_switch, windshield_wipers_ok_switch, interior_rearview_mirror_switch,
            exterior_rearview_mirrors_switch, front_right_tire_ok_switch, front_left_tire_ok_switch, rear_left_tire_ok_switch,
            rear_right_tire_ok_switch, spare_tire_ok_switch, interior_cleanliness_ok_switch, body_damage_switch;

    public VisualInspectionFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VisualInspectionFragment newInstance(String param1, String param2) {
        VisualInspectionFragment fragment = new VisualInspectionFragment();
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
        getActivity().setTitle("Visual Inspection");
        View view = inflater.inflate(R.layout.fragment_visual_inspection, container, false);
        headlightsOk_switch = (Switch) view.findViewById(R.id.headlightsOk_switch);
        tail_lights_ok_switch = (Switch) view.findViewById(R.id.tail_lights_ok_switch);
        turn_signal_lights_ok_switch = (Switch) view.findViewById(R.id.turn_signal_lights_ok_switch);
        break_lights_switch = (Switch) view.findViewById(R.id.break_lights_switch);
        axels_and_wheels_ok_switch = view.findViewById(R.id.axels_and_wheels_ok_switch);
        drivetrain_ok_switch = (Switch) view.findViewById(R.id.drivetrain_ok_switch);
        muffler_ok_switch = (Switch) view.findViewById(R.id.muffler_ok_switch);
        break_inspection_ok_switch = (Switch) view.findViewById(R.id.break_inspection_ok_switch);
        parking_break_inspection_switch = (Switch) view.findViewById(R.id.parking_break_inspection_switch);
        steering_mechanism_switch = (Switch) view.findViewById(R.id.steering_mechanism_switch);
        horn_switch = (Switch) view.findViewById(R.id.horn_switch);
        bumper_switch = (Switch) view.findViewById(R.id.bumper_switch);
        all_doors_ok_switch = (Switch) view.findViewById(R.id.all_doors_ok_switch);
        front_seat_adjustment_switch = (Switch) view.findViewById(R.id.front_seat_adjustment_switch);
        seat_belts_ok_switch = (Switch) view.findViewById(R.id.seat_belts_ok_switch);
        ac_heat_ok_switch = (Switch) view.findViewById(R.id.ac_heat_ok_switch);
        windshield_ok_switch = (Switch) view.findViewById(R.id.windshield_ok_switch);
        rear_windows_ok_switch = (Switch) view.findViewById(R.id.rear_windows_ok_switch);
        windshield_wipers_ok_switch = (Switch) view.findViewById(R.id.windshield_wipers_ok_switch);
        interior_rearview_mirror_switch = (Switch) view.findViewById(R.id.interior_rearview_mirror_switch);
        exterior_rearview_mirrors_switch = (Switch) view.findViewById(R.id.exterior_rearview_mirrors_switch);
        front_right_tire_ok_switch = (Switch) view.findViewById(R.id.front_right_tire_ok_switch);
        front_left_tire_ok_switch = (Switch) view.findViewById(R.id.front_left_tire_ok_switch);
        rear_left_tire_ok_switch = (Switch) view.findViewById(R.id.rear_left_tire_ok_switch);
        rear_right_tire_ok_switch = (Switch) view.findViewById(R.id.rear_right_tire_ok_switch);
        spare_tire_ok_switch = (Switch) view.findViewById(R.id.spare_tire_ok_switch);
        interior_cleanliness_ok_switch = (Switch) view.findViewById(R.id.interior_cleanliness_ok_switch);
        body_damage_switch = (Switch) view.findViewById(R.id.body_damage_switch);


        if (GlobalVarible.uploadRecord.VisualInspectionRecordModel != null) {
            headlightsOk_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.HeadLights);
            tail_lights_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.TailLights);
            turn_signal_lights_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.TurnSignalLights);
            break_lights_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.BreakLights);
            axels_and_wheels_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AxelAndWheels);
            drivetrain_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.DriveTrain);
            muffler_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.MufflerAndExhaust);
            break_inspection_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.VisualBreakInspection);
            parking_break_inspection_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.ParkingBreak);
            steering_mechanism_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SteeringMechanism);
            horn_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.Horn);
            bumper_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.Bumper);
            all_doors_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AllDoorsOpenCloseLock);
            front_seat_adjustment_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontSeatsAdjustment);
            seat_belts_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SeatBelts);
            ac_heat_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AcHeat);
            windshield_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.WindShield);
            rear_windows_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearWindows);
            windshield_wipers_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.WindShieldWipers);
            interior_rearview_mirror_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.InteriorRearViewMirror);
            exterior_rearview_mirrors_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.ExternalRearViewMirror);
            front_right_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontRightTire);
            front_left_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontLeftTire);
            rear_left_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearLeftTire);
            rear_right_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearRightTire);
            spare_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SpareTire);
            interior_cleanliness_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.InteriorCleanliness);
            body_damage_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AnyBodyDamage);
        }


        Button btnPrevious = view.findViewById(R.id.lightsbuttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fragment fragment = new CarValuationFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });

        Button btnNext = view.findViewById(R.id.lightsbuttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    VisualInspectionRecordModel visualInspectionRecordModel = new VisualInspectionRecordModel();
                    visualInspectionRecordModel.HeadLights = headlightsOk_switch.isChecked();
                    visualInspectionRecordModel.TailLights = tail_lights_ok_switch.isChecked();
                    visualInspectionRecordModel.TurnSignalLights = turn_signal_lights_ok_switch.isChecked();
                    visualInspectionRecordModel.BreakLights = break_lights_switch.isChecked();
                    visualInspectionRecordModel.AxelAndWheels = axels_and_wheels_ok_switch.isChecked();
                    visualInspectionRecordModel.DriveTrain = drivetrain_ok_switch.isChecked();
                    visualInspectionRecordModel.MufflerAndExhaust = muffler_ok_switch.isChecked();
                    visualInspectionRecordModel.VisualBreakInspection = break_inspection_ok_switch.isChecked();
                    visualInspectionRecordModel.ParkingBreak = parking_break_inspection_switch.isChecked();
                    visualInspectionRecordModel.SteeringMechanism = steering_mechanism_switch.isChecked();
                    visualInspectionRecordModel.Horn = horn_switch.isChecked();
                    visualInspectionRecordModel.Bumper = bumper_switch.isChecked();
                    visualInspectionRecordModel.AllDoorsOpenCloseLock = all_doors_ok_switch.isChecked();
                    visualInspectionRecordModel.FrontSeatsAdjustment = front_seat_adjustment_switch.isChecked();
                    visualInspectionRecordModel.SeatBelts = seat_belts_ok_switch.isChecked();
                    visualInspectionRecordModel.AcHeat = ac_heat_ok_switch.isChecked();
                    visualInspectionRecordModel.WindShield = windshield_ok_switch.isChecked();
                    visualInspectionRecordModel.RearWindows = rear_windows_ok_switch.isChecked();
                    visualInspectionRecordModel.WindShieldWipers = windshield_wipers_ok_switch.isChecked();
                    visualInspectionRecordModel.InteriorRearViewMirror = interior_rearview_mirror_switch.isChecked();
                    visualInspectionRecordModel.ExternalRearViewMirror = exterior_rearview_mirrors_switch.isChecked();
                    visualInspectionRecordModel.FrontRightTire = front_right_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.FrontLeftTire = front_left_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.RearLeftTire = rear_left_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.RearRightTire = rear_right_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.SpareTire = spare_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.InteriorCleanliness = interior_cleanliness_ok_switch.isChecked();
                    visualInspectionRecordModel.AnyBodyDamage = body_damage_switch.isChecked();

                    visualInspectionRecordModel.UploadRecordID = GlobalVarible.uploadRecord.UploadRecordID;
                    GlobalVarible.uploadRecord.VisualInspectionRecordModel = visualInspectionRecordModel;

                    Fragment fragment = new EngineSystemFragment();
                    replaceFragment(fragment);
                } catch (Exception ex) {
                    Utilities.LogException(ex);
                }
            }
        });
        return view;
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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

    //EngineSystemFragment


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
