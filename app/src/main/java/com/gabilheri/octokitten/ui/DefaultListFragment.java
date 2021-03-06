package com.gabilheri.octokitten.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gabilheri.octokitten.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 1/27/15.
 */
public abstract class DefaultListFragment<T> extends DefaultFragment<T> {

    @Optional
    @InjectView(R.id.progress_wheel)
    protected ProgressWheel progressWheel;

    @Optional
    @InjectView(R.id.recycler_list)
    protected CardRecyclerView recyclerViewList;

    protected CardArrayRecyclerViewAdapter mCardArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), null);
        recyclerViewList.setAdapter(mCardArrayAdapter);

    }

    public Runnable refreshList() {
        return new Runnable() {
            @Override
            public void run() {
                mCardArrayAdapter.notifyDataSetChanged();
                recyclerViewList.refreshDrawableState();
                recyclerViewList.invalidate();
            }
        };
    }

    public void initList(ArrayList<Card> cards) {
        mCardArrayAdapter.setCards(cards);
        recyclerViewList.setAdapter(mCardArrayAdapter);
        progressWheel.setVisibility(View.GONE);
        refreshList();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_list;
    }
}
