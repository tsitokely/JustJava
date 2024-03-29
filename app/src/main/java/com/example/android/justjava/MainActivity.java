package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this,getString(R.string.decrement_boundary), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Enter user name
        EditText name = (EditText) findViewById(R.id.username);
        String userName = name.getText().toString();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(userName ,price, hasWhippedCream, hasChocolate);
        String emailSubject = getString(R.string.email_subject,userName);
        composeEmail(emailSubject,message);

    }

    /**
     * Calculates the price of the order.
     * @param whiteCream to check if the users wanted whiteCream
     * @param chocolate to check if the users wanted chocolate
     * @return total price
     */
    private int calculatePrice(boolean whiteCream, boolean chocolate) {
        int totalPrice = 5;
        if (whiteCream){
            totalPrice += 1;
        }
        if (chocolate){
            totalPrice += 2;
        }
        totalPrice = totalPrice *quantity;
        return totalPrice;
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String userName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getString(R.string.user_name,userName);
        priceMessage += getString(R.string.add_white_cream) +
                        ((addWhippedCream) ? getString(R.string.yes) : getString(R.string.no));
        priceMessage += getString(R.string.add_chocolate) +
                ((addChocolate) ? getString(R.string.yes) : getString(R.string.no));
        priceMessage += getString(R.string.quantity,quantity);
        priceMessage += getString(R.string.total_dollar,price);
        priceMessage += getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(numberOfCoffees);
    }

    public void composeEmail(String emailSubject, String emailBody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}