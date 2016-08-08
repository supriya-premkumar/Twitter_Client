package com.codepath.apps.Tweetster.models;

import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by supriya on 8/5/16.
 */
@Parcel
public class TxtTweetModel {
    String tweet;
    String userName;
    String screenName;
    String timeStamp;
    String profileImageUrl;
    Long id;
    String mediaUrl;

    public String getBannerUrl() {
        return bannerUrl;
    }

    String bannerUrl;

    public TxtTweetModel(){

    }

    public String getMediaUrl() {
        return mediaUrl;
    }


    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTimeStamp() {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
        String duration = "";
        String relativeDate;
        try {
            long dateMillis = sf.parse(timeStamp).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            duration = relativeDate.replace("In", "");
            duration = duration.replace("ago", "");
            if (duration.contains("minutes")) {
                duration = duration.replace("minutes", "m");
            }
            if (duration.contains("minute")) {
                duration = duration.replace("minute", "m");
            }
            if (duration.contains("hour")) {
                duration = duration.replace("hour", "h");
            }
            if (duration.contains("hours")) {
                duration = duration.replace("hours", "h");
            }
            if (duration.contains("second")) {
                duration = duration.replace("second", "s");
            }
            if (duration.contains("seconds")) {
                duration = duration.replace("seconds", "s");
            }
            if (duration.contains("days")) {
                duration = duration.replace("days", "d");
            }
            if (duration.contains("day")) {
                duration = duration.replace("day", "d");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return duration;

    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public TxtTweetModel(JSONObject rawTweetData) {

        try {
            this.id = rawTweetData.getLong("id");
            this.tweet = rawTweetData.getString("text");
            JSONObject user = rawTweetData.getJSONObject("user");
            this.userName = user.getString("name");
            this.screenName = "@" + user.getString("screen_name");
            this.profileImageUrl = user.getString("profile_image_url");
            this.bannerUrl=user.has("profile_banner_url")?user.getString("profile_banner_url"):"";

            this.timeStamp = rawTweetData.getString("created_at");
            JSONObject media = rawTweetData.getJSONObject("entities");
            if(media.has("media")){
                JSONArray mediaArray = media.getJSONArray("media");
                if(mediaArray.length()>0){
                    JSONObject mediaArrayObject = mediaArray.getJSONObject(0);
                    if(mediaArrayObject.getString("type").equals("photo")){
                        this.mediaUrl = mediaArray.getJSONObject(0).getString("media_url");
                    }else if(mediaArrayObject.getString("type").equals("video"));{

                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
