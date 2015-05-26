package com.gabilheri.octokitten.ui.repo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.ui.repo.code.SourceCodeListFragment;
import com.gabilheri.octokitten.ui.widgets.BreadCrumbContainer;
import com.gabilheri.octokitten.ui.widgets.BreadCrumbTextView;
import com.gabilheri.octokitten.ui.widgets.NavigateUpListener;

import java.util.List;
import java.util.Stack;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;
import icepick.Icicle;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class ReposListActivity extends BaseRepoActivity implements NavigateUpListener {

    @Icicle
    public Bundle extras;

    @InjectView(R.id.breadcrumb_container)
    BreadCrumbContainer breadCrumbContainer;

    protected SourceCodeListFragment codeFragment;

    protected Stack<List<RepoContent>> stack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        extras = getIntent().getExtras().getBundle(Intent.EXTRA_INTENT);
        String repoName = extras.getString("title");
        setTitle(repoName);
        codeFragment = new SourceCodeListFragment();
        codeFragment.setArguments(extras);
        addFragmentToContainer(codeFragment, null);
        breadCrumbContainer.addBreadCrumb(buildBreadCrumb(""));
    }

    public void addDir(List<RepoContent> contents, String title) {
        stack.push(codeFragment.getContents());
        Log.d(getClass().getSimpleName(), "Adding directory to stack... " + stack.size());
//        logger.d("Adding directory to stack... " + stack.size());
        final String[] tArr = title.split("/");
        final String t = tArr[tArr.length -1].replaceAll("/", "");
        breadCrumbContainer.addBreadCrumb(buildBreadCrumb(t));
        codeFragment.refreshSourceList(contents);
    }

    private BreadCrumbTextView buildBreadCrumb(String t) {
        final BreadCrumbTextView btv = (BreadCrumbTextView) getLayoutInflater().inflate(R.layout.btv_layout, null);
        btv.setText(t);
        btv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breadCrumbContainer.clickBreadCrumb((BreadCrumbTextView) v);
            }
        });
        return btv;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onBackPressed() {
        if(stack.size() == 0) {
            super.onBackPressed();
        }
        navigateUp();
    }

    @Override
    public void navigateUp() {
        logger.d("Stack count: " + stack.size());
        if(stack.size() > 0) {
            List<RepoContent> r = stack.pop();
            codeFragment.refreshSourceList(r);
            breadCrumbContainer.removeLastBreadCrumb();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_repo_contents;
    }
}
