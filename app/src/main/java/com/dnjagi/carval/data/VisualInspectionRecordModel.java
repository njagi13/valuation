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
    public boolean HeadLightsOk;

    public boolean TailLightsOk;

    public boolean BreakLightsOk;

    public boolean TurnSignalLightsOk;

    public boolean AxelAndWheelsOk;

    public boolean DriveTrainOk;

    public boolean MufflerAndExhaustOk;

    public boolean VisualBreakInspectionOk;

    public boolean ParkingBreakOk;

    public boolean SteeringMechanismOk;

    public boolean HornOk;

    public boolean BumperOk;

    public boolean AllDoorsOpenCloseLockOk;

    public boolean FrontSeatsAdjustmentOk;

    public boolean SeatBeltsOk;

    public boolean AcHeatOk;

    public boolean WindShieldOk;

    public boolean RearWindowsOk;

    public boolean WindShieldWipersOk;

    public boolean InteriorRearViewMirrorOk;

    public boolean ExternalRearViewMirrorOk;

    public boolean FrontRightTireOk;

    public boolean FrontLeftTireOk;

    public boolean RearRightTireOk;

    public boolean RearLeftTireOk;

    public boolean SpareTireOk;

    public boolean InteriorCleanlinessOk;

    public boolean AnyBodyDamage;
}
