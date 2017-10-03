// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.codepath.apps.restclienttemplate.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComposeActivity_ViewBinding<T extends ComposeActivity> implements Unbinder {
  protected T target;

  private View view2131493000;

  private TextWatcher view2131493000TextWatcher;

  private View view2131493001;

  @UiThread
  public ComposeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.etComposeTweet, "field 'etComposeTweet' and method 'onComposeTweetTextChanged'");
    target.etComposeTweet = Utils.castView(view, R.id.etComposeTweet, "field 'etComposeTweet'", EditText.class);
    view2131493000 = view;
    view2131493000TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onComposeTweetTextChanged();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131493000TextWatcher);
    view = Utils.findRequiredView(source, R.id.btnTweet, "field 'btnTweet' and method 'onTweetButtonClicked'");
    target.btnTweet = Utils.castView(view, R.id.btnTweet, "field 'btnTweet'", Button.class);
    view2131493001 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onTweetButtonClicked();
      }
    });
    target.tvTweetSize = Utils.findRequiredViewAsType(source, R.id.tvTweetSize, "field 'tvTweetSize'", TextView.class);
    target.ivUseImage = Utils.findRequiredViewAsType(source, R.id.ivUseImage, "field 'ivUseImage'", ImageView.class);
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tvUserName, "field 'tvUserName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etComposeTweet = null;
    target.btnTweet = null;
    target.tvTweetSize = null;
    target.ivUseImage = null;
    target.tvUserName = null;

    ((TextView) view2131493000).removeTextChangedListener(view2131493000TextWatcher);
    view2131493000TextWatcher = null;
    view2131493000 = null;
    view2131493001.setOnClickListener(null);
    view2131493001 = null;

    this.target = null;
  }
}
