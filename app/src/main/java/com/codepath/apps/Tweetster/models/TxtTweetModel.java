package com.codepath.apps.Tweetster.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by supriya on 8/5/16.
 */
public class TxtTweetModel implements Serializable {
    String profileName;
    String userName;
    String body;
    String image;

    public String getProfileName() {
        return profileName;
    }

    public String getUserName() {
        return userName;
    }

    public String getBody() {
        return body;
    }

    public String getImage() {
        return image;
    }

    public TxtTweetModel(JSONObject txtTweet){
//        this.profileName = profileName;
//        this.userName = screenName;
//        this.body = body;
//        this.image = imgContent;

        try{
            this.profileName = txtTweet.getString("name");
            this.userName= txtTweet.getString("screen_name");
            this.body = txtTweet.getString("description");
            this.image = txtTweet.getString("profile_image_url");
        }catch(JSONException e){
            e.printStackTrace();

        }
    }
}
