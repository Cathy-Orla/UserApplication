package com.example.cathy.myapplication.activities;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.adapters.ProfilesRecyclerAdapter;
import com.example.cathy.myapplication.model.Profile;
import com.example.cathy.myapplication.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EquineProfileActivity extends AppCompatActivity {

    private AppCompatActivity activity = EquineProfileActivity.this;
    private AppCompatTextView textViewProfileName;
    private RecyclerView recyclerViewProfiles;
    private List<Profile> listProfiles;
    private ProfilesRecyclerAdapter profilesRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equine_profile);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }
    private void initObjects() {
        listProfiles = new ArrayList<>();
        profilesRecyclerAdapter = new ProfilesRecyclerAdapter(listProfiles);

        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getApplicationContext()); //***
        recyclerViewProfiles.setLayoutManager(nLayoutManager);
        recyclerViewProfiles.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProfiles.setHasFixedSize(true);
        recyclerViewProfiles.setAdapter(profilesRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        dataFromSQLite();
    }

    private void initViews() {
            textViewProfileName = (AppCompatTextView) findViewById(R.id.textViewProfileName);
            recyclerViewProfiles = (RecyclerView) findViewById(R.id.recyclerViewProfiles);
        }

    private void dataFromSQLite() {
        //method which adds all profiles to array list
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listProfiles.clear();
                listProfiles.addAll(databaseHelper.getAllProfile());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                profilesRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}






