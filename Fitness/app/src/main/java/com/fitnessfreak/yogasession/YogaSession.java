package com.fitnessfreak.yogasession;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessfreak.R;
import com.fitnessfreak.yogasessions.YogaSessionsAdapter;
import com.fitnessfreak.yogasessions.YogaSessionsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YogaSession extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client;
    @BindView(R.id.yogasessionsrecycler)
    RecyclerView yogasessionsrecycler;
    List<YogaSessionsModel> yogaSessionsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_session);
        ButterKnife.bind(this);
        yogaSessionsModelList = new ArrayList<>();
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        client = new OkHttpClient();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String response = post("http://192.168.43.168:8080/admin/find-yoga-title", title);
            JSONArray jsonArray = new JSONArray(response);
            Log.d("res",response);
            for (int k = 0; k < jsonArray.length(); k++) {
                JSONObject jsonObject = jsonArray.getJSONObject(k);
                yogaSessionsModelList.add(new YogaSessionsModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("image"),
                        jsonObject.getString("video"),
                        jsonObject.getString("description"),
                        jsonObject.getString("duration"),
                        jsonObject.getString("difficulty"),
                        jsonObject.getString("benefits")
                ));
            }
            YogaSessionsAdapter yogaSessionAdapter = new YogaSessionsAdapter(yogaSessionsModelList, this);
            yogasessionsrecycler.setLayoutManager(new LinearLayoutManager(this));
            yogasessionsrecycler.setNestedScrollingEnabled(true);
            yogasessionsrecycler.setAdapter(yogaSessionAdapter);
            yogasessionsrecycler.setHasFixedSize(true);
            yogasessionsrecycler.scrollToPosition(0);
            yogaSessionAdapter.notifyDataSetChanged();
            Log.d("yoga-response", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String post(String url, String title) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("title", title);
        String ref = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(ref)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
