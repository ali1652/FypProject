package multi.screen.fypproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class volunteerCard extends ArrayAdapter<Volunteer> {
    DatabaseReference reference;
    FirebaseDatabase database;
    Button deleteButton;



    //constructor for card
    //connecting the card to the shirts section of firebase database
    public volunteerCard(Context context, List<Volunteer> volunteerList) {
        super(context, 0, volunteerList);
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Volunteer");
    }

    // getting the data to display on the card
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Volunteer volunteer = getItem(position);
        if (convertView == null) {
            //setting the cards to the card.xml layout created
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.volunteercard, parent, false);

            deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(position);
                }
            });


        }
        //setting data in firebase to card items
        TextView name = convertView.findViewById(R.id.volunteerName);
        TextView age = convertView.findViewById(R.id.volunteerAge);
        TextView email = convertView.findViewById(R.id.volunteerEmail);
        TextView kNumber = convertView.findViewById(R.id.volunteerKnumber);

        age.setText(String.valueOf(volunteer.getAge()));
        name.setText(volunteer.getfName()+" "+volunteer.getsName());
        email.setText(volunteer.getEmail());
        kNumber.setText(String.valueOf(volunteer.getKnumber()));

        return convertView;

    }

    // Delete function for delete button
    private void delete(int position) {
        Volunteer volunteer = getItem(position);
        //obtaining specific card and database item
        reference.child(volunteer.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                remove(volunteer);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Volunteer deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }







}
