package com.fitnessfreak;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessfreak.gymprograms.GymProgramsActivity;
import com.fitnessfreak.pedometer.PedometerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private String username;
    @BindView(R.id.home_icon)
    ImageView home_icon;
    @BindView(R.id.home_text)
    TextView home_text;
    @BindView(R.id.programs_icon)
    ImageView programs_icon;
    @BindView(R.id.programs_text)
    TextView programs_text;
    @BindView(R.id.yoga_icon)
    ImageView yoga_icon;
    @BindView(R.id.yoga_text)
    TextView yoga_text;
    @BindView(R.id.recipes_icon)
    ImageView recipes_icon;
    @BindView(R.id.recipes_text)
    TextView recipes_text;
    @BindView(R.id.gym_icon)
    ImageView gym_icon;
    @BindView(R.id.gym_text)
    TextView gym_text;
    @BindView(R.id.name)
    TextView name;

    @OnClick(R.id.profile_image)
    void openPedometer() {
        startActivity(new Intent(this, PedometerActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.home)
    void openHome() {
        home_icon.setBackground(getDrawable(R.drawable.home));
        home_text.setTextColor(getColor(R.color.colorPrimary));
        programs_icon.setBackground(getDrawable(R.drawable.programs_unselected));
        programs_text.setTextColor(getColor(R.color.unselected_color));
        yoga_icon.setBackground(getDrawable(R.drawable.yoga_unselected));
        yoga_text.setTextColor(getColor(R.color.unselected_color));
        gym_icon.setBackground(getDrawable(R.drawable.gym_unselected));
        gym_text.setTextColor(getColor(R.color.unselected_color));
        recipes_icon.setBackground(getDrawable(R.drawable.recipe_unselected));
        recipes_text.setTextColor(getColor(R.color.unselected_color));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.programs)
    void openPrograms() {
        home_icon.setBackground(getDrawable(R.drawable.home_unselected));
        home_text.setTextColor(getColor(R.color.unselected_color));
        programs_icon.setBackground(getDrawable(R.drawable.programs));
        programs_text.setTextColor(getColor(R.color.colorPrimary));
        yoga_icon.setBackground(getDrawable(R.drawable.yoga_unselected));
        yoga_text.setTextColor(getColor(R.color.unselected_color));
        gym_icon.setBackground(getDrawable(R.drawable.gym_unselected));
        gym_text.setTextColor(getColor(R.color.unselected_color));
        recipes_icon.setBackground(getDrawable(R.drawable.recipe_unselected));
        recipes_text.setTextColor(getColor(R.color.unselected_color));
    }

    ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.yoga)
    void openYoga() {
        home_icon.setBackground(getDrawable(R.drawable.home_unselected));
        home_text.setTextColor(getColor(R.color.unselected_color));
        programs_icon.setBackground(getDrawable(R.drawable.programs_unselected));
        programs_text.setTextColor(getColor(R.color.unselected_color));
        yoga_icon.setBackground(getDrawable(R.drawable.yoga));
        yoga_text.setTextColor(getColor(R.color.colorPrimary));
        gym_icon.setBackground(getDrawable(R.drawable.gym_unselected));
        gym_text.setTextColor(getColor(R.color.unselected_color));
        recipes_icon.setBackground(getDrawable(R.drawable.recipe_unselected));
        recipes_text.setTextColor(getColor(R.color.unselected_color));
    }

    ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.gym)
    void openGym() {
        startActivity(new Intent(this, GymProgramsActivity.class));
        home_icon.setBackground(getDrawable(R.drawable.home_unselected));
        home_text.setTextColor(getColor(R.color.unselected_color));
        programs_icon.setBackground(getDrawable(R.drawable.programs_unselected));
        programs_text.setTextColor(getColor(R.color.unselected_color));
        yoga_icon.setBackground(getDrawable(R.drawable.yoga_unselected));
        yoga_text.setTextColor(getColor(R.color.unselected_color));
        gym_icon.setBackground(getDrawable(R.drawable.gym));
        gym_text.setTextColor(getColor(R.color.colorPrimary));
        recipes_icon.setBackground(getDrawable(R.drawable.recipe_unselected));
        recipes_text.setTextColor(getColor(R.color.unselected_color));
    }

    ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.recipes)
    void openRecipes() {
        home_icon.setBackground(getDrawable(R.drawable.home_unselected));
        home_text.setTextColor(getColor(R.color.unselected_color));
        programs_icon.setBackground(getDrawable(R.drawable.programs_unselected));
        programs_text.setTextColor(getColor(R.color.unselected_color));
        yoga_icon.setBackground(getDrawable(R.drawable.yoga_unselected));
        yoga_text.setTextColor(getColor(R.color.unselected_color));
        gym_icon.setBackground(getDrawable(R.drawable.gym_unselected));
        gym_text.setTextColor(getColor(R.color.unselected_color));
        recipes_icon.setBackground(getDrawable(R.drawable.recipe));
        recipes_text.setTextColor(getColor(R.color.colorPrimary));
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        username = getSharedPreferences(getString(R.string.login), Context.MODE_PRIVATE).getString("name", null);
        name.setText(username);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FragmentHome());
        fragmentTransaction.commit();
    }
}
