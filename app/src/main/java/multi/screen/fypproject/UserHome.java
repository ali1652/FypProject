package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        mAuth = FirebaseAuth.getInstance();

    }

    public void toVolunteer(View view) {
        Intent intent = new Intent(this,VolunteerForm.class);
        startActivity(intent);
    }

    public void toDonate(View view) {
        Intent intent = new Intent(this,donationForm.class);
        startActivity(intent);
    }

    public void toViewFood(View view) {
        Intent intentt = new Intent(this,foodDatabase.class);
        startActivity(intentt);
    }

    public void toAppointments(View view) {
        Intent intent = new Intent(this,UserAppointments.class);
        startActivity(intent);
    }

    public void signOut(View view) {
        Intent intent = new Intent(this,LogIn.class);
        //exsisting activites are cleared and cleared from the stack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void toRequestFood(View view) {
        Intent intent = new Intent(this,requestForm.class);
        startActivity(intent);
    }

    public void toFoodAdmin(View view) {
        Intent intent = new Intent(this,ADMINhome.class);
        startActivity(intent);
    }
}