package com.fitnessfreak.gymprograms;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

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

public class GymProgramsActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client;
    @BindView(R.id.gymprogramsrecycler)
    RecyclerView gymrecycler;
    List<GymProgramsModel> gymProgramsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_programs);
        ButterKnife.bind(this);
        client = new OkHttpClient();
        gymProgramsModelList = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String response = post("http://192.168.43.168:8080/admin/all-gym-programs");
            JSONArray jsonArray = new JSONArray(response);
            Log.d("res", response);
            for (int k = 0; k < jsonArray.length(); k++) {
                JSONObject jsonObject = jsonArray.getJSONObject(k);
                gymProgramsModelList.add(new GymProgramsModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image"),
                        jsonObject.getString("url")
                ));
            }
            GymProgramsAdapter gymProgramsAdapter = new GymProgramsAdapter(gymProgramsModelList, this,"hello");
            gymrecycler.setLayoutManager(new LinearLayoutManager(this));
            gymrecycler.setNestedScrollingEnabled(true);
            gymrecycler.setAdapter(gymProgramsAdapter);
            gymrecycler.setHasFixedSize(true);
            gymrecycler.scrollToPosition(0);
            gymProgramsAdapter.notifyDataSetChanged();
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
