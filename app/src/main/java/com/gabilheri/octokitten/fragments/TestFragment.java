package com.gabilheri.octokitten.fragments;

import android.os.Bundle;
import android.view.View;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.rest.RepoService;
import com.gabilheri.octokitten.ui.cards.CardRepo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import rx.Observable;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/15.
 */
public class TestFragment extends DefaultListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GithubClient client = new GithubClient(true);
        RepoService repoService = client.createService(RepoService.class);
        Observable<List<Repo>> repos = repoService.getRepos("fnk0");
        AppObservable.bindFragment(this, repos);
        repos.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<Repo>>() {
                @Override
                public void call(List<Repo> repos) {
                    Collections.sort(repos);
                    ArrayList<Card> cards = new ArrayList<>();
                    for (Repo r : repos) {
                        cards.add(new CardRepo(getActivity(), r));
                    }
                    mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);
                    recyclerViewList.setAdapter(mCardArrayAdapter);
                    refreshList();
                    progressWheel.setVisibility(View.GONE);
                }
            });
    }
}
