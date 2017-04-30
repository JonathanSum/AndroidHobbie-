package com.example.android.miwok;

/**
 * Created by JonathanSum on 4/9/2017.
 */
import android.view.View;
import android.widget.Toast;

public class NumbersClickListener implements View.OnClickListener  {

    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(),"Open the list of numbers:D", Toast.LENGTH_SHORT).show();
    }
}
