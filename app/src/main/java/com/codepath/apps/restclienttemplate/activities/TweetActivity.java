package com.codepath.apps.restclienttemplate.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.DateTimeUtil;
import com.codepath.apps.restclienttemplate.utils.NetworkUtil;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import javax.inject.Inject;

public class TweetActivity extends AppCompatActivity {

    @Inject TwitterClient mClient;
    @Inject NetworkUtil mNetworkUtil;

    @BindView(R.id.ivUserImage) ImageView ivUserImage;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvTweetTimeAgo) TextView tvTweetTimeAgo;
    @BindView(R.id.tvTweetBody) TextView tvTweetBody;
    @BindView(R.id.btnReply) Button btnReply;
    @BindView(R.id.etComposeReply) EditText etComposeReply;
    @BindView(R.id.btnSendReply) Button btnSendReply;

    Tweet mTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tweet);

        ((TwitterApp) getApplication()).getTwitterComponent().inject(this);
        ButterKnife.bind(this);

        mTweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvName.setText(mTweet.user.screenName);
        tvTweetBody.setText(mTweet.body);
        String relativeTime = new DateTimeUtil().getRelativeTimeAgo(mTweet.createdAt);
        tvTweetTimeAgo.setText(relativeTime);

        Glide.with(this).load(mTweet.user.profileImageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(ivUserImage);
    }

    @OnClick(R.id.btnReply)
    public void onReplyClicked() {
        btnReply.setVisibility(View.INVISIBLE);
        etComposeReply.setVisibility(View.VISIBLE);
        btnSendReply.setVisibility(View.VISIBLE);
        String replyString = "@" + mTweet.user.screenName + " ";
        etComposeReply.setText(replyString);
    }

    @OnClick(R.id.btnSendReply)
    public void onSendTweetClicked() {
        final String tweet = etComposeReply.getText().toString();
        if (tweet.length() > 0) {
            final Intent returnIntent = new Intent();
            returnIntent.putExtra("result", tweet);
            returnIntent.putExtra("uid", mTweet.uid);
            setResult(Activity.RESULT_OK, returnIntent);
        } else {
            setResult(Activity.RESULT_CANCELED, null);
        }
        finish();
    }

    @OnClick(R.id.ivUserImage)
    public void onIconClick() {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("user", Parcels.wrap(mTweet.user));
        startActivity(i);
    }
}
