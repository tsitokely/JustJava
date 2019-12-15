package com.example.android.justjava;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean wantWhippedCream = WhippedCream.isChecked();
        int orderPrice = calculatePrice();
        String orderSummaryMessage = createOrderSummary(orderPrice,wantWhippedCream);
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

    /**
     * Create summary of the order
     *
     * @param orderPrice price of the order
     * @param checked is whether the user checked a checkbox or not
     * @return text summary
     */
    private String createOrderSummary(int orderPrice, boolean checked){
        return "Name: Kaptain Kunal" +
        "\nAdd whipped cream? " + checked +
        "\nQuantity: " + numberOfCoffees +
        "\nTotal: $" + orderPrice +
        "\nThank you!";
    }
}