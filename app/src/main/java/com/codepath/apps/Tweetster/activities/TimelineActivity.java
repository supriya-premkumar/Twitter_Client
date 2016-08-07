package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.codepath.apps.Tweetster.EndlessRecyclerViewScrollListener;
import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.TwitterApplication;
import com.codepath.apps.Tweetster.TwitterClient;
import com.codepath.apps.Tweetster.adapters.TweetsRecyclerViewAdapter;
import com.codepath.apps.Tweetster.models.TweetModel;
import com.codepath.apps.Tweetster.models.TxtTweetModel;
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

        rvTweets = (RecyclerView) findViewById(R.id.rvTweets);
        // Create the arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        adapter = new TweetsRecyclerViewAdapter(this, tweets);
        Log.d("onCreate: ", adapter.toString());
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rvTweets.setLayoutManager(gridLayoutManager);
        rvTweets.setAdapter(adapter);

        //get the client
        client = TwitterApplication.getRestClient(); //Singleton client
        populateTimeline(0);
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
//                customLoadMoreDataFromApi(page);
                Log.d("SCROLLED X TIMES", "Abhi");
                populateTimeline(page);
            }
        });
    }

    private void populateTimeline(int page) {
        long since_id = 1;
        long max_id = 1;
        if (!tweets.isEmpty()) {
            max_id = ((TxtTweetModel) tweets.get(0)).getId() - 1;
            since_id = ((TxtTweetModel) tweets.get(tweets.size() - 1)).getId();
        }


        client.getHomeTimeline(since_id, max_id, page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("onSuccess Timeline: ", json.toString());
                //JSON HERE
                //Deserialize JSON
                //Create models and add them to the adapter
                //load the model data into list view
                tweets.addAll(TweetModel.fromJsonArray(json));
                Log.d("Arraylist", json.toString());
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