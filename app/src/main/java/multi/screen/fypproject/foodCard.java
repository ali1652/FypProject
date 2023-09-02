package multi.screen.fypproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class foodCard extends ArrayAdapter<foodItem> {

    DatabaseReference reference;
    FirebaseDatabase database;
    Button deleteButton;




    //constructor for card
    //connecting the card to the shirts section of firebase database
    public foodCard(Context context, List<foodItem> foodList) {
        super(context, 0, foodList);
        database = FirebaseDatabase.getInstance("https://fypproject-55e70-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Food");
    }


    // getting the data to display on the card
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        foodItem foodItems = getItem(position);
        if (convertView == null) {
            //setting the cards to the card.xml layout created
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.foodcard, parent, false);

            deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(position);
                }
            });

            EditText textViewnum = convertView.findViewById(R.id.editFoodAmount);

            textViewnum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        saveUpdatedQuantity(position, Integer.parseInt(textViewnum.getText().toString()));
                    }
                }
            });



        }
        //setting data in firebase to card items
        TextView name = convertView.findViewById(R.id.foodItemName);
        EditText textViewnum = convertView.findViewById(R.id.editFoodAmount);
        TextView foodW = convertView.findViewById(R.id.foodItemWeight);
        TextView foodM = convertView.findViewById(R.id.foodItemMeasurement);

        textViewnum.setText(String.valueOf(foodItems.getQuantity()));
        name.setText(foodItems.getBrand()+" "+foodItems.getName());
        foodW.setText(String.valueOf(foodItems.getSize()));
        foodM.setText(foodItems.getMeasurement());

        return convertView;
    }

    // Delete function for delete button
    private void delete(int position) {
        foodItem foodItems = getItem(position);
        //obtaining specific card and database item
        reference.child(foodItems.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                remove(foodItems);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Food item deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveUpdatedQuantity(int postion, int updatedQuantity){
        foodItem foodItems = getItem(postion);
        DatabaseReference itemReference = reference.child(foodItems.getKey());

        itemReference.child("quantity").setValue(updatedQuantity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                foodItems.setQuantity(updatedQuantity);
            }
        });
    }




}