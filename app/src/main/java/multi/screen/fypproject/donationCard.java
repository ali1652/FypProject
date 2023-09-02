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

public class donationCard extends ArrayAdapter<Donation> {

    DatabaseReference reference;
    FirebaseDatabase database;
    Button deleteButton;


    //constructor for card
    //connecting the card to the shirts section of firebase database
    public donationCard(Context context, List<Donation> donationList) {
        super(context, 0, donationList);
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Donations");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Donation donation = getItem(position);
        if (convertView == null) {
            //setting the cards to the card.xml layout created
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.donationcard, parent, false);

            deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(position);
                }
            });


        }
        TextView name = convertView.findViewById(R.id.donationName);

        TextView email = convertView.findViewById(R.id.donationEmail);
        TextView date = convertView.findViewById(R.id.Ddate);
        TextView time = convertView.findViewById(R.id.Dtime);



        name.setText(donation.getfName()+" "+donation.getsName());
        email.setText(donation.getEmail());
        date.setText(donation.getDate());
        time.setText(donation.getTime());

        return convertView;

    }
    // Delete function for delete button
    private void delete(int position) {
        Donation donation = getItem(position);
        //obtaining specific card and database item
        reference.child(donation.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                remove(donation);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Donation Appointment Deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
