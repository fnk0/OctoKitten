package com.gabilheri.octokitten.ui.auth;

import dagger.Module;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Module
public class AuthModule {

    private final SignInActivity signInActivity;

    public AuthModule(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
    }
}
