package com.codepath.apps.Tweetster.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by supriya on 8/2/16.
 */

/*
"user": {
 "name": "OAuth Dancer",
      "profile_sidebar_fill_color": "DDEEF6",
      "profile_background_tile": true,
      "profile_sidebar_border_color": "C0DEED",
      "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
      "created_at": "Wed Mar 03 19:37:35 +0000 2010",
      "location": "San Francisco, CA",
      "follow_request_sent": false,
      "id_str": "119476949",
      "is_translator": false,
      "profile_link_color": "0084B4",
 */
public class User {
    //list attributes
    private String name;
    private long uid;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    private String profileImageUrl;
    private String screenName;

    //Deserialize  the user json to => User object
    public static User fromJSON(JSONObject json) {
        // Extract and fill the values
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Return a user

        return u;
    }
}
