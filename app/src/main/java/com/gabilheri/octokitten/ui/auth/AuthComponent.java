package com.gabilheri.octokitten.ui.auth;

import com.gabilheri.octokitten.app.ApplicationComponent;
import com.gabilheri.octokitten.ui.ActivityScope;

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
                AuthModule.class
        }
)
public interface AuthComponent {

    Timber.Tree tree();

    void inject(SignInActivity activity);
    void inject(SignInFragment fragment);

}
