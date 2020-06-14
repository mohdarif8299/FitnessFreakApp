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
import java.util.List;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class YogaFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<GymProgramsModel> yogaList;
    //@BindView(R.id.gymprogramsrecycler)
    RecyclerView gymrecycler;
    ProgressBar progressBar;

    public YogaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_yoga, container, false);
        yogaList=new ArrayList();
        gymrecycler=view.findViewById(R.id.gymprogramsrecycler);
        ButterKnife.bind(getActivity());
        progressBar=view.findViewById(R.id.n_progress);
        progressBar.setVisibility(View.VISIBLE);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Yoga");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                Log.d("datasnapshot",dataSnapshot.getChildrenCount()+"sasa");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post

                    yogaList.add(new GymProgramsModel(postSnapshot.getKey(),postSnapshot.child("imageUrl").getValue().toString()));

                    Log.d("datasnapshot",postSnapshot.child("Shrugs").toString());
                    //  Toast.makeText(getContext(),""+postSnapshot.getValue(),Toast.LENGTH_LONG).show();
                }

                GymProgramsAdapter gymProgramsAdapter = new GymProgramsAdapter(yogaList, getContext(),dataSnapshot.getKey());
                gymrecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                gymrecycler.setNestedScrollingEnabled(true);
                gymrecycler.setAdapter(gymProgramsAdapter);
                gymrecycler.setHasFixedSize(true);
                //gymrecycler.scrollToPosition(0);
                gymProgramsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}
