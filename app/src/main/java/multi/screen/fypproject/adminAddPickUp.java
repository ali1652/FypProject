package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminAddPickUp extends AppCompatActivity {
    EditText Vemail;
    EditText firstName;
    EditText secondName;
    EditText kNumber;
    EditText date;
    EditText time;
    Spinner categorySpinner;
    Spinner foodItemSpinner;
    Spinner categorySpinner2;
    Spinner foodItemSpinner2;
    Spinner categorySpinner3;
    Spinner foodItemSpinner3;

    ListView listViewR;

    FirebaseDatabase database;
    DatabaseReference reference;



    ArrayList<requestFormClass> requestList = new ArrayList<requestFormClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pick_up);



        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Requests");

        firstName = findViewById(R.id.rFirstName);
        secondName = findViewById(R.id.rSecondName);
        kNumber = findViewById(R.id.rKnumber);
        Vemail = findViewById(R.id.rEmail);
        //listViewR = findViewById(R.id.listViewR);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);



        categorySpinner = findViewById(R.id.rTest);
        foodItemSpinner = findViewById(R.id.rTest2);
        categorySpinner2 = findViewById(R.id.sCat2);
        foodItemSpinner2 = findViewById(R.id.sFood2);
        categorySpinner3 = findViewById(R.id.sCat3);
        foodItemSpinner3 = findViewById(R.id.sFood3);
        setupCategorySpinner();





    }

    private void setupCategorySpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner_items,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner2.setAdapter(adapter);
        categorySpinner3.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String selectedCategory = parent.getItemAtPosition(position).toString();
                setupFoodItemSpinner(selectedCategory);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        categorySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String selectedCategory = parent.getItemAtPosition(position).toString();
                setupFoodItemSpinner2(selectedCategory);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        categorySpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String selectedCategory = parent.getItemAtPosition(position).toString();
                setupFoodItemSpinner3(selectedCategory);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });




    }

    private void setupFoodItemSpinner(String category){
        DatabaseReference foodRef = database.getReference().child("Food");
        Query query = foodRef.orderByChild("category").equalTo(category);
        ArrayList<String> foodItems = new ArrayList<>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItems.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    foodItem item = snapshot.getValue(foodItem.class);
                    if(item != null){
                        foodItems.add(item.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(adminAddPickUp.this, android.R.layout.simple_spinner_item, foodItems);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodItemSpinner.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupFoodItemSpinner2(String category){
        DatabaseReference foodRef = database.getReference().child("Food");
        Query query = foodRef.orderByChild("category").equalTo(category);
        ArrayList<String> foodItems = new ArrayList<>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItems.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    foodItem item = snapshot.getValue(foodItem.class);
                    if(item != null){
                        foodItems.add(item.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(adminAddPickUp.this, android.R.layout.simple_spinner_item, foodItems);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodItemSpinner2.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupFoodItemSpinner3(String category){
        DatabaseReference foodRef = database.getReference().child("Food");
        Query query = foodRef.orderByChild("category").equalTo(category);
        ArrayList<String> foodItems = new ArrayList<>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItems.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    foodItem item = snapshot.getValue(foodItem.class);
                    if(item != null){
                        foodItems.add(item.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(adminAddPickUp.this, android.R.layout.simple_spinner_item, foodItems);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodItemSpinner3.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }









    public void addRequest(View view) {
        String emaill = Vemail.getText().toString();
        String firstNamee = firstName.getText().toString();
        String secondNamee = secondName.getText().toString();
        String item11 = foodItemSpinner.getSelectedItem().toString();
        String item22 = foodItemSpinner2.getSelectedItem().toString();
        String item33 = foodItemSpinner3.getSelectedItem().toString();
        String datee = date.getText().toString();
        String timee = time.getText().toString();
        int kNum = Integer.parseInt(kNumber.getText().toString());


        requestFormClass request = new requestFormClass(emaill,firstNamee, secondNamee,kNum,item11,item22,item33,datee,timee);
        Vemail.setText("");
        firstName.setText("");
        secondName.setText("");
        kNumber.setText("");
        date.setText("");
        time.setText("");

        reference.push().setValue(request);

        Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
    }

}