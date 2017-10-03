package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import javax.inject.Inject;

public class MentionsTimelineFragment extends TweetsListFragment {

    @Inject TwitterClient mClient;
    @Inject SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateMentionsTimeline(0L);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TwitterApp) context.getApplicationContext()).getTwitterComponent().inject(this);;
    }

    private void populateMentionsTimeline(long sinceId) {
        if (!mNetworkUtil.isNetworkAvailable()) {
            //populate timeline from DB
//            addItemsFromDB();
        }
        mClient.getMentionsTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // if got response delete data in DB to refresh
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
        }, sinceId);
    }
}