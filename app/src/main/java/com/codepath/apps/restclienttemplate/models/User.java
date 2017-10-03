package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;
    public int followers;
    public int following;
    public String description;

    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");
        user.followers = json.getInt("followers_count");
        user.following = json.getInt("friends_count");
        user.description = json.getString("description");
        return user;
    }

    public static User fromDB(TweetModel tweetModel) {
        User user = new User();

        user.name = tweetModel.getUserName();
        user.uid = tweetModel.getUserUid();
        user.screenName = tweetModel.getScreenName();
        user.profileImageUrl = tweetModel.getProfileImageUrl();
        user.followers = tweetModel.getFollowers();
        user.following = tweetModel.getFollowing();
        user.description = tweetModel.getDescription();
        return user;
    }
}
