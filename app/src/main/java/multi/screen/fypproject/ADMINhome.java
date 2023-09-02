package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ADMINhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
    }

    public void toFoodDatabase(View view) {
        Intent intent = new Intent(this,ADMINfoodDatabase.class);
        startActivity(intent);
    }

    public void toAddFood(View view) {
        Intent intent = new Intent(this,ADMINaddFood.class);
        startActivity(intent);
    }

    public void toVolunteerDatabase(View view) {
        Intent intent = new Intent(this,ADMINvolunteerDatabase.class);
        startActivity(intent);
    }

    public void toAddVolunteer(View view) {
        Intent intent = new Intent(this,ADMINaddVolunteer.class);
        startActivity(intent);
    }

    public void toDonationDatabase(View view) {
        Intent intent = new Intent(this,ADMINdonation.class);
        startActivity(intent);

    }

    public void toAddDonation(View view) {
        Intent intent = new Intent(this,ADMINaddDonation.class);
        startActivity(intent);
    }

    public void toPickUpDatabase(View view) {
        Intent intent = new Intent(this,ADMINpickupDatabase.class);
        startActivity(intent);
    }

    public void toAddPickUp(View view) {
        Intent intent = new Intent(this,adminAddPickUp.class);
        startActivity(intent);
    }

    public void signOutAdmin(View view) {
        Intent intent = new Intent(this,LogIn.class);
        //exsisting activites are cleared and cleared from the stack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}