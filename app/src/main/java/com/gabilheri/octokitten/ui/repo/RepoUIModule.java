package com.gabilheri.octokitten.ui.repo;

import dagger.Module;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@Module
public class RepoUIModule {

    BaseRepoActivity baseRepoActivity;

    public RepoUIModule(BaseRepoActivity baseRepoActivity) {
        this.baseRepoActivity = baseRepoActivity;
    }
}
