package com.fitnessfreak.nutritionplans;

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
import com.fitnessfreak.featureprograms.FeaturedProgram;
import com.fitnessfreak.featureprograms.FeaturedProgramModel;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutritionPlanAdapter extends RecyclerView.Adapter<NutritionPlanAdapter.ViewHolder> {
    List<NutritionPlanModel> featuredProgramList;
    Context context;
    public NutritionPlanAdapter(List<NutritionPlanModel> featuredProgramList, Context context) {
        this.featuredProgramList = featuredProgramList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_nutrition_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NutritionPlanModel  featuredProgram = featuredProgramList.get(position);
        Glide.with(context).load(featuredProgram.getImage()).into(holder.imageView);
        Log.d("Response", featuredProgram.getImage());
        holder.title.setText(featuredProgram.getTitle());
        holder.linearLayout.setOnClickListener(v->{
            Intent intent = new Intent(context,NutritionPlans.class);
            Gson gson = new Gson();
            String s = gson.toJson(featuredProgram);
            intent.putExtra("item",s);
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
        @BindView(R.id.item)
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
