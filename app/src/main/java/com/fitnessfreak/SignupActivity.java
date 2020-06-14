package com.fitnessfreak;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

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
    private FirebaseAuth mAuth;

    @OnClick(R.id.signup)
    void registerUser() {
        if (isSignupEnabled)
            if (TextUtils.isEmpty(email.getText())) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
                email.setError("Invalid Email");
                return;
            } else if (TextUtils.isEmpty(password.getText())) {
                Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                password.setError("Password is blank");
                return;
            } else if (password.getText().toString().trim().length() < 5) {
                Toast.makeText(this, "Password must contain 5 digits", Toast.LENGTH_SHORT).show();
                password.setError("Must Contain 5 digits");
                return;
            } else if (!Objects.equals(password.getText().toString().trim(), c_password.getText().toString().trim())) {
                Toast.makeText(this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                password.setError("Not Matching");
                c_password.setError("Not Matching");
                return;
            } else {
                isSignupEnabled = false;
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        progressBar.setVisibility(View.GONE);
                                        onBackPressed();
                                        //   updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
//                    String response = post("http://192.168.43.168:8080/admin/registeruser", postdata);
//                    Log.d("RegisterResponse", response + "");
//                    new Handler().postDelayed(() -> {
//                        progressBar.setVisibility(View.GONE);
//                        onBackPressed();
//                    }, 1000);
                } catch (Exception e) {
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

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
