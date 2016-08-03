package com.codepath.apps.Tweetster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by supriya on 8/2/16.
 */

//parse the json + store the data, encapsulate state logic or display lohic
public class Tweet {
    //List out the Attribute
    private String body;
    private long uid;// unique data base id for the tweet

    public User getUser() {
        return user;
    }

    private User user; //store embedded user object
    private String createdAt;

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }
//Deserialize the JSON
    //Tweet.fromJSON{"{....}"} => <Tweet>

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        //Extract the values from json and store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //return the tweet object
        return tweet;
    }

    //Tweet.fromJSONARRAY => List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();
        //iterate the JSON Array and create tweets
        for (int i = 0; i < jsonArray.length(); i++){
            try{
                JSONObject tweetJSON = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJSON);
                if(tweet!=null){
                    tweets.add(tweet);
                }

            }catch(JSONException e){
                e.printStackTrace();
                continue;
            }



        }
        //Return the finished list
        return tweets;

    }



}
