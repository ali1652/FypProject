package multi.screen.fypproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class userFoodCard extends ArrayAdapter<foodItem> {


        DatabaseReference reference;
        FirebaseDatabase database;


    public userFoodCard(Context context, List<foodItem> foodList) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.userfoodcard, parent, false);

        }
        //setting data in firebase to card items
        TextView name = convertView.findViewById(R.id.foodItemName);
        TextView textViewnum = convertView.findViewById(R.id.editFoodAmount);
        TextView foodW = convertView.findViewById(R.id.foodItemWeight);
        TextView foodM = convertView.findViewById(R.id.foodItemMeasurement);

        textViewnum.setText(String.valueOf(foodItems.getQuantity()));
        name.setText(foodItems.getBrand()+" "+foodItems.getName());
        foodW.setText(String.valueOf(foodItems.getSize()));
        foodM.setText(foodItems.getMeasurement());

        return convertView;
    }








}
