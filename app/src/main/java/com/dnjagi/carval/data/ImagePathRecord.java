package com.dnjagi.carval.data;

import com.dnjagi.carval.database.PersistAnnotation;
import com.dnjagi.carval.Model.DatabaseObject;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 7/30/2018.
 */
@PersistAnnotation
public class ImagePathRecord extends DatabaseObject {

    @SerializedName("FileStatus")
    public int FileStatus;
    public int FileStatus() {
        return FileStatus;
    }

    @SerializedName("ImagePath")
    public String ImagePath;

    @SerializedName("FileName")
    public String FileName;

    @SerializedName("UploadRecordID")
    public String UploadRecordID;
    @Override
    public UUID getObjectID() {
        return null;
    }

    @Override
    public void setObjectID(UUID objectID) {

    }
}
