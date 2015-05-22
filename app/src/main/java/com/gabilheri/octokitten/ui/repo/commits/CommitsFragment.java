package com.gabilheri.octokitten.ui.repo.commits;

import com.gabilheri.octokitten.ui.DefaultListFragment;
import com.gabilheri.octokitten.ui.repo.RepoComponent;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class CommitsFragment extends DefaultListFragment<RepoComponent> {

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }
}
