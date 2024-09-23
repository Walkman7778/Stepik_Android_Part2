package com.example.myapp05cafe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class OrderDetilActivity extends AppCompatActivity {
    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";
    private static final String EXTRA_DRINK_TYPE = "drinkType";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detil);
    }



    public static Intent newIntentOrderDetail(Context context,
                                              String userName,
                                              String drink,
                                              String additives,
                                              String drinkType){
        Intent intent = new Intent(context, OrderDetilActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }
}