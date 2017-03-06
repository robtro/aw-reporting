// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.adwords.awreporting.proxy;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * Specialized {@code JaxWsProxySelector} for Jax-Ws.
 *
 * Jax-Ws does not load the default java Proxy parameters unless a ProxySelector is provided.
 */
public class JaxWsProxySelector extends ProxySelector {

  private static final String PROXY_HOST = "proxyHost";
  private static final String PROXY_PORT = "proxyPort";
  private static final String PROXY_USER = "proxyUser";
  private static final String PROXY_PASSWORD = "proxyPassword";

  private static final String SOCKS_PROXY_HOST = "socksProxyHost";
  private static final String SOCKS_PROXY_PORT = "socksProxyPort";
  private static final String SOCKS_PROXY_USER = "socksProxyHost";
  private static final String SOCKS_PROXY_PASSWORD = "socksProxyPassword";
  
  private static final Set<String> SUPPORTED_SCHEMES = Sets.newHashSet("http", "https", "ftp");

  private final ProxySelector delegate;

  private final Properties proxyProperties;
  
  public JaxWsProxySelector(@Nullable ProxySelector proxySelector, Properties properties) {
    delegate = proxySelector;
    this.proxyProperties = Preconditions.checkNotNull(properties, 
        "A valid system properties is required.");
  }

  public JaxWsProxySelector(@Nullable ProxySelector proxySelector) {
    this(proxySelector, System.getProperties());
  }
  
  /**
   * Selects the Proxies available to do the user authentication.
   */
  @Override
  public List<Proxy> select(URI uri) {
    List<Proxy> list = Lists.newArrayList();

    if (uri == null) {
      throw new IllegalArgumentException("URI can't be null.");
    }

    String scheme = uri.getScheme().toLowerCase();
    
    if (SUPPORTED_SCHEMES.contains(scheme)){
      Proxy proxy = getProxy(scheme, proxyProperties.getProperty(scheme + "." + PROXY_HOST),
          proxyProperties.getProperty(scheme + "." + PROXY_PORT), 
          proxyProperties.getProperty(scheme + "." + PROXY_USER));
      
      if (proxy != null){
        list.add(proxy);
      }
    }
    
    if (scheme.startsWith("http") || "ftp".equals(scheme) || scheme.startsWith("socks")){
      Proxy proxy = getProxy("socks", proxyProperties.getProperty(SOCKS_PROXY_HOST),
          proxyProperties.getProperty(SOCKS_PROXY_PORT), 
          proxyProperties.getProperty(SOCKS_PROXY_USER));
      
      if (proxy != null){
        list.add(proxy);
      }
    }

    if (list.size() > 0) {
      return list;
    }
    return delegateSelect(uri);
  }
  
  /**
   * Create {@code Proxy} based on the scheme, host, port and password and set the default
   * {@code Authenticator}.
   */
  private Proxy getProxy(String scheme, String proxyHost, String proxyPort, String proxyUser){
    if (!Strings.isNullOrEmpty(proxyHost) && !Strings.isNullOrEmpty(proxyPort)){
      if (!Strings.isNullOrEmpty(proxyUser)){
        Authenticator.setDefault(new JaxWsProxyAuthenticator(scheme, proxyProperties));
      }
      
      if ("socks".equals(scheme)) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost,
            Integer.parseInt(proxyPort)));
      } else {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, 
            Integer.parseInt(proxyPort)));
      }
    }
    return null;
  }

  /**
   * Delegates the select to the embedded selector.
   *
   * @param uri the {@code URI}.
   * @return the list of proxies.
   */
  private List<Proxy> delegateSelect(URI uri) {
    if (delegate != null) {
      return delegate.select(uri);
    }
    
    return Arrays.asList(Proxy.NO_PROXY);
  }

  /**
   * Handles the connection failing when connecting to the Proxy. This method does not handle
   * selecting or reordering proxies. It simply checks parameters for null.
   */
  @Override
  public void connectFailed(URI uri, SocketAddress socketAddress, IOException ioException) {
    Preconditions.checkNotNull(uri, "Uri can not be null");
    Preconditions.checkNotNull(socketAddress, "SocketAddress can not be null");
    Preconditions.checkNotNull(ioException, "IOException can not be null");
  }

  /**
   * {@code Authenticator} to set username and password used in the proxy authentication.
   */
  private static final class JaxWsProxyAuthenticator extends Authenticator {
    
    private final String scheme;

    private final Properties systemProperties;
    
    public JaxWsProxyAuthenticator(String scheme, Properties properties) {
      this.scheme = scheme;
      this.systemProperties = Preconditions.checkNotNull(properties, 
          "A valid system properties is required.");
    }
    
    /**
     * Does the authentication against the Proxy providing the username and the password.
     */
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
      if (SUPPORTED_SCHEMES.contains(scheme)){
        if (!Strings.isNullOrEmpty(scheme + "." + PROXY_USER)){
          return new PasswordAuthentication(systemProperties.getProperty(scheme + "." + PROXY_USER),
              systemProperties.getProperty(scheme + "." + PROXY_PASSWORD).toCharArray());
        }
      }
      
      return new PasswordAuthentication(systemProperties.getProperty(SOCKS_PROXY_USER),
          systemProperties.getProperty(SOCKS_PROXY_PASSWORD).toCharArray());
    }
  }
}
