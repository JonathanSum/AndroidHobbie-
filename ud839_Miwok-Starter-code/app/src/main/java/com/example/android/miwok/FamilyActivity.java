package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә",R.drawable.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother));
        words.add(new Word("daughter", "angsi",R.drawable.family_daughter));
        words.add(new Word("older brother", "tune",R.drawable.family_older_brother));
        words.add(new Word("younger brother", "taachi",R.drawable.family_younger_brother));
        words.add(new Word("older sister", "chalitti",R.drawable.family_older_sister));
        words.add(new Word("younger sister", "teṭe",R.drawable.family_younger_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister));
        words.add(new Word("grandmother", "ama",R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);
        ListView gridview = (ListView) findViewById(R.id.list);
        //ListView listView = (ListView) findViewById(R.id.list);

        //listView.setAdapter(itemsAdapter);
        gridview.setAdapter(adapter);
    }
}
