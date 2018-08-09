package com.dnjagi.carval.data;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import Model.DatabaseObject;

/**
 * Created by user on 8/1/2018.
 */

public class TransmissionSystemRecord extends DatabaseObject {

    public int UploadRecordID;

    public boolean AutomaticGearboxSmooth;

    public boolean EvidenceOfTorqueConverterSpin;

    public boolean ManualGearSynchromeshSmooth;

    public boolean AnyGearJumpOutOfMesh;

    public boolean ClutchSlip;

    public boolean GearBoxMountingsWornOut;

    public boolean DriveShaftCVJointsWornOut;

    public boolean FluidLeaksInTransmission;

    public boolean PropellerShaftBentOrWobling;

    public boolean DifferentialUnityNoisy;

    @SerializedName("CommentsOnTransmission")
    public String CommentsOnTransmission;

    public String getCommentsOnTransmission() {
        return CommentsOnTransmission;
    }

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
