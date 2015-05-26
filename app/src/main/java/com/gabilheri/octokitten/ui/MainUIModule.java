package com.gabilheri.octokitten.ui;

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

    private final BaseMainActivity activity;

    public MainUIModule(BaseMainActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseMainActivity provideActivity() {
        return activity;
    }
    
}
