package com.dnjagi.carval;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.dnjagi.carval.data.BrakingSystemRecord;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.data.UploadRecordAPI;
import com.dnjagi.carval.database.SugarContext;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.dnjagi.carval", appContext.getPackageName());
    }

    public static Context appContext;
    @Test
    public void postAssessmentTest(){
        appContext =  InstrumentationRegistry.getTargetContext();
        SugarContext.init(appContext);
        UploadRecord uploadRecord = new UploadRecord();
        uploadRecord.BodyType = "Hattch";
        uploadRecord.ChassisNumber= "256664SA552565";
        uploadRecord.Color = "White";
        uploadRecord.DateOfReg = "May 2007";
        uploadRecord.EngineNumber = "522264454W556";
        uploadRecord.EngineRating = "2500cc";
        uploadRecord.Email = "njoroge@gmail.com";
        uploadRecord.FirstName = "John";
        uploadRecord.LastName = "Kiarie";
        uploadRecord.BrakingSystemRecordModel = brakingSystem();

        UploadRecordAPI uploadRecordAPI = new UploadRecordAPI();
        uploadRecordAPI.PostUploadRecord(uploadRecord);


    }

    private BrakingSystemRecord brakingSystem() {
        BrakingSystemRecord brakingSystemRecord = new BrakingSystemRecord();
        brakingSystemRecord.ABSOperateProperly = true;
        brakingSystemRecord.BrakePedalFreePlayndTravelNormal = true;
        brakingSystemRecord.BrakesEffective = true;
        brakingSystemRecord.FluidLeaksInBrakeSystem = false;
        return  brakingSystemRecord;
    }
}
