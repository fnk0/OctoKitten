package com.gabilheri.octokitten.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.repo.ReposListActivity;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.utils.CustomUtils;

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
    TextView repoIcon;

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
        repoIcon.setTypeface(CustomUtils.getGithubTypeface(this.getContext()));

        if(repo != null) {
            titleView.setText(repo.getName());
            subTitleView.setText(repo.getDescription());
            starCountView.setText("" + repo.getStargazersCount());

            if(repo.getFork()) {
                repoIcon.setText("\uf002");
            } else {
                repoIcon.setText("\uf001");
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

        Intent i = new Intent(getContext(), ReposListActivity.class);
        i.putExtra(Intent.EXTRA_INTENT, b);
        getContext().startActivity(i);
    }
}
