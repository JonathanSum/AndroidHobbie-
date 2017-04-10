package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView textView = new TextView(this)
    }

    /**
     * This method is called when the order button is clicked.
     */
    private int quantity = 1;
    boolean isWhippedCreamAdded;


    public void submitOrder(View view) {
        String Name = getName();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, Name);
        //displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"))
        .putExtra(Intent.EXTRA_EMAIL, new String[]{"777JonathanSum@gmail.com"})
        .putExtra(Intent.EXTRA_SUBJECT, "This is your Coffe, Sir"+ Name)
        .putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
        //composeEmail(new String[]{"777JonathanSum@gmail.com"},priceMessage,priceMessage);

    }
    public void composeEmail(String[] address, String subject,String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onCheckboxClicked(View view) {
        isWhippedCreamAdded = true;
        displayPrice(calculatePrice());

    }

    public void onCheckboxClickedChocolate(View view) {
        boolean isChocolateAdded = true;
        displayPrice(calculatePrice());

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private String getName() {
        EditText Name = (EditText) findViewById(R.id.name_field);
        String returnName = Name.getText().toString();
        return returnName;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayMessage(String word) {
        TextView submitTextView = (TextView) findViewById(R.id.order_summary__text_view);
        submitTextView.setText("" + word);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
        displayPrice(calculatePrice());
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
        displayPrice(calculatePrice());
    }

    public int calculatePrice() {
        int basedPrice = 0;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        if (hasWhippedCream) {
            basedPrice += 1;
        }
        if (hasChocolate) {
            basedPrice += 1;
        }
        int price = quantity * (2 + basedPrice);
        return price;
    }


    private void displayPrice(int number) {
        TextView orderSummary = (TextView) findViewById(R.id.order_summary__text_view);
        orderSummary.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String Name) {
        String priceMessage = "Name: " + Name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }


}