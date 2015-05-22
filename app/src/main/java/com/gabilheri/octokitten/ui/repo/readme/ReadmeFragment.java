package com.gabilheri.octokitten.ui.repo.readme;

import com.gabilheri.octokitten.ui.DefaultFragment;
import com.gabilheri.octokitten.ui.repo.RepoComponent;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class ReadmeFragment extends DefaultFragment<RepoComponent> {

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }
}
