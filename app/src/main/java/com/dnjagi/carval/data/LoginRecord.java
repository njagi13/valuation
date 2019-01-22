package com.dnjagi.carval.data;

import com.dnjagi.carval.Model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by user on 7/30/2018.
 */

public class LoginRecord extends DatabaseObject {


    @SerializedName("email")
    public String email;
    public String getEmail() {
        return email;
    }

    @SerializedName("password")
    public String password;
    public String getpassword() {
        return password;
    }

    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
