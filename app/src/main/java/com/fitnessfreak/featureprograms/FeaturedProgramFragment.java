package com.fitnessfreak.featureprograms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessfreak.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeaturedProgramFragment extends Fragment {
    private List<FeaturedProgramModel> featuredProgramModelList;
    @BindView(R.id.featuredprogramrecycler)
    RecyclerView featuredProgramsRecycler;
    @BindView(R.id.display_title)
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured_program, container, false);
        ButterKnife.bind(this, view);
        linearLayout.setVisibility(View.GONE);
        featuredProgramModelList = new ArrayList<>();
        loadFeaturedPrograms();
        return view;
    }

    private void loadFeaturedPrograms() {
        featuredProgramModelList.clear();
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
                    featuredProgramsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    featuredProgramsRecycler.setNestedScrollingEnabled(true);
                    featuredProgramsRecycler.smoothScrollToPosition(0);
                    featuredProgramsRecycler.setAdapter(featuredProgramAdapter);
                    featuredProgramsRecycler.setHasFixedSize(true);
                    featuredProgramAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}