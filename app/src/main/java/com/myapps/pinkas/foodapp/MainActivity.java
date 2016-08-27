package com.myapps.pinkas.foodapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView textData;
    private ListView recipeListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();


        ImageLoader.getInstance().init(config);


        recipeListView = (ListView)findViewById(R.id.recipeListView);


        //        new NetworkTask().execute("http://food2fork.com/api/search?key=8a811aa57c72de0f39a639d0d6a44076&q=fish");
    }

    public class NetworkTask extends AsyncTask<String,String, List<Recipe>>{

        @Override
        protected  List<Recipe> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params [0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);

                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("recipes");

                List<Recipe> recipeList = new ArrayList<>();
                for (int i= 0; i<parentArray.length(); i++) {

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    Recipe recipe = new Recipe();
                    recipe.setRecipePublisherName(finalObject.getString("publisher"));
                    recipe.setRecipeTitle(finalObject.getString("title"));
                    recipe.setRecipePublisherUrl(finalObject.getString("publisher_url"));
                    recipe.setRecipeF2fUrl(finalObject.getString("f2f_url"));
                    recipe.setRecipeSourceUrl(finalObject.getString("source_url"));
                    recipe.setRecipeImageUrl(finalObject.getString("image_url"));
                   // recipe.setRecipeID(finalObject.getInt("recipe_id"));


                    //adding the object in the list
                    recipeList.add(recipe);
                }
                return recipeList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute( List<Recipe> result) {
            super.onPostExecute(result);

            RecipeAdapter recipeAdapter = new RecipeAdapter(getApplicationContext(),R.layout.row_data, result);
            recipeListView.setAdapter(recipeAdapter);

        }

    }

    public class RecipeAdapter extends ArrayAdapter<Recipe>{

        private List<Recipe> recipeList;
        private int resource;
        private LayoutInflater inflater;

        public RecipeAdapter(Context context, int resource, List<Recipe> objects) {
            super(context, resource, objects);
            recipeList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;// = new ViewHolder();

            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.row_data, parent, false);
                holder = new ViewHolder();
                holder.publisherName = (TextView)convertView.findViewById(R.id.publishrNameTextView);
                holder.title = (TextView)convertView.findViewById(R.id.titleTextView);
                holder.recipeImage = (ImageView) convertView.findViewById(R.id.recipeImageView);
                holder.recipeRatingBar = (RatingBar) convertView.findViewById(R.id.recipeRatingBar);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            Recipe currentRecipe = getItem(position);


            holder.publisherName.setText(currentRecipe.getRecipePublisherName());
            holder.title.setText(recipeList.get(position).getRecipeTitle());


            String imageUrl = currentRecipe.getRecipeImageUrl();
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(imageUrl, holder.recipeImage);

            //rating bar
            holder.recipeRatingBar.setRating(currentRecipe.getRecipeF2fSocialRank()/2);



            return convertView;
        }

        class ViewHolder{
          private TextView publisherName;
          private TextView title;
          private ImageView recipeImage;
          private RatingBar recipeRatingBar;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_data){
            new NetworkTask().execute("http://food2fork.com/api/search?key=8a811aa57c72de0f39a639d0d6a44076&q=fish");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}