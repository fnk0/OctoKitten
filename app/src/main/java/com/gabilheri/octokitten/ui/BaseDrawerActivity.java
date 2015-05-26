package com.gabilheri.octokitten.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.ui.about.AboutActivity;
import com.gabilheri.octokitten.ui.auth.SignInActivity;
import com.gabilheri.octokitten.ui.helpers.GithubFont;
import com.gabilheri.octokitten.ui.main.MainActivity;
import com.gabilheri.octokitten.ui.newsfeed.NewsFeedActivity;
import com.gabilheri.octokitten.ui.settings.SettingsActivity;
import com.gabilheri.octokitten.ui.starred.StarredActivity;
import com.gabilheri.octokitten.ui.user_profile.UserProfileActivity;
import com.gabilheri.octokitten.utils.Preferences;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.Random;

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

    protected int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(com.mikepenz.materialdrawer.R.style.MaterialDrawerTheme_Light_DarkToolbar_TranslucentStatus);

        String username = PrefManager.with(this).getString(Preferences.USERNAME, "");

        IProfile profile = new ProfileDrawerItem()
                .withName(username)
                .withEmail(PrefManager.with(this).getString(Preferences.EMAIL, ""))
                .withIcon(PrefManager.with(this).getString(Preferences.AVATAR_URL, "https://github.com/identicons/octokitten.png"));

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

        currentItem = getIntent().getExtras().getInt(EXTRA_CURRENT);

        final boolean isSignedIn = !username.equals("");

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
                        new PrimaryDrawerItem().withName(isSignedIn ? "Sign In" : "Sign Out").withIcon(GithubFont.Icon.gh_sign_in).withIdentifier(SIGN_IN_OUT)
                )
                .withAccountHeader(header)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, IDrawerItem iDrawerItem) {

                        if (iDrawerItem.getIdentifier() == currentItem) {
                            return;
                        }

                        Intent i = null;
                        switch (iDrawerItem.getIdentifier()) {
                            case NEWS:
                                i = new Intent(getBaseContext(), NewsFeedActivity.class);
                                break;
                            case MY_REPOS:
                                i = new Intent(getBaseContext(), MainActivity.class);
                                break;
                            case STARRED:
                                i = new Intent(getBaseContext(), StarredActivity.class);
                                break;
                            case PROFILE:
                                i = new Intent(getBaseContext(), UserProfileActivity.class);
                                break;
                            case SETTINGS:
                                i = new Intent(getBaseContext(), SettingsActivity.class);
                                break;
                            case ABOUT:
                                i = new Intent(getBaseContext(), AboutActivity.class);
                                break;
                            case SIGN_IN_OUT:
                                if (!isSignedIn) {
                                    i = new Intent(getBaseContext(), SignInActivity.class);
                                }

                                break;
                        }
                        if (i != null) {
                            i.putExtra(EXTRA_CURRENT, iDrawerItem.getIdentifier());
                            startActivity(i);
                        }


                    }
                })
                .build();
    }
}
