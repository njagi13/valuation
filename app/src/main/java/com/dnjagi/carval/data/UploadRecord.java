package com.dnjagi.carval.data;

import com.dnjagi.carval.model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by user on 7/30/2018.
 */

public class UploadRecord extends DatabaseObject {
    @SerializedName("UploadRecordID")
    public UUID UploadRecordID;

    @SerializedName("Make")
    public String Make;

    public String getMake() {
        return Make;
    }

    @SerializedName("Model")
    public String Model;

    public String getModel() {
        return Model;
    }

    @SerializedName("BodyType")
    public String BodyType;

    public String getBodyType() {
        return BodyType;
    }

    @SerializedName("RegistrationNumber")
    public String RegistrationNumber;

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    @SerializedName("Color")
    public String Color;

    public String getColor() {
        return Color;
    }

    @SerializedName("OdomReading")
    public String OdomReading;

    public String getOdomReading() {
        return OdomReading;
    }

    @SerializedName("YearOfManufacture")
    public String YearOfManufacture;

    public String getYearOfManufacture() {
        return YearOfManufacture;
    }

    @SerializedName("DateOfReg")
    public String DateOfReg;

    public String getDateOfReg() {
        return DateOfReg;
    }

    @SerializedName("ChasisNumber")
    public String ChassisNumber;

    public String getChasisNumber() {
        return ChassisNumber;
    }

    @SerializedName("EngineNumber")
    public String EngineNumber;

    public String getEngineNumber() {
        return EngineNumber;
    }

    @SerializedName("EngineRating")
    public String EngineRating;

    public String getEngineRating() {
        return EngineRating;
    }

    @SerializedName("InsCompany")
    public String InsCompany;

    public String getInsCompany() {
        return InsCompany;
    }

    @SerializedName("PolicyNumber")
    public String PolicyNumber;

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    @SerializedName("InsExpiryDate")
    public String InsExpiryDate;

    public String getInsExpiryDate() {
        return InsExpiryDate;
    }

    @SerializedName("FirstName")
    public String FirstName;

    public String getFirstName() {
        return FirstName;
    }

    @SerializedName("LastName")
    public String LastName;

    public String getLastName() {
        return LastName;
    }

    @SerializedName("PhoneNumber1")
    public String PhoneNumber1;

    public String getPhoneNumber1() {
        return PhoneNumber1;
    }

    @SerializedName("PhoneNumber2")
    public String PhoneNumber2;

    public String getPhoneNumber2() {
        return PhoneNumber2;
    }

    @SerializedName("Email")
    public String Email;

    public String getEmail() {
        return Email;
    }


    @SerializedName("ImagesPath")
    public String ImagesPath;

    public String getImagesPath() {
        return ImagesPath;
    }

    @SerializedName("EngineSystemRec")
    public EngineSystemRecord EngineSystemRecordModel;

    @SerializedName("TransmissionSystemRecordModel")
    public TransmissionSystemRecord TransmissionSystemRecordModel;

    @SerializedName("SuspensionSystemRecordModel")
    public SuspensionSystemRecord SuspensionSystemRecordModel;

    @SerializedName("BrakingSystemRecordModel")
    public BrakingSystemRecord BrakingSystemRecordModel;


    @SerializedName("Sent")
    public boolean Sent;

    public boolean getSent() {
        return Sent;
    }

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
