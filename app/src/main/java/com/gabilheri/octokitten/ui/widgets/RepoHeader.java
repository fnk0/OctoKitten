package com.gabilheri.octokitten.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.octokitten.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/24/15.
 */
public class RepoHeader extends LinearLayout {

    @InjectView(R.id.stars_count)
    TextView starsCount;

    @InjectView(R.id.forks_count)
    TextView forksCount;

    @InjectView(R.id.watchers_count)
    TextView watchersCount;

    @InjectView(R.id.repo_name)
    TextView tvRepoName;

    @InjectView(R.id.repo_description)
    TextView tvRepoDescription;

    int stars, forks, watchers;
    String repoName, repoDescription;

    public RepoHeader(Context context, int stars, int forks, int watchers, String repoName, String repoDescription) {
        super(context);
        this.stars = stars;
        this.forks = forks;
        this.watchers = watchers;
        this.repoName = repoName;
        this.repoDescription = repoDescription;
        init();
    }

    public RepoHeader(Context context) {
        super(context);
        init();
    }

    public RepoHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepoHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.repo_header, this);
        ButterKnife.inject(this);
        if(repoName != null && tvRepoName != null) {
            tvRepoName.setText(repoName);
            tvRepoDescription.setText(repoDescription);
            starsCount.setText("" + stars);
            forksCount.setText("" + forks);
            watchersCount.setText("" + watchers);
        }


    }
}
