package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class testActivity extends AppCompatActivity {
    EditText brand;
    EditText name;
    EditText category;
    EditText size;
    EditText amount;
    EditText foodId;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> userShirts;
    SearchView searchView;
    TextView randomItemDisplay;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    Spinner spinner;
    Spinner addCategorySpinner;
    Spinner chooseMeasurement;

    ArrayList<foodItem> foodList = new ArrayList<foodItem>();
    ArrayAdapter<Volunteer> adapter;


    String selectedFilter = "all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        brand = findViewById(R.id.foodBrand);
        name = findViewById(R.id.foodName);
        category = findViewById(R.id.foodCategory);
        searchView = findViewById(R.id.foodSearchView);
        size = findViewById(R.id.foodSize);
        amount = findViewById(R.id.foodAmount);
        foodId = findViewById(R.id.foodId);
        // randomItemDisplay = findViewById(R.id.randomItemShirt);
        spinner = findViewById(R.id.spinnerCategory);
        addCategorySpinner = findViewById(R.id.spinnerChooseCategory);
        setupSpinner();
        chooseMeasurement = findViewById(R.id.spinnerChooseMeasurement);
        setUpMeasurementSpinner();


        // String getShirtsInput = brand.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Food");

        //creating arrayList for shirts
        userShirts = new ArrayList<>();


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
                            filteredFoodItem.add(foodItems);
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

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addCategorySpinner.setAdapter(adapter);

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

    private void setUpMeasurementSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.measurement_array_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseMeasurement.setAdapter(adapter);
    }








    // adds data to the firebase database once user has inputted a shirt
    public void buttonClick(View view) {
        String brandd = brand.getText().toString();
        String categoryy = addCategorySpinner.getSelectedItem().toString();
        String namee = name.getText().toString();
        int sizee = Integer.parseInt(size.getText().toString());
        int quantityy = Integer.parseInt(amount.getText().toString());
       // int quantityy = Integer.parseInt(.getText().toString));
        String measurementt = chooseMeasurement.getSelectedItem().toString();

        foodItem foodItems = new foodItem(brandd,namee,categoryy,sizee,measurementt,quantityy);
        brand.setText("");
        name.setText("");
        size.setText("");
        amount.setText("");

        reference.push().setValue(foodItems);

        Toast.makeText(testActivity.this, "Sucess", Toast.LENGTH_SHORT).show();


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





    //home button function
    public void toHomePage(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void allFilter(View view) {
        foodCard adapter  = new foodCard(getApplicationContext(),foodList);
        listView.setAdapter(adapter);
    }

    public void filterSnacks(View view) {
        filterList("Snacks");
    }

    //updates amount of food once id has been entered
    public void updateAmount(View view) {
        String inPuttedId = foodId.getText().toString();
        if (!inPuttedId.isEmpty()) {
            int id = Integer.parseInt(inPuttedId);
            Query query = reference.orderByChild("id").equalTo(id);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                            int newAmount = Integer.parseInt(amount.getText().toString());
                            itemSnapshot.getRef().child("quantity").setValue(newAmount);
                            Toast.makeText(testActivity.this, "Amount Updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(testActivity.this, "Id not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        } else {
            Toast.makeText(this, "Please enter a ID", Toast.LENGTH_SHORT).show();
        }
    }






}

