package com.example.android.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.id;
import static com.example.android.menu.R.id.logMessage;
import static com.example.android.menu.R.id.menu_item_1;
import static com.example.android.menu.R.id.menu_item_2;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        TextView firstMessage = (TextView) findViewById(R.id.menu_item_1);
        String firstString = firstMessage.getText().toString();
        TextView secondMessage = (TextView) findViewById(R.id.menu_item_2);
        String secondString = secondMessage.getText().toString();
        TextView thirdMessage = (TextView) findViewById(R.id.menu_item_3);
        String thirdString = thirdMessage.getText().toString();
        Log.e("Error","This is a ERROR MESSAGE");
        Log.w("Warning","This phone is going to explosion");
        Log.i("Information","The batterey is too hot");
        Log.d("Debug","The phone is trying to cool down itself.");
        Log.v("Verbose","This is just a joke. Your phone is fine.");
        Log.v("Verbose",firstString+"\n"+secondString+"\n"+thirdString);
        // Find first menu item TextView and print the text to the logs

        // Find second menu item TextView and print the text to the logs

        // Find third menu item TextView and print the text to the logs

    }

}