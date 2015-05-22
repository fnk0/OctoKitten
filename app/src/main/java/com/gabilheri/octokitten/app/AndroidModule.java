package com.gabilheri.octokitten.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Module
public class AndroidModule {

    private final OctoKittenApp application;

    public AndroidModule(OctoKittenApp application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application application() {
        return application;
    }
}
