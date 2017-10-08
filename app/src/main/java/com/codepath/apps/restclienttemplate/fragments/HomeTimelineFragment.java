package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.models.TweetModel;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.List;

import javax.inject.Inject;


public class HomeTimelineFragment extends TweetsListFragment {

    @Inject TwitterClient mClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateHomeTimeline(0L);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TwitterApp) context.getApplicationContext()).getTwitterComponent().inject(this);;
    }

    @Override
    public void onScroll(Long lastTweetId) {
        populateHomeTimeline(lastTweetId);
    }

    @Override
    public void onSwipeRefresh() {
        populateHomeTimeline(0L);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String tweet = data.getStringExtra("result");
        Long uid = data.getLongExtra("uid", 0L);
        if (uid == 0L) {
            uid = null;
        }
        submitTweet(tweet, uid);
    }

    private void submitTweet(String tweet, Long replyId) {
        mClient.submitTweet(tweet, replyId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                addItem(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse)
            {
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONArray errorResponse)
            {
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                Throwable throwable)
            {
            }
        });
    }

    private void populateHomeTimeline(long sinceId) {
        if (!mNetworkUtil.isNetworkAvailable()) {
            List<TweetModel> tweetModelList = TweetModel.orderByDate();
            addItemsFromDB(tweetModelList);
        }
        mClient.getHomeTimeline(sinceId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                addItems(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                throwable.printStackTrace();
            }
        });
    }
}
