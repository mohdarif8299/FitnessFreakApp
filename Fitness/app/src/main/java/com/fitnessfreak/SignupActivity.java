package com.fitnessfreak;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.c_password)
    EditText c_password;
    OkHttpClient okHttpClient;
    MediaType MEDIA_TYPE = MediaType.parse("application/json");
    boolean isSignupEnabled = true;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnClick(R.id.signup)
    void registerUser() {
        if (isSignupEnabled)
            if (TextUtils.isEmpty(email.getText())) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(username.getText())) {
                Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(password.getText())) {
                Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.getText().length() < 5) {
                Toast.makeText(this, "password must contain 5 digits", Toast.LENGTH_SHORT).show();
                return;
            } else {
                isSignupEnabled = false;
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    JSONObject postdata = new JSONObject();
                    postdata.put("email", email.getText().toString());
                    postdata.put("username", username.getText().toString());
                    postdata.put("password", password.getText().toString());
                    Log.d("Json", "" + postdata.toString());
                    String response = post("http://192.168.43.168:8080/admin/registeruser", postdata);
                    Log.d("RegisterResponse", response + "");
                    new Handler().postDelayed(()->{
                        progressBar.setVisibility(View.GONE);
                      onBackPressed();
                    },1000);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    isSignupEnabled = true;
                }
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        okHttpClient = new OkHttpClient();
    }

    String post(String url, JSONObject jsonObject) throws IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
