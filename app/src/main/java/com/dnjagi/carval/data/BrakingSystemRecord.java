package com.dnjagi.carval.data;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import Model.DatabaseObject;

/**
 * Created by user on 8/1/2018.
 */

public class BrakingSystemRecord extends DatabaseObject {
    public int UploadRecordID;

    public boolean ParkingBrakeEffective;

    public boolean FootBrakeSpongy;

    public boolean BrakePedalFreePlayndTravelNormal;

    public boolean NoiseAndVibrationInBrakes;

    public boolean ABSOperateProperly;

    public boolean BrakesEffective;

    public boolean FluidLeaksInBrakeSystem;


    @SerializedName("CommentsOnBraking")
    public String CommentsOnBraking;

    public String getCommentsOnBraking() {
        return CommentsOnBraking;
    }

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
