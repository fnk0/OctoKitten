package com.gabilheri.octokitten.network;

import android.content.Context;

import com.gabilheri.octokitten.data.api.github.GithubService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/15.
 */

public class GithubClient {

    public static final String API_URL = "https://api.github.com";
    public static final String CLIENT_SECRET = "793dc35ab593704de8e6d689a7ab53b5ec5afbf7";
    public static final String CLIENT_ID = "4fc2b3dbccbff4c4ce40";

    private RestAdapter restAdapter;

    public GithubClient(boolean debug, RequestInterceptor interceptor, Context context) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setRequestInterceptor(interceptor == null ? new BaseInterceptor() : interceptor)
                .setLogLevel(debug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setClient(new OkClient())
                .build();
    }

    public GithubClient(OkClient client, RequestInterceptor interceptor) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(client)
                .setRequestInterceptor(interceptor == null ? new BaseInterceptor() : interceptor)
                .build();
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public <T> T createService(Class<T> service) {
        return restAdapter.create(service);
    }

    public GithubService createGithubService() {
        return createService(GithubService.class);
    }
}


