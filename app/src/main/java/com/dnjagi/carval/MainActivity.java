package com.dnjagi.carval;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dnjagi.carval.data.LogRecord;
import com.dnjagi.carval.database.SchemaGenerator;
import com.dnjagi.carval.database.SugarContext;
import com.dnjagi.carval.database.SugarDb;

import com.dnjagi.carval.Services.UploadService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        CarValuationFragment.OnFragmentInteractionListener,
        ReportsFragment.OnFragmentInteractionListener,
        CameraViewFragment.OnFragmentInteractionListener,
        EngineSystemFragment.OnFragmentInteractionListener,
        TransmissionSystemFragment.OnFragmentInteractionListener,
        SuspensionSystemFragment.OnFragmentInteractionListener,
        BrakingSystemFragment.OnFragmentInteractionListener,
        AccidentAssessmentFragment.OnFragmentInteractionListener {

    public static Context appContext;
    private boolean cleanUpDatabase = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                Toast toast = Toast.makeText(getApplicationContext(), "Welcome" , Toast.LENGTH_LONG);
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //NOTE: creating fragment object
        Fragment fragment = null;
        if (id == R.id.car_valuation) {
            fragment = new CarValuationFragment();
        } else if (id == R.id.ocr) {
            //fragment = new AccidentAssessmentFragment();
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            startActivity(intent);
        } else if (id == R.id.reports) {
            fragment = new ReportsFragment();
        }
       /* } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }*/

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
