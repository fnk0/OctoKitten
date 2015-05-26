package com.gabilheri.octokitten.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.repo.ReposListActivity;

import java.util.Stack;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/23/15.
 */
public class BreadCrumbContainer extends LinearLayout {

    private BreadCrumbTextView previousBtv;

    private Stack<String> tags = new Stack<>();

    public BreadCrumbContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        isInEditMode();
        setBackgroundColor(context.getResources().getColor(R.color.primary));
        setOrientation(HORIZONTAL);
    }

    public void clickBreadCrumb(BreadCrumbTextView btv) {
        String tag = btv.getText().toString();
        btv.setCurrent(true);
        while (!tags.peek().equals(tag)) {

            if(getContext() instanceof ReposListActivity) {
                ((NavigateUpListener) getContext()).navigateUp();
            }

            if(size() != 1) {
                View v = findViewWithTag(tags.pop());
                removeView(v);
            } else {
                break;
            }

        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        isInEditMode();
    }

    public void addBreadCrumb(BreadCrumbTextView btv) {
        btv.setTag(btv.getText().toString());
        tags.push(btv.getText().toString());
        btv.setCurrent(true);
        addView(btv);
        if(previousBtv != null) {
            previousBtv.setCurrent(false);
        }
        previousBtv = btv;
    }

    public void removeLastBreadCrumb() {
        removeViewAt(getChildCount() -1);
    }

    public int size() {
        return getChildCount();
    }
}
