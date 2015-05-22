package com.gabilheri.octokitten.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.auth.SignInActivity;
import com.gabilheri.octokitten.ui.helpers.GithubFont;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.Random;

import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public abstract class BaseDrawerActivity<T> extends BaseActivity<T> {

    Drawer.Result drawer;

    protected static final int NEWS = 0;
    protected static final int MY_REPOS = 1;
    protected static final int STARRED = 2;
    protected static final int PROFILE = 3;
    protected static final int ABOUT = 4;
    protected static final int SETTINGS = 5;
    protected static final int SIGN_IN_OUT = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(com.mikepenz.materialdrawer.R.style.MaterialDrawerTheme_Light_DarkToolbar_TranslucentStatus);
        IProfile profile = new ProfileDrawerItem()
                .withName("Marcus Gabilheri")
                .withEmail("marcusandreog@gmail.com")
                .withIcon("https://avatars3.githubusercontent.com/u/4286642?v=3&s=460");

        int[] headers = {
                R.drawable.drawer_wall1,
                R.drawable.drawer_wall2,
                R.drawable.drawer_wall3,
                R.drawable.drawer_wall4,
                R.drawable.drawer_wall5
        };

        int drawerResource = headers[new Random().nextInt(4)];

        AccountHeader.Result header = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(drawerResource)
                .addProfiles(profile)
                .build();

        drawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("News").withIcon(GithubFont.Icon.gh_news).withIdentifier(NEWS),
                        new PrimaryDrawerItem().withName("My Repositories").withIcon(GithubFont.Icon.gh_repo).withIdentifier(MY_REPOS),
                        new PrimaryDrawerItem().withName("Starred").withIcon(GithubFont.Icon.gh_star).withIdentifier(STARRED),
                        new PrimaryDrawerItem().withName("Profile").withIcon(GithubFont.Icon.gh_octicon_face).withIdentifier(PROFILE)
                )
                .withStickyFooterDivider(true)
                .addStickyDrawerItems(
                        new PrimaryDrawerItem().withName("About").withIcon(GithubFont.Icon.gh_info).withIdentifier(ABOUT),
                        new PrimaryDrawerItem().withName("Settings").withIcon(GithubFont.Icon.gh_settings).withIdentifier(SETTINGS),
                        new PrimaryDrawerItem().withName("Sign In").withIcon(GithubFont.Icon.gh_sign_in).withIdentifier(SIGN_IN_OUT)
                )
                .withAccountHeader(header)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, IDrawerItem iDrawerItem) {
                        switch (iDrawerItem.getIdentifier()) {
                            case NEWS:
                                Timber.i("News");
                                break;
                            case MY_REPOS:
                                Timber.i("My Repos");
                                break;
                            case STARRED:
                                Timber.i("Starred");
                                break;
                            case PROFILE:
                                Timber.i("Profile");
                                break;
                            case SETTINGS:
                                Timber.i("Settings");
                                break;
                            case ABOUT:
                                Timber.i("About");
                                break;
                            case SIGN_IN_OUT:
                                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                                startActivity(i);
                                break;

                        }

                    }
                })
                .build();
    }
}
