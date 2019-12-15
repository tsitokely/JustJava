/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;
    int unitPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int orderPrice = calculatePrice();
        String orderSummaryMessage =
                "Name: Kaptain Kunal" +
                "\nQuantity: " + numberOfCoffees +
                "\nTotal: $" + orderPrice +
                "\nThank you!";
        displayMessage(orderSummaryMessage);
    }

    /**
     * This method is called when the order button is clicked.
     */
    private int calculatePrice() {
        return numberOfCoffees * unitPrice;
    }

    /**
     * This method is called when the increase button is clicked.
     */
    public void increase(View view) {
        numberOfCoffees += 1;
        display(numberOfCoffees);
    }

    /**
     * This method is called when the decrease button is clicked.
     */
    public void decrease(View view) {
        numberOfCoffees -= 1;
        display(numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}