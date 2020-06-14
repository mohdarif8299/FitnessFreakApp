package com.fitnessfreak.recieps;

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

public class ReciepeActivity extends AppCompatActivity {

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
    @BindView(R.id.food_type)
    TextView foodType;
    @BindView(R.id.calories)
    TextView calories;
    @BindView(R.id.ingredients)
    TextView ingredients;
    @BindView(R.id.instructions)
    TextView instructions;
    ReciepeModel nutritionPlanModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciepe);
        ButterKnife.bind(this);
        Intent i = getIntent();
        if (i == null) Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        else {
            Gson gson = new Gson();
            nutritionPlanModel = gson.fromJson(getIntent().getStringExtra("item"), ReciepeModel.class);
            andExoPlayerView.setSource(nutritionPlanModel.getVideo());
            foodType.setText(nutritionPlanModel.getFood_type());
            mainTitle.setText(nutritionPlanModel.getTitle());
            title.setText(nutritionPlanModel.getTitle());
            calories.setText(nutritionPlanModel.getCalories());
            description.setText(nutritionPlanModel.getDescription());
            duration.setText(nutritionPlanModel.getCooking_time());
            benefits.setText(nutritionPlanModel.getBenefits());
            ingredients.setText(nutritionPlanModel.getIngredients());
            instructions.setText(nutritionPlanModel.getInstruction());
        }
    }
}