package com.example.cathy.myapplication.adapters;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cathy.myapplication.R;
import com.example.cathy.myapplication.activities.EquineProfileActivity;
import com.example.cathy.myapplication.activities.MainPageActivity;
import com.example.cathy.myapplication.model.Profile;
import com.example.cathy.myapplication.sql.DatabaseHelper;

import java.util.List;

public class ProfilesRecyclerAdapter extends RecyclerView.Adapter<ProfilesRecyclerAdapter.ProfileViewHolder> {

    private List<Profile> listProfiles;
    private DatabaseHelper databaseHelper;

    public ProfilesRecyclerAdapter(List<Profile> listProfiles) {
        this.listProfiles = listProfiles;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_recycler, parent, false);

        return new ProfileViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        Log.v(ProfilesRecyclerAdapter.class.getSimpleName(), "" + listProfiles.size());
        return listProfiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder { //HERE

        public AppCompatTextView textViewProfileName;
        public AppCompatTextView textViewDam;
        public AppCompatTextView textViewSire;
        public AppCompatTextView textViewAge;
        public AppCompatTextView textViewGender;
        public AppCompatTextView textViewPaddock;
        public AppCompatButton deleteProfile;

        private ProfileViewHolder(View view) {
            super(view);
            textViewProfileName = (AppCompatTextView) view.findViewById(R.id.textViewProfileName);
            textViewDam = (AppCompatTextView) view.findViewById(R.id.textViewDam);
            textViewSire = (AppCompatTextView) view.findViewById(R.id.textViewSire);
            textViewAge = (AppCompatTextView) view.findViewById(R.id.textViewAge);
            textViewGender = (AppCompatTextView) view.findViewById(R.id.textViewGender);
            textViewPaddock = (AppCompatTextView) view.findViewById(R.id.textViewPaddock);
            deleteProfile = (AppCompatButton) view.findViewById(R.id.deleteProfile);
        }
    }
    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        holder.textViewProfileName.setText(listProfiles.get(position).getName());
        holder.textViewDam.setText(listProfiles.get(position).getDam());
        holder.textViewSire.setText(listProfiles.get(position).getSire());
        holder.textViewAge.setText(listProfiles.get(position).getAge());
        holder.textViewGender.setText(listProfiles.get(position).getGender());
        holder.textViewPaddock.setText(listProfiles.get(position).getPaddock());

        /* holder.deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.deleteProfile:
                        //databaseHelper.deleteProfileMethod(profile);
                        break;
                }
            } */
        }
    }

