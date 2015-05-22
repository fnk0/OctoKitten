package com.gabilheri.octokitten.ui.repo.code;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.DefaultFragment;
import com.gabilheri.octokitten.ui.helpers.PrettifyHighlighter;
import com.gabilheri.octokitten.ui.repo.RepoComponent;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/15/15.
 */
public class SourceCodeFragment extends DefaultFragment<RepoComponent> {

    @InjectView(R.id.source_code)
    TextView sourceCode;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        if(getArguments() != null) {

            String url = getArguments().getString("url");

            PrettifyHighlighter highlighter = new PrettifyHighlighter();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_code;
    }

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }
}
