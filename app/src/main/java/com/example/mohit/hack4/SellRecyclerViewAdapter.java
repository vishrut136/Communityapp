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

public class SellRecyclerViewAdapter extends RecyclerView.Adapter<SellRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<SellItem> sellItems;

    public SellRecyclerViewAdapter(Context mContext, List<SellItem> sellItems) {
        this.mContext = mContext;
        this.sellItems = sellItems;
    }
    @NonNull
    @Override
    public SellRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.item_selllist,viewGroup,false);
        final SellRecyclerViewAdapter.MyViewHolder viewHolder=new SellRecyclerViewAdapter.MyViewHolder(v);

        viewHolder.Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("app", "onClick: +phone");
                int j=viewHolder.getAdapterPosition();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sellItems.get(j).getPhone()));
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
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(sellItems.get(j).getLocation()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");//calling Map
                v.getContext().startActivity(mapIntent);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SellRecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.SellItem.setText(sellItems.get(i).getItemName());
        myViewHolder.Description.setText(String.valueOf(sellItems.get(i).getDescription()));
        myViewHolder.Price.setText(String.valueOf(sellItems.get(i).getPrice()));
        myViewHolder.Name.setText(sellItems.get(i).getName());
        myViewHolder.Phone.setText(String.valueOf(sellItems.get(i).getPhone()));
        myViewHolder.Email.setText(sellItems.get(i).getEmail());
        myViewHolder.Map.setText(String.valueOf(sellItems.get(i).getLocation()));


    }

    @Override
    public int getItemCount() {
        return sellItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView SellItem,Description,Price,Name,Phone,Email,Map;



        private LinearLayout item_sell;
        private CardView sellcardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            SellItem=itemView.findViewById(R.id.sellItem);
            Description=itemView.findViewById(R.id.sellDescription);
            Name=itemView.findViewById(R.id.sellName);
            Phone=itemView.findViewById(R.id.sellPhone);
            Email=itemView.findViewById(R.id.sellEmail);
            Price=itemView.findViewById(R.id.sellPrice);
            Map=itemView.findViewById(R.id.sellMap);
            item_sell=itemView.findViewById(R.id.sellitem);
            sellcardView=itemView.findViewById(R.id.sellcardView);

        }
    }
}
