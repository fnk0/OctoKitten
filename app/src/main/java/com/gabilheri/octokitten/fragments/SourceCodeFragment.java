package com.gabilheri.octokitten.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.rest.RepoService;
import com.gabilheri.octokitten.ui.cards.CardFileItem;

import java.util.ArrayList;
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
public class SourceCodeFragment extends DefaultListFragment {

    Observable<List<RepoContent>> repos;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        Bundle b = getArguments();
        GithubClient client = new GithubClient(false);
        RepoService service = client.createService(RepoService.class);
        repos = service.getRepoContents(b.getString("user"), b.getString("title"));
        repos.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(repoContents);
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
            ArrayList<Card> cards = new ArrayList<>();
            for(RepoContent r : repoContents) {
                Log.d(LOG_TAG, r.getName());
                cards.add(new CardFileItem(getActivity(), r));
            }
            initList(cards);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        repoContents.unsubscribe();
    }
}
