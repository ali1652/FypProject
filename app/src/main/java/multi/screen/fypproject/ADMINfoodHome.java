package multi.screen.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ADMINfoodHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfood_home);
    }

    public void toFoodDatabaseAdmin(View view) {
        Intent intent = new Intent(this,ADMINfoodDatabase.class);
        startActivity(intent);
    }

    public void toAddFood(View view) {
        Intent intent = new Intent(this,ADMINaddFood.class);
        startActivity(intent);
    }

    public void toEditFood(View view) {
    }
}