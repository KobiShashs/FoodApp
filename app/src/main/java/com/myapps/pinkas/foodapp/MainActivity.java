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

    private final String URL_STRING ="http://food2fork.com/api/search?key=8a811aa57c72de0f39a639d0d6a44076&q=fish";
    private TextView textData;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progDailog;
    private ArrayList<Recipe> data = new ArrayList<>();

    RecipeAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region CACHE CONFIGURATION
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();


        ImageLoader.getInstance().init(config);
        //endregion

        listView = (ListView) findViewById(R.id.recipeListView);


        //        new NetworkTask().execute("http://food2fork.com/api/search?key=8a811aa57c72de0f39a639d0d6a44076&q=fish");
    }


    //Save list when state changes
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("key", data);
        super.onSaveInstanceState(outState);
    }

    //restore list when state changes
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            data = (ArrayList<Recipe>) savedInstanceState.getSerializable("key");
            adapter = new RecipeAdapter(getApplicationContext(), data);
            listView = (ListView) findViewById(R.id.recipeListView);
            listView.setAdapter(adapter);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_data) {
            new NetworkTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class NetworkTask extends AsyncTask<URL, Void, ArrayList<Recipe>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }
        @Override
        protected ArrayList<Recipe> doInBackground(URL... params) {


            URL url = QueryUtils.createUrl(URL_STRING);
            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            //  Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Recipe> recipes = QueryUtils.extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return recipes;
        }

        protected void onPostExecute(ArrayList<Recipe> recipes) {
            if (recipes == null) {
                return;
            }
            updateUi(recipes);
            progDailog.dismiss();
        }

    }
    private void updateUi(ArrayList<Recipe> recipes) {
        data = recipes;
        adapter = new RecipeAdapter(getApplicationContext(), recipes);
        listView = (ListView) findViewById(R.id.recipeListView);
        listView.setEmptyView(findViewById(R.id.empty_list_item));
        listView.setAdapter(adapter);
    }


}