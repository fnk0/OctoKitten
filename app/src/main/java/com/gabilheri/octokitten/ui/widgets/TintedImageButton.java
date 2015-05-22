package com.gabilheri.octokitten.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.helpers.ColorHelper;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/14/15.
 */
public class TintedImageButton extends ImageButton {
    private int colorResource;
    private Drawable defaultDrawable;

    public TintedImageButton(Context context) {
        super(context);
    }

    public TintedImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TintedImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TintedImageView, defStyleAttr, 0);
        colorResource = a.getColor(R.styleable.TintedImageView_paint, 0);
        Drawable d = getDrawable();
        if (d != null && colorResource != 0) {
            setImageDrawable(d);
        }
    }


    @Override
    public void setImageDrawable(Drawable drawable) {
        defaultDrawable = drawable;
        if (colorResource != 0) {
            BitmapDrawable d = new BitmapDrawable(getResources(), ColorHelper.getColoredBitmap(drawable, colorResource));
            super.setImageDrawable(d);
        } else {
            super.setImageDrawable(drawable);
        }
    }

    public void setPaint(int color){
        colorResource = color;
        if (getDrawable() != null){
            setImageDrawable(defaultDrawable);
        }
    }

    public void setPaintResource(int resource){
        setPaint(getResources().getColor(resource));
    }
}
