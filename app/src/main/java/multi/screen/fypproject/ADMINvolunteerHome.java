package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ADMINvolunteerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminvolunteer_home);
    }

    public void toVolunteerDatabase(View view) {
        Intent intent = new Intent(this,ADMINvolunteerDatabase.class);
        startActivity(intent);
    }

    public void toAddVolunteer(View view) {
        Intent intent = new Intent(this,ADMINaddVolunteer.class);
        startActivity(intent);
    }
}