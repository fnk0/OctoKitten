package com.gabilheri.octokitten.ui;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gabilheri.octokitten.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
public abstract class DefaultRecyclerListFragment<T> extends DefaultFragment<T> {

    @Optional
    @InjectView(R.id.progress_wheel)
    protected ProgressWheel progressWheel;

    @Optional
    @InjectView(R.id.recycler_list)
    protected RecyclerView recyclerViewList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public Runnable refreshList(final RecyclerView.Adapter adapter) {
        return new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                recyclerViewList.refreshDrawableState();
                recyclerViewList.invalidate();
            }
        };
    }

    public void initList(RecyclerView.Adapter adapter) {
        progressWheel.setVisibility(View.GONE);
        recyclerViewList.setAdapter(adapter);
        refreshList(adapter);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.recycler_list_fragment;
    }
}
