package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.app.DialogFragment;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Singleplayer extends Activity{

    LinearLayout button_space,record_line, time_score;
    Button but1,pause,menu;
    TextView score,time,name,record_view;
    ProgressBar prbrecord;
    ProgressBar prbsec;
    Vibrator v;
    int x = 0;
    int s = 30;
    int sec = s;
    int record = 0;
    MyTask task;
    boolean st = true;
    boolean rmsg = true;
    boolean pause_p = true;
    boolean task_st = true;
    boolean begin;
    boolean lag = true;
    boolean sound,music;
    int theme;
    int z=150;
    int t = 17;//17
    String ChName,ChName1, record1, record2,OtherPlayer1,OtherPlayer2,OtherPlayer3,OtherPlayer4,OtherPlayer5,OtherRecord1,OtherRecord2,OtherRecord3,OtherRecord4,OtherRecord5;
    DialogFragment dialog;


    Settings settings;
    //SharedPreferences sp;
//    final String FILE_INTEGER_RECORD1 = "RECORD";
//    final String FILE_INTEGER_RECORD2 = "RECORD1";
//    final String FILE_INTEGER_RECORD3 = "RECORD2";
//    final String FILE_INTEGER_RECORD4 = "RECORD3";
//    final String FILE_INTEGER_RECORD5 = "RECORD4";
//    final String FILE_INTEGER_RECORD6 = "RECORD5";
//    final String FILE_INTEGER_RECORD7 = "RECORD6";
//    final String FILE_INTEGER_THEME = "THEME";
//    final String FILE_NAME1 = "YOUR_NAME";
//    final String FILE_NAME2 = "FRIENDS_NAME";
//    final String FILE_NAME3 = "FIRST_NAME";
//    final String FILE_NAME4 = "SECOND_NAME";
//    final String FILE_NAME5 = "THIRD_NAME";
//    final String FILE_NAME6 = "FOURTH_NAME";
//    final String FILE_NAME7 = "FIFTH_NAME";
//    final String FILE_BOOLEAN_SOUND = "SOUND";

 //   final String FILE_BOOLEAN_MUSIC = "MUSIC";


    ///////////////////////////
    SoundPool mSoundPool;
    AssetManager assets;
    int[] sounds = new int[4];
    Context mContext;
    /////////////////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.singleplayer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        but1 = (Button) findViewById(R.id.knopka);
        pause = (Button) findViewById(R.id.pause);
        menu = (Button) findViewById(R.id.menu);
        button_space = (LinearLayout) findViewById(R.id.button_space);
        record_line = (LinearLayout) findViewById(R.id.record_line);
        time_score = (LinearLayout) findViewById(R.id.time_score);
        score = (TextView) findViewById(R.id.gamer_points);
        time = (TextView) findViewById(R.id.seconds);
        record_view = (TextView) findViewById(R.id.record_view);
        prbrecord = (ProgressBar) findViewById(R.id.prbr);
        prbsec = (ProgressBar) findViewById(R.id.prbs);
        name = (TextView) findViewById(R.id.name);
        settings = new Settings(getSharedPreferences("settings",MODE_PRIVATE));

////////////////////////////////////////////////////////////////

       findViewById(R.id.knopka).setOnTouchListener(new OnTouchListener() {
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
        findViewById(R.id.pause).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pause_s();
                        break;


                }
                return false;
            }
        });
        findViewById(R.id.menu).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        menu();
                        break;


                }
                return false;
            }
        });

        //////////////////////////////////////////////////////////////////

        dialog = new ChName();

        timer_first_start();
        prbsec.setMax(s);
        LoadInfo();

        if (theme == 0) {
            but1.setBackgroundResource(R.drawable.bw_s1);
            button_space.setBackgroundResource(R.drawable.bw_s5);
            record_line.setBackgroundResource(R.drawable.bw_s3);
            time_score.setBackgroundResource(R.drawable.bw_s2);
            menu.setBackgroundResource(R.drawable.bw_s4);
            pause.setBackgroundResource(R.drawable.bw_s4);
            name.setTextColor(Color.WHITE);
            record_view.setTextColor(Color.WHITE);
            score.setTextColor(Color.WHITE);
            menu.setTextColor(Color.WHITE);
            pause.setTextColor(Color.WHITE);
            time.setTextColor(Color.WHITE);
            but1.setTextColor(Color.CYAN);

        }
        else if (theme == 1) {
            but1.setBackgroundResource(R.drawable.by_s1);
            button_space.setBackgroundResource(R.drawable.by_s5);
            record_line.setBackgroundResource(R.drawable.by_s3);
            time_score.setBackgroundResource(R.drawable.by_s2);
            menu.setBackgroundResource(R.drawable.by_s4);
            pause.setBackgroundResource(R.drawable.by_s4);
            name.setTextColor(Color.BLACK);
            record_view.setTextColor(Color.BLACK);
            score.setTextColor(Color.WHITE);
            menu.setTextColor(Color.WHITE);
            pause.setTextColor(Color.WHITE);
            time.setTextColor(Color.WHITE);

        }
        else {
            but1.setBackgroundResource(R.drawable.rb_s1);
            button_space.setBackgroundResource(R.drawable.bw_s5);
            record_line.setBackgroundResource(R.drawable.rb_s3);
            time_score.setBackgroundResource(R.drawable.rb_s2);
            menu.setBackgroundResource(R.drawable.rb_s4);
            pause.setBackgroundResource(R.drawable.rb_s4);
            score.setTextColor(Color.rgb(150, 255, 0));
            menu.setTextColor(Color.CYAN);
            pause.setTextColor(Color.CYAN);
            time.setTextColor(Color.rgb(141, 34, 255));
            name.setTextColor(Color.rgb(255, 136, 0));
            record_view.setTextColor(Color.rgb(255, 136, 0));
            but1.setTextColor(Color.RED);
        }
        name.setText(ChName);
        ////////////////////////////////////////
        mContext = this;
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        assets = getAssets();

        sounds[1] = loadSound("record.wav");
        sounds[2] = loadSound("l_button.wav");

