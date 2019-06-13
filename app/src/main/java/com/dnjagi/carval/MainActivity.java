package com.dnjagi.carval;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Services.UploadService;
import com.dnjagi.carval.data.LogRecord;
import com.dnjagi.carval.database.SchemaGenerator;
import com.dnjagi.carval.database.SugarContext;
import com.dnjagi.carval.database.SugarDb;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        VisualInspectionFragment.OnFragmentInteractionListener,
        CarValuationFragment.OnFragmentInteractionListener,
        ReportsFragment.OnFragmentInteractionListener,
        CameraViewFragment.OnFragmentInteractionListener,
        EngineSystemFragment.OnFragmentInteractionListener,
        TransmissionSystemFragment.OnFragmentInteractionListener,
        SuspensionSystemFragment.OnFragmentInteractionListener,
        BrakingSystemFragment.OnFragmentInteractionListener,
        ValuationsFragment.OnFragmentInteractionListener,
        AccidentAssessmentFragment.OnFragmentInteractionListener,
        ConfirmCorrectionFragment.OnFragmentInteractionListener {

    public static Context appContext;
    private boolean cleanUpDatabase = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //request for permissions
        GrantPermissions();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //SET TOKEN
        SharedPreferences preferences = getSharedPreferences(GlobalVarible.LoggedIn, getApplicationContext().MODE_PRIVATE);
        GlobalVarible.token = preferences.getString("Token", null);
        GlobalVarible.email = preferences.getString("Email", null);

        //CHECH TOKEN
        if (preferences.getLong("ExpiredDate", -1) > System.currentTimeMillis()) {

        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        appContext = this.getApplicationContext();
        SugarContext.init(this.getApplicationContext());
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        boolean tableExists = schemaGenerator.tableExists(LogRecord.class, new SugarDb(getApplicationContext()).getDB());
        if (!tableExists) {
            schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
            SugarContext.init(getApplicationContext());
            schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
        }

        //when need to clean up database
        if (cleanUpDatabase) {
            schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
            SugarContext.init(getApplicationContext());
            schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
        }

        //Start background services
        startService(new Intent(appContext, UploadService.class));


        // go to home page
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new HomeFragment());
        ft.commit();
    }


    public void GrantPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG);
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  if (count == 0) {
           finish();
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //NOTE: creating fragment object
        Fragment fragment = null;
        if (id == R.id.car_valuation) {
            GlobalVarible.uploadRecord = null;
            fragment = new CarValuationFragment();
        } else if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.ocr) {
            //fragment = new AccidentAssessmentFragment();
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            startActivity(intent);
        } else if (id == R.id.reports) {
            fragment = new ValuationsFragment();
        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getSharedPreferences(GlobalVarible.LoggedIn, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            finish();
            GlobalVarible.token = "";
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        final Fragment finalFragment = fragment;
        new Thread(new Runnable() {
            public void run() {
                //NOTE: Fragment changing code
                if (finalFragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, finalFragment);
                    ft.commit();
                }

                //NOTE:  Closing the drawer after selecting

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //Ya you can also globalize this variable :P
                        drawer.closeDrawer(GravityCompat.START);
                    }
                });
            }
        }).start();
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
