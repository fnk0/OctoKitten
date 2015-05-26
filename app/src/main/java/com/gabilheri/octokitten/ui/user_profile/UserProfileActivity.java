package com.gabilheri.octokitten.ui.user_profile;

import android.os.Bundle;

import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.ui.BaseMainActivity;
import com.gabilheri.octokitten.utils.Preferences;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class UserProfileActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user = getIntent().getExtras().getString(EXTRA_LOGIN);

        if(user == null) {
            setTitle(PrefManager.with(this).getString(Preferences.USERNAME, ""));
        }
        UserProfileFragment profileFragment = new UserProfileFragment();
        profileFragment.setArguments(getIntent().getExtras());
        addFragmentToContainer(profileFragment, null);
    }
}
