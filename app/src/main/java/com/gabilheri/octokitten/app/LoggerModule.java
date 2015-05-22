package com.gabilheri.octokitten.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Module
public class LoggerModule {

    @Singleton
    @Provides
    Timber.Tree provideTimberTree() {
        Timber.plant(new Timber.DebugTree());
        return Timber.asTree();
    }
}
