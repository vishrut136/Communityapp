package com.example.mohit.hack4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellitActivity extends AppCompatActivity {

    public static final String item_name="itemname";
    public static final String category ="category";
    public static final String description="description";
    public static final int price=100;
    public static final String name="name";
    public static final String phone="phone";
    public static final String emailid="emailid";
    public static final String location="location";



    EditText Itemname,Category,Description,Price,Name,Phone,Emailid,Location;
    Button submit;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellit);

        databaseUser = FirebaseDatabase.getInstance().getReference("sell");// passing the path in order to get to the root node of the JSON tree

        Itemname = (EditText)findViewById(R.id.etSellItemName);
        Category=(EditText)findViewById(R.id.etSellCategory);
        Description=(EditText)findViewById(R.id.etSellDescription);
        Price=(EditText)findViewById(R.id.etSellPrice);
        Name=(EditText)findViewById(R.id.etSellName);
        Phone=(EditText)findViewById(R.id.etSellPhone);
        Emailid=(EditText)findViewById(R.id.etSellEmail);
        Location=findViewById(R.id.etSellLocation);
        submit=findViewById(R.id.btSellSubmit);


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addSellItem();
            }
        });
    }

    private void addSellItem()
    {
        String itemname=Itemname.getText().toString().trim();
        String category=Category.getText().toString().trim();
        String description=Description.getText().toString().trim();
        int price=Integer.parseInt(Price.getText().toString());
        String name=Name.getText().toString().trim();
        String phone=Phone.getText().toString().trim();
        String emailid=Emailid.getText().toString().trim();
        String location=Location.getText().toString().trim();



        if (itemname.isEmpty()) {
            Itemname.setError("Item name is required");
            Itemname.requestFocus();
            return;
        }

        if (category.isEmpty()) {
            Category.setError("Category is required");
            Category.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            Description.setError("Email is required");
            Description.requestFocus();
            return;
        }
        if (price<0) {
            Price.setError("Enter a valid price");
            Price.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            Phone.setError("Phone is required");
            Phone.requestFocus();
            return;
        }
        if (emailid.isEmpty()) {
            Emailid.setError("Email is required");
            Emailid.requestFocus();
            return;
        }
        if(location.isEmpty()){
            Location.setError("Location is required");
            Location.requestFocus();
            return;
        }




        if(!TextUtils.isEmpty(name))
        {
            String id=databaseUser.push().getKey();//it will create unique string of the path"users"
            // creating an unique id for the nodes

            SellItem sellItem = new SellItem(itemname,category,description,price,name,phone,emailid,location);

            databaseUser.child(id).setValue(sellItem);//using child(id) to uniquely store the values under that particular id
            //doing so just so that our data does not get overidden everytime

            Toast.makeText(getApplicationContext(),"Sellable Item ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"You should enter corect details",Toast.LENGTH_SHORT).show();
        }
    }


}

