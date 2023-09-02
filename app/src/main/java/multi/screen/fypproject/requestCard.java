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

public class requestCard extends ArrayAdapter<requestFormClass> {
    DatabaseReference reference;
    FirebaseDatabase database;
    Button deleteButton;


    //constructor for card
    //connecting the card to the shirts section of firebase database
    public requestCard(Context context, List<requestFormClass> requestList) {
        super(context, 0, requestList);
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Requests");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        requestFormClass request = getItem(position);
        if (convertView == null) {
            //setting the cards to the card.xml layout created
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.requestcard, parent, false);

            deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(position);
                }
            });


        }
        TextView name = convertView.findViewById(R.id.Name);

        TextView email = convertView.findViewById(R.id.Email);
        TextView kNumber = convertView.findViewById(R.id.Knumber);
        TextView item1 = convertView.findViewById(R.id.rFoodItem1);
        TextView item2 = convertView.findViewById(R.id.rFoodItem2);
        TextView item3 = convertView.findViewById(R.id.rFoodItem3);
        TextView date = convertView.findViewById(R.id.date);
        TextView time = convertView.findViewById(R.id.time);



        name.setText(request.getFirstName()+" "+request.getLastName());
        email.setText(request.getEmail());
        kNumber.setText(String.valueOf(request.getkNumber()));
        item1.setText(request.getItem1());
        item2.setText(request.getItem2());
        item3.setText(request.getItem3());
        date.setText(request.getDate());
        time.setText(request.getTime());

        return convertView;

    }
    // Delete function for delete button
    private void delete(int position) {
        requestFormClass request = getItem(position);
        //obtaining specific card and database item
        reference.child(request.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                remove(request);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Request Appointment Deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
