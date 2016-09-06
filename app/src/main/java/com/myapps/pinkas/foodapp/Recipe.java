package com.myapps.pinkas.foodapp;

/**
 * Created by pinkas on 8/24/2016.
 */
public class Recipe {




    private String mPublisherName;
    private String mF2fUrl;
    private String mTitle;
    private String mSourceUrl;
    private String mID;
    private String mImageUrl;
    private double mF2fSocialRank;
    private String mPublisherUrl;
//    private int recipeOnPageNumber;
//    private String recipeIngredients;

    public Recipe(String mPublisherName, String mF2fUrl,String mTitle,String mSourceUrl,String mID,String mImageUrl,
                  double mF2fSocialRank,String mPublisherUrl){
        setmPublisherName(mPublisherName);
        setmF2fUrl(mF2fUrl);
        setmTitle(mTitle);
        setmSourceUrl(mSourceUrl);
        setmID(mID);
        setmImageUrl(mImageUrl);
        setmF2fSocialRank(mF2fSocialRank);
        setmPublisherUrl(mPublisherUrl);
    }

    public String getmPublisherName() {
        return mPublisherName;
    }

    public void setmPublisherName(String mPublisherName) {
        this.mPublisherName = mPublisherName;
    }

    public String getmF2fUrl() {
        return mF2fUrl;
    }

    public void setmF2fUrl(String mF2fUrl) {
        this.mF2fUrl = mF2fUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSourceUrl() {
        return mSourceUrl;
    }

    public void setmSourceUrl(String mSourceUrl) {
        this.mSourceUrl = mSourceUrl;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public double getmF2fSocialRank() {
        return mF2fSocialRank;
    }

    public void setmF2fSocialRank(double mF2fSocialRank) {
        this.mF2fSocialRank = mF2fSocialRank;
    }

    public String getmPublisherUrl() {
        return mPublisherUrl;
    }

    public void setmPublisherUrl(String mPublisherUrl) {
        this.mPublisherUrl = mPublisherUrl;
    }


}
