package com.example.fotfeed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileEmail, etUsername;
    private TextView navHome, navAbout, navProfile;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navHome = findViewById(R.id.navHome);
        navAbout = findViewById(R.id.navAbout);
        navProfile = findViewById(R.id.navProfile);

        Button btnEditInfo = findViewById(R.id.btnEditInfo);
        Button btnSignOut = findViewById(R.id.btnSignOut);

        // Edit Info Button
        btnEditInfo.setOnClickListener(v -> showEditPopup());

        // Sign Out Button
        btnSignOut.setOnClickListener(v -> showSignOutPopup());


        navHome.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, NewsActivity.class));
            finish();
        });

        navAbout.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, AboutUsActivity.class));
            finish();
        });

        navProfile.setOnClickListener(v -> {
            // Stay here
        });

        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        etUsername = findViewById(R.id.etUsername);
        profilePic = findViewById(R.id.profilePic);

        loadUserData();
    }


    private void showSignOutPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.signout_popup, null);
        Button btnOk = popupView.findViewById(R.id.btnSignOutOk);
        Button btnCancel = popupView.findViewById(R.id.btnSignOutCancel);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(popupView)
                .create();

        btnOk.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
            finish();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }



    private void showEditPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.edit_popup, null);
        EditText editUsername = popupView.findViewById(R.id.editUsername);
        EditText editEmail = popupView.findViewById(R.id.editEmail);
        Button btnOk = popupView.findViewById(R.id.btnOk);
        Button btnCancel = popupView.findViewById(R.id.btnCancel);

        editUsername.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        editEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(popupView)
                .create();

        btnOk.setOnClickListener(view -> {
            String newUsername = editUsername.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newUsername)
                        .build();

                user.updateProfile(profileUpdates);
                user.updateEmail(newEmail);

                etUsername.setText("User Name: " + newUsername);
                tvProfileEmail.setText("Email: " + newEmail);

                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }




    private void loadUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            tvProfileEmail.setText("Email: " + user.getEmail());
            etUsername.setText("User Name: " + user.getDisplayName());
            // You can load profile photo if using Firebase Auth with Google, etc.
        } else {
            tvProfileEmail.setText("Not logged in");
            etUsername.setText("");
        }
    }
}