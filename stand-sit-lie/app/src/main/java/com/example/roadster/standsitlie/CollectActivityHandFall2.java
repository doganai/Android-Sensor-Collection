package com.example.roadster.standsitlie;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.roadster.standsitlie.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CollectActivityHandFall2 extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor accelerometer;
    private Sensor gravity;
    private Sensor gyroscope;
    private Sensor linear_accelerometer;
    private Sensor magneticfield;
    private Sensor rotation_vector;
    private Handler handler;

    //TIME
    int timeRemaining = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_activity_hand_fall2);

        //BEEP SOUND
        final MediaPlayer beep = MediaPlayer.create(this, R.raw.beep);

        //TIMER FOR BEEPS
        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                timeRemaining = timeRemaining - 1000;
                if(timeRemaining == 0)
                {

                    beep.start();

                }
                else
                {
                    handler.postDelayed(this, 1000);
                }


            }
        };

        //EXECUTES RUNNABLE
        handler.postDelayed(runnable, 1000);

        //Sensors
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);

        gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        linear_accelerometer = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        magneticfield = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        rotation_vector = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        sm.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        sm.registerListener(this, gravity,SensorManager.SENSOR_DELAY_NORMAL);

        sm.registerListener(this, gyroscope,SensorManager.SENSOR_DELAY_NORMAL);

        sm.registerListener(this, linear_accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        sm.registerListener(this, magneticfield,SensorManager.SENSOR_DELAY_NORMAL);

        sm.registerListener(this, rotation_vector,SensorManager.SENSOR_DELAY_NORMAL);

    }


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

    @Override
    public void onSensorChanged(SensorEvent event) {

        CharSequence accString = null;
        CharSequence gravString = null;
        CharSequence gyroString = null;
        CharSequence linearString = null;
        CharSequence magString = null;
        CharSequence rotationString = null;

        Sensor source = event.sensor;

        String sensorLine = "";

        Long tsLong = System.currentTimeMillis();

        String tsString = tsLong.toString();

        //IF STATEMENT FOR SENSOR CLARIFICATION
        if(source.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            accString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "ACCELEROMETER" + " " + accString;

        }
        if(source.getType() == Sensor.TYPE_GRAVITY)
        {
            gravString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "GRAVITY" + " " + gravString;

        }
        if(source.getType() == Sensor.TYPE_GYROSCOPE)
        {
            gyroString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "GYROSCOPE" + " " + gyroString;

        }
        if(source.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {
            linearString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "LINEAR_ACCELERATION" + " " + linearString;

        }
        if(source.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            magString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "MAGNETIC_FIELD" + " " + magString;

        }
        if(source.getType() == Sensor.TYPE_ROTATION_VECTOR)
        {
            rotationString = event.values[0] + " " + event.values[1] + " " + event.values[2];

            sensorLine = tsString + " " + "ROTATION_VECTOR" + " " + rotationString;

        }

        writeToExternal(sensorLine);

    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void writeToExternal(String data)//WRITE TO EXTERNAL
    {

        String newAct = getIntent().getStringExtra("new");

        FileOutputStream outputStream;

        String finalString = data + "\n\n";

        File acc = Environment.getExternalStorageDirectory();

        File userFile = new File(acc.getAbsolutePath() + "/UserFiles" + "/sitFallHand_" + newAct +  ".txt");

        try {
            userFile.createNewFile();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            outputStream = new FileOutputStream(acc.getAbsolutePath() + "/UserFiles" + "/sitFallHand_" + newAct +  ".txt", true);
            outputStream.write(finalString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //STOP ALL
    public void stopButton(View view){

        sm.unregisterListener(this);

        String newAct = getIntent().getStringExtra("new");

        //Creates New Intent
        Intent startNewActivity = new Intent(this, CollectActivityPocket2.class);

        startNewActivity.putExtra("new", newAct);

        //CLOSE ACTIVITY
        this.finish();

        //Send To New Activity
        startActivity(startNewActivity);



    }
}
