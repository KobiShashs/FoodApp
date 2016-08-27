package com.myapps.pinkas.foodapp;

/**
 * Created by pinkas on 8/24/2016.
 */
public class Recipe {

    private String recipeImageUrl;
    private int recipeID;
    private String recipeSourceUrl;
    private String recipeF2fUrl;
    private String recipeTitle;
    private String recipePublisherName;
    private String recipePublisherUrl;
    private float recipeF2fSocialRank;
    private int recipeOnPageNumber;
    private String recipeIngredients;

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeF2fUrl() {
        return recipeF2fUrl;
    }

    public void setRecipeF2fUrl(String recipeF2fUrl) {
        this.recipeF2fUrl = recipeF2fUrl;
    }

    public String getRecipeSourceUrl() {
        return recipeSourceUrl;
    }

    public void setRecipeSourceUrl(String recipeSourceUrl) {
        this.recipeSourceUrl = recipeSourceUrl;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipePublisherUrl() {
        return recipePublisherUrl;
    }

    public void setRecipePublisherUrl(String recipePublisherUrl) {
        this.recipePublisherUrl = recipePublisherUrl;
    }

    public String getRecipePublisherName() {
        return recipePublisherName;
    }

    public void setRecipePublisherName(String recipePublisherName) {
        this.recipePublisherName = recipePublisherName;
    }

    public float getRecipeF2fSocialRank() {
        return recipeF2fSocialRank;
    }

    public void setRecipeF2fSocialRank(float recipeF2fSocialRank) {
        this.recipeF2fSocialRank = recipeF2fSocialRank;
    }

    public int getRecipeOnPageNumber() {
        return recipeOnPageNumber;
    }

    public void setRecipeOnPageNumber(int recipeOnPageNumber) {
        this.recipeOnPageNumber = recipeOnPageNumber;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
