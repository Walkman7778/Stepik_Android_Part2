package com.example.myapp05cafe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetilActivity extends AppCompatActivity {
    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";
    private static final String EXTRA_DRINK_TYPE = "drinkType";

    private TextView textViewName;
    private TextView textViewDrink;
    private TextView textViewDrinkType;
    private TextView textViewDrinkAdditives;
    private String resultName0;
    private String resultDrink0;
    private String resultDrinkType0 ;
    private String resultDrinkAdditives0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detil);
        initViewOrderDetail();
        setUpParameters();
    }



    public static Intent newIntentOrderDetail(Context context,
                                              String userName,
                                              String drink,
                                              String additives,
                                              String drinkType1){
        Intent intent = new Intent(context, OrderDetilActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType1);
        return intent;
    }

    private void initViewOrderDetail(){
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewDrinkAdditives = findViewById(R.id.textViewAdditives);

    }

    private void setUpParameters(){
        resultName0 = getIntent().getStringExtra(EXTRA_USER_NAME);
        resultDrink0 = getIntent().getStringExtra(EXTRA_DRINK);
        resultDrinkType0 = getIntent().getStringExtra(EXTRA_DRINK_TYPE);
        resultDrinkAdditives0 = getIntent().getStringExtra(EXTRA_ADDITIVES);
        textViewName.setText(resultName0);
        textViewDrink.setText(resultDrink0);
        textViewDrinkType.setText(resultDrinkType0);
        textViewDrinkAdditives.setText(resultDrinkAdditives0);

    }



}