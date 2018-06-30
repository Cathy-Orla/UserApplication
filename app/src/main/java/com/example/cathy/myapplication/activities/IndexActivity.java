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

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.com.example.cathy.myapplication.helpers.InputValidation;
import com.example.cathy.myapplication.model.User;
import com.example.cathy.myapplication.sql.DatabaseHelper;

import java.util.List;

import static com.example.cathy.myapplication.R.id.logout;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = IndexActivity.this;

    private AppCompatButton logout;
    private AppCompatButton newProfile;
    private AppCompatButton editDetails;
    private AppCompatTextView loggedInAs;
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

        initViews();
        initListeners();
        initObjects();
    }
    @SuppressLint("WrongViewCast") //look into button vs appcombatbutton, appcombat is a descendent of button
    private void initViews() {

        logout = (AppCompatButton) findViewById(R.id.logout);
        loggedInAs = (AppCompatTextView) findViewById(R.id.loggedInAs);
        newProfile = (AppCompatButton) findViewById(R.id.newProfile);
        editDetails = (AppCompatButton) findViewById(R.id.editDetails);
        profileHeader = (AppCompatTextView) findViewById(R.id.profileHeader);
        horseProfiles = (AppCompatTextView) findViewById(R.id.horseProfiles);
        profilesList = (RecyclerView) findViewById(R.id.profilesList);
    }

    private void initListeners() {
        logout.setOnClickListener(this);
        newProfile.setOnClickListener(this);
        editDetails.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                finish();
                break;
            /*case R.id.newProfile:
                // Navigate to RegisterActivity
                Intent intentNewHorse = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break; */
            //case R.id.editDetails:

        }
    }
}
