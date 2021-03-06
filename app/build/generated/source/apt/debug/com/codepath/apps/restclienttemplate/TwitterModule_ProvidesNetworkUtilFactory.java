// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.codepath.apps.restclienttemplate;

import com.codepath.apps.restclienttemplate.utils.NetworkUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TwitterModule_ProvidesNetworkUtilFactory implements Factory<NetworkUtil> {
  private final TwitterModule module;

  public TwitterModule_ProvidesNetworkUtilFactory(TwitterModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public NetworkUtil get() {
    return Preconditions.checkNotNull(
        module.providesNetworkUtil(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<NetworkUtil> create(TwitterModule module) {
    return new TwitterModule_ProvidesNetworkUtilFactory(module);
  }

  /** Proxies {@link TwitterModule#providesNetworkUtil()}. */
  public static NetworkUtil proxyProvidesNetworkUtil(TwitterModule instance) {
    return instance.providesNetworkUtil();
  }
}
