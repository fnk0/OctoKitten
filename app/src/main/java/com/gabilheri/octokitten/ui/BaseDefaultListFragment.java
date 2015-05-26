package com.gabilheri.octokitten.ui;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class BaseDefaultListFragment extends DefaultListFragment<MainComponent> {

    @Override
    protected void inject(MainComponent component) {
        component.inject(this);
    }
}
