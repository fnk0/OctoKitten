package com.gabilheri.octokitten.ui.main;

import com.gabilheri.octokitten.app.ApplicationComponent;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.ui.ActivityScope;
import com.squareup.otto.Bus;

import dagger.Component;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                MainUIModule.class
        }
)
public interface MainComponent {

    GithubService githubService();

    Bus bus();

    Timber.Tree tree();

    void inject(MainActivity mainActivity);
    void inject(ReposFragment fragment);
}
