package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.Tweetster.R;
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
        TweetModel tweet = Parcels.unwrap(getIntent().getParcelableExtra("txt_tweet"));
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


        Log.d("DETAILED_VIEW:", tweet.getTweet());


//        WebView webView = (WebView) findViewById(R.id.wvArticle);
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(request.toString());
//                return true;
//            }
//        });
//
//        webView.loadUrl(url);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate menu resource file.
//        getMenuInflater().inflate(R.menu.article_display_activity_menu, menu);
//        // Locate MenuItem with ShareActionProvider
//        MenuItem item = menu.findItem(R.id.menu_item_share);
//        // Fetch reference to the share action provider
//        miShare = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//
//        // get reference to WebView
//        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);
//        // pass in the URL currently being used by the WebView
//        shareIntent.putExtra(Intent.EXTRA_TEXT, wvArticle.getUrl());
//
//        miShare.setShareIntent(shareIntent);
//        return super.onCreateOptionsMenu(menu);
//
//    }

}