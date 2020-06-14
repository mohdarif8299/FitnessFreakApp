package com.fitnessfreak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessfreak.user.UserModel;

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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    boolean isLoginEnabled = false;
    OkHttpClient client;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnClick(R.id.skip)
    void openHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @OnClick(R.id.login)
    void checkCredentials() {
        if (isLoginEnabled) {
            try {
                progressBar.setVisibility(View.VISIBLE);
                JSONObject postdata = new JSONObject();
                postdata.put("email", username.getText().toString());
                postdata.put("password", password.getText().toString());
                Log.d("Json", "" + postdata.toString());
                String response = post("http://192.168.43.168:8080/admin/loginuser", postdata);
                Log.d("RegisterResponse", response + "");
                if (response.contains("Invalid Username")) {
                    Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else if (response.contains("Invalid Password")) {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    JSONObject jsonObject = new JSONObject(response);
                    UserModel userDetails = new UserModel(jsonObject.getString("email"), jsonObject.getString("name"), jsonObject.getString("password"));
                    SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.login), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", userDetails.getUsername());
                    editor.putString("email", userDetails.getEmail());
                    editor.commit();
                    new Handler().postDelayed(() -> {
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    }, 1000);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        } else {
        }
    }

    @OnClick(R.id.signup)
    void openSignup() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSharedPreferences(getString(R.string.login), Context.MODE_PRIVATE) != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        client = new OkHttpClient();
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 10 || password.getText().length() <= 5) {
                    isLoginEnabled = false;
                    login.setBackgroundColor(getColor(R.color.unselected_color));
                } else if (s.length() != 0 && password.getText().length() > 5) {
                    isLoginEnabled = true;
                    login.setBackgroundColor(getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 5 || username.getText().length() <= 10) {
                    isLoginEnabled = false;
                    login.setBackgroundColor(getColor(R.color.unselected_color));
                } else if (s.length() > 5 && username.getText().length() != 0) {
                    isLoginEnabled = true;
                    login.setBackgroundColor(getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    String post(String url, JSONObject jsonObject) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
