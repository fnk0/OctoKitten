package com.gabilheri.octokitten.ui;

import android.view.Menu;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class BaseMainActivity extends BaseDrawerActivity<MainComponent> {

    @Override
    protected MainComponent buildComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainUIModule(new MainUIModule(this))
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
