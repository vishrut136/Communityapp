package com.example.mohit.hack4;

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



public class SellFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<SellItem> sellItems;

    private DatabaseAdapter databaseAdapter;
    SellRecyclerViewAdapter sellRecyclerViewAdapter;

    public SellFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_sell, container, false);
        sellItems=new ArrayList<SellItem>();
        databaseAdapter=new DatabaseAdapter(getContext());
        final SellRecyclerViewAdapter sellRecyclerViewAdapter=new SellRecyclerViewAdapter(getContext(),sellItems);
        DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("sell");

        databaseUser.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //onDataChange will be executed everytime we will make any changes to the database
                //used to read the values of FireBase Database.
                //returns all the values inside the specified references.
                //it will contain all the data inside the "dataSnapshot" object.
                sellItems.clear(); //req. to remove multiple insertions of the same users.
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    SellItem sellItem = userSnapshot.getValue(SellItem.class);
                    sellItems.add(sellItem);
                    Log.i("app", "onDataChange: "+sellItem.getItemName());
                    sellRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView=v.findViewById(R.id.sellrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sellRecyclerViewAdapter);
        return v;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