/////////////////////////////////////////////////
        Ref();
        if((record_view.getText().toString().equals("0"))&pause.getText().toString().equals("Begin"))Toast.makeText(this,"Let's dial your first score!",Toast.LENGTH_LONG).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.general, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.singleplayer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        record_view = (TextView) findViewById(R.id.record_view);
        return super.onOptionsItemSelected(item);
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

            if (pause_p == true) {
                if (begin == true) {
                    sec--;
                    prbsec.setProgress(s - sec);
                }
                if (sec >= 10) time.setText("T:0:" + String.valueOf(sec));

                else if (sec < 10) time.setText("T:0:0" + String.valueOf(sec));
                if (sec <= 0) {
                    begin();
                }

            }
        }

    }



    public void buttoncl_1() {
        if (begin == true) {
            pause_p = true;
            if (lag == true) {
                but1.setText("");
                menu.setText("Menu");
                lag = false;
            }
            if (name.getText().toString() == ChName) record = Integer.valueOf(record1);
            else if (name.getText().toString() == ChName1) record = Integer.valueOf(record2);
            else if ( OtherPlayer1.toString().equals(name.getText().toString())) record = Integer.valueOf(OtherRecord1);
            else if ( OtherPlayer2.toString().equals(name.getText().toString())) record = Integer.valueOf(OtherRecord2);
            else if ( OtherPlayer3.toString().equals(name.getText().toString())) record = Integer.valueOf(OtherRecord3);
            else if ( OtherPlayer4.toString().equals(name.getText().toString())) record = Integer.valueOf(OtherRecord4);
            else if ( OtherPlayer5.toString().equals(name.getText().toString())) record = Integer.valueOf(OtherRecord5);
            if (record==0)prbrecord.setMax(1);
            else prbrecord.setMax(record);

            x++;
            PlayButtonSound();
            prbrecord.setProgress(x);

            score.setText("G:" + String.valueOf(x));
            ///////
            SaveInfo();
            /////////
            if (x == record+1 && rmsg == true) {
            Toast.makeText(this, "New Record!!!",
                    Toast.LENGTH_SHORT).show();
               record();

            rmsg = false;
        }
            if (record <= x) {
                record = x;
                record_view.setText(String.valueOf(record));
                SaveInfo();
            }

        }
    }

    public void Change_name(View v){
        ButtonSound();
        Animation but_ani = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        record_view.startAnimation(but_ani);

        if(pause.getText().toString().equals("Begin")||but1.getText().toString()=="TAP To GO") {
            dialog.show(getFragmentManager(), "Dialog1");
        }
        else Toast.makeText(this,"You can not add the player now,please restart the game.",Toast.LENGTH_LONG).show();


    }

    public void timer_first_start() {
        if (task_st == true) {
            task = new MyTask();
            task.execute();
            task_st = false;
        }
    }

    public void timer_stop() {
        task.cancel(true);
    }

    public void pause_s() {
        ButtonSound();
        if (begin == false) {
              pause();
        } else {
            begin();
        }
    }

    public void begin(){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        pause.startAnimation(animation9);
        if(sound==true)if(sound==true) v.vibrate(z);
        prbsec.setProgress(s - sec);
        if (record < x) record = x;
        record_view.setText(String.valueOf(record));
        //   st = true;
        rmsg = true;
        x = 0;
        sec = s;
        pause.setText("Begin");
        but1.setText("TAP\n Begin To\n GO");
        begin = false;
        pause_p = false;
        SaveInfo();
        LoadInfo();
    }

    public  void pause(){
        prbsec.setProgress(s - sec);
        prbrecord.setProgress(x);
        time.setText("T:0:" + String.valueOf(sec));
        score.setText("G:" + String.valueOf(x));
        pause.setText("Restart");
        but1.setText("TAP To GO");
        lag = true;
        begin = true;
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        pause.startAnimation(animation9);
    }

