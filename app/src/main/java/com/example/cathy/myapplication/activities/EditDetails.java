package com.example.cathy.myapplication.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.com.example.cathy.myapplication.helpers.InputValidation;
import com.example.cathy.myapplication.model.User;
import com.example.cathy.myapplication.sql.DatabaseHelper;

public class EditDetails extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = EditDetails.this;

    private TextInputLayout oldPass;
    private TextInputLayout newPass;
    private String oldPW;
    private String newPW;
    private Button submit2;
    private DatabaseHelper databaseHelper;
    private User user;
    //private String email = request.getSession.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();


    }

    private void initViews() {

        oldPass = (TextInputLayout) findViewById(R.id.oldPass);
        newPass = (TextInputLayout) findViewById(R.id.newPass);
        submit2 = (Button) findViewById(R.id.submit2);
    }

    private void initListeners() {

        //submit2.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }

    Button button = (Button) findViewById(R.id.submit2);

    @Override
    public void onClick(View view) {
            /*switch (view.getId()) {
                case R.id.submit2:
                    databaseHelper.changePassword(newPass,email);
                    break; */
    }
   /* public void updatePassword(View view) { //pass in SQLiteDatabase db?
            oldPW = oldPass.toString(); //relevant?
            newPW = newPass.toString();
            DatabaseHelper.changePassword(newPW, email);
        }
    }*/

}