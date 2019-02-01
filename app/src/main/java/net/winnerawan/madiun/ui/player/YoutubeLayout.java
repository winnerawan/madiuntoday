package net.winnerawan.madiun.ui.player;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class YoutubeLayout {
//    private final r a;
//    private PlayerView b;
//    private LinearLayout c;
//    private float d;
//    private float e;
//    private int f;
//    private int g;
//    private float h;
//    private int i;
//
//    private class a extends android.support.v4.widget.r.a {
//        final /* synthetic */ YoutubeLayout a;
//
//        private a(YoutubeLayout youtubeLayout) {
//            this.a = youtubeLayout;
//        }
//
//        public int a(View view) {
//            return this.a.f;
//        }
//
//        public int a(View view, int i, int i2) {
//            return Math.min(Math.max(i, this.a.getPaddingTop()), (this.a.getHeight() - this.a.b.getHeight()) - this.a.b.getPaddingBottom());
//        }
//
//        public void a(View view, float f, float f2) {
//            int paddingTop = this.a.getPaddingTop();
//            if (f2 <= 0.0f) {
//                if (f2 != 0.0f || this.a.h <= 0.5f) {
//                    this.a.i = 0;
//                    ((MainActivity) this.a.getContext()).k();
//                    this.a.a.a(view.getLeft(), paddingTop);
//                    this.a.invalidate();
//                }
//            }
//            paddingTop += this.a.f;
//            this.a.i = 1;
//            ((MainActivity) this.a.getContext()).l();
//            this.a.a.a(view.getLeft(), paddingTop);
//            this.a.invalidate();
//        }
//
//        public void a(View view, int i, int i2, int i3, int i4) {
//            this.a.g = i2;
//            this.a.h = ((float) i2) / ((float) this.a.f);
//            i = (int) TypedValue.applyDimension(1, 114.0f, this.a.getResources().getDisplayMetrics());
//            this.a.b.setPivotX((float) (this.a.b.getWidth() - ((int) TypedValue.applyDimension(1, 30.0f, this.a.getResources().getDisplayMetrics()))));
//            this.a.b.setPivotY((float) (this.a.b.getHeight() - i));
//            this.a.b.setScaleX(1.0f - (this.a.h / 1.78f));
//            this.a.b.setScaleY(1.0f - (this.a.h / 1.78f));
//            this.a.c.setAlpha(1.0f - (this.a.h / 10.0f));
//            this.a.requestLayout();
//        }
//
//        public boolean a(View view, int i) {
//            return view == this.a.b;
//        }
//    }
//
//    public YoutubeLayout(Context context) {
//        this(context, null);
//    }
//
//    public YoutubeLayout(Context context, AttributeSet attributeSet) {
//        this(context, attributeSet, 0);
//    }
//
//    public YoutubeLayout(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//        this.i = 0;
//        this.a = r.a((ViewGroup) this, 1.0f, new a());
//    }
//
//    private void a(float f) {
//        if (this.a.a(this.b, this.b.getLeft(), (int) (((float) getPaddingTop()) + (f * ((float) this.f))))) {
//            android.support.v4.h.r.d(this);
//        }
//    }
//
//    private boolean a(View view, int i, int i2) {
//        int[] iArr = new int[2];
//        view.getLocationOnScreen(iArr);
//        int[] iArr2 = new int[2];
//        getLocationOnScreen(iArr2);
//        int i3 = iArr2[0] + i;
//        int i4 = iArr2[1] + i2;
//        return i3 >= iArr[0] && i3 < iArr[0] + view.getWidth() && i4 >= iArr[1] && i4 < iArr[1] + view.getHeight();
//    }
//
//    public void a() {
//        a(0.0f);
//        this.i = 0;
//        ((MainActivity) getContext()).k();
//    }
//
//    public void b() {
//        a(1.0f);
//        this.i = 1;
//        ((MainActivity) getContext()).l();
//    }
//
//    public int c() {
//        return this.i;
//    }
//
//    public void computeScroll() {
//        if (this.a.a(true)) {
//            android.support.v4.h.r.d(this);
//        }
//    }
//
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        this.b = (PlayerView) findViewById(R.id.player_view);
//        this.c = (LinearLayout) findViewById(R.id.Containermore);
//    }
//
//    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
//        return this.a.a(motionEvent);
//    }
//
//    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
//        this.f = getHeight() - this.b.getHeight();
//        this.b.layout(0, this.g, i3, this.g + this.b.getMeasuredHeight());
//        this.c.layout(0, this.g + this.b.getMeasuredHeight(), i3, this.g + i4);
//    }
//
//    protected void onMeasure(int i, int i2) {
//        measureChildren(i, i2);
//        setMeasuredDimension(resolveSizeAndState(MeasureSpec.getSize(i), i, 0), resolveSizeAndState(MeasureSpec.getSize(i2), i2, 0));
//    }
//
//    @SuppressLint({"ClickableViewAccessibility"})
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        this.a.b(motionEvent);
//        int action = motionEvent.getAction();
//        float x = motionEvent.getX();
//        float y = motionEvent.getY();
//        int i = (int) x;
//        int i2 = (int) y;
//        boolean b = this.a.b(this.b, i, i2);
//        switch (motionEvent.getActionMasked() & action) {
//            case 0:
//                this.d = x;
//                this.e = y;
//                break;
//            case 1:
//                x -= this.d;
//                y -= this.e;
//                int a = this.a.a();
//                if ((x * x) + (y * y) < ((float) (a * a)) && b && this.h != 0.0f) {
//                    a(0.0f);
//                    this.i = 0;
//                    break;
//                }
//            default:
//                break;
//        }
//        return (b && a(this.b, i, i2)) || a(this.c, i, i2);
//    }
}
