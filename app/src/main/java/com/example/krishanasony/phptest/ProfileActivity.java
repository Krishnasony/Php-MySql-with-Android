package com.example.krishanasony.phptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
     TextView textViewusername,textViewuseremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewusername = findViewById(R.id.textviewusername);
        textViewuseremail = findViewById(R.id.textviewuseremail);
        textViewusername.setText(SharedPrefManager.getInstance(this).getUsername());
        textViewuseremail.setText(SharedPrefManager.getInstance(this).getUserEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return true;
    }
}
