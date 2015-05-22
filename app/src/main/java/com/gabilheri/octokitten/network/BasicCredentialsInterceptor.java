package com.gabilheri.octokitten.network;

import android.content.Context;

import com.squareup.okhttp.Credentials;

import retrofit.RequestInterceptor;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/14/15.
 */
public class BasicCredentialsInterceptor implements RequestInterceptor {

    private String encodedCredentials;
    private Context context;

    public BasicCredentialsInterceptor(String username, String password, Context context) {
        this.encodedCredentials = Credentials.basic(username, password);
        this.context = context;
    }

    public BasicCredentialsInterceptor(String encodedCredentials, Context context) {
        this.encodedCredentials = encodedCredentials;
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "OctoKitten for GitHub");
        request.addHeader("Accept", "application/vnd.github.v3+json");
        request.addHeader("Authorization", encodedCredentials);
    }
}
