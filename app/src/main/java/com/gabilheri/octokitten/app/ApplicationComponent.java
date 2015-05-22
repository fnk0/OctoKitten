package com.gabilheri.octokitten.app;

import android.app.Application;

import com.gabilheri.octokitten.data.DataModule;
import com.gabilheri.octokitten.data.GithubModule;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.client.Client;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        BusModule.class,
        DataModule.class,
        LoggerModule.class,
        GithubModule.class
})
public interface ApplicationComponent {

    Application application();

    Bus bus();

    Timber.Tree tree();

    Client client();

    GithubService githubService();

    void inject(OctoKittenApp app);
}
