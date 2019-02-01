package net.winnerawan.madiun.ui.player;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.utils.AppConstants;
import net.winnerawan.madiun.utils.CommonUtils;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubeView;
    private LinearLayout lyt_on_touch;
    private ImageView img_back;
    private ImageView img_close;
    private LinearLayout lyt_next_episode;
    private Handler handler;
    private TextView txt_session;
    private TextView txt_sub_title;
    private TextView txt_title;
    private int TIME_STORE = -1;
    private long seek = 0;
    private String video_id;
    private Runnable r;

    private String API_KEY = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_detail_page_youtube);
        setUp();
    }

    protected void setUp() {
        SharedPreferences preferences = getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE);
        API_KEY = preferences.getString("KEY_YOUTUBE_KEY", "AIzaSyAyuhfZeqqVG9yWyhN8zACmo1oHziB1Czw");
        initLayout(API_KEY);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            String cue = bundle.getString("cue");
            String title = bundle.getString("title");
            String subtitle = bundle.getString("subtitle");
            String channel = bundle.getString("channel");
            initData(title, CommonUtils.prettyDateFormat(subtitle), channel);
            if (cue!=null) {
                video_id = cue;
            }
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            if (this.seek > 0) {
                youTubePlayer.loadVideo(this.video_id, (int) TimeUnit.SECONDS.toMillis(this.seek));
            } else {
                youTubePlayer.loadVideo(this.video_id);
            }
            this.handler = new Handler();
            this.r = new Runnable() {
                public void run() {
                    if (youTubePlayer != null) {
                        try {
                            if (youTubePlayer.getCurrentTimeMillis() < youTubePlayer.getDurationMillis()) {
                                PlayerActivity.this.TIME_STORE = (int) TimeUnit.MILLISECONDS.toSeconds((long) youTubePlayer.getCurrentTimeMillis());
                                //PlayerActivity.this.storeCurentTime();
                                PlayerActivity.this.handler.postDelayed(this, 5000);
                                return;
                            }
                            PlayerActivity.this.handler.removeCallbacks(this);
                        } catch (IllegalStateException e) {
                        }
                    }
                }
            };
            this.handler.postDelayed(this.r, 5000);
            youTubePlayer.setFullscreenControlFlags(2);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                public void onLoading() {
                }

                public void onLoaded(String s) {
                }

                public void onAdStarted() {
                }

                public void onVideoStarted() {
                    PlayerActivity.this.lyt_on_touch.setVisibility(View.GONE);
                }

                public void onVideoEnded() {
                    //PlayerActivity.this.storeCurentTime();
                    PlayerActivity.this.lyt_on_touch.setVisibility(View.VISIBLE);
                    //if (PlayerActivity.this.NEXT_EPISODE) {
                    //    PlayerActivity.this.lyt_next_episode.setVisibility(0);
                    //}
                }

                public void onError(YouTubePlayer.ErrorReason errorReason) {
                }
            });
            youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                public void onPlaying() {
                    PlayerActivity.this.lyt_on_touch.setVisibility(View.GONE);
                    Log.d("DETITKKKKK", "-- " + youTubePlayer.getCurrentTimeMillis());
                }

                public void onPaused() {
                    PlayerActivity.this.lyt_on_touch.setVisibility(View.VISIBLE);
                }

                public void onStopped() {
                    PlayerActivity.this.lyt_on_touch.setVisibility(View.VISIBLE);
                }

                public void onBuffering(boolean b) {
                }

                public void onSeekTo(int i) {
                }
            });
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
            return;
        }
        Log.d("dyoutube ", " --> " + String.format("error ", new Object[]{youTubeInitializationResult.toString()}));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return this.youTubeView;
    }

    private void initLayout(String KEY){
        this.youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        this.youTubeView.initialize(KEY, this);
        this.lyt_on_touch = (LinearLayout) findViewById(R.id.lyt_on_touch);
        this.img_back = (ImageView) findViewById(R.id.img_back);
        this.img_close = (ImageView) findViewById(R.id.img_close);
        this.lyt_next_episode = (LinearLayout) findViewById(R.id.lyt_next_episode);
    }

    private void initData(String title, String subtitle, String channel) {
        this.txt_session = (TextView) findViewById(R.id.txt_session);
        this.txt_title = (TextView) findViewById(R.id.txt_title);
        this.txt_sub_title = (TextView) findViewById(R.id.txt_sub_title);
        txt_session.setText(channel);
        txt_sub_title.setText(subtitle);
        txt_title.setText(title);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void finishAction() {
        this.handler.removeCallbacks(this.r);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAction();
    }
}
