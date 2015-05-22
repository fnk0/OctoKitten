package com.gabilheri.octokitten.ui.main;

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
public class MainUIModule {

    private final MainActivity activity;

    public MainUIModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainActivity provideActivity() {
        return activity;
    }
    
}
