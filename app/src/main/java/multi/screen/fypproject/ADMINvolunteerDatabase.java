package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ADMINvolunteerDatabase extends AppCompatActivity {
    ListView listview;

    FirebaseDatabase database;
    DatabaseReference reference;


    ArrayList<Volunteer> volunteerList = new ArrayList<Volunteer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminvolunteer_database);


        listview = findViewById(R.id.listviewV);

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Volunteer");

      ;
        volunteerCard adapter = new volunteerCard(this, volunteerList);

        listview.setAdapter(adapter);

        //shows on listView
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                volunteerList.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Volunteer volunteer = snapshot.getValue(Volunteer.class);
                    if (volunteer != null) {
                        volunteer.setKey(snapshot.getKey());
                        volunteerList.add(volunteer);
                    }
                }
                //tells array adapter to update
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}