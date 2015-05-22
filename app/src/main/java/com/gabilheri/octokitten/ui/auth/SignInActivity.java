package com.gabilheri.octokitten.ui.auth;

import android.os.Bundle;

import com.gabilheri.octokitten.ui.BaseActivity;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class SignInActivity extends BaseActivity<AuthComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        enableBackNav();
        setTitle("Sign In");
        addFragmentToContainer(new SignInFragment(), "sign_in");
    }

    @Override
    protected AuthComponent buildComponent() {
        return DaggerAuthComponent.builder()
                .applicationComponent(getApplicationComponent())
                .authModule(new AuthModule(this))
                .build();
    }
}
