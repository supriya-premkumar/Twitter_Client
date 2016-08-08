package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.models.TxtTweetModel;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by supriya on 8/4/16.
 */
public class DetailedTweetActivity extends AppCompatActivity {

    private TxtTweetModel tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_detail);
        TxtTweetModel tweet = Parcels.unwrap(getIntent().getParcelableExtra("txt_tweet"));
        TextView title = (TextView) findViewById(R.id.tvDetailName);
        TextView screenName = (TextView) findViewById(R.id.screenName);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView profileImage = (ImageView) findViewById(R.id.UserImage);
        ImageView background = (ImageView) findViewById(R.id.ivHeader);
        ImageView mediaImage = (ImageView) findViewById(R.id.profileImageUrl);
        if (tweet.getBannerUrl() != null)
            Glide.with(this)
                    .load(tweet.getBannerUrl())
                    .into(background);

        title.setText(tweet.getUserName());
        screenName.setText(tweet.getScreenName());
        description.setText(tweet.getTweet());
        Glide.with(this)
                .load(tweet.getProfileImageUrl())
                .bitmapTransform(new RoundedCornersTransformation(this, 4, 1, RoundedCornersTransformation.CornerType.ALL))
                .into(profileImage);

        if (tweet.getMediaUrl() != null) {
            String thumbnail = tweet.getMediaUrl();
            if (!TextUtils.isEmpty(thumbnail)) {
                Glide.with(this)
                        .load(thumbnail)
                        .into(mediaImage);
            }

        }


        Log.d("DETAILED_VIEW:", tweet.getTweet());
    }


}