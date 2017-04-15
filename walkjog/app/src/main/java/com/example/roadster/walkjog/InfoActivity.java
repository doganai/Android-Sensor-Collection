package com.example.roadster.walkjog;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoActivity extends AppCompatActivity {

    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText emailEdit;
    EditText genderEdit;

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
        firstNameEdit = (EditText)findViewById(R.id.firstName);
        String firstName = firstNameEdit.getText().toString();
        lastNameEdit = (EditText)findViewById(R.id.lastName);
        String lastName = lastNameEdit.getText().toString();
        emailEdit = (EditText)findViewById(R.id.email);
        String email = emailEdit.getText().toString();
        genderEdit = (EditText)findViewById(R.id.gender);
        String gender = genderEdit.getText().toString();

        //Create new File and Save All Info
        FileOutputStream outputStream;

        //GET DATE FOR INFO FILE
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm:ss");
        String t = sdf.format(new Date());

        String infoString = firstName + "\n" + lastName + "\n" + email + "\n" + gender + "\n" + t;

        File acc = Environment.getExternalStorageDirectory();


        try {
            outputStream = new FileOutputStream(acc.getAbsolutePath() + "/UserFiles" + "/info_walkjog_" + email + "_" + t +".txt");
            outputStream.write(infoString.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String newAct = email + "_" + t;

        startNewActivity.putExtra("new",newAct);

        this.finish();

        //Send To New Activity
        startActivity(startNewActivity);

    }
}
