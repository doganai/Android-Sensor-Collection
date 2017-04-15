package com.example.roadster.walk_jog;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InfoActivity extends AppCompatActivity {

    String firstName;
    String lastName;
    String email;
    String gender;
    String currentDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    //Save user info
    public void saveInfo(View view){

        //Creates New Intent
        Intent startNewActivity = new Intent(this, CollectActivityHand1.class);

        //Gather Info From Activity
        firstName = findViewById(R.id.firstName).toString();
        lastName = findViewById(R.id.lastName).toString();
        email = findViewById(R.id.email).toString();
        gender = findViewById(R.id.gender).toString();
        currentDate = findViewById(R.id.currentDate).toString();
        currentTime = findViewById(R.id.currentTime).toString();

        //Create new File and Save All Info
        FileOutputStream outputStream;

        String infoString = firstName + "\n" + lastName + "\n" + email + "\n" + gender + "\n"
                + currentDate + "\n" +  currentTime;

        File acc = Environment.getExternalStorageDirectory();

        try {
            outputStream = new FileOutputStream(acc.getAbsolutePath() + "/MyAccFile" + "/info_" + lastName + ".txt");
            outputStream.write(infoString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Send To New Activity
        startActivity(startNewActivity);
        
    }
}
