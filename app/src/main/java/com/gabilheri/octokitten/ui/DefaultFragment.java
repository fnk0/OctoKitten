package com.gabilheri.octokitten.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.HasComponent;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 1/27/15.
 */
public abstract class DefaultFragment<T> extends Fragment {

    protected static final String LOG_TAG = DefaultFragment.class.getSimpleName();
    protected static final boolean DEBUG = true;
    protected CompositeSubscription mCompositeSubscription;
    protected boolean unsubscribe = true;

    @Inject
    protected Timber.Tree logger;

    @Inject
    protected Bus bus;

    @Inject
    protected GithubService service;

    public DefaultFragment() {
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.beginTransaction().remove(this).commit();
        }
        super.onInflate(activity, attrs, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(hasOptionsMenu());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        attemptInject(activity);
    }

    private void attemptInject(Activity activity) {
        if(activity instanceof HasComponent) {
            @SuppressWarnings("unchecked")
            HasComponent<T> hasComponent = (HasComponent<T>) activity;
            T component = hasComponent.getComponent();
            inject(component);
        }
    }

    protected abstract void inject(T component);

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(unsubscribe) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     *
     * @return
     *      The R.layout.id of the layout used by this fragment
     */
    public int getLayoutResource() {
        return R.layout.default_fragment;
    }

    public boolean hasOptionsMenu() {
        return false;
    }
}
