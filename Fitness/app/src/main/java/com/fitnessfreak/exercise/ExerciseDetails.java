package com.fitnessfreak.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessfreak.R;
import com.google.gson.Gson;
import com.potyvideo.library.AndExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetails extends AppCompatActivity {
    @BindView(R.id.main_title)
    TextView mainTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.andExoPlayerView)
    AndExoPlayerView video;
    @BindView(R.id.description)
    TextView description;
    ExerciseModel exerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        ButterKnife.bind(this);
        Intent i = getIntent();
        if (i == null) Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        else {
            Gson gson = new Gson();
            exerciseModel = gson.fromJson(getIntent().getStringExtra("exercise"), ExerciseModel.class);
            mainTitle.setText(exerciseModel.getTitle());
            title.setText(exerciseModel.getTitle());
            video.setSource(exerciseModel.getVideo());
            description.setText(exerciseModel.getDescription());
        }
    }
}
