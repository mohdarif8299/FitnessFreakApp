package com.fitnessfreak.featureprograms;

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

public class FeaturedProgram extends AppCompatActivity {
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
    FeaturedProgramModel featuredProgramModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_program);
        ButterKnife.bind(this);
        Intent i = getIntent();
        if (i == null) Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        else {
            Gson gson = new Gson();
            featuredProgramModel = gson.fromJson(getIntent().getStringExtra("item"), FeaturedProgramModel.class);
            andExoPlayerView.setSource(featuredProgramModel.getVideo());
            mainTitle.setText(featuredProgramModel.getTitle());
            title.setText(featuredProgramModel.getTitle());
            description.setText(featuredProgramModel.getDescription());
            duration.setText(featuredProgramModel.getDuration());
            difficulty.setText(featuredProgramModel.getDifficulty());
            benefits.setText(featuredProgramModel.getBenefits());
        }
    }
}
