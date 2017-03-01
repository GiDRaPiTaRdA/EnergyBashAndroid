package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class General extends Activity implements OnCompletionListener {

    final String LOG_TAG = "MY_LOG";

    Settings settings;

    Button but, but1, but2, but3, but4;
    LinearLayout wall;

    SoundPool mSoundPool;
    int buttonPressSound;

    MediaPlayer mediaPlayer;
    int antiRepeat;
    ArrayList<Uri> uriMusicList;
    int[] soundsRawResIds = new int[]{
            R.raw.track1,
            R.raw.track2,
            R.raw.track3,
            R.raw.track4,
            R.raw.track5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu_general);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        but = (Button) findViewById(R.id.button);
        but1 = (Button) findViewById(R.id.button2);
        but2 = (Button) findViewById(R.id.button3);
        but3 = (Button) findViewById(R.id.button4);
        but4 = (Button) findViewById(R.id.button5);
        wall = (LinearLayout) findViewById(R.id.wall);

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonPressSound = loadSound("button.wav");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        antiRepeat = -1;
        uriMusicList =new ArrayList<Uri>();
        for (int currentId :soundsRawResIds)
            uriMusicList.add(ResourceToUri(getApplicationContext(),currentId));

        settings = new Settings(getSharedPreferences("settings",MODE_PRIVATE));

        EnableButtons();

        InnitRandomMusic();

        StartPlayingMusic();

    }


    public void kn1_cl(View v) {
        DisableButtons();
        ButtonEffects(v);
        Intent intent = new Intent(General.this, Singleplayer.class);
        startActivity(intent);
    }

    public void kn2_cl(View v) {
        DisableButtons();
        ButtonEffects(v);
        Intent intent = new Intent(General.this, Multiplayer.class);
        startActivity(intent);
    }

    public void kn3_cl(View v) {
        DisableButtons();
        ButtonEffects(v);
        Intent intent = new Intent(General.this, Statistic.class);
        startActivity(intent);
    }

    public void kn4_cl(View v) {
        DisableButtons();
        ButtonEffects(v);
        Intent intent = new Intent(General.this, Options.class);
        startActivity(intent);
    }

    public void kn5_cl(View v) {
        DisableButtons();
        ButtonEffects(v);
        finish();
    }

    //////// OVERRIDE
    @Override
    protected void onResume() {
        super.onResume();

        EnableButtons();

        UpdateView(settings.GetTheme());

        if(settings.GetMusic())
            StartPlayingMusic();
        else
            PausePlayingMusic();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       PausePlayingMusic();
        //mediaPlayer.release();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(but4.isEnabled()) {
            PausePlayingMusic();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PausePlayingMusic();
    }
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(LOG_TAG,"onCompletion");
        InnitRandomMusic();
        StartPlayingMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //if(but4.isEnabled())
            PausePlayingMusic();
    }
    ///////

    public void UpdateView(int theme) {
            ButtonLoadAnimation();
            if (theme == 0) {
                wall.setBackgroundResource(R.drawable.black_white);
                but.setBackgroundResource(R.drawable.bw_singleplayer);
                but1.setBackgroundResource(R.drawable.bw_multiplayer);
                but2.setBackgroundResource(R.drawable.bw_statistic);
                but3.setBackgroundResource(R.drawable.bw_options);
                but4.setBackgroundResource(R.drawable.bw_quit);
            } else if (theme == 1) {
                wall.setBackgroundResource(R.drawable.black_yellow);
                but.setBackgroundResource(R.drawable.by_singleplayer);
                but1.setBackgroundResource(R.drawable.by_multiplayer);
                but2.setBackgroundResource(R.drawable.by_statistic);
                but3.setBackgroundResource(R.drawable.by_options);
                but4.setBackgroundResource(R.drawable.by_quit);
            }else if(theme ==2){
                wall.setBackgroundResource(R.drawable.rainbow);
                but.setBackgroundResource(R.drawable.rb_singleplayer);
                but1.setBackgroundResource(R.drawable.rb_multiplayer);
                but2.setBackgroundResource(R.drawable.rb_statistic);
                but3.setBackgroundResource(R.drawable.rb_options);
                but4.setBackgroundResource(R.drawable.rb_quit);
        }
    }

    protected void playSound(int sound) {

            if (sound > 0)
                mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
    private int loadSound(String fileName) {
        AssetManager assets = getAssets();
        AssetFileDescriptor afd = null;
        try {
                afd = assets.openFd(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Не могу загрузить файл " + fileName,
                        Toast.LENGTH_SHORT).show();
                return -1;
            }
            return mSoundPool.load(afd, 1);
        }

    /////////// BUTTONS
    public void ButtonEffects(View v){
        ButtonSound();
        Vibrate();
        ButtonAnimation(v);
    }
    public void ButtonAnimation(View v){
        if (settings.GetTheme() == 0) v.setBackgroundResource(R.drawable.bw_loading);
        else if (settings.GetTheme() == 1) v.setBackgroundResource(R.drawable.by_loading);
        else if (settings.GetTheme() ==2)v.setBackgroundResource(R.drawable.rb_loading);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        v.startAnimation(animation5);
    }
    public void ButtonLoadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_for_left);
        but.startAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_for_right);
        but1.startAnimation(animation1);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_for_left);
        but2.startAnimation(animation2);
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.anim_for_right);
        but3.startAnimation(animation3);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_for_left);
        but4.startAnimation(animation4);
    }
    public void EnableButtons(){
        but.setEnabled(true);
        but1.setEnabled(true);
        but2.setEnabled(true);
        but3.setEnabled(true);
        but4.setEnabled(true);
    }
    public void DisableButtons(){
        but.setEnabled(false);
        but1.setEnabled(false);
        but2.setEnabled(false);
        but3.setEnabled(false);
        but4.setEnabled(false);
    }
    public void Vibrate(){
        Vibrator  v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(settings.GetSound()==true)v.vibrate(15);
    }
    public void ButtonSound() {
        if(settings.GetSound()==true)
            playSound(buttonPressSound);
    }
    ///////////

    /////////// MEDIA PLAYER
    private boolean PrepareMediaPlayer(MediaPlayer mediaPlayer, Uri uri){
        boolean result;
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            result = true;
        }
        catch (Exception e){
            Log.d(LOG_TAG,"Exception ",e);
            Log.d(LOG_TAG,"Uri : "+String.valueOf(uri));
            result = false;
        }
        return result;
    }
    public void StartPlayingMusic(){
        if(mediaPlayer!=null&&!mediaPlayer.isPlaying()&&settings.GetMusic())
            mediaPlayer.start();
    }
    public void PausePlayingMusic(){
        if(mediaPlayer!=null&&mediaPlayer.isPlaying())
                mediaPlayer.pause();}
    public void InnitMusic(){
        switch (settings.GetTheme()) {
            case 0:
                mediaPlayer = MediaPlayer.create(this, soundsRawResIds[0]);
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, soundsRawResIds[1]);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, soundsRawResIds[2]);
                break;
            default:
                mediaPlayer = MediaPlayer.create(this, soundsRawResIds[0]);
                break;
        
        }
    }
    public void InnitRandomMusic(){
        int count = uriMusicList.size();
        Random random = new Random();
        int tempRand;
        do {
            tempRand = random.nextInt(count);
        }
        while (tempRand==antiRepeat);
        antiRepeat = tempRand;
        try {
            if(mediaPlayer!=null)
                mediaPlayer.reset();
            mediaPlayer.setDataSource(getApplicationContext(),uriMusicList.get(tempRand));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Uri ResourceToUri(Context context, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID) );
    }
    ///////////
}




