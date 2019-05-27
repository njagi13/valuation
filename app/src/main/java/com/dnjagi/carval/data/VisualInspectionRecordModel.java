package com.dnjagi.carval.data;

import java.util.UUID;

import Model.DatabaseObject;

public class VisualInspectionRecordModel extends DatabaseObject {

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }

    public UUID UploadRecordID;

    //lights
    public boolean HeadLights;

    public boolean TailLights;

    public boolean BreakLights;

    public boolean TurnSignalLights;

    public boolean AxelAndWheels;

    public boolean DriveTrain;

    public boolean MufflerAndExhaust;

    public boolean VisualBreakInspection;

    public boolean ParkingBreak;

    public boolean SteeringMechanism;

    public boolean Horn;

    public boolean Bumper;

    public boolean AllDoorsOpenCloseLock;

    public boolean FrontSeatsAdjustment;

    public boolean SeatBelts;

    public boolean AcHeat;

    public boolean WindShield;

    public boolean RearWindows;

    public boolean WindShieldWipers;

    public boolean InteriorRearViewMirror;

    public boolean ExternalRearViewMirror;

    public boolean FrontRightTire;

    public boolean FrontLeftTire;

    public boolean RearRightTire;

    public boolean RearLeftTire;

    public boolean SpareTire;

    public boolean InteriorCleanliness;

    public boolean AnyBodyDamage;
}
