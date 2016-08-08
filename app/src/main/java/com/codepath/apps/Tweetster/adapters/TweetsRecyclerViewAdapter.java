package com.codepath.apps.Tweetster.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.Tweetster.R;
import com.codepath.apps.Tweetster.activities.DetailedTweetActivity;
import com.codepath.apps.Tweetster.models.TweetModel;

import org.parceler.Parcels;

import java.util.List;

public class TweetsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public ImageView ivProfilePhoto;
        public TextView tvUserName;
        public TextView tvName;
        public TextView tvBody;
        public TextView tvTimeStamp;
        public ImageView ivPoster;
        public ImageView ivResponse;


        public ImageView getIvPoster() {
            return ivPoster;
        }

        public ImageView getIvResponse() {
            return ivResponse;
        }

        public TextView getTvTimeStamp() {
            return tvTimeStamp;
        }


        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvName() {
            return tvName;
        }


        public void setTvBody(TextView tvBody) {
            this.tvBody = tvBody;
        }

        public void setIvProfilePhoto(ImageView ivProfilePhoto) {
            this.ivProfilePhoto = ivProfilePhoto;
        }

        public void setTvUserName(TextView tvUserName) {
            this.tvUserName = tvUserName;
        }


        public TextView getTvUserName() {
            return tvUserName;
        }

        public ImageView getIvProfilePhoto() {
            return ivProfilePhoto;
        }

        public TextView getTvBody() {
            return tvBody;
        }


        public ViewHolder1(View itemView) {
            super(itemView);

            ivProfilePhoto = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);
            ivPoster = (ImageView)itemView.findViewById(R.id.ivMedia);
            ivResponse = (ImageView)itemView.findViewById(R.id.ivReply);

        }

    }

//    public static class ViewHolder2 extends RecyclerView.ViewHolder {
//        public TextView tvName;
//        public TextView tvUserName;
//        public TextView tvBody;
//        public ImageView ivPost;
//
//
//        public ImageView getIvPost() {
//            return ivPost;
//        }
//
//
//        public TextView getTvTimeStamp() {
//            return tvTimeStamp;
//        }
//
//        public TextView tvTimeStamp;
//
//
//        public TextView getTvUserName() {
//            return tvUserName;
//        }
//
//        public void setTvUserName(TextView tvUserName) {
//            this.tvUserName = tvUserName;
//        }
//
//        public TextView getTvBody() {
//            return tvBody;
//        }
//
//        public void setTvBody(TextView tvBody) {
//            this.tvBody = tvBody;
//        }
//
//
//        public TextView getTvName() {
//            return tvName;
//        }
//
//        public void setTvName(TextView tvName) {
//            this.tvName = tvName;
//        }
//
//
//        public ViewHolder2(View itemView) {
//            super(itemView);
//
//            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
//            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
//            tvName = (TextView) itemView.findViewById(R.id.tvName);
//            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);
//            ivPost = (ImageView)itemView.findViewById(R.id.ivMedia)
//
//        }
//    }

    private List<Object> mTweets;
    private final int IMG_ARTICLE = 1, TXT_ARTICLE = 2;
    private Context mContext;

    public TweetsRecyclerViewAdapter(Context context, List<Object> tweets) {
        this.mTweets = tweets;
        this.mContext = context;
    }

    private Context getmContext() {
        return mContext;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

//        switch (viewType) {
//            case IMG_ARTICLE:
        View v1 = inflater.inflate(R.layout.txt_tweet, parent, false);
        viewHolder = new ViewHolder1(v1);
//                break;
//            case TXT_ARTICLE:
//                View v2 = inflater.inflate(R.layout.img_tweet, parent, false);
//                viewHolder = new ViewHolder2(v2);
//                break;
//        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

//        switch (viewHolder.getItemViewType()) {
//            case IMG_ARTICLE:
        ViewHolder1 vh1 = (ViewHolder1) viewHolder;
        vh1.getTvBody().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailedTweetActivity(position);
            }
        });
        configureViewHolder(vh1, position);
//                break;
//                case TXT_ARTICLE:
//                    ViewHolder2 vh2 = (ViewHolder2) viewHolder;
////                    vh2.getTvBody().setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            startArticleDisplayActivity(position);
////                        }
////                    });
//                    configureViewHolder(vh2, position);
//                    break;
    }
//    }

    private void startDetailedTweetActivity (int position) {
        Intent intent = new Intent(getmContext(), DetailedTweetActivity.class);
        Object tweets = mTweets.get(position);
        intent.putExtra("txt_tweet", Parcels.wrap((TweetModel) tweets));
//        if (tweets instanceof TweetModel) {
//            intent.putExtra("url", ((TweetModel) tweets).getWebUrl());
//        } else {
//            intent.putExtra("url", ((TxtArticleModel) article).getWebUrl());
//        }

        getmContext().startActivity(intent);
    }

    private void configureViewHolder(ViewHolder1 vh1, int position) {
        TweetModel tweet = (TweetModel) mTweets.get(position);
        if (tweet != null) {
            vh1.getTvBody().setText(tweet.getTweet());
            vh1.getTvName().setText(tweet.getUserName());
            vh1.getTvUserName().setText(tweet.getScreenName());
            vh1.getIvProfilePhoto().setImageResource(0);
            vh1.getTvTimeStamp().setText(tweet.getTimeStamp());

            if(tweet.getMediaUrl()!= null){
                vh1.ivPoster.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(tweet.getMediaUrl()).into(vh1.ivPoster);
            }else{
                vh1.ivPoster.setVisibility(View.GONE);
            }


            String thumbnail = tweet.getProfileImageUrl();
            if (!TextUtils.isEmpty(thumbnail)) {
                Glide.with(getmContext()).load(thumbnail).fitCenter().into(vh1.getIvProfilePhoto());
//                .resize(512, 256)
            }
        }
    }

//    private void configureViewHolder(ViewHolder2 vh2, int position) {
//        TweetModel tweetModel = (TweetModel) mTweets.get(position);
//        if (tweetModel != null) {
//            vh2.getTvUserName().setText("@" + tweetModel.getUser().getScreenName());
//            vh2.getTvBody().setText(tweetModel.getBody());
//            vh2.getTvName().setText(tweetModel.getUser().getName());
//
//        }
//    }


    @Override
    public int getItemCount() {
        return mTweets.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (mTweets.get(position) instanceof TweetModel) {
//            return IMG_ARTICLE;
//        } else if (mTweets.get(position) instanceof TweetModel) {
//            return TXT_ARTICLE;
//        }
//        return -1;
//    }
}