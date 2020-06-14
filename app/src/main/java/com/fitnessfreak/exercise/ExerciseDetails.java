package com.fitnessfreak.exercise;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessfreak.R;
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
    @BindView(R.id.difficulty)
    TextView diff;
    @BindView(R.id.duration)
    TextView dura;
    @BindView(R.id.b_title)
    TextView b_title;
    @BindView(R.id.b_desc)
    TextView b_desc;
    @BindView(R.id.t_muscles)
    TextView t_muss;
    ExerciseModel exerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        ButterKnife.bind(this);
        exerciseModel=getIntent().getParcelableExtra("exercise");
        video.setSource(exerciseModel.getVideo());
        mainTitle.setText("Excercise");
        title.setText(exerciseModel.getTitle());
        diff.setText(exerciseModel.getDifficulty());
        dura.setText(exerciseModel.getDuration());
        description.setText(exerciseModel.getDescription());
        b_title.setText("Benefit");
        b_desc.setText(exerciseModel.getBenefit());
        t_muss.setText(exerciseModel.getTargetMuscle());
    }
}
