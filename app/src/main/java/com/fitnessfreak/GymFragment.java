package com.fitnessfreak;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessfreak.gymprograms.GymProgramsAdapter;
import com.fitnessfreak.gymprograms.GymProgramsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class GymFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<GymProgramsModel> gymList;
    //@BindView(R.id.gymprogramsrecycler)
    RecyclerView gymrecycler;
    ProgressBar progressBar;

    public GymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gym, container, false);
        gymList = new ArrayList();
        gymrecycler = view.findViewById(R.id.gymprogramsrecycler);
        ButterKnife.bind(getActivity());
        progressBar = view.findViewById(R.id.n_progress);
        progressBar.setVisibility(View.VISIBLE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Gym");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("datasnapshot", dataSnapshot.getKey() + "sasa");
                progressBar.setVisibility(View.GONE);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    gymList.add(new GymProgramsModel(postSnapshot.getKey(), postSnapshot.child("imageUrl").getValue().toString()));
                    Log.d("datasnapshot", postSnapshot.child("Shrugs").toString());
                }
                Collections.reverse(gymList);
                GymProgramsAdapter gymProgramsAdapter = new GymProgramsAdapter(gymList, getContext(), dataSnapshot.getKey());
                gymrecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                gymrecycler.setNestedScrollingEnabled(true);
                gymrecycler.setAdapter(gymProgramsAdapter);
                gymrecycler.setHasFixedSize(true);
                gymProgramsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
