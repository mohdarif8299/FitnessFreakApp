package com.fitnessfreak.yogasession;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitnessfreak.R;
import com.fitnessfreak.nutritionplans.NutritionPlans;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class YogaSessionAdapter extends RecyclerView.Adapter<YogaSessionAdapter.ViewHolder> {
    List<YogaSessionModel> yogaSessionList;
    Context context;
    public YogaSessionAdapter(List<YogaSessionModel> yogaSessionList, Context context) {
        this.yogaSessionList = yogaSessionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_yoga_session, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YogaSessionModel featuredProgram = yogaSessionList.get(position);
        Glide.with(context).load(featuredProgram.getImage()).into(holder.imageView);
        Log.d("Response", featuredProgram.getImage());
        holder.title.setText(featuredProgram.getTitle());
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, YogaSession.class);
            intent.putExtra("title",featuredProgram.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return yogaSessionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.item)
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
