package com.subratgupta.mmealsonwheels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurentActivity extends AppCompatActivity implements RestaurentAdapter.ItemClickListener{

    ArrayList<RestaurentType> restaurentTypeArrayList = new ArrayList<>();
    RestaurentAdapter restaurentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent);

        setRestaurentList();
    }

    private void setRestaurentList() {
        MainActivity.mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                restaurentTypeArrayList.clear();
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array

                for (DataSnapshot restaurentIDs : dataSnapshot.child("restaurents").child("agra").child("restaurent_list").getChildren()) {
                    RestaurentType id = restaurentIDs.getValue(RestaurentType.class);
                    restaurentTypeArrayList.add(id);
                }

                RecyclerView recyclerView = findViewById(R.id.restaurent__recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(RestaurentActivity.this));
                restaurentAdapter = new RestaurentAdapter(RestaurentActivity.this, restaurentTypeArrayList);
                restaurentAdapter.setClickListener(RestaurentActivity.this);
                recyclerView.setAdapter(restaurentAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(),restaurentTypeArrayList.get(position).getName(),Toast.LENGTH_LONG).show();
    }
}
