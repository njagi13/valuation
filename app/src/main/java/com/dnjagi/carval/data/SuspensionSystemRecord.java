package com.dnjagi.carval.data;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import Model.DatabaseObject;

/**
 * Created by user on 8/1/2018.
 */

public class SuspensionSystemRecord extends DatabaseObject {

    public UUID UploadRecordID;

    public boolean BushesWornOut;

    public boolean SuspensionShockAbsorbersWornOut;

    public boolean CenterBoltCenterRuberBroken;

    public boolean SuspensionBallJointWornOut;

    public boolean LeafSpringBrokenOrWeakOrMisaligned;

    public boolean CoilSpringsBroken;

    public boolean SpringAnchorPointsOk;


    @SerializedName("CommentsOnSuspension")
    public String CommentsOnSuspension;

    public String getCommentsOnSuspension() {
        return CommentsOnSuspension;
    }

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
