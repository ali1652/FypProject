package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }


    public void toSignUp(View view) {
        Intent toLogInPage = new Intent(this, SignUp.class);
        startActivity(toLogInPage);
    }

    public void toLogIn2(View view) {
        Intent toLogInPage = new Intent(this, LogIn.class);
        startActivity(toLogInPage);
    }
}