package com.example.roadster.standsitlie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.roadster.standsitlie.R;

public class CollectActivityPocket2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_activity_pocket2);
    }

    public void walkStart(View view){

        String newAct = getIntent().getStringExtra("new");

        //Creates New Intent
        Intent startNewActivity = new Intent(this, CollectActivityPocketFall2.class);

        startNewActivity.putExtra("new",newAct);

        this.finish();

        //Send To New Activity
        startActivity(startNewActivity);



    }
}
