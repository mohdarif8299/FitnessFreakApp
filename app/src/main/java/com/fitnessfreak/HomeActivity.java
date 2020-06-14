package com.fitnessfreak;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessfreak.featureprograms.FeaturedProgramFragment;
import com.fitnessfreak.recieps.ReciepeFragment;
import com.fitnessfreak.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skydoves.powermenu.PowerMenu;

import java.util.ArrayList;

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
    PowerMenu powerMenu;
    FirebaseAuth auth;
    @BindView(R.id.Appbar)
    TextView Appbar;
    int i = 0;

    @OnClick(R.id.profile_image)
    void openUserProfile() {
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.home)
    void openHome() {
        Appbar.setText("Home");
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FragmentHome());
        fragmentTransaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.programs)
    void openPrograms() {
        Appbar.setText("Programs");
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FeaturedProgramFragment());
        fragmentTransaction.commit();
    }

    ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.yoga)
    void openYoga() {
        Appbar.setText("Yoga");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new YogaFragment());
        fragmentTransaction.commit();
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new GymFragment());
        fragmentTransaction.commit();
        Appbar.setText("Gym");
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
        Appbar.setText("Recipes");
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new ReciepeFragment());
        fragmentTransaction.commit();
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        ArrayList<String> userData = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(user.getEmail().replace("@", "_").replace(".", ""));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String data = dataSnapshot1.getValue(String.class);
                   userData.add(data);
                }
                try {
                    name.setText(userData.get(0));
                } catch (Exception e) {
                    name.setText("Welcome");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        name.setText("");
        username = getSharedPreferences(getString(R.string.login), Context.MODE_PRIVATE).getString("name", null);
        name.setText(username);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FragmentHome());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (currentFragment instanceof FragmentHome)
            finishAffinity();
        else {
            openHome();
        }
    }
}
