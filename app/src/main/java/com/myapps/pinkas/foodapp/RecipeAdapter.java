package com.myapps.pinkas.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/6/2016.
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private List<Recipe> recipeList;
    private int resource;
    private LayoutInflater inflater;

    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        super(context, 0, items);
    }

//    public RecipeAdapter(Context context, int resource, List<Recipe> objects) {
//        super(context, resource, objects);
//        recipeList = objects;
//        this.resource = resource;
//        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;// = new ViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row_data, parent, false);
            holder = new ViewHolder();
            holder.publisherName = (TextView) convertView.findViewById(R.id.publishrNameTextView);
            holder.title = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.recipeImage = (ImageView) convertView.findViewById(R.id.recipeImageView);
            holder.recipeRatingBar = (RatingBar) convertView.findViewById(R.id.recipeRatingBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Recipe currentRecipe = getItem(position);


        holder.publisherName.setText(currentRecipe.getmPublisherName());
       // holder.title.setText(recipeList.get(position).getmTitle());


        String imageUrl = currentRecipe.getmImageUrl();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, holder.recipeImage);

        //rating bar
        holder.recipeRatingBar.setRating((float) (currentRecipe.getmF2fSocialRank() / 2));


        return convertView;
    }

    class ViewHolder {
        private TextView publisherName;
        private TextView title;
        private ImageView recipeImage;
        private RatingBar recipeRatingBar;
    }
}
