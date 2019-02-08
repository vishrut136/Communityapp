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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    View v;
    private RecyclerView recyclerView;
    private List<ShareItem> shareItems;

    private DatabaseAdapter databaseAdapter;
    ShareRecyclerViewAdapter shareRecyclerViewAdapter;




    // private OnFragmentInteractionListener mListener;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_share, container, false);
        shareItems=new ArrayList<ShareItem>();
        databaseAdapter=new DatabaseAdapter(getContext());
        shareRecyclerViewAdapter=new ShareRecyclerViewAdapter(getContext(),shareItems);
        DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("share");

        databaseUser.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //onDataChange will be executed everytime we will make any changes to the database
                //used to read the values of FireBase Database.
                //returns all the values inside the specified references.
                //it will contain all the data inside the "dataSnapshot" object.
                shareItems.clear(); //req. to remove multiple insertions of the same users.
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    ShareItem shareItem = userSnapshot.getValue(ShareItem.class);
                    shareItems.add(shareItem);
                    Log.i("app", "onDataChange: "+shareItem.getItemName());
                    shareRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView=v.findViewById(R.id.sharerecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shareRecyclerViewAdapter);
        return v;
    }

//
}
