package com.dnjagi.carval.data;

import com.dnjagi.carval.Model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class ValuationTypeRecord extends DatabaseObject {
    @SerializedName("ObjectID")
    public UUID ObjectID;

    @SerializedName("Description")
    public String Description;

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
