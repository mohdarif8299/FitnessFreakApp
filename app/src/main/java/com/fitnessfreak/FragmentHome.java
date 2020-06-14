package com.fitnessfreak;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitnessfreak.featureprograms.FeaturedProgramAdapter;
import com.fitnessfreak.featureprograms.FeaturedProgramModel;
import com.fitnessfreak.gymprograms.GymProgramsAdapter;
import com.fitnessfreak.gymprograms.GymProgramsModel;
import com.fitnessfreak.nutritionplans.NutritionPlanModel;
import com.fitnessfreak.recieps.ReciepeAdapter;
import com.fitnessfreak.recieps.ReciepeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

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

public class FragmentHome extends Fragment {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client;
    @BindView(R.id.carouselView)
    CarouselView carouselView;
    List<String> sampleImages;
    List<FeaturedProgramModel> featuredProgramModelList;
    List<NutritionPlanModel> nutritionPlanModelList;
    List<GymProgramsModel> yogaSessionModelList;
    List<ReciepeModel> reciepeModelList;
    @BindView(R.id.featuredprogramrecycler)
    RecyclerView featuredProgramsRecycler;
    @BindView(R.id.yogasessionrecycler)
    RecyclerView yogasessionrecycler;
    @BindView(R.id.reciepe_recycler)
    RecyclerView reciepeRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sampleImages = new ArrayList<>();
        featuredProgramModelList = new ArrayList<>();
        nutritionPlanModelList = new ArrayList<>();
        yogaSessionModelList = new ArrayList<>();
        reciepeModelList = new ArrayList<>();
        client = new OkHttpClient();
        ButterKnife.bind(this, view);
        try {
            String response = loadCarousel();
            Log.d("Carousel", "" + response);
            JSONObject jsonObject = new JSONObject(response);
            JSONObject embed = jsonObject.getJSONObject("_embedded");
            Log.d("Aarousel", "" + embed);
            JSONArray jsonArray = embed.getJSONArray("images");
            Log.d("Darousel", "" + jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                sampleImages.add(jsonObject1.getString("imageUrl"));
            }
            carouselView.setPageCount(jsonArray.length());
            carouselView.setImageListener(imageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Featured Programs
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("FaturedPrograms");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        FeaturedProgramModel featuredProgramModel = dataSnapshot1.getValue(FeaturedProgramModel.class);
                        featuredProgramModelList.add(featuredProgramModel);
                    }
                    FeaturedProgramAdapter featuredProgramAdapter = new FeaturedProgramAdapter(featuredProgramModelList, getActivity());
                    featuredProgramsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    featuredProgramsRecycler.setNestedScrollingEnabled(true);
                    featuredProgramsRecycler.setAdapter(featuredProgramAdapter);
                    featuredProgramsRecycler.setHasFixedSize(true);
                    featuredProgramsRecycler.scrollToPosition(0);
                    featuredProgramAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ends here

        // Reciepes Programs
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Recieps");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ReciepeModel featuredProgramModel = dataSnapshot1.getValue(ReciepeModel.class);
                        reciepeModelList.add(featuredProgramModel);
                    }
                    ReciepeAdapter featuredProgramAdapter = new ReciepeAdapter(reciepeModelList, getActivity());
                    reciepeRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    reciepeRecycler.setNestedScrollingEnabled(true);
                    reciepeRecycler.setAdapter(featuredProgramAdapter);
                    reciepeRecycler.setHasFixedSize(true);
                    reciepeRecycler.scrollToPosition(0);
                    featuredProgramAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ends here

        // yoga starts here
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Yoga");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    yogaSessionModelList.add(new GymProgramsModel(postSnapshot.getKey(), postSnapshot.child("imageUrl").getValue().toString()));
                }
                GymProgramsAdapter gymProgramsAdapter = new GymProgramsAdapter(yogaSessionModelList, getContext(), dataSnapshot.getKey());
                yogasessionrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                yogasessionrecycler.setNestedScrollingEnabled(true);
                yogasessionrecycler.setAdapter(gymProgramsAdapter);
                yogasessionrecycler.setHasFixedSize(true);
                //gymrecycler.scrollToPosition(0);
                gymProgramsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // ends here


        return view;
    }

    ImageListener imageListener = (position, imageView) -> {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(sampleImages.get(position)).into(imageView);
    };

    private String loadCarousel() throws IOException {
        Request request = new Request.Builder()
                .url("http://helloworld-env.eba-kbdpvps9.ap-south-1.elasticbeanstalk.com/api/images")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
