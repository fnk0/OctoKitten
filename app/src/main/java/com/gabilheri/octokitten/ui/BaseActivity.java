package com.gabilheri.octokitten.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.ApplicationComponent;
import com.gabilheri.octokitten.app.HasComponent;
import com.gabilheri.octokitten.app.OctoKittenApp;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import hugo.weaving.DebugLog;
import icepick.Icepick;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public abstract class BaseActivity<T> extends AppCompatActivity implements HasComponent<T> {

    public static final String EXTRA_CURRENT = "extra_current";
    public static final String EXTRA_LOGIN = "extra_login";

    @Inject
    protected Timber.Tree logger;

    @Inject
    protected Bus bus;

    protected final FragmentManager fragmentManager = this.getFragmentManager();

    private T component;

    @Optional
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Optional
    @InjectView(R.id.container)
    FrameLayout containerLayout;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = buildComponent();
        setContentView(getLayoutResource());
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Override
    public T getComponent() {
        return component;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((OctoKittenApp) getApplication()).getComponent();
    }

    protected void enableBackNav() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public void setTitle(String title) {
        if(toolbar != null) {
            toolbar.setTitle(title);
        }
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void addFragmentToContainer(Fragment fragment, @Nullable String backStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack(backStack).commit();
    }

    public void addFragmentToContainer(Fragment fragment, String fragmentName, @Nullable String backStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragmentName).addToBackStack(backStack).commit();
    }


    public int getLayoutResource() {
        return R.layout.activity_base;
    }

    protected abstract T buildComponent();
}
