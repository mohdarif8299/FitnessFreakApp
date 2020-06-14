package com.fitnessfreak.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessfreak.LoginActivity;
import com.fitnessfreak.R;
import com.fitnessfreak.pedometer.PedometerActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private List<String> userData;
    @BindView(R.id.email)
    TextView email;

    @OnClick(R.id.update_name)
    void updateName() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(auth.getCurrentUser().getEmail().replace("@", "_").replace(".", ""));
        databaseReference.child("name").setValue(nameEdit.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nameEdit.clearFocus();
                setUserData();
                // nameEdit.setText(nameEdit.getText().toString().trim());
                Toast.makeText(UserProfileActivity.this, "Name Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nameEdit.clearFocus();
                Toast.makeText(UserProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.profile_image)
    void backPressed() {
        onBackPressed();
    }

    @OnClick(R.id.update_number)
    void updateNumber() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(auth.getCurrentUser().getEmail().replace("@", "_").replace(".", ""));
        databaseReference.child("number").setValue(numberEdit.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                numberEdit.clearFocus();
                setUserData();
//                numberEdit.setText(numberEdit.getText().toString().trim());
                Toast.makeText(UserProfileActivity.this, "Number Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                numberEdit.clearFocus();
                Toast.makeText(UserProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        ;
    }

    @OnClick(R.id.logout)
    void logout() {
        getSharedPreferences(getString(R.string.login), MODE_PRIVATE).edit().clear().apply();
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    @OnClick(R.id.pedometer)
    void openPedometer() {
        startActivity(new Intent(this, PedometerActivity.class));
    }

    @BindView(R.id.update_name)
    TextView updateName;
    @BindView(R.id.update_number)
    TextView updateNumber;

    @BindView(R.id.name)
    EditText nameEdit;
    @BindView(R.id.number)
    EditText numberEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        userData = new ArrayList<>();
        setUserData();
        email.setText(auth.getCurrentUser().getEmail());
        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 4) {
                    updateName.setVisibility(View.GONE);
                } else {
                    updateName.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        numberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 10) {
                    updateNumber.setVisibility(View.GONE);
                } else {
                    updateNumber.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(auth.getCurrentUser().getEmail().replace("@", "_").replace(".", ""));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String data = dataSnapshot1.getValue(String.class);
                    userData.add(data);
                }
                try {
                    nameEdit.setText(userData.get(0) + "");
                    numberEdit.setText(userData.get(1) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}