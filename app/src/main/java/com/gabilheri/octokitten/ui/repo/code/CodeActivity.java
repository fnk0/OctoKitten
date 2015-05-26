package com.gabilheri.octokitten.ui.repo.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.gabilheri.octokitten.ui.repo.BaseRepoActivity;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/23/15.
 */
public class CodeActivity extends BaseRepoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras().getBundle(Intent.EXTRA_INTENT);
        SourceCodeFragment codeFragment = new SourceCodeFragment();
        codeFragment.setArguments(b);
        setTitle(b.getString("title"));
        addFragmentToContainer(codeFragment, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
