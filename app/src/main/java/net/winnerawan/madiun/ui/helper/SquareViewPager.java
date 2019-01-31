package net.winnerawan.madiun.ui.helper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SquareViewPager extends ViewPager {

    public SquareViewPager(final Context context) {
        super(context);
    }

    public SquareViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // here we are creating square view pager where height = width
        // so instead of height we pass width as a parameter
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
