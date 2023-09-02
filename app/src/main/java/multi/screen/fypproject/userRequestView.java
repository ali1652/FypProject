package multi.screen.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userRequestView extends AppCompatActivity {
    ListView listViewR;

    FirebaseDatabase database;
    DatabaseReference reference;
    ValueEventListener valueEventListener;

    ArrayList<requestFormClass> requestList = new ArrayList<requestFormClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_view);
        listViewR = findViewById(R.id.listViewR);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();



        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Requests");

        Query userRequestFormQuery = reference.orderByChild("email").equalTo(userEmail);


        //shows on listView
        userRequestFormQuery.addValueEventListener(new ValueEventListener() {
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
                userRequestCard adapter = new userRequestCard(userRequestView.this, requestList);
                listViewR.setAdapter(adapter);
                //tells array adapter to update
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}