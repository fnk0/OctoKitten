package com.gabilheri.octokitten.activities;

import android.content.Intent;
import android.os.Bundle;

import com.gabilheri.octokitten.fragments.CommitsFragment;
import com.gabilheri.octokitten.fragments.ContributorsFragment;
import com.gabilheri.octokitten.fragments.IssuesFragment;
import com.gabilheri.octokitten.fragments.ReadmeFragment;
import com.gabilheri.octokitten.fragments.SourceCodeFragment;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class RepoActivity extends BasePagerActivity {

    public Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        enableBackNav();

        extras = getIntent().getExtras().getBundle(Intent.EXTRA_INTENT);
        String repoName = extras.getString("title");
        setTitle(repoName);

        String user = extras.getString("user");

        titleList.add("Readme");
        titleList.add("Code");
        titleList.add("Commits");
        titleList.add("Issues");
        titleList.add("Contributors");

        ReadmeFragment readmeFragment = new ReadmeFragment();
        readmeFragment.setArguments(extras);

        SourceCodeFragment codeFragment = new SourceCodeFragment();
        codeFragment.setArguments(extras);

        CommitsFragment commitsFragment = new CommitsFragment();
        IssuesFragment issuesFragment = new IssuesFragment();
        ContributorsFragment contributorsFragment = new ContributorsFragment();

        fragmentList.add(readmeFragment);
        fragmentList.add(codeFragment);
        fragmentList.add(commitsFragment);
        fragmentList.add(issuesFragment);
        fragmentList.add(contributorsFragment);

        initPager();
        setPagerNumber(1);

    }
}
