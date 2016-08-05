package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.TwitterApplication;
import com.codepath.apps.Tweetster.TwitterClient;
import com.codepath.apps.Tweetster.adapters.TweetsRecyclerViewAdapter;
import com.codepath.apps.Tweetster.models.TweetModel;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private TweetsRecyclerViewAdapter adapter;
    private ArrayList<Object> tweets;
    private RecyclerView rvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeline);

        rvTweets =(RecyclerView) findViewById(R.id.lvTweets);
        // Create the arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        adapter = new TweetsRecyclerViewAdapter(this, tweets);
        Log.d("onCreate: ", adapter.toString());
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rvTweets.setLayoutManager(gridLayoutManager);
//        rvTweets.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        //Connect adapter to list view
        rvTweets.setAdapter(adapter);

        //get the client
        client = TwitterApplication.getRestClient(); //Singleton client
        populateTimeline();
    }

    private void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("onSuccess Timeline: ", json.toString());
                //JSON HERE
                //Deserialize JSON
                //Create models and add them to the adapter
                //load the model data into list view
                tweets.addAll(TweetModel.fromJsonArray(json));
                Log.d("Arraylist", tweets.toString());
                adapter.notifyDataSetChanged();
//                rvTweets.scrollToPosition(0);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("onFailure: Timeline ", errorResponse.toString());
            }
        });

    }
}