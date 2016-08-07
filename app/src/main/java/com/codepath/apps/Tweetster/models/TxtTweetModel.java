package com.codepath.apps.Tweetster.models;

import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by supriya on 8/5/16.
 */
public class TxtTweetModel implements Serializable {
    String tweet;
    String userName;
    String screenName;
    String timeStamp;
    String profileImageUrl;
    Long id;

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
            if (duration.contains("hour")) {
                duration = duration.replace("hour", "h");
            }
            if (duration.contains("second")) {
                duration = duration.replace("second", "s");
            }
            if (duration.contains("days")) {
                duration = duration.replace("days", "d");
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
            this.timeStamp = rawTweetData.getString("created_at");


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
