package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ADMINfoodDatabase extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    ListView listView;
    Spinner spinner;
    SearchView searchView;

    ArrayList<foodItem> foodList = new ArrayList<foodItem>();
    ArrayAdapter<Volunteer> adapter;


    String selectedFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfood_database);

        searchView = findViewById(R.id.foodSearchView);

        spinner = findViewById(R.id.spinnerCategory);

        setupSpinner();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Food");




        listView = findViewById(R.id.listview);

        foodCard adapter = new foodCard(this, foodList);

        listView.setAdapter(adapter);


        //to filter when searching for items
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<foodItem> filteredFoodItem = new ArrayList();
                for (foodItem foodItems : foodList) {
                    if (foodItems.getName().toLowerCase().contains(s.toLowerCase())) {
                        if (selectedFilter.equals("all")) {

                        } else if (foodItems.getCategory().contains(selectedFilter)) {
                            filteredFoodItem.add(foodItems);
                        }
                    }
                }
                foodCard adapter = new foodCard(getApplicationContext(), filteredFoodItem);
                listView.setAdapter(adapter);
                return false;
            }
        });




        //shows on listView
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                foodList.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    foodItem food = snapshot.getValue(foodItem.class);
                    if (food != null) {
                        food.setKey(snapshot.getKey());
                        foodList.add(food);
                    }
                }
                //tells array adapter to update
                adapter.notifyDataSetChanged();
                filterList(selectedFilter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void filterList(String status){
        selectedFilter = status;
        ArrayList<foodItem> filteredFoodItem = new ArrayList();
        for (foodItem foodItems : foodList)
        {
            if(foodItems.getCategory().contains(status))
            {
                filteredFoodItem.add(foodItems);
            }
        }

        foodCard adapter  = new foodCard(getApplicationContext(),filteredFoodItem);
        listView.setAdapter(adapter);

    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                filterList(selectedOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void allFilter(View view) {
        foodCard adapter  = new foodCard(getApplicationContext(),foodList);
        listView.setAdapter(adapter);
    }

}