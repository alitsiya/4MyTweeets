// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.codepath.apps.restclienttemplate;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TwitterModule_ContextFactory implements Factory<Context> {
  private final TwitterModule module;

  public TwitterModule_ContextFactory(TwitterModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Context get() {
    return Preconditions.checkNotNull(
        module.context(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Context> create(TwitterModule module) {
    return new TwitterModule_ContextFactory(module);
  }
}