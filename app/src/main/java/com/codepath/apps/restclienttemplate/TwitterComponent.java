package com.codepath.apps.restclienttemplate;

import android.content.Context;

import com.codepath.apps.restclienttemplate.activities.ComposeActivity;
import com.codepath.apps.restclienttemplate.activities.LoginActivity;
import com.codepath.apps.restclienttemplate.activities.ProfileActivity;
import com.codepath.apps.restclienttemplate.activities.TimelineActivity;
import com.codepath.apps.restclienttemplate.activities.TweetActivity;
import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.MentionsTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.utils.NetworkUtil;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules={AppModule.class, TwitterModule.class})
public interface TwitterComponent {
    void inject(TimelineActivity timelineActivity);
    void inject(ProfileActivity profileActivity);
    void inject(ComposeActivity composeActivity);
    void inject(TweetActivity tweetActivity);
    void inject(LoginActivity loginActivity);
    void inject(HomeTimelineFragment homeTimelineFragment);
    void inject(MentionsTimelineFragment mentionsTimelineFragment);
    void inject(UserTimelineFragment userTimelineFragment);
}