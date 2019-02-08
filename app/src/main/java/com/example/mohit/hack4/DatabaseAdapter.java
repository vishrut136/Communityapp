package com.example.mohit.hack4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter  {

    Context mContext;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseUser;
    List<SellItem> sellItems;
    List<DonateItem> donateItems;
    List<ShareItem> shareItems;

    public DatabaseAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public List<SellItem> getAllSellableItems(){


        return sellItems;
    }


    public List<DonateItem> getAllDonatableItems(){
//        String id=databaseUser.getKey();

        return donateItems;
    }


    public List<ShareItem> getAllSharableItems(){

        return shareItems;
    }


}
