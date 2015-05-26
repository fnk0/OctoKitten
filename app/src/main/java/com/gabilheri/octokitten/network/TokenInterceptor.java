package com.gabilheri.octokitten.network;

import android.content.Context;

import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.utils.Preferences;

import retrofit.RequestInterceptor;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/14/15.
 */
public class TokenInterceptor implements RequestInterceptor {

    private Context context;

    public TokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "OctoKitten for GitHub");
        request.addHeader("Accept", "application/vnd.github.v3+json");

        String token = PrefManager.with(context).getString(Preferences.AUTH_TOKEN, null);
        if(token != null) {
            request.addHeader("Authorization", "Token " + token);
        }

    }
}
