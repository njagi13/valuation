package com.dnjagi.carval.data;

import com.dnjagi.carval.model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by user on 7/30/2018.
 */

public class UploadRecord extends DatabaseObject {

    @SerializedName("RegistrationNumber")
    public String RegistrationNumber;
    public String getRegistrationNumber() {
        return RegistrationNumber;
    }


    @SerializedName("Brand")
    public String Brand;
    public String getBrand() {
        return Brand;
    }

    @SerializedName("Model")
    public String Model;
    public String getModel() {
        return Model;
    }

    @SerializedName("Year")
    public String Year;
    public String getYear() {
        return Year;
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
