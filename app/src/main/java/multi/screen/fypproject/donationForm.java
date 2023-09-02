package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donationForm extends AppCompatActivity {
    EditText email;
    EditText firstName;
    EditText secondName;
    EditText date;
    EditText time;

    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_form);

        email = findViewById(R.id.rEmail);
        firstName = findViewById(R.id.rFirstName);
        secondName = findViewById(R.id.rSecondName);
        date = findViewById(R.id.rdate);
        time = findViewById(R.id.rtime);

        email.setText(userEmail);


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
       



        Toast.makeText(this, "Donation Appointment Made", Toast.LENGTH_SHORT).show();
    }



}