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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cathy.myapplication.MainActivity;
import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.com.example.cathy.myapplication.helpers.InputValidation;
import com.example.cathy.myapplication.model.User;
import com.example.cathy.myapplication.sql.DatabaseHelper;

import java.util.List;


public class IndexActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private final AppCompatActivity activity = IndexActivity.this;

    private AppCompatButton newProfile;
    private AppCompatButton editDetails;
    private AppCompatTextView profileHeader;
    private AppCompatTextView horseProfiles;
    private RecyclerView profilesList;
    private List<User> listHorses;
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
    //look into button vs appcombatbutton, appcombat is a descendent of button
    private void initViews() {

        newProfile = (AppCompatButton) findViewById(R.id.newProfile);
        profileHeader = (AppCompatTextView) findViewById(R.id.profileHeader);
        horseProfiles = (AppCompatTextView) findViewById(R.id.horseProfiles);
        profilesList = (RecyclerView) findViewById(R.id.profilesList);
        Toast.makeText(IndexActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {
        newProfile.setOnClickListener(this);
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
            //case R.id.editDetails:

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0:
                //settings
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
