package com.example.mohit.hack4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DonateRecyclerViewAdapter extends RecyclerView.Adapter<DonateRecyclerViewAdapter.MyViewHolder>{
    Context mContext;
    List<DonateItem> donateItems;

    public DonateRecyclerViewAdapter(Context mContext, List<DonateItem> donateItems) {
        this.mContext = mContext;
        this.donateItems = donateItems;
    }

    @NonNull
    @Override
    public DonateRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.item_donatelist,viewGroup,false);
        final MyViewHolder viewHolder=new MyViewHolder(v);
        viewHolder.Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("app", "onClick: +phone");
                int j=viewHolder.getAdapterPosition();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + donateItems.get(j).getPhone()));
                v.getContext().startActivity(intent);


            }
        });

        viewHolder.Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=viewHolder.getAdapterPosition();
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(donateItems.get(j).getLocation()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");//calling Map
                v.getContext().startActivity(mapIntent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonateRecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.DonateItem.setText(donateItems.get(i).getItemName());
        myViewHolder.Description.setText(String.valueOf(donateItems.get(i).getDescription()));
        myViewHolder.Name.setText(donateItems.get(i).getName());
        myViewHolder.Phone.setText(String.valueOf(donateItems.get(i).getPhone()));
        myViewHolder.Email.setText(donateItems.get(i).getEmail());
        myViewHolder.Map.setText(String.valueOf(donateItems.get(i).getLocation()));


    }

    @Override
    public int getItemCount() {
        return donateItems.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView DonateItem,Description,Name,Phone,Email,Map;



        private LinearLayout item_donate;
        private CardView donatecardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DonateItem=itemView.findViewById(R.id.donateItem);
            Description=itemView.findViewById(R.id.donateDescription);
            Name=itemView.findViewById(R.id.donateName);
            Phone=itemView.findViewById(R.id.donatePhone);
            Email=itemView.findViewById(R.id.donateEmail);
            Map=itemView.findViewById(R.id.donateMap);
            item_donate=itemView.findViewById(R.id.donateitem);
            donatecardView=itemView.findViewById(R.id.donatecardView);

        }
    }
}
