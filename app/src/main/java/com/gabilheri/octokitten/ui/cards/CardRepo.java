package com.gabilheri.octokitten.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.ui.repo.readme.ReadmeActivity;
import com.gabilheri.octokitten.ui.widgets.GithubTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/9/15.
 */
public class CardRepo extends Card implements Card.OnCardClickListener{

    private static final String LOG_TAG = CardRepo.class.getSimpleName();

    @InjectView(R.id.repo_title)
    TextView titleView;

    @InjectView(R.id.repo_subtitle)
    TextView subTitleView;

    @InjectView(R.id.stars_count)
    TextView starCountView;

    @InjectView(R.id.repo_icon)
    GithubTextView repoIcon;

    @InjectView(R.id.forks_count)
    TextView forksCountView;

    @InjectView(R.id.repo_private)
    GithubTextView repoPrivate;

    private Repo repo;

    public CardRepo(Context context, Repo repo) {
        super(context, R.layout.repo_card);
        this.repo = repo;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        ButterKnife.inject(this, view);

        if(repo != null) {
            titleView.setText(repo.getName());
            subTitleView.setText(repo.getDescription());
            starCountView.setText("" + repo.getStargazersCount());
            forksCountView.setText("" + repo.getForksCount());

            if(repo.getFork()) {
                repoIcon.setText("\uf002");
            } else {
                repoIcon.setText("\uf001");
            }

            if(repo.isPrivate()) {
                repoPrivate.setVisibility(TextView.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Log.i(LOG_TAG, repo.getName());

        Bundle b = new Bundle();
        String owner = repo.getOwner().getLogin();
        String url = "repos/" + owner + "/" + repo.getName() + "/contents";

        b.putString(getContext().getString(R.string.url), url);
        b.putString("user", owner);
        b.putString(getContext().getString(R.string.title), repo.getName());
        b.putInt("star_count", repo.getStargazersCount());
        b.putInt("fork_count", repo.getForksCount());
        b.putInt("watchers_count", repo.getWatchersCount());
        b.putString("description", repo.getDescription());

        Intent i = new Intent(getContext(), ReadmeActivity.class);
        i.putExtra(Intent.EXTRA_INTENT, b);
        getContext().startActivity(i);
    }
}
