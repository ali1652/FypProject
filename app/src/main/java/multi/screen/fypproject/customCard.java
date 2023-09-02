package multi.screen.fypproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.List;

public class customCard extends ArrayAdapter<String> {

    DatabaseReference reference;
    FirebaseDatabase database;

    //constructor for card
    //connecting the card to the shirts section of firebase database
    public customCard(Context context, List<String> testList) {
        super(context, 0, testList);
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Food");
    }


    // getting the data to display on the card
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String string = getItem(position);
        if (convertView == null) {
            //setting the cards to the card.xml layout created
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);

            Button deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(position);
                }
            });


        }
        //setting data in firebase to card items
        TextView textView = convertView.findViewById(R.id.shirtItem);
        textView.setText(string);
        return convertView;
    }
    // Delete function for delete button
    private void delete(int position) {
        String item = getItem(position);
        //obtaining specific card and database item
        reference.orderByValue().equalTo(item).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    //gets snapshot of item and deletes it
                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        snapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //removing item from list and listview
                                remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(getContext(), "Shirt Deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}