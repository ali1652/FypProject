package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ADMINaddDonation extends AppCompatActivity {

    EditText email;
    EditText firstName;
    EditText secondName;
    EditText date;
    EditText time;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminadd_donation);


        email = findViewById(R.id.rEmail);
        firstName = findViewById(R.id.rFirstName);
        secondName = findViewById(R.id.rSecondName);
        date = findViewById(R.id.rdate);
        time = findViewById(R.id.rtime);



        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Donations");


    }

    public void addDonation(View view) {
        String emaill = email.getText().toString();
        String firstNamee = firstName.getText().toString();
        String secondNamee = secondName.getText().toString();

        String datee = date.getText().toString();
        String timee = time.getText().toString();


        Donation donation = new Donation(firstNamee,secondNamee,emaill,timee,datee);
        email.setText("");
        firstName.setText("");
        secondName.setText("");
        date.setText("");
        time.setText("");

        reference.push().setValue(donation);
        Toast.makeText(this, "Donation Appointment Added", Toast.LENGTH_SHORT).show();
    }
}