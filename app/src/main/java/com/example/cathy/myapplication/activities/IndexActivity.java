package com.example.cathy.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.sql.DatabaseHelper;



public class IndexActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private final AppCompatActivity activity = IndexActivity.this;

    private AppCompatButton newProfile;
    private AppCompatButton editDetails;
    private AppCompatButton accessProfilesButton;
    /*private AppCompatTextView profileHeader;
    private AppCompatTextView horseProfiles;*/
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();



        Spinner spinner = findViewById(R.id.settings);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        initViews();
        initListeners();
        initObjects();
    }


    @SuppressLint("WrongViewCast")
    private void initViews() {

        newProfile = (AppCompatButton) findViewById(R.id.newProfile);
       /* profileHeader = (AppCompatTextView) findViewById(R.id.profileHeader);
        horseProfiles = (AppCompatTextView) findViewById(R.id.horseProfiles);
        //profilesList = (RecyclerView) findViewById(R.id.profilesList);*/
        accessProfilesButton = (AppCompatButton) findViewById(R.id.accessProfilesButton);
        Toast.makeText(IndexActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {

        newProfile.setOnClickListener(this);
        accessProfilesButton.setOnClickListener(this);
    }

    private void initObjects() {

        databaseHelper = new DatabaseHelper(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings:
                finish();
                break;
            case R.id.newProfile:
                // Navigate to ProfileActivity
                Intent intentNewHorse = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intentNewHorse);
                break;
            case R.id.accessProfilesButton:
                Intent intentToProfile = new Intent(getApplicationContext(), EquineProfileActivity.class);
                startActivity(intentToProfile);
                break;
            //case R.id.editDetails:

        }
    }
    //methods for drop down menu
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0:
                finish();//settings --- change this later
                break;
            case 1:
                finish();
                break;
            case 2:
                Intent intentEdit = new Intent(getApplicationContext(), EditDetails.class);
                startActivity(intentEdit);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
