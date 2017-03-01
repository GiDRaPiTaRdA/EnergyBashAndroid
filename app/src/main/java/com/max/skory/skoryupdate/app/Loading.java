package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Loading extends Activity implements Animation.AnimationListener {

    ImageView imageView;
    Intent intent;
    LinearLayout layout;
    MediaPlayer mediaPlayer;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        intent = new Intent(Loading.this, General.class);
        imageView = (ImageView)findViewById(R.id.imageView);
        layout = (LinearLayout)findViewById(R.id.PBack);
        mContext = this;

        mediaPlayer = MediaPlayer.create(mContext, R.raw.loading);
        mediaPlayer.setVolume(0.5f,0.5f);

        animation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    public void resume(View v){
        resume1();
    }

    public void resume1 (){
        mediaPlayer.stop();
        startActivity(intent);
        finish();
    }
 public void resume (){
  //   Intent intent = new Intent(Loading.this, General.class);
     try {
         Thread.sleep(850) ;
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
     startActivity(intent);
    finish();
 }

    public void animation(){
        Animation preview_on = AnimationUtils.loadAnimation(this, R.anim.preview_on);
        mediaPlayer.start();
        imageView.startAnimation(preview_on);

        preview_on.setAnimationListener(this);
//        preview_on.

//        Animation preview_off = AnimationUtils.loadAnimation(this, R.anim.preview_off);
//        imageView.startAnimation(preview_off);
    }

    public  void ani_test(View v) {
        animation();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        layout.setBackgroundResource(R.drawable.pre_v);
        Animation preview_off = AnimationUtils.loadAnimation(this, R.anim.preview_off);
        preview_off.setAnimationListener(new mylistener());
        imageView.startAnimation(preview_off);
        //imageView.setAlpha(0.0f);
        //resume();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    class mylistener implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {


        }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.setAlpha(0.0f);
        resume();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
