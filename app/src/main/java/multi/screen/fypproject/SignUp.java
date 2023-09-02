package multi.screen.fypproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputPassword2;
    Button signUp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputPassword2 = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUpButton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }
    public void signUpFunction(View view) {
        PerformAuth();
    }



    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String password2 = inputPassword2.getText().toString();

        //making sure an email is entered
        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter correct email");
        }else if(password.isEmpty()|| password.length()<6)
        //only accepts passwords if they match and are at least 6 characters
        {
            inputPassword.setError("Please enter a password that is at leat six characters");
        }else if (!password.equals(password2))
        {
            inputPassword2.setError("Passwords not not match");
        }else
        {


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        sendUserToNextActivity();
                        Toast.makeText(SignUp.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                    }else
                    {

                        Toast.makeText(SignUp.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void sendUserToNextActivity(){
        Intent intent = new Intent(SignUp.this,SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}