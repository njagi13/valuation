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
            headlightsOk_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.HeadLightsOk);
            tail_lights_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.TailLightsOk);
            turn_signal_lights_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.TurnSignalLightsOk);
            break_lights_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.BreakLightsOk);
            axels_and_wheels_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AxelAndWheelsOk);
            drivetrain_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.DriveTrainOk);
            muffler_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.MufflerAndExhaustOk);
            break_inspection_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.VisualBreakInspectionOk);
            parking_break_inspection_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.ParkingBreakOk);
            steering_mechanism_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SteeringMechanismOk);
            horn_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.HornOk);
            bumper_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.BumperOk);
            all_doors_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AllDoorsOpenCloseLockOk);
            front_seat_adjustment_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontSeatsAdjustmentOk);
            seat_belts_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SeatBeltsOk);
            ac_heat_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.AcHeatOk);
            windshield_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.WindShieldOk);
            rear_windows_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearWindowsOk);
            windshield_wipers_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.WindShieldWipersOk);
            interior_rearview_mirror_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.InteriorRearViewMirrorOk);
            exterior_rearview_mirrors_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.ExternalRearViewMirrorOk);
            front_right_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontRightTireOk);
            front_left_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.FrontLeftTireOk);
            rear_left_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearLeftTireOk);
            rear_right_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.RearRightTireOk);
            spare_tire_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.SpareTireOk);
            interior_cleanliness_ok_switch.setChecked(GlobalVarible.uploadRecord.VisualInspectionRecordModel.InteriorCleanlinessOk);
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
                    visualInspectionRecordModel.HeadLightsOk = headlightsOk_switch.isChecked();
                    visualInspectionRecordModel.TailLightsOk = tail_lights_ok_switch.isChecked();
                    visualInspectionRecordModel.TurnSignalLightsOk = turn_signal_lights_ok_switch.isChecked();
                    visualInspectionRecordModel.BreakLightsOk = break_lights_switch.isChecked();
                    visualInspectionRecordModel.AxelAndWheelsOk = axels_and_wheels_ok_switch.isChecked();
                    visualInspectionRecordModel.DriveTrainOk = drivetrain_ok_switch.isChecked();
                    visualInspectionRecordModel.MufflerAndExhaustOk = muffler_ok_switch.isChecked();
                    visualInspectionRecordModel.VisualBreakInspectionOk = break_inspection_ok_switch.isChecked();
                    visualInspectionRecordModel.ParkingBreakOk = parking_break_inspection_switch.isChecked();
                    visualInspectionRecordModel.SteeringMechanismOk = steering_mechanism_switch.isChecked();
                    visualInspectionRecordModel.HornOk = horn_switch.isChecked();
                    visualInspectionRecordModel.BumperOk = bumper_switch.isChecked();
                    visualInspectionRecordModel.AllDoorsOpenCloseLockOk = all_doors_ok_switch.isChecked();
                    visualInspectionRecordModel.FrontSeatsAdjustmentOk = front_seat_adjustment_switch.isChecked();
                    visualInspectionRecordModel.SeatBeltsOk = seat_belts_ok_switch.isChecked();
                    visualInspectionRecordModel.AcHeatOk = ac_heat_ok_switch.isChecked();
                    visualInspectionRecordModel.WindShieldOk = windshield_ok_switch.isChecked();
                    visualInspectionRecordModel.RearWindowsOk = rear_windows_ok_switch.isChecked();
                    visualInspectionRecordModel.WindShieldWipersOk = windshield_wipers_ok_switch.isChecked();
                    visualInspectionRecordModel.InteriorRearViewMirrorOk = interior_rearview_mirror_switch.isChecked();
                    visualInspectionRecordModel.ExternalRearViewMirrorOk = exterior_rearview_mirrors_switch.isChecked();
                    visualInspectionRecordModel.FrontRightTireOk = front_right_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.FrontLeftTireOk = front_left_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.RearLeftTireOk = rear_left_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.RearRightTireOk = rear_right_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.SpareTireOk = spare_tire_ok_switch.isChecked();
                    visualInspectionRecordModel.InteriorCleanlinessOk = interior_cleanliness_ok_switch.isChecked();
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
