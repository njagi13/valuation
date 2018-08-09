package com.dnjagi.carval.data;

import com.dnjagi.carval.model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by user on 8/1/2018.
 */

public class EngineSystemRecord extends DatabaseObject {

    // Engine System

    public int UploadRecordID;

    public boolean StartsAndIdlesProperly;

    public boolean MountingsWornOut;

    public boolean ValveMechanismNoisy;

    public boolean DriveBeltsLooseOrWornOut;

    public boolean EngineLeaks;

    public boolean EngineOperatesWellAtVariousRPM;

    public boolean EngineAbnormalVibration;

    public boolean WaterPumpOperateWell;

    public boolean RadiatorCapOperateWell;

    public boolean CoolantLevelCorrect;

    @SerializedName("CommentsOnEngine")
    public String CommentsOnEngine;
    public String getCommentsOnEngine() {
        return CommentsOnEngine;
    }


    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
