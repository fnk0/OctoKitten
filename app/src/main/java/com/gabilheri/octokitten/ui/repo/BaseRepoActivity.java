package com.gabilheri.octokitten.ui.repo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.BaseActivity;
import com.gabilheri.octokitten.ui.helpers.GithubFont;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class BaseRepoActivity extends BaseActivity<RepoComponent> {

    protected Drawer.Result drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        ButterKnife.inject(this);
        enableBackNav();
        setTheme(com.mikepenz.materialdrawer.R.style.MaterialDrawerTheme_Light_DarkToolbar_TranslucentStatus);
        drawer = new Drawer()
                .withActivity(this)
                .withDrawerGravity(Gravity.END)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Dashboard").withIcon(GithubFont.Icon.gh_dashboard),
                        new PrimaryDrawerItem().withName("Readme").withIcon(GithubFont.Icon.gh_readme),
                        new PrimaryDrawerItem().withName("Code").withIcon(GithubFont.Icon.gh_code),
                        new PrimaryDrawerItem().withName("Commits").withIcon(GithubFont.Icon.gh_git_commit),
                        new PrimaryDrawerItem().withName("Issues").withIcon(GithubFont.Icon.gh_issues),
                        new PrimaryDrawerItem().withName("Contributors").withIcon(GithubFont.Icon.gh_people),
                        new PrimaryDrawerItem().withName("Settings").withIcon(GithubFont.Icon.gh_tools)
                )
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_drawer:
                if(drawer != null && !drawer.isDrawerOpen()) {
                    drawer.openDrawer();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected RepoComponent buildComponent() {
        return DaggerRepoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .repoUIModule(new RepoUIModule(this))
                .build();
    }
}
