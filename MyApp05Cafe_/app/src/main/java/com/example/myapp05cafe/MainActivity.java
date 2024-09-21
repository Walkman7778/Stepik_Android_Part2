package com.example.myapp05cafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationName = name.getText().toString().trim();
                String verificationPassword = password.getText().toString().trim();
                if ((verificationName.isEmpty()) | verificationPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,
                            getString(R.string.error_fields_empty),
                            // getString(R.string.error_fields_empty), here is the  same as
                            // R.string.error_fields_empty
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    launchNextscreen(verificationName);
                }


            }
        });
    }


    private void launchNextscreen(String verificationName){
        Intent intent = MakeOrderActivity.newIntent(this, verificationName);
        startActivity(intent);
    }


    private void initViews (){
        btn = findViewById(R.id.signInButton);
        name = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassword);

    }

}