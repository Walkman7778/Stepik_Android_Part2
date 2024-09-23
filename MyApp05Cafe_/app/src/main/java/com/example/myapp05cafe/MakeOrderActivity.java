package com.example.myapp05cafe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private String drink;
    private static final String ExtraUserName = "name";

    private Button btnMakeOrder;

    private TextView greetings;
    private TextView questionAdditives;

    private Spinner spinenrTea;
    private Spinner spinenrCoffee;

    private RadioButton tea;
    private RadioButton coffee;
    private RadioGroup chooseDrink;

    private CheckBox sugar;
    private CheckBox milk;
    private CheckBox lemon;

    private String userName;

    private String additives1;

    private String drinkType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setUpUseName();
        chooseDrink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == tea.getId()){
                    choosenTea();

                } else if (checkedId == coffee.getId()) {
                    choosenCoffee();
                }

            }
        });
        tea.setChecked(true);

        btnMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMadeOrder();
                launchNextscreenMkorder(userName, drink, additives1 ,drinkType);
            }
        });



    }




    private void onUserMadeOrder(){
        ArrayList additives = new ArrayList<>();
        if (sugar.isChecked()) {
            additives.add(sugar.getText().toString());
        }
        if (milk.isChecked()) {
            additives.add(milk.getText().toString());
        }
        if (lemon.isChecked()) {
            additives.add(lemon.getText().toString());
        }

        String drinkType = "";
        if (tea.isChecked()){
            drinkType = spinenrTea.getSelectedItem().toString();
        } else if (coffee.isChecked()) {
            drinkType = spinenrCoffee.getSelectedItem().toString();
        }

        additives1 = additives.toString();
    }


    private void choosenTea(){
        drink = getString(R.string.tea);
        String aditives = getString(R.string.additives, drink);
        questionAdditives.setText(aditives);
        lemon.setVisibility(View.VISIBLE);
        spinenrTea.setVisibility(View.VISIBLE);
        spinenrCoffee.setVisibility(View.INVISIBLE);


    }
    private void choosenCoffee(){
        drink = getString(R.string.coffee);
        String aditives = getString(R.string.additives, drink);
        questionAdditives.setText(aditives);
        lemon.setVisibility(View.INVISIBLE);
        spinenrCoffee.setVisibility(View.VISIBLE);
        spinenrTea.setVisibility(View.INVISIBLE);
    }



    private void setUpUseName(){
        userName = getIntent().getStringExtra(ExtraUserName);
        String result = getString(R.string.greetings, userName);
        greetings.setText(result);

    }


    private void initViews() {

        btnMakeOrder = findViewById(R.id.buttonMakeOrder);
        greetings = findViewById(R.id.textViewGreetings);
        questionAdditives = findViewById(R.id.textViewAdditives);
        spinenrTea = findViewById(R.id.spinnerTea);
        spinenrCoffee = findViewById(R.id.spinnerCoffee);
        chooseDrink = findViewById(R.id.radioGruopDrinks);
        tea = findViewById(R.id.radioButtonTea);
        coffee = findViewById(R.id.radioButtonCoffe);
        sugar = findViewById(R.id.checkBoxSugar);
        milk = findViewById(R.id.checkBoxMilk);
        lemon = findViewById(R.id.checkBoxLemon);
    }



    public static Intent newIntent(Context context, String verificationName){
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(ExtraUserName, verificationName);
        return intent;
    }


    private void launchNextscreenMkorder(String userName,
                                         String drink,
                                         String additives1 ,
                                         String drinkType) {
        Intent intent = OrderDetilActivity.newIntentOrderDetail(this,
                userName,
                drink,
                additives1 ,
                drinkType);
        startActivity(intent);
    }

}