package com.fitnessfreak.exercise;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitnessfreak.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Typeface.BOLD;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    List<ExerciseModel> featuredProgramList;
    Context context;

    public ExerciseAdapter(List<ExerciseModel> featuredProgramList, Context context) {
        this.featuredProgramList = featuredProgramList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseModel featuredProgram = featuredProgramList.get(position);
        Glide.with(context).load(featuredProgram.getImage()).into(holder.imageView);
        Log.d("Response", featuredProgram.getImage());
        holder.title.setText(featuredProgram.getTitle());
        Typeface typeface = ResourcesCompat.getFont(context, R.font.exo);
        holder.title.setTypeface(typeface,BOLD);
        holder.t_mus.setText(featuredProgram.getTargetMuscle());
        holder.t_mus.setTypeface(typeface,BOLD);
        holder.diff.setText("Difficulty : " + featuredProgram.getDifficulty());
        holder.diff.setTypeface(typeface,BOLD);
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ExerciseDetails.class);
            intent.putExtra("exercise", (Parcelable) featuredProgram);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return featuredProgramList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.tmus)
        TextView t_mus;
        @BindView(R.id.difficulty)
        TextView diff;
        @BindView(R.id.item)
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
