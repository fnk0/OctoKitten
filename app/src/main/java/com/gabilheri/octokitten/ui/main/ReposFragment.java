package com.gabilheri.octokitten.ui.main;

import android.os.Bundle;
import android.view.View;

import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.network.TokenInterceptor;
import com.gabilheri.octokitten.ui.DefaultListFragment;
import com.gabilheri.octokitten.ui.cards.CardRepo;
import com.gabilheri.octokitten.utils.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import it.gmariotti.cardslib.library.internal.Card;
import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class ReposFragment extends DefaultListFragment<MainComponent> {


    @Inject
    Timber.Tree logger;

    Observable<List<Repo>> repos;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String user = null;
        if(getArguments() != null) {
            user = getArguments().getString("user");
        }

        String token = PrefManager.with(getActivity()).getString(Preferences.AUTH_TOKEN, null);
        GithubService githubService = new GithubClient(DEBUG, token == null ? null : new TokenInterceptor(getActivity()), getActivity()).createGithubService();

        if(user != null) {
            repos = githubService.getRepos(user, "updated");
        } else {
            repos = githubService.getRepos("updated");
        }

        mCompositeSubscription.add(repos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));

        AppObservable.bindFragment(this, repos);
    }

    public Subscriber<List<Repo>> subscriber = new Subscriber<List<Repo>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(List<Repo> repos) {
            Collections.sort(repos);
            ArrayList<Card> cards = new ArrayList<>();
            for (Repo r : repos) {
                cards.add(new CardRepo(getActivity(), r));
            }
            initList(cards);
        }
    };

    @Override
    protected void inject(MainComponent component) {
        component.inject(this);
    }
}
