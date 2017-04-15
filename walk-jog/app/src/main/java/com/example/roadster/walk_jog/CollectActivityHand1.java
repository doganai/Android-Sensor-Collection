package com.example.roadster.walk_jog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CollectActivityHand1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_activity_hand1);
    }

    public void walkStart(View view){

        //Creates New Intent
        Intent startNewActivity = new Intent(this, CollectActivityHandFall1.class);

        //Send To New Activity
        startActivity(startNewActivity);



    }
}
