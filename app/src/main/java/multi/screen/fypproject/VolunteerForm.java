package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class VolunteerForm extends AppCompatActivity {
    EditText email;
    EditText firstName;
    EditText secondName;
    EditText age;
    EditText kNumber;

    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_form);
        email = findViewById(R.id.vEmail);
        firstName = findViewById(R.id.vFirstName);
        secondName = findViewById(R.id.vSecondName);
        age = findViewById(R.id.vAge);
        kNumber = findViewById(R.id.vKnumber);

        email.setText(userEmail);

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Volunteer");



    }




    public void addVolunteer(View view) {
        String emaill = email.getText().toString();
        String firstNamee = firstName.getText().toString();
        String secondNamee = secondName.getText().toString();
        int agee = Integer.parseInt(age.getText().toString());
        int kNum = Integer.parseInt(kNumber.getText().toString());

        Volunteer volunteer = new Volunteer(firstNamee,secondNamee,emaill,agee,kNum);
        email.setText("");
        firstName.setText("");
        secondName.setText("");
        age.setText("");
        kNumber.setText("");

        reference.push().setValue(volunteer);

        Toast.makeText(this, "Form Sent", Toast.LENGTH_SHORT).show();

    }

    public void toHome(View view) {
        Intent intent = new Intent(this,UserHome.class);
        startActivity(intent);
    }

    public void bpgBack(View view) {
        onBackPressed();
    }
}