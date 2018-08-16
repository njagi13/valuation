package com.dnjagi.carval.data;

import com.dnjagi.carval.database.PersistAnnotation;
import com.dnjagi.carval.model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 7/30/2018.
 */
@PersistAnnotation
public class LogRecord extends DatabaseObject {

    @SerializedName("sent")
    public int sent;

    public int getsent() {
        return sent;
    }

    @SerializedName("ErrorMessage")
    public String ErrorMessage;
    public String getErrorMessage() {
        return ErrorMessage;
    }

    @SerializedName("EventDate")
    public Date EventDate = new Date();
    public Date getEventDate() {
        return EventDate;
    }

    @SerializedName("ErrorLevel")
    public String ErrorLevel;
    public String getErrorLevel() {
        return ErrorLevel;
    }

    @SerializedName("StackTrace")
    public String StackTrace;
    public String getStackTrace() {
        return StackTrace;
    }


    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
