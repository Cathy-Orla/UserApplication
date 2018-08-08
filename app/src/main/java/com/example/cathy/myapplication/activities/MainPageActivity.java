package com.example.cathy.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.sql.DatabaseHelper;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener {

    private final AppCompatActivity activity = MainPageActivity.this;

    private AppCompatButton newProfile;
    private AppCompatButton accessProfilesButton;
    private DatabaseHelper databaseHelper;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();

        spinner = findViewById(R.id.settings);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        initViews();
        initListeners();
        initObjects();
    }


    @SuppressLint("WrongViewCast")
    private void initViews() {

        newProfile = (AppCompatButton) findViewById(R.id.newProfile);
        accessProfilesButton = (AppCompatButton) findViewById(R.id.accessProfilesButton);
        Toast.makeText(MainPageActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {
        //spinner.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                finish();//settings --- change this later
                break;
            case 1:
                Intent intentAccounts = new Intent(getApplicationContext(), UsersListActivity.class);
                break;
            case 2:
                Intent intentEdit = new Intent(getApplicationContext(), EditDetails.class);
                startActivity(intentEdit);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(MainPageActivity.this, "No option selected", Toast.LENGTH_SHORT).show();
    }
}
