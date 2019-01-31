package net.winnerawan.madiun.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.ui.base.BaseActivity;
import net.winnerawan.madiun.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashMvpPresenter<SplashView> mPresenter;

    private static final int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        //mPresenter.app();
        startSplashAnimation();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void startSplashAnimation() {
//        Object localObject = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
//        ((ObjectAnimator)localObject).setInterpolator(new AccelerateDecelerateInterpolator());
//        ((ObjectAnimator)localObject).setDuration(1500L);
//        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
//        localObjectAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
//        localObjectAnimator1.setDuration(1500L);
//        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
//        localObjectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
//        localObjectAnimator2.setDuration(1500L);
//        AnimatorSet localAnimatorSet = new AnimatorSet();
//        localAnimatorSet.play((Animator)localObject).with(localObjectAnimator1).with(localObjectAnimator2);
//        localAnimatorSet.setStartDelay(1500L);
//        localAnimatorSet.start();
//        findViewById(R.id.imagelogo).setAlpha(1.0F);
//        localObject = AnimationUtils.loadAnimation(this, R.anim.fade);
//        findViewById(R.id.imagelogo).startAnimation((Animation)localObject);
    }


}
