package com.example.myapp05cafe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MakeOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
    }


    public static Intent newIntent(Context context, String verificationName){
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra("name", verificationName);
        return intent;
    }
}