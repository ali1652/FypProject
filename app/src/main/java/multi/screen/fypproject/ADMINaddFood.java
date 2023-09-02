package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ADMINaddFood extends AppCompatActivity {
    EditText brand;
    EditText name;
    EditText size;
    EditText amount;
    Spinner addCategorySpinner;
    Spinner chooseMeasurement;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminadd_food);

        brand = findViewById(R.id.foodBrand);
        name = findViewById(R.id.foodName);

        size = findViewById(R.id.foodSize);
        amount = findViewById(R.id.foodAmount);
        addCategorySpinner = findViewById(R.id.spinnerChooseCategory);
        setupSpinner();
        chooseMeasurement = findViewById(R.id.spinnerChooseMeasurement);
        setUpMeasurementSpinner();


        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Food");

    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addCategorySpinner.setAdapter(adapter);


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
        String measurementt = chooseMeasurement.getSelectedItem().toString();

        foodItem foodItems = new foodItem(brandd,namee,categoryy,sizee,measurementt,quantityy);
        brand.setText("");
        name.setText("");
        size.setText("");
        amount.setText("");

        reference.push().setValue(foodItems);

        Toast.makeText(ADMINaddFood.this, "Food Item Added", Toast.LENGTH_SHORT).show();


    }


}