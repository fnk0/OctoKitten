package com.gabilheri.octokitten.ui.repo.code;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.network.TokenInterceptor;
import com.gabilheri.octokitten.ui.DefaultListFragment;
import com.gabilheri.octokitten.ui.cards.CardFileItem;
import com.gabilheri.octokitten.ui.repo.RepoComponent;
import com.gabilheri.octokitten.utils.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import it.gmariotti.cardslib.library.internal.Card;
import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class SourceCodeListFragment extends DefaultListFragment<RepoComponent> {

    Observable<List<RepoContent>> repos;

    protected List<RepoContent> contents = new ArrayList<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        Bundle b = getArguments();
        String token = PrefManager.with(getActivity()).getString(Preferences.AUTH_TOKEN, null);
        GithubService githubService = new GithubClient(true, token == null ? null : new TokenInterceptor(getActivity()), getActivity()).createGithubService();

        if(b.getString("url") != null) {
            Log.d(LOG_TAG, "URL: " + b.getString("url"));
            repos = githubService.getRepoContents(b.getString("url"));
        } else {
            repos = githubService.getRepoContents(b.getString("user"), b.getString("title"), "");
        }
        mCompositeSubscription.add(repos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoContents));
        AppObservable.bindFragment(this, repos);
    }

    public Subscriber<List<RepoContent>> repoContents = new Subscriber<List<RepoContent>>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(List<RepoContent> repoContents) {
            initList(getCards(repoContents));
        }
    };

    public ArrayList<Card> getCards(List<RepoContent> repoContents) {
        Collections.sort(repoContents);
        contents = repoContents;
        ArrayList<Card> cards = new ArrayList<>();
        for(RepoContent r : repoContents) {
            cards.add(new CardFileItem(getActivity(), r));
        }
        return cards;
    }

    public void refreshSourceList(List<RepoContent> repoContents) {
        initList(getCards(repoContents));
    }

    public List<RepoContent> getContents() {
        return contents;
    }

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_repo_contents;
    }
}
