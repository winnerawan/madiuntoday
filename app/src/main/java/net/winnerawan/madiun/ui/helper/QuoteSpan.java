package net.winnerawan.madiun.ui.helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.text.Layout;
import android.text.ParcelableSpan;
import android.text.style.LeadingMarginSpan;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
@SuppressWarnings("UnusedDeclaration")
public class QuoteSpan implements LeadingMarginSpan, ParcelableSpan {

    private final static int QUOTE_SPAN = 9;

    private int mStripeWidth = 2;
    private int mGapWidth = 30;
    private int mColor;

    public QuoteSpan() {
        super();
        mColor = 0xff0000ff;
    }

    public QuoteSpan(int color) {
        super();
        mColor = color;
    }

    public QuoteSpan(int color, int stripeWidth) {
        super();
        mColor = color;
        mStripeWidth = stripeWidth;
    }

    public QuoteSpan(int color, int stripeWidth, int gapWidth) {
        super();
        mColor = color;
        mStripeWidth = stripeWidth;
        mGapWidth = gapWidth;
    }

    public QuoteSpan(Parcel src) {
        mColor = src.readInt();
    }

    public int getSpanTypeId() {
        return QUOTE_SPAN;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mColor);
    }

    public int getColor() {
        return mColor;
    }

    public int getLeadingMargin(boolean first) {
        return mStripeWidth + mGapWidth;
    }

    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline,
                                  int bottom, CharSequence text, int start, int end, boolean first,
                                  Layout layout) {

        float strokeWidth = p.getStrokeWidth();
        int color = p.getColor();

        p.setStrokeWidth(mStripeWidth);
        p.setColor(mColor);

        c.drawLine(x, top, x, bottom, p);

        p.setStrokeWidth(strokeWidth);
        p.setColor(color);
    }
}
