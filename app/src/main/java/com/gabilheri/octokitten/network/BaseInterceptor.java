package com.gabilheri.octokitten.network;

import retrofit.RequestInterceptor;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/14/15.
 */
public class BaseInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Accept", "application/vnd.github.v3+json");
        request.addHeader("User-Agent", "OctoKitten for GitHub");
    }
}
