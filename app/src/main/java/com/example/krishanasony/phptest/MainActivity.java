package com.example.krishanasony.phptest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText editTextemail,editTextusername,editTextpassword;
    private ProgressDialog progressDialog;
    private Button button;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
        editTextemail = findViewById(R.id.edittextemail);
        editTextusername = findViewById(R.id.edittextname);
        editTextpassword = findViewById(R.id.edittextpassword);
        button = findViewById(R.id.register);
        login = findViewById(R.id.textviewlogin);
        progressDialog = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registeruser();
            }
        });
    }
    private void registeruser(){

        final String username = editTextusername.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();
        final String password = editTextpassword.getText().toString().trim();
         progressDialog.setMessage("Register User....");
         progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

      RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
