package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;


public class Options extends Activity {
    LinearLayout layout;
    Button apply;
    RadioButton bw,by,m;
    Switch soundSwitch;
    Switch musicSwitch;
    int theme;
    Vibrator v;
    EditText text,text2;
    //SharedPreferences sp;

    //Boolean LBooleanSound,LBooleanMusic;

    Settings settings;

    final String FILE_NAME1 = "YOUR_NAME";
    final String FILE_NAME2 = "FRIENDS_NAME";
    final String FILE_BOOLEAN_SOUND = "SOUND";
    final String FILE_BOOLEAN_MUSIC = "MUSIC";
    final String FILE_INTEGER_THEME = "THEME";


    ///////////////////////////
    SoundPool mSoundPool;
    AssetManager assets;
    int[] sounds = new int[4];
    Context mContext;
    /////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optios);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        apply = (Button)findViewById(R.id.button);
        bw = (RadioButton)findViewById(R.id.radioButton);
        by = (RadioButton)findViewById(R.id.radioButton2);
        m = (RadioButton)findViewById(R.id.radioButton3);
        soundSwitch = (Switch)findViewById(R.id.switch1);
        musicSwitch = (Switch)findViewById(R.id.switch2);
        layout = (LinearLayout) findViewById(R.id.theme);
        text = (EditText)findViewById(R.id.pl_yourname);
        text2 = (EditText)findViewById(R.id.pl_friendsname);
        settings = new Settings(getSharedPreferences("settings",MODE_PRIVATE));
        loadtext();
        if(theme ==0) {
            layout.setBackgroundResource(R.drawable.bw_s7);
            apply.setTextColor(Color.WHITE);
            apply.setBackgroundResource(R.drawable.bw_s4);
        }
        else if(theme==1){
            layout.setBackgroundResource(R.drawable.by_s7);
            apply.setTextColor(Color.rgb(255,225,0));
            apply.setBackgroundResource(R.drawable.by_s4);
        }
        else{
            layout.setBackgroundResource(R.drawable.rb_s7);
            apply.setTextColor(Color.rgb(255,136,0));
            apply.setBackgroundResource(R.drawable.rb_s8);
        }

        ////////////////////////////////////////
        mContext = this;
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        assets = getAssets();


        sounds[0] = loadSound("l_button.wav");
        sounds[1] = loadSound("switch_off.wav");
        sounds[2] = loadSound("themes.wav");
/////////////////////////////////////////////////

    }
    @Override
    protected void onPause() {
        super.onPause();
        savetext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void apply(View v){
        ButtonSound();
        Animation animation5 = AnimationUtils.loadAnimation(this,R.anim.but_ani);
        apply.startAnimation(animation5);
        String n1 = (text.getText()).toString();
        String n2 = (text2.getText()).toString();

        if (text.getText().toString().equals("")||text2.getText().toString().equals("")){
            Toast.makeText(this,"Extremely short name!",Toast.LENGTH_SHORT).show();
        }
        else  finish();

    }
    public void black_white(View v) {
        ThemeSound();
        theme = 0;
        by.setChecked(false);
        m.setChecked(false);
        layout.setBackgroundResource(R.drawable.bw_s7);
        apply.setTextColor(Color.WHITE);
        apply.setBackgroundResource(R.drawable.bw_s4);
    }
    public void black_yellow(View v){
        ThemeSound();
        theme = 1;
        bw.setChecked(false);
        m.setChecked(false);
        layout.setBackgroundResource(R.drawable.by_s7);
        apply.setTextColor(Color.rgb(255,225,0));
        apply.setBackgroundResource(R.drawable.by_s4);
    }
    public void multicolour(View v) {
        ThemeSound();
        theme =2;
        bw.setChecked(false);
        by.setChecked(false);
        layout.setBackgroundResource(R.drawable.rb_s7);
        apply.setTextColor(Color.rgb(255,136,0));
        apply.setBackgroundResource(R.drawable.rb_s8);

    }


    private void savetext(){
        settings.SetName1( text.getText().toString());
        settings.SetName2( text2.getText().toString());
        settings.SetSound(soundSwitch.isChecked());
        settings.SetMusic( musicSwitch.isChecked());
        settings.SetTheme(theme);


    }
    public void loadtext(){
        String LText = settings.GetName1();
        String LText2 = settings.GetName2();
        Integer LIntegerTheme = settings.GetTheme();

        theme = LIntegerTheme;
        if(LText!="") {
            text.setText(LText);
        }
        else text.setText("Player");
        if(LText2!="") {
            text2.setText(LText2);
        }
        else text2.setText("Guest");
        soundSwitch.setChecked(settings.GetSound());
        musicSwitch.setChecked(settings.GetMusic());
        if (LIntegerTheme == 0) { bw.setChecked(true);by.setChecked(false); m.setChecked(false);}
        else if (LIntegerTheme == 1) { bw.setChecked(false);by.setChecked(true); m.setChecked(false);}
        else  {bw.setChecked(false);by.setChecked(false); m.setChecked(true);}
    }

    public void Switch(View v){ ButtonSound();}

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
    public void ButtonSound(){
        vibro();
        if(settings.GetSound()==true)button_sound();
    }
    public void CheckerSound(View v){
        vibro();
        if(settings.GetSound()==true)switchBut();

    }
    public void ThemeSound(){
        vibro();
        if(settings.GetSound()==true)themes();
    }
    public void vibro(){
        if(settings.GetSound()==true)v.vibrate(15);
    }
    public void button_sound() {
        playSound(sounds[0]);
    }

    public void switchBut() {   playSound(sounds[1]); }

    public void themes() {
        playSound(sounds[2]);
    }

}
