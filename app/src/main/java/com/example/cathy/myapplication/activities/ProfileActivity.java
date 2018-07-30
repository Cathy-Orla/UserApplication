package com.example.cathy.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.os.Bundle;
import android.view.View;

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.model.Profile;
import com.example.cathy.myapplication.model.User;
import com.example.cathy.myapplication.sql.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = ProfileActivity.this;
    //declaring variables
    private NestedScrollView nestedScrollView; //Check this

    private TextInputLayout textProfileNameLayout;
    private TextInputLayout textProfileSireLayout;
    private TextInputLayout textProfileDamLayout;
    private TextInputLayout textProfileAgeLayout;
    private TextInputLayout textProfileGenderLayout;
    private TextInputLayout textProfilePaddockLayout;

    private TextInputEditText textProfileNameEdit;
    private TextInputEditText textProfileSireEdit;
    private TextInputEditText textProfileDamEdit;
    private TextInputEditText textProfileAgeEdit;
    private TextInputEditText textProfileGenderEdit;
    private TextInputEditText textProfilePaddockEdit;

    private AppCompatButton appCompatButtonProfile;
    private DatabaseHelper databaseHelper;
    private Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textProfileNameLayout = (TextInputLayout) findViewById(R.id.textProfileNameLayout);
        textProfileSireLayout = (TextInputLayout) findViewById(R.id.textProfileSireLayout);
        textProfileDamLayout = (TextInputLayout) findViewById(R.id.textProfileDamLayout);
        textProfileAgeLayout = (TextInputLayout) findViewById(R.id.textProfileAgeLayout);
        textProfileGenderLayout = (TextInputLayout) findViewById(R.id.textProfileGenderLayout);
        textProfilePaddockLayout = (TextInputLayout) findViewById(R.id.textProfilePaddockLayout);

        textProfileNameEdit = (TextInputEditText) findViewById(R.id.textProfileNameEdit);
        textProfileSireEdit = (TextInputEditText) findViewById(R.id.textProfileSireEdit);
        textProfileDamEdit = (TextInputEditText) findViewById(R.id.textProfileDamEdit);
        textProfileAgeEdit = (TextInputEditText) findViewById(R.id.textProfileAgeEdit);
        textProfileGenderEdit = (TextInputEditText) findViewById(R.id.textProfileGenderEdit);
        textProfilePaddockEdit = (TextInputEditText) findViewById(R.id.textProfilePaddockEdit);


        appCompatButtonProfile = (AppCompatButton) findViewById(R.id.appCompatButtonProfile);

    }

    private void initListeners() {
        appCompatButtonProfile.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        profile = new Profile();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatButtonProfile:
                dataToSQLite();
                break;
        }
    }

    private void dataToSQLite() {  //WITHOUT VALIDATION


            profile.setName(textProfileNameEdit.getText().toString().trim());
            profile.setSire(textProfileSireEdit.getText().toString().trim());
            profile.setDam(textProfileDamEdit.getText().toString().trim());
            profile.setAge(textProfileAgeEdit.getText().toString().trim());
            profile.setGender(textProfileGenderEdit.getText().toString().trim());
            profile.setPaddock(textProfilePaddockEdit.getText().toString().trim());

            databaseHelper.addProfile(profile);

            // Snack Bar to show success message that record saved successfully, bottom of page
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
    }
    
    private void emptyInputEditText() {
        textProfileNameEdit.setText(null);
        textProfileSireEdit.setText(null);
        textProfileDamEdit.setText(null);
        textProfileAgeEdit.setText(null);
        textProfileGenderEdit.setText(null);
        textProfilePaddockEdit.setText(null);
    }
}