package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ADMINUSER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuser);
    }

    public void toAdminHome(View view) {
        Intent intent = new Intent(this,ADMINhome.class);
        startActivity(intent);
    }

    public void toUserHome(View view) {
        Intent intent = new Intent(this,UserHome.class);
        startActivity(intent);
    }
}