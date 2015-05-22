package com.gabilheri.octokitten.ui.repo;

import com.gabilheri.octokitten.app.ApplicationComponent;
import com.gabilheri.octokitten.ui.ActivityScope;
import com.gabilheri.octokitten.ui.repo.code.SourceCodeFragment;
import com.gabilheri.octokitten.ui.repo.code.SourceCodeListFragment;
import com.gabilheri.octokitten.ui.repo.commits.CommitsFragment;
import com.gabilheri.octokitten.ui.repo.contributors.ContributorsFragment;
import com.gabilheri.octokitten.ui.repo.issues.IssuesFragment;
import com.gabilheri.octokitten.ui.repo.readme.ReadmeFragment;

import dagger.Component;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
            RepoUIModule.class
        }
)
public interface RepoComponent {

    void inject(BaseRepoActivity baseRepoActivity);
    void inject(IssuesFragment issuesFragment);
    void inject(SourceCodeListFragment sourceCodeListFragment);
    void inject(SourceCodeFragment sourceCodeFragment);
    void inject(WebViewFragment webViewFragment);
    void inject(CommitsFragment commitsFragment);
    void inject(ContributorsFragment contributorsFragment);
    void inject(ReadmeFragment readmeFragment);

}
