package com.gabilheri.octokitten.ui.user_profile;

import android.os.Bundle;
import android.view.View;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.DefaultFragment;
import com.gabilheri.octokitten.ui.MainComponent;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class UserProfileFragment extends DefaultFragment<MainComponent> {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void inject(MainComponent component) {
        component.inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_profile;
    }
}
