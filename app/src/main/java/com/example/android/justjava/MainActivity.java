package com.example.android.justjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity <= 1) {
            Toast.makeText(this , "You cannot have less than 1 coffee" , Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity-1;
        display(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name_field);
        String value      =  nameField.getText().toString();
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream , hasChocolate);
//        Log.v("Main Activity", "Has Whipped Cream" + hasWhippedCream);

        String priceMessage = createOrderSummary(value, price, hasWhippedCream , hasChocolate);
        displayMessage(priceMessage);
    }

    private String createOrderSummary(String name , int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd Whipped Cream? " + hasWhippedCream;
        priceMessage += "\nAdd Chocolate? " + hasChocolate;
        priceMessage += "\nQuantity : " + quantity;
        priceMessage += "\nTotal : $" + price;
        priceMessage += "\nThankYou!";
        return priceMessage;

    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int baseprice = 5;
        if(hasWhippedCream) baseprice += 1;
        if(hasChocolate) baseprice += 2;
        return baseprice*quantity;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}