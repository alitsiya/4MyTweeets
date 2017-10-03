// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.codepath.apps.restclienttemplate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TweetActivity_ViewBinding<T extends TweetActivity> implements Unbinder {
  protected T target;

  private View view2131492994;

  private View view2131492996;

  @UiThread
  public TweetActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.ivUserImage = Utils.findRequiredViewAsType(source, R.id.ivUserImage, "field 'ivUserImage'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvTweetTimeAgo = Utils.findRequiredViewAsType(source, R.id.tvTweetTimeAgo, "field 'tvTweetTimeAgo'", TextView.class);
    target.tvTweetBody = Utils.findRequiredViewAsType(source, R.id.tvTweetBody, "field 'tvTweetBody'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnReply, "field 'btnReply' and method 'onReplyClicked'");
    target.btnReply = Utils.castView(view, R.id.btnReply, "field 'btnReply'", Button.class);
    view2131492994 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onReplyClicked();
      }
    });
    target.etComposeReply = Utils.findRequiredViewAsType(source, R.id.etComposeReply, "field 'etComposeReply'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnSendReply, "field 'btnSendReply' and method 'onSendTweetClicked'");
    target.btnSendReply = Utils.castView(view, R.id.btnSendReply, "field 'btnSendReply'", Button.class);
    view2131492996 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendTweetClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivUserImage = null;
    target.tvName = null;
    target.tvTweetTimeAgo = null;
    target.tvTweetBody = null;
    target.btnReply = null;
    target.etComposeReply = null;
    target.btnSendReply = null;

    view2131492994.setOnClickListener(null);
    view2131492994 = null;
    view2131492996.setOnClickListener(null);
    view2131492996 = null;

    this.target = null;
  }
}
