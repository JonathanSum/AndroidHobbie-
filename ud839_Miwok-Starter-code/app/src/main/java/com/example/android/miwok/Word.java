package com.example.android.miwok;

/**
 * Created by JonathanSum on 4/14/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourcesId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int ImageResourcesId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourcesId = ImageResourcesId;
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

    public boolean hasImage() {
        return mImageResourcesId != NO_IMAGE_PROVIDED;
    }
}
