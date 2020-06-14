package com.fitnessfreak.recieps;

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

public class ReciepeFragment extends Fragment {

    List<ReciepeModel> reciepeModelList;
    @BindView(R.id.reciepe_recycler)
    RecyclerView reciepeRecycler;
    @BindView(R.id.display_title)
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reciepe, container, false);
        ButterKnife.bind(this, view);
        reciepeModelList = new ArrayList<>();
        linearLayout.setVisibility(View.GONE);
        loadRecieps();
        return view;
    }

    private void loadRecieps() {
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
                    reciepeRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
    }
}