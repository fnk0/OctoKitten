package com.gabilheri.octokitten.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gabilheri.octokitten.utils.CustomUtils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/24/15.
 */
public class GithubTextView extends TextView {

    public GithubTextView(Context context) {
        super(context);
        init();
    }

    public GithubTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GithubTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setTypeface(CustomUtils.getGithubTypeface(getContext()));
    }
}
