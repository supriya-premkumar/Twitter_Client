package com.codepath.apps.Tweetster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TweetModel implements Serializable {

    public static ArrayList<Object> fromJsonArray(JSONArray array) {
        ArrayList<Object> results = new ArrayList<>();



        for (int x = 0; x < array.length(); x++) {
            try {
                JSONObject rawTweetData = array.getJSONObject(x);

//                JSONObject tweetContent = rawTweet.getJSONObject("text");
//                Log.d("fromJsonArray:complete ", tweetContent.toString());

//                JSONObject user = rawTweetData.getJSONObject("user");
//                String tweetText = rawTweetData.getString("text");
//                String profileName = user.getString("name");
//
//                String screenName = user.getString("screen_name");
//
//                Long id = user.getLong("id");
//
//                String body = user.getString("description");
//
//
//
//                String imgContent = user.getString("profile_image_url");
//                Log.d("fromJsonArray: img ", imgContent);


//                if (media != null) {
//                    ImgTweetModel imgTweet = new ImgTweetModel(rawTweetData);
//                    results.add(imgTweet);
//                }
                TxtTweetModel txtTweet = new TxtTweetModel(rawTweetData);
                results.add(txtTweet);
                Log.d( "xxxx", rawTweetData.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return results;


    }

}