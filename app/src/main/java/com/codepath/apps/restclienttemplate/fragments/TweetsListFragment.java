package com.codepath.apps.restclienttemplate.fragments;

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
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    @Inject NetworkUtil mNetworkUtil;
    @Inject TwitterClient mClient;

    private TweetAdapter tweetAdapter;
    private ArrayList<Tweet> tweets;
    private RecyclerView rvTweets;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener mScrollListener;

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
        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(tweets, this);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvTweets.setLayoutManager(mLinearLayoutManager);
        rvTweets.setAdapter(tweetAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTweets.getContext(),
            DividerItemDecoration.VERTICAL);
        rvTweets.addItemDecoration(dividerItemDecoration);

        mScrollListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (mNetworkUtil.isNetworkAvailable()) {
                    populateHomeTimeline(tweets.get(tweets.size() - 1).uid);
                }
            }
        };
        rvTweets.addOnScrollListener(mScrollListener);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tweetAdapter.clear();
                populateHomeTimeline(0L);
                swipeContainer.setRefreshing(false);
            }
        });
        return v;
    }

    public void addItems(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(tweets.size() - 1);
                // Save TweetModel if not in DB
                //TODO
//                if (TweetModel.byId(tweet.uid) == null) {
//                    TweetModel tweetModel = new TweetModel(response.getJSONObject(i));
//                    tweetModel.save();
//                }
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
            // Save TweetModel to DB
            if (TweetModel.byId(tweet.uid) == null) {
                TweetModel tweetModel = new TweetModel(response);
                tweetModel.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addItemsFromDB() {
        List<TweetModel> tweetModelList = TweetModel.orderByDate();
        for (int i = 0; i < tweetModelList.size(); i++) {
            Tweet tweet = Tweet.fromDB(tweetModelList.get(i));
            tweets.add(tweet);
            tweetAdapter.notifyItemInserted(tweets.size() - 1);
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }

    private void populateHomeTimeline(long sinceId) {
        if (!mNetworkUtil.isNetworkAvailable()) {
            //populate timeline from DB
            addItemsFromDB();
        }
        mClient.getHomeTimeline(new JsonHttpResponseHandler() {
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