package com.codepath.apps.restclienttemplate.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.network.TwitterClient;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener {

    private final int REPLY_TWEET_REQUEST = 1;
    @Inject TwitterClient mClient;
    private Context mContext;
    private User mUser;

    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.tvUserName) TextView tvUserName;
    @BindView(R.id.tvTagLine) TextView tvTagLine;
    @BindView(R.id.tvFollowers) TextView tvFollowers;
    @BindView(R.id.tvFollowing) TextView tvFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        ((TwitterApp) getApplication()).getTwitterComponent().inject(this);

        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mUser = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        toolbar.setTitle("@" + mUser.screenName);
        UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(mUser.screenName);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flContainer, userTimelineFragment);
        ft.commit();

        Glide.with(mContext).load(mUser.profileImageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfileImage);
        tvUserName.setText(mUser.name);
        tvTagLine.setText(mUser.description);
        String followersCount = String.valueOf(mUser.followers) + " followers";
        String followingCount = String.valueOf(mUser.following) + " following";
        tvFollowers.setText(followersCount);
        tvFollowing.setText(followingCount);
    }

    @Override
    public void onTweetSelected(Tweet tweet) {
        Intent i = new Intent(this, TweetActivity.class);
        i.putExtra("tweet", Parcels.wrap(tweet));
        startActivityForResult(i, REPLY_TWEET_REQUEST);
    }
}
