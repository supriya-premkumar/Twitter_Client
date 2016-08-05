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
                JSONObject rawTweet = array.getJSONObject(x);
                JSONObject user = rawTweet.getJSONObject("user");
                Log.d("fromJsonArray: ", user.toString());
                String profileName = user.getString("name");
                Log.d("fromJsonArray:Pname ", profileName);

                String screenName = user.getString("screen_name");
                Log.d("fromJsonArray:screen", screenName);

                Long id = user.getLong("id");
                String body = user.getString("description");
                Log.d("fromJsonArray:body", body);



                String imgContent = user.getString("profile_image_url");
                Log.d("fromJsonArray: img ", imgContent);


//                if (imgContent != null) {
//                    ImgTweetModel imgTweet = new ImgTweetModel(user, name, id, screenName, imgContent);
//                    results.add(imgTweet);
//                }
                TxtTweetModel txtTweet = new TxtTweetModel( user);
                results.add(txtTweet);
                Log.d( "xxx ", txtTweet.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            /*
               To differentiate what tweet type it is(img, video or txt)


             */

//            try {
//                JSONObject article = array.getJSONObject(x);
//                JSONArray multimedia = article.getJSONArray("multimedia");
//                if (multimedia.length() > 0) {
//                    JSONObject multimediaJson = multimedia.getJSONObject(0);
//                    String thumbnail = "http://www.nytimes.com/" + multimediaJson.getString("url");
//                    ImgArticleModel imgArticleModel = new ImgArticleModel(article, thumbnail);
//                    webUrl = imgArticleModel.getWebUrl();
//                    results.add(imgArticleModel);
//                }
//                else {
//                    TxtArticleModel txtArticleModel = new TxtArticleModel(article);
//                    webUrl = txtArticleModel.getWebUrl();
//                    results.add(txtArticleModel);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
        return results;


    }

}