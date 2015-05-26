package com.gabilheri.octokitten.ui.repo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.BaseActivity;
import com.gabilheri.octokitten.ui.helpers.GithubFont;
import com.gabilheri.octokitten.ui.repo.commits.CommitsActivity;
import com.gabilheri.octokitten.ui.repo.contributors.ContributorsActivity;
import com.gabilheri.octokitten.ui.repo.issues.IssuesActivity;
import com.gabilheri.octokitten.ui.repo.readme.ReadmeActivity;
import com.gabilheri.octokitten.ui.repo.repo_settings.RepoSettingsActivity;
import com.gabilheri.octokitten.ui.widgets.RepoHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.ButterKnife;
import icepick.Icicle;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class BaseRepoActivity extends BaseActivity<RepoComponent> {

    public static final int REPO_DASHBOARD = 0;
    public static final int REPO_README = 1;
    public static final int REPO_CODE = 2;
    public static final int REPO_COMMITS = 3;
    public static final int REPO_ISSUES = 4;
    public static final int REPO_CONTRIBUTORS = 5;
    public static final int REPO_SETTINGS = 6;

    protected Drawer.Result drawer;

    @Icicle
    protected Bundle extras;

    protected int currentSelection = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        ButterKnife.inject(this);
        enableBackNav();

        extras = getIntent().getExtras().getBundle(Intent.EXTRA_INTENT);

        String title = extras.getString("title");
        int stars = extras.getInt("star_count");
        int forks = extras.getInt("fork_count");
        int watchers = extras.getInt("watchers_count");
        String description = extras.getString("description");

        currentSelection = getIntent().getExtras().getInt(EXTRA_CURRENT);

        setTheme(com.mikepenz.materialdrawer.R.style.MaterialDrawerTheme_Light_DarkToolbar_TranslucentStatus);
        drawer = new Drawer()
                .withActivity(this)
                .withDrawerGravity(Gravity.END)
                .withHeader(new RepoHeader(this, stars, forks, watchers, title, description))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Dashboard").withIcon(GithubFont.Icon.gh_dashboard).withIdentifier(REPO_DASHBOARD),
                        new PrimaryDrawerItem().withName("Readme").withIcon(GithubFont.Icon.gh_readme).withIdentifier(REPO_README),
                        new PrimaryDrawerItem().withName("Code").withIcon(GithubFont.Icon.gh_code).withIdentifier(REPO_CODE),
                        new PrimaryDrawerItem().withName("Commits").withIcon(GithubFont.Icon.gh_git_commit).withIdentifier(REPO_COMMITS),
                        new PrimaryDrawerItem().withName("Issues").withIcon(GithubFont.Icon.gh_issues).withIdentifier(REPO_ISSUES),
                        new PrimaryDrawerItem().withName("Contributors").withIcon(GithubFont.Icon.gh_people).withIdentifier(REPO_CONTRIBUTORS),
                        new PrimaryDrawerItem().withName("Settings").withIcon(GithubFont.Icon.gh_tools).withIdentifier(REPO_SETTINGS)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l, IDrawerItem iDrawerItem) {

                        if(iDrawerItem.getIdentifier() == currentSelection) {
                            return;
                        }

                        Intent i = null;
                        switch (iDrawerItem.getIdentifier()) {
                            case REPO_DASHBOARD: //TODO make a dashboard stuff....
                                break;
                            case REPO_README:
                                i = new Intent(getBaseContext(), ReadmeActivity.class);
                                break;
                            case REPO_CODE:
                                i = new Intent(getBaseContext(), ReposListActivity.class);
                                break;
                            case REPO_COMMITS:
                                i = new Intent(getBaseContext(), CommitsActivity.class);
                                break;
                            case REPO_ISSUES:
                                i = new Intent(getBaseContext(), IssuesActivity.class);
                                break;
                            case REPO_CONTRIBUTORS:
                                i = new Intent(getBaseContext(), ContributorsActivity.class);
                                break;
                            case REPO_SETTINGS:
                                i = new Intent(getBaseContext(), RepoSettingsActivity.class);
                                break;
                        }
                        if(i != null) {
                            i.putExtra(EXTRA_CURRENT, iDrawerItem.getIdentifier());
                            startActivity(i);
                        }
                    }
                })
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
            case android.R.id.home:
                super.onBackPressed();
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
