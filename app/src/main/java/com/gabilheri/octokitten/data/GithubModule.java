package com.gabilheri.octokitten.data;

import android.app.Application;

import com.gabilheri.octokitten.data.api.Api;
import com.gabilheri.octokitten.data.api.github.GithubService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Module
public class GithubModule {

    static final String API_URL = "https://api.github.com";

    @Inject
    Application app;

    @Singleton
    @Api("github")
    @Provides
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(API_URL);
    }

    @Singleton
    @Api("github")
    @Provides
    RestAdapter provideRestAdapter(Client client, @Api("github") Endpoint endpoint) {



        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .build();
    }

    @Singleton
    @Provides
    GithubService provideGitHubService(@Api("github") RestAdapter restAdapter) {
        return restAdapter.create(GithubService.class);
    }
}
