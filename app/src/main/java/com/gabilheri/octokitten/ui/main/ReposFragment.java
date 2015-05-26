package com.gabilheri.octokitten.ui.main;

import android.os.Bundle;
import android.view.View;

import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.ui.BaseDefaultListFragment;
import com.gabilheri.octokitten.ui.cards.CardRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class ReposFragment extends BaseDefaultListFragment {

    Observable<List<Repo>> repos;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String user = null;
        if(getArguments() != null) {
            user = getArguments().getString("user");
        }

        if(user != null) {
            repos = service.getRepos(user, "updated")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            repos = service.getRepos("updated")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        mCompositeSubscription.add(repos.subscribe(subscriber));

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
}
