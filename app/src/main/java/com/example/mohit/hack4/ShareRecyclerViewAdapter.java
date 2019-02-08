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

public class ShareRecyclerViewAdapter extends RecyclerView.Adapter<ShareRecyclerViewAdapter.MyViewHolder>  {
    Context mContext;
    List<ShareItem> shareItems;

    public ShareRecyclerViewAdapter(Context mContext, List<ShareItem> shareItems) {
        this.mContext = mContext;
        this.shareItems = shareItems;
    }


    @NonNull
    @Override
    public ShareRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.item_sharelist,viewGroup,false);
        final ShareRecyclerViewAdapter.MyViewHolder viewHolder=new ShareRecyclerViewAdapter.MyViewHolder(v);
        viewHolder.Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("app", "onClick: +phone");
                int j=viewHolder.getAdapterPosition();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + shareItems.get(j).getPhone()));
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
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(shareItems.get(j).getLocation()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");//calling Map
                v.getContext().startActivity(mapIntent);



            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShareRecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.ShareItem.setText(shareItems.get(i).getItemName());
        myViewHolder.Description.setText(String.valueOf(shareItems.get(i).getDescription()));
        myViewHolder.Availability.setText(String.valueOf(shareItems.get(i).getSharingTime()));
        myViewHolder.Name.setText(shareItems.get(i).getName());
        myViewHolder.Phone.setText(String.valueOf(shareItems.get(i).getPhone()));
        myViewHolder.Email.setText(shareItems.get(i).getEmail());
        myViewHolder.Map.setText(String.valueOf(shareItems.get(i).getLocation()));

    }

    @Override
    public int getItemCount() {
        return shareItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ShareItem,Description,Availability,Name,Phone,Email,Map;
        private LinearLayout shareitem;
        private CardView sharecardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ShareItem=itemView.findViewById(R.id.shareItem);
            Description=itemView.findViewById(R.id.shareDescription);
            Name=itemView.findViewById(R.id.shareName);
            Phone=itemView.findViewById(R.id.sharePhone);
            Email=itemView.findViewById(R.id.shareEmail);
            Map=itemView.findViewById(R.id.shareMap);
            Availability=itemView.findViewById(R.id.shareAvailability);
            shareitem=itemView.findViewById(R.id.shareitem);
            sharecardView=itemView.findViewById(R.id.sharecardView);
        }
    }
}
