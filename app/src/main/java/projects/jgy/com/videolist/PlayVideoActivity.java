package projects.jgy.com.videolist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.os.Environment.DIRECTORY_MOVIES;

public class PlayVideoActivity extends AppCompatActivity implements EasyVideoCallback {

    @Bind(R.id.player)
    EasyVideoPlayer player;
    @Bind(R.id.ll_down_error)
    LinearLayout llDownError;
    @Bind(R.id.progressBarDown)
    ProgressBar progressBarDown;
    @Bind(R.id.ll_downing)
    LinearLayout llDowning;
    @Bind(R.id.rl_down)
    RelativeLayout rlDown;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @Bind(R.id.iv_complete)
    ImageView ivComplete;
    @Bind(R.id.tv_complete)
    TextView tvComplete;
    @Bind(R.id.tv_go_remake_video)
    TextView tvGoRemakeVideo;
    @Bind(R.id.iv_go_remake_video)
    ImageView ivGoRemakeVideo;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.iv_confirm)
    ImageView ivConfirm;
    @Bind(R.id.shade_layout)
    RelativeLayout shadeLayout;

    public static void start(Context context, String path) {
        Intent starter = new Intent(context, PlayVideoActivity.class);
        starter.putExtra("path", path);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("米听test");
        String path = getIntent().getStringExtra("path");
        player.setCallback(this);
        player.setAutoPlay(false);
        player.disableControls();
        if (path.startsWith("http")) {
            File file = new File(getExternalFilesDir(DIRECTORY_MOVIES), path.substring(path.lastIndexOf("/") + 1));
            player.setSource(Uri.fromFile(file));
        } else {
            player.setSource(Uri.fromFile(new File(path)));
        }
        tvGoRemakeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean playing = player.isPlaying();
                if (playing) {
                    ivPlay.setImageResource(R.drawable.ic_playing);
                    player.pause();
                } else {
                    player.start();
                    ivPlay.setImageResource(R.drawable.ic_play_pause);
                }
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayVideoActivity.super.onBackPressed();
            }
        });
        /*RxView.clicks(ivPlay)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                });*/
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {

    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {

    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        player.seekTo(0);
        player.pause();
        ivPlay.setImageResource(R.drawable.ic_playing);
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }
}
