package com.fitnessfreak.gymprograms;

import android.content.Context;
import android.content.Intent;
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
import com.fitnessfreak.exercise.ExerciseActivity;
import com.fitnessfreak.yogasession.YogaSession;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GymProgramsAdapter extends RecyclerView.Adapter<GymProgramsAdapter.ViewHolder> {
    List<GymProgramsModel> yogaSessionList;
    Context context;

    public GymProgramsAdapter(List<GymProgramsModel> yogaSessionList, Context context) {
        this.yogaSessionList = yogaSessionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_gym_programs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GymProgramsModel featuredProgram = yogaSessionList.get(position);
        Glide.with(context).load(featuredProgram.getImage()).into(holder.imageView);
        Log.d("Response", featuredProgram.getImage());
        holder.title.setText(featuredProgram.getTitle());
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ExerciseActivity.class);
            intent.putExtra("url", featuredProgram.getUrl());
            intent.putExtra("title", featuredProgram.getTitle());
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
