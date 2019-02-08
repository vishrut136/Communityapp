package com.example.mohit.hack4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonateitActivity extends AppCompatActivity {

    public static final String item_name="itemname";
    public static final String category ="category";
    public static final String description="description";
    public static final String name="name";
    public static final String phone="phone";
    public static final String emailid="emailid";
    public static final String location="location";



    EditText Itemname,Category,Description,Name,Phone,Emailid,Location;
    Button submit;

    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donateit);

        databaseUser = FirebaseDatabase.getInstance().getReference("donate");// passing the path in order to get to the root node of the JSON tree

        Itemname = (EditText)findViewById(R.id.etDonateItemName);
        Category=(EditText)findViewById(R.id.etDonateCategory);
        Description=(EditText)findViewById(R.id.etDonateDescription);
        Name=(EditText)findViewById(R.id.etDonateName);
        Phone=(EditText)findViewById(R.id.etDonatePhone);
        Emailid=(EditText)findViewById(R.id.etDonateEmail);
        Location=findViewById(R.id.etDonateLocation);
        submit=findViewById(R.id.btDonateSubmit);


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addDonateItem();
            }
        });
    }

    private void addDonateItem()
    {
        String itemname=Itemname.getText().toString().trim();
        String category=Category.getText().toString().trim();
        String description=Description.getText().toString().trim();
        String name=Name.getText().toString().trim();
        String phone=Phone.getText().toString().trim();
        String emailid=Emailid.getText().toString().trim();
        String location=Location.getText().toString().trim();





        if(!TextUtils.isEmpty(name))
        {
            String id=databaseUser.push().getKey();//it will create unique string of the path"users"
            // creating an unique id for the nodes

            DonateItem donateItem = new DonateItem(itemname,category,description,name,phone,emailid,location);


            if(id!=null) {
                Log.i("app", "addDonateItem: added to database");
                databaseUser.child(id).setValue(donateItem);//using child(id) to uniquely store the values under that particular id
                //doing so just so that our data does not get overidden everytime
            }
            Toast.makeText(getApplicationContext(),"Donatable Item ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"You should enter corect details",Toast.LENGTH_SHORT).show();
        }
    }
}


