package com.fitnessfreak.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessfreak.R;

import org.json.JSONArray;
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

public class ExerciseActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
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
        client = new OkHttpClient();
        mainTitle.setText(title);
        exerciseModelList = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String response = post(url);
            Log.d("exercise_path", title);
            JSONArray jsonArray = new JSONArray(response);
            Log.d("res", response);
            for (int k = 0; k < jsonArray.length(); k++) {
                JSONObject jsonObject = jsonArray.getJSONObject(k);
                exerciseModelList.add(new ExerciseModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image"),
                        jsonObject.getString("video"),
                        jsonObject.getString("description")
                ));
            }
            ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exerciseModelList, this);
            exerciserecycler.setLayoutManager(new LinearLayoutManager(this));
            exerciserecycler.setNestedScrollingEnabled(true);
            exerciserecycler.setAdapter(exerciseAdapter);
            exerciserecycler.setHasFixedSize(true);
            exerciserecycler.scrollToPosition(0);
            exerciseAdapter.notifyDataSetChanged();
            Log.d("yoga-response", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String post(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
