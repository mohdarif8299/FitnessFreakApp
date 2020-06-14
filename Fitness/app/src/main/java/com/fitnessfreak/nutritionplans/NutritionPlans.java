package com.fitnessfreak.nutritionplans;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fitnessfreak.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NutritionPlans extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.meal)
    TextView meal;
    @BindView(R.id.snack)
    TextView snack;
    @BindView(R.id.main_title)
    TextView mainTitle;
    @BindView(R.id.v1)
    View view1;
    @BindView(R.id.v2)
    View view2;
    @BindView(R.id.v3)
    View view3;
    @BindView(R.id.total) TextView total;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.breakfast) void setBreakfast(){
        view1.setBackgroundColor(getColor(R.color.colorPrimary));
        view2.setBackgroundColor(Color.WHITE);
        view3.setBackgroundColor(Color.WHITE);
        meal.setText(nutritionPlanModel.getBreakfast());
        snack.setText(nutritionPlanModel.getSnack1());
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.lunch) void setLunch(){
        view2.setBackgroundColor(getColor(R.color.colorPrimary));
        view1.setBackgroundColor(Color.WHITE);
        view3.setBackgroundColor(Color.WHITE);
        meal.setText(nutritionPlanModel.getLunch());
        snack.setText(nutritionPlanModel.getSnack2());
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.dinner) void setDinner(){
        view3.setBackgroundColor(getColor(R.color.colorPrimary));
        view1.setBackgroundColor(Color.WHITE);
        view2.setBackgroundColor(Color.WHITE);
        meal.setText(nutritionPlanModel.getDinner());
        snack.setText(nutritionPlanModel.getSnack3());
    }
    NutritionPlanModel nutritionPlanModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_plans);
        ButterKnife.bind(this);
        Intent i = getIntent();
        if (i==null) Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        else {
            Gson gson = new Gson();
            nutritionPlanModel = gson.fromJson(getIntent().getStringExtra("item"), NutritionPlanModel.class);
            mainTitle.setText(nutritionPlanModel.getTitle());
            title.setText(nutritionPlanModel.getTitle());
            meal.setText(nutritionPlanModel.getBreakfast());
            Glide.with(this).load(nutritionPlanModel.getImage()).into(image);
            snack.setText(nutritionPlanModel.getSnack1());
            total.setText(nutritionPlanModel.getDailyTotal());
        }
    }
}
