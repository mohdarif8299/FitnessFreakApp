package com.fitnessfreak.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessfreak.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class ExerciseActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    OkHttpClient client;
    @BindView(R.id.exerciserecycler)
    RecyclerView exerciserecycler;
    List<ExerciseModel> exerciseModelList;
    @BindView(R.id.main_title)
    TextView mainTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ButterKnife.bind(this);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String url = i.getStringExtra("url");
        String key = i.getStringExtra("key");
        client = new OkHttpClient();
        mainTitle.setText(title);
        exerciseModelList = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(key).child(title);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("SingleValue", dataSnapshot.getValue() + "cc");
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    Log.d("AingleValue", post.getValue() + "cc");
                    if (!post.getKey().equals("imageUrl")) {
                        exerciseModelList.add(new ExerciseModel(post.getKey(),
                                post.child("imageUrl").getValue().toString(),
                                post.child("video").getValue().toString(),
                                post.child("description").getValue().toString(),
                                post.child("difficulty").getValue().toString(),
                                post.child("targeted muscles").getValue().toString(),
                                post.child("benefit").getValue().toString(),
                                post.child("duration").getValue().toString()
                        ));
                    }
                }
                Collections.reverse(exerciseModelList);
                ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exerciseModelList, getApplicationContext());
                exerciserecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                exerciserecycler.setNestedScrollingEnabled(true);
                exerciserecycler.setAdapter(exerciseAdapter);
                exerciserecycler.setHasFixedSize(true);
                exerciserecycler.scrollToPosition(0);
                exerciseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
