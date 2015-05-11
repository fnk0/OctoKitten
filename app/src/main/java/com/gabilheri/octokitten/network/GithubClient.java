package com.gabilheri.octokitten.network;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/15.
 */
public class GithubClient {

    public static final String API_URL = "https://api.github.com";

    private RestAdapter restAdapter;

    public GithubClient(boolean debug) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/vnd.github.v3+json");
                        request.addHeader("User-Agent", "OctoKitten for GitHub");
                    }
                })
                .setLogLevel(debug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public <T> T createService(Class<T> service) {
        return restAdapter.create(service);
    }
}


