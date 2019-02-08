package com.example.mohit.hack4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DonateFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<DonateItem> donateItems;

    private DatabaseAdapter databaseAdapter;
    DonateRecyclerViewAdapter donateRecyclerViewAdapter;

    public DonateFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_donate, container, false);
        donateItems=new ArrayList<DonateItem>();
        databaseAdapter=new DatabaseAdapter(getContext());
        donateRecyclerViewAdapter=new DonateRecyclerViewAdapter(getContext(),donateItems);
        DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("donate");

        databaseUser.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //onDataChange will be executed everytime we will make any changes to the database
                //used to read the values of FireBase Database.
                //returns all the values inside the specified references.
                //it will contain all the data inside the "dataSnapshot" object.
                donateItems.clear(); //req. to remove multiple insertions of the same users.
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    DonateItem donateItem = userSnapshot.getValue(DonateItem.class);
                    donateItems.add(donateItem);
                    Log.i("app", "onDataChange: "+donateItem.getItemName());
                    donateRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView=v.findViewById(R.id.donaterecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(donateRecyclerViewAdapter);
        return v;
    }
}