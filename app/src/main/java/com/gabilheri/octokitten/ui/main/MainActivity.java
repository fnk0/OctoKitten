package com.gabilheri.octokitten.ui.main;

import android.os.Bundle;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.BaseDrawerActivity;

import butterknife.ButterKnife;


public class MainActivity extends BaseDrawerActivity<MainComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        ButterKnife.inject(this);
        setTitle(getString(R.string.app_name));
        addFragmentToContainer(new ReposFragment(), "repos_root");
    }

    @Override
    protected MainComponent buildComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainUIModule(new MainUIModule(this))
                .build();
    }
}
