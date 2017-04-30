package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager AudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                            || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //realse audio focus
                        //Stop playback
                        releaseMediaPlayer();
                    }
                }
            };
    //private AudioManager.OnAudioFocusChangeListener afChangeListener;
    private MediaPlayer.OnCompletionListener mCompleteionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("daughter", "angsi", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "tune", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "taachi", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "chalitti", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "teṭe", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView gridview = (ListView) findViewById(R.id.list);
        //ListView listView = (ListView) findViewById(R.id.list);

        //listView.setAdapter(itemsAdapter);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Word word = words.get(position);

//                AudioManager.OnAudioFocusChangeListener afChangeListener;

                // Request audio focus for playback
                int result = AudioManager.requestAudioFocus(mOnAudioFocusChangeListener,

                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have audio focus now
                    // Start playback

                    Log.v("FamilyActivity", "Current word: " + word);

                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getSoundResourcesId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompleteionListener);
                }
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        //stop the music player
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // Abandon audio focus when playback complete
            //unregisters the audioFocusChangeListener so we don't get anymore callback
            AudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
