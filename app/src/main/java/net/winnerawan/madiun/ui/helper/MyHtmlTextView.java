package net.winnerawan.madiun.ui.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.View;

import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.sufficientlysecure.htmltextview.LocalLinkMovementMethod;

public class MyHtmlTextView extends HtmlTextView {
    public MyHtmlTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyHtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHtmlTextView(Context context) {
        super(context);
    }

    @Override
    public void setHtml(@NonNull String html, @Nullable Html.ImageGetter imageGetter) {
        super.setHtml(html, imageGetter);
    }

    private Spanned addLinkCallbacksToSpanned(Spanned text) {
        SpannableString buffer = (SpannableString) text;
        Linkify.addLinks(buffer, Linkify.WEB_URLS);
        URLSpan[] spans = text.getSpans(0, text.length(), URLSpan.class);
        for(URLSpan us : spans) {
            final int end = text.getSpanEnd(us); //Getting the spans position
            final int start = text.getSpanStart(us);
            buffer.setSpan(new URLSpan(us.getURL()) { // A new span with the same URL
                @Override
                public void onClick(View widget) {
                    if(mLinkHandler != null) {
                        mLinkHandler.onClick(getURL());
                    } else {
                        super.onClick(widget);
//                        super.OnClick(widget);
                    }
                }
            }, start, end, 0); //Overwriting the old span
        }
        setText(buffer);
        setMovementMethod(LocalLinkMovementMethod.getInstance());
        return buffer;
    }

    private LinkClickHandler mLinkHandler;

    public void setLinkHandler(LinkClickHandler mLinkHandler) {
        this.mLinkHandler = mLinkHandler;
    }

    public interface LinkClickHandler {

        void onClick(String url);

    }
}
