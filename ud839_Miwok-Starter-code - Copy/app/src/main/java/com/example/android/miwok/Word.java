package com.example.android.miwok;

/**
 * Created by JonathanSum on 4/14/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourcesId = NO_IMAGE_PROVIDED;
    private int mSoundResourcesId = NO_SOUND_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_SOUND_PROVIDED = -1;
    public Word(String defaultTranslation, String miwokTranslation,int soundResourcesId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundResourcesId=soundResourcesId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourcesId, int soundResourcesId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourcesId = imageResourcesId;
        mSoundResourcesId = soundResourcesId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiworkTranslation() {

        return mMiwokTranslation;
    }

    public int getImageResourcesId() {
        return mImageResourcesId;
    }
    public int getSoundResourcesId() {
        return mSoundResourcesId;
    }

    public boolean hasImage() {
        return mImageResourcesId != NO_IMAGE_PROVIDED;
    }
    public boolean hasSound() {
        return mSoundResourcesId != NO_SOUND_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                '}';
    }

}
