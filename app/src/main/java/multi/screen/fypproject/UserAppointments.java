package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserAppointments extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointments);

    }

    public void toRequestView(View view) {
        Intent intent = new Intent(this,userRequestView.class);
        startActivity(intent);
    }

    public void toDonationView(View view) {
        Intent intent = new Intent(this,userDonationView.class);
        startActivity(intent);
    }
}