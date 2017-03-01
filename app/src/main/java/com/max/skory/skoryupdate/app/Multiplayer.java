package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Multiplayer extends Activity {

    LinearLayout MBack, MCenter;
    Button but1;
    Button but2;
    Button menu_but;
    Button pause;
    TextView score1;
    TextView score2;
    TextView time;
    TextView PlayerName1;
    TextView PlayerName2;
    ProgressBar prb1;
    ProgressBar prb2;
    ProgressBar tpb;
    boolean restart = false;
    int x = 0;
    int y = 0;
    int sec = 0;
    int t = 30;
    int theme;
    boolean st = true;
    boolean t_p = true;
    boolean b1_p;
    boolean b2_p;
    boolean restart_p;
    boolean m_r_button = true;
    Boolean sound,music;
    MyTask task;

    Settings settings;
    //SharedPreferences sp;
    //final String FILE_NAME1 = "YOUR_NAME";
    //final String FILE_NAME2 = "FRIENDS_NAME";
   //final String FILE_INTEGER_THEME = "THEME";
    //final String FILE_BOOLEAN_SOUND = "SOUND";

 //   final String FILE_BOOLEAN_MUSIC = "MUSIC";

    Vibrator v;
    ///////////////////////////
    SoundPool mSoundPool;
    AssetManager assets;
    int[] sounds = new int[4];
    Context mContext;
    /////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        but1 = (Button) findViewById(R.id.knopka1);
        but2 = (Button) findViewById(R.id.knopka2);
        pause = (Button) findViewById(R.id.pause);
        menu_but = (Button) findViewById(R.id.menu_restart);
        score1 = (TextView) findViewById(R.id.gamer_points);
        score2 = (TextView) findViewById(R.id.textV2);
        prb1 = (ProgressBar) findViewById(R.id.PGB1);
        prb2 = (ProgressBar) findViewById(R.id.PGB2);
        time = (TextView) findViewById(R.id.timer);
        MBack = (LinearLayout) findViewById(R.id.mback);
        MCenter = (LinearLayout) findViewById(R.id.mcenter);
        tpb = (ProgressBar) findViewById(R.id.TPB);
        tpb.setMax(t);
        PlayerName1 = (TextView) findViewById(R.id.plnM1);
        PlayerName2 = (TextView) findViewById(R.id.plnM2);
        settings = new Settings(getSharedPreferences("settings",MODE_PRIVATE));


        st = true;
//        sp = getSharedPreferences("settings",MODE_PRIVATE);
//        PlayerName1.setText(sp.getString(FILE_NAME1, ""));
//        PlayerName2.setText(sp.getString(FILE_NAME2, ""));
        start();
        t_p = true;
        LoadInfo();
        if (theme == 0) {
            MBack.setBackgroundResource(R.drawable.m_back_bw);
            MCenter.setBackgroundResource(R.drawable.m32_bw1);
            but1.setBackgroundResource(R.drawable.m_bw_rec);
            but2.setBackgroundResource(R.drawable.m_bw_tri);
            pause.setBackgroundResource(R.drawable.m32_bw);
            menu_but.setBackgroundResource(R.drawable.m32_bw);
            but1.setTextColor(Color.WHITE);
            but2.setTextColor(Color.WHITE);
            pause.setTextColor(Color.BLACK);
            menu_but.setTextColor(Color.BLACK);
            PlayerName1.setTextColor(Color.BLACK);
            PlayerName2.setTextColor(Color.BLACK);
            score1.setTextColor(Color.WHITE);
            score2.setTextColor(Color.WHITE);
            time.setTextColor(Color.WHITE);

        } else if (theme == 1) {
            MBack.setBackgroundResource(R.drawable.m_back_by);
            MCenter.setBackgroundResource(R.drawable.m32_by);
            but1.setBackgroundResource(R.drawable.m);
            but2.setBackgroundResource(R.drawable.m2);
            but1.setTextColor(Color.rgb(170, 102, 204));
            but2.setTextColor(Color.rgb(102, 153, 0));
            pause.setBackgroundResource(R.drawable.by_s4);
            menu_but.setBackgroundResource(R.drawable.by_s4);
            pause.setTextColor(Color.rgb(246, 225, 0));
            menu_but.setTextColor(Color.rgb(246, 225, 0));
            PlayerName1.setTextColor(Color.WHITE);
            PlayerName2.setTextColor(Color.WHITE);
            score1.setTextColor(Color.rgb(246, 225, 0));
            score2.setTextColor(Color.rgb(246, 225, 0));
            time.setTextColor(Color.rgb(255, 176, 0));
        } else {
            MBack.setBackgroundResource(R.drawable.m_back_rb);
            MCenter.setBackgroundResource(R.drawable.m3_rb);
            pause.setBackgroundResource(R.drawable.rb_s4);
            menu_but.setBackgroundResource(R.drawable.rb_s4);
            pause.setTextColor(Color.CYAN);
            menu_but.setTextColor(Color.CYAN);
            but1.setBackgroundResource(R.drawable.m_rb_rec);
            but2.setBackgroundResource(R.drawable.m_rb_tri);
            but1.setTextColor(Color.RED);
            but2.setTextColor(Color.RED);
            PlayerName1.setTextColor(Color.WHITE);
            PlayerName2.setTextColor(Color.WHITE);
            score1.setTextColor(Color.CYAN);
            score2.setTextColor(Color.CYAN);
            time.setTextColor(Color.rgb(246, 225, 0));
        }
        ////////////////////////////////////////
        mContext = this;
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        assets = getAssets();

        sounds[0] = loadSound("button.wav");
        sounds[1] = loadSound("l_button.wav");
/////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        findViewById(R.id.knopka1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttoncl_1();
                        break;


                }
                return false;
            }
        });
        findViewById(R.id.knopka2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttoncl_2();
                        break;


                }
                return false;
            }
        });
        findViewById(R.id.menu_restart).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        menu_restart_button();
                        break;


                }
                return false;
            }
        });
        findViewById(R.id.pause).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pause();
                        break;


                }
                return false;
            }
        });
        ////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t_p = false;
        task.cancel(true);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        t_p = false;
