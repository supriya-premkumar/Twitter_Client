package com.codepath.apps.Tweetster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.codepath.apps.Tweetster.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        lvTweets =(ListView)findViewById(R.id.lvTweets);
        // Create the arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        aTweets = new TweetsArrayAdapter(this, tweets);
        //Connect adapter to list view
        lvTweets.setAdapter(aTweets);

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
                aTweets.addAll(Tweet.fromJSONArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("onFailure: Timeline ", errorResponse.toString());
            }
        });

    }
}

