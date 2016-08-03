package com.codepath.apps.Tweetster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.Tweetster.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by supriya on 8/2/16.
 */

//Taking the Tweet objects and turning these into views displayed in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context,android.R.layout.simple_list_item_1 ,tweets);
    }

    //override and setup custom template

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. Get the Tweet
        Tweet tweet = getItem(position);

        //2. Find or inflate the template
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet, parent, false);
        }

        //3. Find the subviews to fill the data in the template
        ImageView ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);


        //4. populate the data into the subviews
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        //5. Return the view to be inserted into the list
        return convertView;
    }
}
