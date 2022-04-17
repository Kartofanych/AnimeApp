package com.example.anime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.icu.number.Precision;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AnimeActivity extends AppCompatActivity {


    private PlayerView playerView;
    private ImageView fullScreen;
    private boolean isFullScreen = false;
    private SimpleExoPlayer player;
    private ProgressBar progressBar;
    private boolean isShowingTrackSelectionDialog;
    private DefaultTrackSelector trackSelector;
    private int startItemIndex;
    private ConstraintLayout constraintLayout;
    private String[] speed = {"0.25x", "0.5x", "Normal", "1.5x", "2x"};
    private String getLive_url;
    private String live_url = "https://cloud.kodik-storage.com/useruploads/04176175-9a51-42f2-8756-63cf08595756/4ab4d312d73997b51ca5575964d606d7:2022041400/manifest.m3u8";
    private TextView speedTxt, raitingRounded;
    private ImageView speedBtn, setting;
    private LinearLayout titlesWithHolder, mainHeroesHolder;
    private LayoutInflater infl;
    private ScrollView scrollView;

    private DisplayMetrics displayMetrics;
    private float dpHeight;
    private float dpWidth;
    private float scale;
    private List<ProgressBar> starsBar;
    private ProgressBar barStarsTogether;
    private ImageView back;

    private List<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_class_activity);
        getWindow().setStatusBarColor(getResources().getColor(R.color.background));

        getViews();

        trackSelector = new DefaultTrackSelector(this);
        player = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build();
        playerView.setPlayer(player);

        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
        HlsMediaSource mediaSources = new HlsMediaSource.Factory(dataSourceFactory).
           createMediaSource(MediaItem.fromUri(live_url));
        player.setMediaSource(mediaSources);
        player.prepare();


        ClickListeners();

        SetUpHolder();


        SetUpMainCharactersHolder();

        SetUpBar();


    }

    private void getViews(){
        infl = getLayoutInflater();
        displayMetrics = getResources().getDisplayMetrics();
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        scale = getResources().getDisplayMetrics().density;

        scrollView = findViewById(R.id.main_scroll);
        constraintLayout = findViewById(R.id.constraint_player);
        playerView = findViewById(R.id.playerView);

        speedTxt = playerView.findViewById(R.id.speed);
        playerView = findViewById(R.id.playerView);

        fullScreen = playerView.findViewById(R.id.exo_fullscreen_button);
        speedBtn = playerView.findViewById(R.id.exo_playback_speed);
        progressBar = findViewById(R.id.progressBar);
        setting = playerView.findViewById(R.id.exo_track_selection_view);
        back = findViewById(R.id.back);
        mainHeroesHolder = findViewById(R.id.heroes_holder);

        titlesWithHolder = findViewById(R.id.connected_list);
        barStarsTogether = findViewById(R.id.progress_stars_together);
        raitingRounded = findViewById(R.id.raiting_rounded);
        starsBar = new ArrayList<>();
        SetUpBar();


    }

    private void SetUpBar() {
        ProgressBar pr1 = findViewById(R.id.stars_five);
        ProgressBar pr2 = findViewById(R.id.stars_four);
        ProgressBar pr3 = findViewById(R.id.stars_three);
        ProgressBar pr4 = findViewById(R.id.stars_two);
        ProgressBar pr5 = findViewById(R.id.stars_one);

        TextView tx1 = findViewById(R.id.procent_one_star);
        TextView tx2 = findViewById(R.id.procent_two_star);
        TextView tx3 = findViewById(R.id.procent_three_star);
        TextView tx4 = findViewById(R.id.procent_four_star);
        TextView tx5 = findViewById(R.id.procent_five_star);



        starsBar.add(pr5);
        starsBar.add(pr4);
        starsBar.add(pr3);
        starsBar.add(pr2);
        starsBar.add(pr1);
        list.add(0, 4);
        list.add(1, 2);
        list.add(2, 7);
        list.add(3, 23);
        list.add(4, 65);
        double max = list.get(0)+list.get(1)+list.get(2)+list.get(3)+list.get(4);
        double sum = (list.get(0)*1+list.get(1)*2+list.get(2)*3+list.get(3)*4+list.get(4)*5);

        barStarsTogether.setMax((int) (5*max));
        barStarsTogether.setProgress((int) sum);

        raitingRounded.setText((double)(Math.round((sum/max) * 10d) / 10d) + " out of 5");
        tx1.setText((int) (list.get(0)*100/max) + "%");
        tx2.setText((int) (list.get(1)*100/max) + "%");
        tx3.setText((int) (list.get(2)*100/max) + "%");
        tx4.setText((int) (list.get(3)*100/max) + "%");
        tx5.setText((int) (list.get(4)*100/max) + "%");
        for(int i = 0; i < list.size(); i ++){
            starsBar.get(i).setMax((int) max);
            starsBar.get(i).setProgress(list.get(i));

        }
    }


    private void SetUpHolder(){
        for(int i = 0; i < 2; i ++){
            View view = infl.inflate(R.layout.preview_card, null, false);
            LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,60,0);
            view.setLayoutParams(layoutParams);

            TextView title = view.findViewById(R.id.title_name);
            ImageView img = view.findViewById(R.id.img_preview);
            if(i == 0) {
                title.setText("Kaguya-sama: in love is like war");
                img.setImageDrawable(getResources().getDrawable(R.drawable.firstseasonex));
            }else if(i == 1) {
                title.setText("Kaguya-sama: in love is like war 2");
                img.setImageDrawable(getResources().getDrawable(R.drawable.secondseasonex));
            }

            titlesWithHolder.addView(view);
        }

    }
    private void SetUpMainCharactersHolder() {
        for(int i = 0; i < 5; i ++){
            View view = infl.inflate(R.layout.preview_card, null, false);
            LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,60,0);
            view.setLayoutParams(layoutParams);

            TextView title = view.findViewById(R.id.title_name);
            ImageView img = view.findViewById(R.id.img_preview);
            if(i == 0) {
                title.setText("Miyuki Shirogane");
                Glide.with(AnimeActivity.this).
                        load("https://aniu.ru/characters/136685.jpg").into(img);
            }else if(i == 1) {
                title.setText("Yu Ishigami");
                Glide.with(AnimeActivity.this).
                        load("https://aniu.ru/characters/143185.jpg").into(img);
            }else if(i == 2) {
                title.setText("Kaguya Shinomiya");
                Glide.with(AnimeActivity.this).
                        load("https://aniu.ru/characters/136359.jpg").into(img);
            }else if(i == 3) {
                title.setText("Chika Fujiwara");
                Glide.with(AnimeActivity.this).
                        load("https://aniu.ru/characters/140810.jpg").into(img);
            }else if(i == 4) {
                title.setText("Miko Iino");
                Glide.with(AnimeActivity.this).
                        load("https://aniu.ru/characters/152052.jpg").into(img);
            }

            mainHeroesHolder.addView(view);
        }
    }


    private void ClickListeners(){
        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(AnimeActivity.this);
                builder.setTitle("Set Speed");
                builder.setItems(speed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]

                        if (which==0){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.25X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            player.setPlaybackParameters(param);


                        }  if (which==1){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.5X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            player.setPlaybackParameters(param);


                        }
                        if (which==2){

                            speedTxt.setVisibility(View.GONE);
                            PlaybackParameters param = new PlaybackParameters(1f);
                            player.setPlaybackParameters(param);


                        }
                        if (which==3){
                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("1.5X");
                            PlaybackParameters param = new PlaybackParameters(1.5f);
                            player.setPlaybackParameters(param);

                        }
                        if (which==4){
                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("2X");

                            PlaybackParameters param = new PlaybackParameters(2f);
                            player.setPlaybackParameters(param);
                        }

                    }
                });
                builder.show();

            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (isFullScreen) {
                    fromFullScreen();
                } else {
                    toFullScreen();
                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowingTrackSelectionDialog
                        && TrackSelectionDialog.willHaveContent(trackSelector)) {
                    isShowingTrackSelectionDialog = true;

                    TrackSelectionDialog trackSelectionDialog =
                            TrackSelectionDialog.createForTrackSelector(
                                    trackSelector,
                                    /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                    trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);




                }


            }
        });

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                    //player.setPlayWhenReady(true);
                } else if (state == Player.STATE_BUFFERING) {
                    progressBar.setVisibility(View.VISIBLE);
                    playerView.setKeepScreenOn(true);
                } else {
                    progressBar.setVisibility(View.GONE);
                    //player.setPlayWhenReady(true);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void toFullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        RelativeLayout.LayoutParams layoutParams = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        constraintLayout.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
        params.width = (int) (dpHeight * scale+0.5f);
        params.height = (int) (dpWidth * scale+0.5f);
        playerView.setLayoutParams(params);
        scrollView.scrollTo(0,0);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollView.scrollTo(0,0);
            }
        });
        isFullScreen = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fromFullScreen(){

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.choose_voice);
        layoutParams.setMarginStart((int) (10*scale +0.5f));
        layoutParams.setMarginEnd((int) (10*scale +0.5f));
        constraintLayout.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();

        params.width = params.MATCH_PARENT;
        params.height = (int) (210 * scale+0.5f);
        playerView.setLayoutParams(params);
        scrollView.setOnScrollChangeListener(null);
        isFullScreen = false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        player.seekToDefaultPosition();
       // player.setPlayWhenReady(true);
    }

    @Override
    protected void onPause() {
        //player.setPlayWhenReady(false);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        player.release();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        if(isFullScreen){
            fromFullScreen();
        }else {
            super.onBackPressed();
        }
    }
}
