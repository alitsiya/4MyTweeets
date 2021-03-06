package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
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

public class UserTimelineFragment extends TweetsListFragment {

    private static String mScreenName;
    @Inject TwitterClient mClient;

    public static UserTimelineFragment newInstance(String screenName) {
        mScreenName = screenName;
        UserTimelineFragment userTimelineFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userTimelineFragment.setArguments(args);
        return userTimelineFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateUserTimeline(0L);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TwitterApp) context.getApplicationContext()).getTwitterComponent().inject(this);
    }

    @Override
    public void onScroll(Long lastTweetId) {
        populateUserTimeline(lastTweetId);
    }

    @Override
    public void onSwipeRefresh() {
        populateUserTimeline(0L);
    }

    private void populateUserTimeline(long lastTweetId) {
        if (!mNetworkUtil.isNetworkAvailable()) {
            List<TweetModel> tweetModelList = TweetModel.byUser(mScreenName);
            addItemsFromDB(tweetModelList);
        }
        mClient.getUserTimeline(mScreenName, lastTweetId, new JsonHttpResponseHandler() {
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
