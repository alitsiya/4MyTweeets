package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.adapters.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.TweetModel;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.codepath.apps.restclienttemplate.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    @Inject NetworkUtil mNetworkUtil;
    @Inject TwitterClient mClient;

    private ArrayList<Tweet> tweets = new ArrayList<>();
    private TweetAdapter tweetAdapter = new TweetAdapter(tweets, this);
    private RecyclerView rvTweets;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private Context mContext;

    public interface TweetSelectedListener {
        void onTweetSelected(Tweet tweet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        tweetAdapter = new TweetAdapter(tweets, this);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvTweets.setLayoutManager(mLinearLayoutManager);
        rvTweets.setAdapter(tweetAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTweets.getContext(),
            DividerItemDecoration.VERTICAL);
        rvTweets.addItemDecoration(dividerItemDecoration);

        mContext = getContext();
        mScrollListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (mNetworkUtil.isNetworkAvailable()) {
                    onScroll(tweets.get(tweets.size() - 1).uid);
                }
            }
        };
        rvTweets.addOnScrollListener(mScrollListener);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tweetAdapter.clear();
                swipeContainer.setRefreshing(false);
                onSwipeRefresh();
            }
        });
        return v;
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }

    public void onScroll(Long lastTweetId) {
    }

    public void onSwipeRefresh() {
    }

    public void addItems(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(tweets.size() - 1);
                mLinearLayoutManager.scrollToPositionWithOffset(0, 0);
                // Save TweetModel if not in DB
                if (TweetModel.byId(tweet.uid) == null) {
                    TweetModel tweetModel = new TweetModel(response.getJSONObject(i));
                    tweetModel.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void addItem(JSONObject response) {
        try {
            Tweet tweet = Tweet.fromJSON(response);
            tweets.add(0, tweet);
            tweetAdapter.notifyItemInserted(0);
            mLinearLayoutManager.scrollToPositionWithOffset(0, 0);
            // Save TweetModel if not in DB
            if (TweetModel.byId(tweet.uid) == null) {
                TweetModel tweetModel = new TweetModel(response);
                tweetModel.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addItemsFromDB(List<TweetModel> listOfTweets) {
        for (int i = 0; i < listOfTweets.size(); i++) {
            Tweet tweet = Tweet.fromDB(listOfTweets.get(i));
            tweets.add(tweet);
            tweetAdapter.notifyItemInserted(tweets.size() - 1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}