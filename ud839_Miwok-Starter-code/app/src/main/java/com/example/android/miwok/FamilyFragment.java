package com.example.android.miwok;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import android.media.AudioManager;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link FamilyFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link FamilyFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FamilyFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager AudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
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
    /* This listener gets triggered when the {@link MediaPlayer} has completed
    playing the audio file.
    */
    private MediaPlayer.OnCompletionListener mCompleteionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static FamilyFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FamilyFragment fragment = new FamilyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        AudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        //ListView listView = (ListView) findViewById(R.id.list);

        //listView.setAdapter(itemsAdapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getSoundResourcesId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompleteionListener);
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//   }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
@Override
public void onStop() {
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