//    public void restart() {
//            SaveInfo();
//            Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
//            PausePlayingMusic.startAnimation(animation9);
//            rmsg = true;
//            sec = s;
//            x = 0;
//            time.setText("T:0:" + String.valueOf(sec));
//            score.setText("G:" + String.valueOf(x));
//            prbsec.setProgress(s - sec);
//            prbrecord.setProgress(x);
//            but1.setText("TAP To GO");
//            pause_p = false;
//            PausePlayingMusic.setText("Begin");
//            LoadInfo();
//             Ref();
//}

    public void menu(){
        ButtonSound();
        if (menu.getText() != "Restart" || pause_p == true) {
            Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
            menu.startAnimation(animation9);
            SaveInfo();
            finish();
        }
    }

    public void Change(View v){
        ButtonSound();
        Animation but_ani = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        name.startAnimation(but_ani);

        if(pause.getText().toString().equals("Begin")||but1.getText().toString()=="TAP To GO") {
        Swag();
            LoadInfo();
            score.setText("G:0");

        }
        else Toast.makeText(this,"You can not change the player now,please restart the game.",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer_stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pause_p = false;
    }

    public void SaveInfo() {
        //sp = getSharedPreferences("settings",MODE_PRIVATE);
        //SharedPreferences.Editor editor = sp.edit();
        if (name.getText().toString() == ChName){ settings.SetRecord1(record_view.getText().toString());}
        else if (name.getText().toString() == ChName1){settings.SetRecord2(record_view.getText().toString());}
        else if ( OtherPlayer1.toString().equals(name.getText().toString()))settings.SetRecord3(record_view.getText().toString());
        else if ( OtherPlayer2.toString().equals(name.getText().toString()))settings.SetRecord4(record_view.getText().toString());
        else if ( OtherPlayer3.toString().equals(name.getText().toString()))settings.SetRecord5(record_view.getText().toString());
        else if ( OtherPlayer4.toString().equals(name.getText().toString()))settings.SetRecord6(record_view.getText().toString());
        else if ( OtherPlayer5.toString().equals(name.getText().toString()))settings.SetRecord7(record_view.getText().toString());
        else Toast.makeText(this,"ERROR SAVE INFO",Toast.LENGTH_LONG).show();
        //editor.commit();
    }

    public void LoadInfo() {
        //sp = getSharedPreferences("settings",MODE_PRIVATE);
        record1 = settings.GetRecord1();
        record2 = settings.GetRecord2();
        OtherRecord1 = settings.GetRecord3();
        OtherRecord2 = settings.GetRecord4();
        OtherRecord3 = settings.GetRecord5();
        OtherRecord4 = settings.GetRecord6();
        OtherRecord5 = settings.GetRecord7();
        sound = settings.GetSound();
      //  music = sp.getBoolean(FILE_BOOLEAN_MUSIC,true);
        int LTheme = settings.GetTheme();
        String name = settings.GetName1();
        String name1 = settings.GetName2();
        ChName = name;
        ChName1 = name1;
        OtherPlayer1=settings.GetName3();
        OtherPlayer2=settings.GetName4();
        OtherPlayer3=settings.GetName5();
        OtherPlayer4=settings.GetName6();
        OtherPlayer5=settings.GetName7();
        theme = LTheme;
    }
    public void Swag(){
        if (ChName == name.getText()) {
            name.setText(ChName1);
            record_view.setText(record2);
        } else {
            name.setText(ChName);
            record_view.setText(record1);
        }
        prbsec.setProgress(s - sec);
        prbrecord.setProgress(x);
    }
    public void ex( String a){

        name.setText(a);

        if(a.toString().equals(settings.GetName3()))record_view.setText(settings.GetRecord3());
        else if(a.toString().equals(settings.GetName4()))record_view.setText(settings.GetRecord4());
        else if(a.toString().equals(settings.GetName5()))record_view.setText(settings.GetRecord5());
        else if(a.toString().equals(settings.GetName6()))record_view.setText(settings.GetRecord6());
        else if(a.toString().equals(settings.GetName7()))record_view.setText(settings.GetRecord7());
        else record_view.setText("0");

        x = 0;
        time.setText("T:0:" + String.valueOf(sec));
        score.setText("G:" + String.valueOf(x));
        prbsec.setProgress(s - sec);
        prbrecord.setProgress(x);

        LoadInfo();
        SaveInfo();
        Ref();
    }
    public  void Ref(){
        if (ChName == name.getText().toString())record_view.setText(record1);
        else if(ChName1 == name.getText().toString()) record_view.setText(record2);
        else if( OtherPlayer1.toString().equals(name.getText().toString())) record_view.setText(settings.GetRecord3());
        else if( OtherPlayer2.toString().equals(name.getText().toString())) record_view.setText(settings.GetRecord4());
        else if( OtherPlayer3.toString().equals(name.getText().toString())) record_view.setText(settings.GetRecord5());
        else if( OtherPlayer4.toString().equals(name.getText().toString())) record_view.setText(settings.GetRecord6());
        else if( OtherPlayer5.toString().equals(name.getText().toString())) record_view.setText(settings.GetRecord7());
        else record_view.setText("ERROR "+OtherPlayer1.toString()+"-opl "+name.getText().toString()+"-name.getT");
    }

    protected void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0, 1);
        mSoundPool.setVolume(200,200,200);
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
    public void PlayButtonSound(){
        vibro();
    }
    public void ButtonSound(){
        vibro();
        ButtonSoundS();
    }

    public void vibro(){
        if(sound==true) {
           v.vibrate(t);
        }
    }
    public void ButtonSoundS() { if(sound==true) playSound(sounds[2]);
    }
    public void record() {
        if(sound==true) playSound(sounds[1]);
        if(sound==true)v.vibrate(z);
    }
}