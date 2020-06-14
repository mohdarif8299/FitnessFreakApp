package com.fitnessfreak;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitnessfreak.featureprograms.FeaturedProgramAdapter;
import com.fitnessfreak.featureprograms.FeaturedProgramModel;
import com.fitnessfreak.nutritionplans.NutritionPlanAdapter;
import com.fitnessfreak.nutritionplans.NutritionPlanModel;
import com.fitnessfreak.yogasession.YogaSessionAdapter;
import com.fitnessfreak.yogasession.YogaSessionModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentHome extends Fragment {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client;
    @BindView(R.id.carouselView)
    CarouselView carouselView;
    List<String> sampleImages;
    List<FeaturedProgramModel> featuredProgramModelList;
    List<NutritionPlanModel> nutritionPlanModelList;
    List<YogaSessionModel> yogaSessionModelList;
    @BindView(R.id.featuredprogramrecycler)
    RecyclerView featuredProgramsRecycler;
    @BindView(R.id.nutritionplanrecycler)
    RecyclerView nutritionPlanRecycler;
    @BindView(R.id.yogasessionrecycler)
    RecyclerView yogasessionrecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sampleImages = new ArrayList<>();
        featuredProgramModelList = new ArrayList<>();
        nutritionPlanModelList = new ArrayList<>();
        yogaSessionModelList = new ArrayList<>();
        client = new OkHttpClient();
        ButterKnife.bind(this, view);
        try {
            String response = loadCarousel();
            Log.d("Carousel", "" + response);
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                sampleImages.add(jsonObject.getString("image_url"));
            }
            carouselView.setPageCount(jsonArray.length());
            carouselView.setImageListener(imageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String response = loadFeaturedPrograms();
            Log.d("Featured", response);
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                featuredProgramModelList.add(new FeaturedProgramModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image"),
                        jsonObject.getString("video"),
                        jsonObject.getString("description"),
                        jsonObject.getString("duration"),
                        jsonObject.getString("difficulty"),
                        jsonObject.getString("benefits")
                ));
            }
            FeaturedProgramAdapter featuredProgramAdapter = new FeaturedProgramAdapter(featuredProgramModelList, getActivity());
            featuredProgramsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            featuredProgramsRecycler.setNestedScrollingEnabled(true);
            featuredProgramsRecycler.setAdapter(featuredProgramAdapter);
            featuredProgramsRecycler.setHasFixedSize(true);
            featuredProgramsRecycler.scrollToPosition(0);
            featuredProgramAdapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        try {
            String response = loadNutritionPlans();
            Log.d("Featured", response);
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nutritionPlanModelList.add(new NutritionPlanModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image"),
                        jsonObject.getString("breakfast"),
                        jsonObject.getString("snack1"),
                        jsonObject.getString("lunch"),
                        jsonObject.getString("snack2"),
                        jsonObject.getString("dinner"),
                        jsonObject.getString("snack3"),
                        jsonObject.getString("dailyTotal")
                ));
            }
            NutritionPlanAdapter nutritionPlanAdapter = new NutritionPlanAdapter(nutritionPlanModelList, getActivity());
            nutritionPlanRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            nutritionPlanRecycler.setNestedScrollingEnabled(true);
            nutritionPlanRecycler.setAdapter(nutritionPlanAdapter);
            nutritionPlanRecycler.setHasFixedSize(true);
            nutritionPlanRecycler.scrollToPosition(0);
            nutritionPlanAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String response = loadYogaSession();
            Log.d("Featured", response);
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                yogaSessionModelList.add(new YogaSessionModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image")
                ));
            }
            YogaSessionAdapter yogaSessionAdapter = new YogaSessionAdapter(yogaSessionModelList, getActivity());
            yogasessionrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            yogasessionrecycler.setNestedScrollingEnabled(true);
            yogasessionrecycler.setAdapter(yogaSessionAdapter);
            yogasessionrecycler.setHasFixedSize(true);
            yogasessionrecycler.scrollToPosition(0);
            yogaSessionAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    ImageListener imageListener = (position, imageView) -> {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(sampleImages.get(position)).into(imageView);
    };

    private String loadCarousel() throws IOException {
        Request request = new Request.Builder()
                .url("http://192.168.43.168:8080/all-carousel-images")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String loadFeaturedPrograms() throws IOException {
        Request request = new Request.Builder()
                .url("http://192.168.43.168:8080/admin/all-featured-programs")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String loadNutritionPlans() throws IOException {
        Request request = new Request.Builder()
                .url("http://192.168.43.168:8080/admin/all-nutrition-plans")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String loadYogaSession() throws IOException {
        Request request = new Request.Builder()
                .url("http://192.168.43.168:8080/admin/all-yoga-session")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
