package com.dnjagi.carval.data;

import com.dnjagi.carval.Model.DatabaseObject;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class CompanyRecord extends DatabaseObject {
    @SerializedName("CompanyID")
    public int CompanyID;
    @SerializedName("ObjectID")
    public UUID ObjectID;

    @SerializedName("CompanyName")
    public String CompanyName;

    @SerializedName("PhoneNumber")
    public String PhoneNumber;

    @SerializedName("Email")
    public String Email;

    @SerializedName("IsInsuranceCompany")
    public boolean IsInsuranceCompany;

    @SerializedName("IsMicroFinance")
    public boolean IsMicroFinance;

    @SerializedName("IsBank")
    public boolean IsBank;

    @SerializedName("CompanyDescription")
    public String CompanyDescription;

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
