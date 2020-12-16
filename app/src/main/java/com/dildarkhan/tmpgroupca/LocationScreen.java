package com.dildarkhan.tmpgroupca;

import android.Manifest;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.util.TimingLogger;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.dildarkhan.tmpgroupca.db.DatabaseClient;
import com.dildarkhan.tmpgroupca.db.LocationUpdates;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class LocationScreen extends AppCompatActivity {
    private static final String TAG = "LocationScreen";
    Button btGetLocation;
    private static final int REQUEST_CODE_LOCATION_PERMISSION =1;

    long selectedInterval;
    String selectedIntervalName;

    FirebaseFirestore db;
    ProgressDialog progressDialog;

    Chronometer chronometer;
    LocationManager locationManager;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_screen);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        db = FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCancelable(false);

        findViewById(R.id.btStartLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nukeTableLocationUpdates();
                //viewLocationUpdates();
                chronometer=new Chronometer("Bench");
                chronometer.restart();
                getLocationDetails();
                chronometer.logElapsedTime();

            }
        });

    }

    private void getLocationDetails(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LocationScreen.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
        }else{
            //startLocationService();

            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                savetoDatabase(lat,longi,"12345");
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }


        }
    }


    private void sendDataToServer(){
        //sending db data to backend if db has data
        //backend api for sending data using retrofit.
        //implement work manager for periodically data sending...
        Map<String, Object> locData = new HashMap<>();
        locData.put("Current Time", "temp Time");
        locData.put("Lat", "1234");
        locData.put("Long", "5678");
        db.collection("LocationUpdates")
                .add(locData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(),"Registration Done Successfully!",Toast.LENGTH_LONG).show();

                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("AddItemScreen", "Error adding document", e);
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getApplicationContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                    }
                });


    }



    private void savetoDatabase(final double lat, final double lon, final String latestTime){
        final Chronometer chronometer;
        chronometer=new Chronometer("Bench DB");
        chronometer.restart();
        Log.d("Bench DB"," Restart");

        class AddLocationUpdates extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                LocationUpdates locationUpdates=new LocationUpdates();
                locationUpdates.setUuid("123");
                locationUpdates.setUserPhone("456789");
                locationUpdates.setTimeCreated(latestTime);
                locationUpdates.setTimeUpdated(latestTime);
                locationUpdates.setLatitude(lat+"");
                locationUpdates.setLongitude(lon+"");
                locationUpdates.setAccuracyMode("");
                locationUpdates.setIntervalPeriod("");
                locationUpdates.setField1("");
                locationUpdates.setField2("");

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .locationUpdatesDao()
                        .insert(locationUpdates);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //do on completion
                Log.d(TAG,"Saving to local DB");

                //mWorkManager.enqueue(mRequest);

                chronometer.logElapsedTime();
            }
        }
        AddLocationUpdates addLocationUpdates=new AddLocationUpdates();
        addLocationUpdates.execute();

    }

}