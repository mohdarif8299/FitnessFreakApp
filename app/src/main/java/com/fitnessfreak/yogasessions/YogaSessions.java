package com.fitnessfreak.yogasessions;

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

public class YogaSessions extends AppCompatActivity {
    @BindView(R.id.andExoPlayerView)
    AndExoPlayerView andExoPlayerView;
    @BindView(R.id.main_title)
    TextView mainTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.benefits)
    TextView benefits;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.difficulty)
    TextView difficulty;
    YogaSessionsModel yogaSessionsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_sessions);
        ButterKnife.bind(this);
        Intent i = getIntent();
        if (i == null) Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        else {
            Gson gson = new Gson();
            yogaSessionsModel = gson.fromJson(getIntent().getStringExtra("item"), YogaSessionsModel.class);
            andExoPlayerView.setSource(yogaSessionsModel.getVideo());
            mainTitle.setText(yogaSessionsModel.getTitle());
            title.setText(yogaSessionsModel.getTitle());
            description.setText(yogaSessionsModel.getDescription());
            duration.setText(yogaSessionsModel.getDuration());
            difficulty.setText(yogaSessionsModel.getDifficulty());
            benefits.setText(yogaSessionsModel.getBenefits());
        }
    }
}