//        task.cancel(true);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.general, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Names();
//        PlayerName1.setText(sp.getString(FILE_NAME1, ""));
//        PlayerName2.setText(sp.getString(FILE_NAME2, ""));
//StartPlayingMusic();
        st = true;
        t_p = false;
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            while (!isCancelled()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            time_refresh();
            checkWin();
        }

    }


    private void checkWin() {
        if (t - sec <= 0) {
            if (x > y) {
                if(sound==true) v.vibrate(150);
                Toast.makeText(Multiplayer.this, settings.GetName1() + " WON!!!", Toast.LENGTH_SHORT).show();
                but1.setText("WIN");
                but2.setText("LOSE");
                restart_p = false;
                pause.setText("Begin");
                t_p = false;
                x = 0;
                y = 0;
                sec = 0;
            } else if (x < y) {
                if(sound==true)v.vibrate(150);
                Toast.makeText(Multiplayer.this, settings.GetName2() + " WON!!!", Toast.LENGTH_SHORT).show();
                but1.setText("LOSE");
                but2.setText("WIN");
                restart_p = false;
                pause.setText("Begin");
                t_p = false;
                x = 0;
                y = 0;
                sec = 0;
            } else {
                if(sound==true)v.vibrate(150);
                but1.setText("BALANCE");
                but2.setText("BALANCE");
                restart_p = false;
                pause.setText("Begin");
                t_p = false;
                x = 0;
                y = 0;
                sec = 0;
            }
            b1_p = false;
            b2_p = false;
        }
    }

    public void buttoncl_1() {

        if (restart_p == true) {
//            b1_p = true;
            menu_but.setText("Menu");
            m_r_button = true;
            but1.setText("Ready");
            //    but2.setText("");
            prb1.setProgress(x - y + 50);
            prb2.setProgress(y - x + 50);
            if (t - sec >= 10) time.setText("T:0:" + String.valueOf(t - sec));
            else if (t - sec < 10) time.setText("T:0:0" + String.valueOf(t - sec));
            score1.setText("G:" + String.valueOf(x));
            score2.setText("G:" + String.valueOf(y));
            if (b1_p == true && b2_p == true) {
                if (st) //StartPlayingMusic();
                    if (x - y + 50 < 100) {
                        x++;
                        vibro();
                        t_p = true;
                        but1.setText("");
                        but2.setText("");
                        score1.setText("G:" + String.valueOf(x));
                        prb1.setProgress(x - y + 50);
                        prb2.setProgress(y - x + 50);
                        restart = false;
                    }

                if (restart == true) {
                    x = 0;
                    y = 0;
                    prb1.setProgress(50);
                    prb2.setProgress(50);
                    score1.setText("G:" + String.valueOf(x));
                    score2.setText("G:" + String.valueOf(y));
                    b1_p = false;
                    b2_p = false;
                }
                if (x - y + 50 == 100) {
                    t_p = false;
                    if(sound==true)v.vibrate(150);
                    Toast.makeText(this, settings.GetName1() + " WON!!!",
                            Toast.LENGTH_SHORT).show();
                    but1.setText("WIN");
                    but2.setText("LOSE");
                    restart_p = false;
                    pause.setText("Begin");
                    b1_p = false;
                    b2_p = false;
                    x = 0;
                    y = 0;
                    sec = 0;
                }


            }
            b1_p = true;
        }
    }


    public void buttoncl_2() {

        if (restart_p == true) {
//            b2_p = true;
            menu_but.setText("Menu");
            m_r_button = true;
            //   but1.setText("");
            but2.setText("Ready");
            prb1.setProgress(x - y + 50);
            prb2.setProgress(y - x + 50);
            if (t - sec >= 10) time.setText("T:0:" + String.valueOf(t - sec));
            else if (t - sec < 10) time.setText("T:0:0" + String.valueOf(t - sec));
            score1.setText("G:" + String.valueOf(x));
            score2.setText("G:" + String.valueOf(y));
            if (b1_p == true && b2_p == true) {
                if (st)
                    //StartPlayingMusic();
                    if (y - x + 50 < 100) {
                        y++;
                        vibro();
                        t_p = true;
                        but1.setText("");
                        but2.setText("");
                        score2.setText("G:" + String.valueOf(y));
                        prb1.setProgress(x - y + 50);
                        prb2.setProgress(y - x + 50);
                        restart = false;
                    }
                if (y - x + 50 == 100) {
                    t_p = false;
                    if(sound==true) v.vibrate(150);
                    Toast.makeText(this, settings.GetName2() + " WON!!!",
                            Toast.LENGTH_SHORT).show();
                    but1.setText("LOSE");
                    but2.setText("WIN");
                    restart_p = false;
                    pause.setText("Begin");
                    b1_p = false;
                    b2_p = false;
                    x = 0;
                    y = 0;
                    sec = 0;
                }


            }
            b2_p = true;
        }
    }

    public void start() {
        if (st) {
            task = new MyTask();
            task.execute();
            st = false;
        }


    }

    public void time_refresh() {
        if (t_p == true) {
            sec++;
            if (t - sec >= 10) time.setText("T:0:" + String.valueOf(t - sec));
            else if (t - sec < 10) time.setText("T:0:0" + String.valueOf(t - sec));
            prb1.setProgress(x - y + 50);
            prb2.setProgress(y - x + 50);
            score1.setText("G:" + String.valueOf(x));
            score2.setText("G:" + String.valueOf(y));
            tpb.setProgress(sec);
        }
    }

    public void pause() {
        ButtonSound();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        pause.startAnimation(animation9);
        if (restart_p == false) {
            restart_p = true;
            pause.setText("Pause");
            but1.setText("TAP To GO");
            but2.setText("TAP To GO");
            sec = 0;
            tpb.setProgress(sec);
            prb1.setProgress(x - y + 50);
            prb2.setProgress(y - x + 50);
            score1.setText("G:" + String.valueOf(x));
            score2.setText("G:" + String.valueOf(y));
            time.setText("T:0:" + String.valueOf(t - sec));

        } else {
            t_p = false;
            but1.setText("PAUSE");
            but2.setText("PAUSE");
            b1_p = false;
            b2_p = false;
            m_r_button = false;
            menu_but.setText("Restart");
        }
    }

    public void menu_restart_button() {
        ButtonSound();
        if (m_r_button == true) {
            Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
            menu_but.startAnimation(animation9);
            st = true;
            finish();
//            Intent intent = new Intent(Multiplayer.this, General.class);
//            startActivity(intent);
        } else if (m_r_button == false) {
            Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
            menu_but.startAnimation(animation9);
            menu_but.setText("Menu");
            pause.setText("Begin");
            but1.setText("TAP Begin\nTo GO");
            but2.setText("TAP Begin\nTo GO");
            restart_p = false;
            m_r_button = true;
            b1_p = false;
            b2_p = false;
            x = 0;
            y = 0;
            sec = 0;
//            t_p=true;
            if (t - sec >= 10) time.setText("T:0:" + String.valueOf(t - sec));
            else if (t - sec < 10) time.setText("T:0:0" + String.valueOf(t - sec));
            prb1.setProgress(x - y + 50);
            prb2.setProgress(y - x + 50);
            score1.setText("G:" + String.valueOf(x));
            score2.setText("G:" + String.valueOf(y));
            tpb.setProgress(sec);
        }
    }

    public void LoadInfo() {
        //sp = getSharedPreferences("settings", MODE_PRIVATE);
        int LTheme = settings.GetTheme();
        sound = settings.GetSound();
       // music = sp.getBoolean(FILE_BOOLEAN_MUSIC,true);
        theme = LTheme;
        Names();
//        if(sp.getString(FILE_NAME1, "")== "") {
//            PlayerName1.setText("Player");
//        }
//        else PlayerName1.setText(sp.getString(FILE_NAME1, "Player"));
//        if(sp.getString(FILE_NAME2, "")== "") {
//            PlayerName2.setText("Guest");
//        }
//        else PlayerName2.setText(sp.getString(FILE_NAME2, "Guest"));

//        PlayerName1.setText(sp.getString(FILE_NAME1, "Player"));
//        PlayerName2.setText(sp.getString(FILE_NAME2, "Guest"));
    }

    public void Names() {
        if (settings.GetName1() == "") {
            PlayerName1.setText("Player");
        } else PlayerName1.setText(settings.GetName1());

        if (settings.GetName2() == "") {
            PlayerName2.setText("Guest");
        } else PlayerName2.setText(settings.GetName2());
    }

    public void ButtonSound() {
        if(sound==true)playSound(sounds[1]);
        vibro();
    }
    protected void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0, 1);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd = null;
        try {
            afd = assets.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    public void vibro() {
        if(sound==true)v.vibrate(17);
    }
}

