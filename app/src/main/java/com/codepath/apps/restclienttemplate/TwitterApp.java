package com.codepath.apps.restclienttemplate;

import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.TypefaceUtil;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.app.Application;
import android.content.Context;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     TwitterClient client = TwitterApp.getRestClient();
 *     // use client to send requests to API
 *
 */
public class TwitterApp extends Application {
	private static Context context;
	private TwitterComponent mTwitterComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		mTwitterComponent = DaggerTwitterComponent.builder()
			.appModule(new AppModule(this))
			.twitterModule(new TwitterModule(this))
			.build();

		FlowManager.init(new FlowConfig.Builder(this).build());
		FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
		TypefaceUtil.overrideFont(getApplicationContext(), "SERIF",
			"helvetica-neue-bold.ttf");
		TwitterApp.context = this;
	}

	public static TwitterClient getRestClient() {
		return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, TwitterApp.context);
	}

	public TwitterComponent getTwitterComponent() {
		return mTwitterComponent;
	}
}