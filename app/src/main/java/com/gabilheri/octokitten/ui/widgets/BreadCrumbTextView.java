package com.gabilheri.octokitten.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gabilheri.octokitten.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/23/15.
 */
public class BreadCrumbTextView extends TextView {

    public BreadCrumbTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isInEditMode();
        setCurrent(false);
        setTextSize(context.getResources().getDimension(R.dimen.btv_font_size));
        int pad = (int) context.getResources().getDimension(R.dimen.btv_padding);
        setPadding(pad, pad, pad, pad);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text + "/", type);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        isInEditMode();
    }

    public void setCurrent(boolean current) {
        if(current) {
            setTextColor(getContext().getResources().getColor(R.color.accent));
        } else {
            setTextColor(getContext().getResources().getColor(R.color.breacrumb_text));
        }

        invalidate();
    }
}
