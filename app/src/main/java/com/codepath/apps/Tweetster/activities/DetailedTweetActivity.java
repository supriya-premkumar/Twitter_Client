package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.fragments.TweetReplyFragment;
import com.codepath.apps.Tweetster.models.TweetModel;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by supriya on 8/4/16.
 */
public class DetailedTweetActivity extends AppCompatActivity {

    private TweetModel tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_detail);
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("txt_tweet"));
        TextView title = (TextView) findViewById(R.id.tvDetailName);
        TextView screenName = (TextView) findViewById(R.id.screenName);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView profileImage = (ImageView) findViewById(R.id.UserImage);
        ImageView background = (ImageView) findViewById(R.id.ivHeader);
        ImageView mediaImage = (ImageView) findViewById(R.id.profileImageUrl);
        ImageView replyTweet = (ImageView) findViewById(R.id.replyTweet);
        if (tweet.getBannerUrl() != null)
            Glide.with(this)
                    .load(tweet.getBannerUrl())
                    .into(background);
        else {
            Glide.with(this)
                    .load(R.drawable.ic_background)
                    .placeholder(R.drawable.ic_background)
                    .into(background);
        }

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
                        //.bitmapTransform(new RoundedCornersTransformation(this, 4, 1, RoundedCornersTransformation.CornerType.ALL))
                        .into(mediaImage);
//                .resize(512, 256)
            }

        }

        replyTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replyTweet();
            }
        });

    }

    private void replyTweet(){
        FragmentManager fm = getSupportFragmentManager();
        TweetReplyFragment replyFragment = TweetReplyFragment.newInstance(tweet.getTweetId(),tweet.getUserName(), tweet.getScreenName(), tweet.getProfileImageUrl());
        replyFragment.show(fm, "filter_settings_fragment");
    }


}