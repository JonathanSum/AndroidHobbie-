package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;


/**
 * Created by JonathanSum on 4/14/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mBackGroundColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int backGroundColor) {
        super(context, 0, words);
        mBackGroundColor = backGroundColor;
    }

    private MediaPlayer mediaPlayer;

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        TextView miworkText = (TextView) listItemView.findViewById(R.id.miwork_text_view);
        miworkText.setText(currentWord.getmMiworkTranslation());

        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTranslation.setText(currentWord.getmDefaultTranslation());

        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.image);
        View backgroundColor = listItemView.findViewById(R.id.text_container);


        int color = ContextCompat.getColor(getContext(), mBackGroundColor);
        backgroundColor.setBackgroundColor(color);
        if (currentWord.hasImage()) {
            wordImage.setImageResource(currentWord.getImageResourcesId());
            wordImage.setVisibility(View.VISIBLE);
        } else {
            wordImage.setVisibility(View.GONE);
        }
        return listItemView;
    }


}
