package com.codepath.apps.Tweetster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;

import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.models.TxtTweetModel;

import org.parceler.Parcels;

/**
 * Created by supriya on 8/4/16.
 */
public class DetailedTweetActivity extends AppCompatActivity {

    private ShareActionProvider miShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_detail);
        TxtTweetModel tweet = (TxtTweetModel) Parcels.unwrap(getIntent().getParcelableExtra("txt_tweet"));


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

