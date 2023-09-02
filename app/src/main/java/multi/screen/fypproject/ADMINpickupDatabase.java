package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ADMINpickupDatabase extends AppCompatActivity {

    ListView listViewR;

    FirebaseDatabase database;
    DatabaseReference reference;

    ArrayList<requestFormClass> requestList = new ArrayList<requestFormClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpickup_database);

        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Requests");


        listViewR = findViewById(R.id.listViewR);

        requestCard adapter = new requestCard(this, requestList);

        listViewR.setAdapter(adapter);

        //shows on listView
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                requestList.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    requestFormClass request = snapshot.getValue(requestFormClass.class);
                    if (request != null) {
                        request.setKey(snapshot.getKey());
                        requestList.add(request);
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