package com.max.skory.skoryupdate.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class Statistic extends ActionBarActivity {

    //SharedPreferences sp;
    String skill1,skill2, skill3,skill4,skill5,skill6,skill7;
    String tps1,tps2, tps3,tps4,tps5,tps6,tps7;
    TextView statistic_name_1,statistic_name_2,statistic_name_3,statistic_name_4,statistic_name_5,statistic_name_6,statistic_name_7;
    TextView statistic_tps_1,statistic_tps_2,statistic_tps_3,statistic_tps_4,statistic_tps_5,statistic_tps_6,statistic_tps_7;
    TextView statistic_mbt_1,statistic_mbt_2,statistic_mbt_3,statistic_mbt_4,statistic_mbt_5,statistic_mbt_6,statistic_mbt_7;
    TextView statistic_record_1,statistic_record_2,statistic_record_3,statistic_record_4,statistic_record_5,statistic_record_6,statistic_record_7;
    CheckBox statistic_check_1,statistic_check_2,statistic_check_3,statistic_check_4,statistic_check_5,statistic_check_6,statistic_check_7;
    String sharing_text;
    Button delete, share;
    Boolean sound, music;
    Vibrator v;

    final String FILE_BOOLEAN_SOUND = "SOUND";
  //  final String FILE_BOOLEAN_MUSIC = "MUSIC";

    final String FILE_NAME1 = "YOUR_NAME";
    final String FILE_NAME2 = "FRIENDS_NAME";

    final String FILE_NAME3 = "FIRST_NAME";
    final String FILE_NAME4 = "SECOND_NAME";
    final String FILE_NAME5 = "THIRD_NAME";
    final String FILE_NAME6 = "FOURTH_NAME";
    final String FILE_NAME7 = "FIFTH_NAME";

    final String FILE_INTEGER_RECORD1 = "RECORD";
    final String FILE_INTEGER_RECORD2 = "RECORD1";
    final String FILE_INTEGER_RECORD3 = "RECORD2";
    final String FILE_INTEGER_RECORD4 = "RECORD3";
    final String FILE_INTEGER_RECORD5 = "RECORD4";
    final String FILE_INTEGER_RECORD6 = "RECORD5";
    final String FILE_INTEGER_RECORD7 = "RECORD6";


    final Intent intent = new Intent(Intent.ACTION_SEND);
  //  final Intent intent1 = new Intent(Intent.ACTION_SEND);

    ///////////////////////////
    SoundPool mSoundPool;
    AssetManager assets;
    int[] sounds = new int[4];
    Context mContext;
    /////////////////////////

    Settings settings;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.statistic);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        delete = (Button) findViewById(R.id.delete);
        share = (Button) findViewById(R.id.share);

        settings = new Settings(getSharedPreferences("settings",MODE_PRIVATE));

        statistic_name_1 = (TextView) findViewById(R.id.statistic_name_1);
        statistic_tps_1 = (TextView) findViewById(R.id.statistic_tps_1);
        statistic_mbt_1 = (TextView) findViewById(R.id.statistic_mbt_1);
        statistic_check_1 = (CheckBox) findViewById(R.id.statistic_check_1);
        statistic_record_1 = (TextView) findViewById(R.id.statistic_record_1);

        statistic_name_2 = (TextView) findViewById(R.id.statistic_name_2);
        statistic_tps_2 = (TextView) findViewById(R.id.statistic_tps_2);
        statistic_mbt_2 = (TextView) findViewById(R.id.statistic_mbt_2);
        statistic_check_2 = (CheckBox) findViewById(R.id.statistic_check_2);
        statistic_record_2 = (TextView) findViewById(R.id.statistic_record_2);

        statistic_name_3 = (TextView) findViewById(R.id.statistic_name_3);
        statistic_tps_3 = (TextView) findViewById(R.id.statistic_tps_3);
        statistic_mbt_3 = (TextView) findViewById(R.id.statistic_mbt_3);
        statistic_check_3 = (CheckBox) findViewById(R.id.statistic_check_3);
        statistic_record_3 = (TextView) findViewById(R.id.statistic_record_3);

        statistic_name_4 = (TextView) findViewById(R.id.statistic_name_4);
        statistic_tps_4 = (TextView) findViewById(R.id.statistic_tps_4);
        statistic_mbt_4 = (TextView) findViewById(R.id.statistic_mbt_4);
        statistic_check_4 = (CheckBox) findViewById(R.id.statistic_check_4);
        statistic_record_4 = (TextView) findViewById(R.id.statistic_record_4);

        statistic_name_5 = (TextView) findViewById(R.id.statistic_name_5);
        statistic_tps_5 = (TextView) findViewById(R.id.statistic_tps_5);
        statistic_mbt_5 = (TextView) findViewById(R.id.statistic_mbt_5);
        statistic_check_5 = (CheckBox) findViewById(R.id.statistic_check_5);
        statistic_record_5 = (TextView) findViewById(R.id.statistic_record_5);

        statistic_name_6 = (TextView) findViewById(R.id.statistic_name_6);
        statistic_tps_6 = (TextView) findViewById(R.id.statistic_tps_6);
        statistic_mbt_6 = (TextView) findViewById(R.id.statistic_mbt_6);
        statistic_check_6 = (CheckBox) findViewById(R.id.statistic_check_6);
        statistic_record_6 = (TextView) findViewById(R.id.statistic_record_6);

        statistic_name_7 = (TextView) findViewById(R.id.statistic_name_7);
        statistic_tps_7 = (TextView) findViewById(R.id.statistic_tps_7);
        statistic_mbt_7 = (TextView) findViewById(R.id.statistic_mbt_7);
        statistic_check_7 = (CheckBox) findViewById(R.id.statistic_check_7);
        statistic_record_7 = (TextView) findViewById(R.id.statistic_record_7);
        //sp = getSharedPreferences("settings", MODE_PRIVATE);


       /////////////////////////////////////////////////////////////

        intent.setType("text/plain");

        ////////////////////////////////////////////////////////////
        ////////////////////////////////////////
        mContext = this;
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        assets = getAssets();


        sounds[0] = loadSound("button.wav");
        sounds[1] = loadSound("delete.wav");
        sounds[2] = loadSound("l_button.wav");
/////////////////////////////////////////////////


        Refresh();
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





    public void Back(View v){
        Back();
        finish();
    }







    public void DeleteInfo(View v) {
        Delete();
        if (statistic_check_1.isChecked() || statistic_check_2.isChecked()||statistic_check_3.isChecked()||
                statistic_check_4.isChecked()||statistic_check_5.isChecked()||statistic_check_6.isChecked()||statistic_check_7.isChecked()) {
            //SharedPreferences.Editor editor = sp.edit();
            if (statistic_check_1.isChecked()) settings.SetRecord1("0");
            if (statistic_check_2.isChecked()) settings.SetRecord2("0");

            if (statistic_check_3.isChecked()){
                settings.SetRecord3("0");
                settings.SetName3("");
            }
            if (statistic_check_4.isChecked()){
                settings.SetRecord4("0");
                settings.SetName4("");
            }
            if (statistic_check_5.isChecked()){
                settings.SetRecord5("0");
                settings.SetName5("");
            }
            if (statistic_check_6.isChecked()){
                settings.SetRecord6("0");
                settings.SetName6("");
            }
            if (statistic_check_7.isChecked()){
                settings.SetRecord7("0");
                settings.SetName7("");
            }

            //editor.commit();

            Refresh();

            Toast.makeText(this, " All chosen staff were removed.", Toast.LENGTH_LONG).show();

           delete.setEnabled(false);
           share.setEnabled(false);

            statistic_check_1.setChecked(false);
            statistic_check_2.setChecked(false);
            statistic_check_3.setChecked(false);
            statistic_check_4.setChecked(false);
            statistic_check_5.setChecked(false);
            statistic_check_6.setChecked(false);
            statistic_check_7.setChecked(false);
        }
  }








    public void Checker_check1(View v) {
        if (!statistic_record_1.getText().toString().equals("0")){
            vibro();
            delete.setEnabled(true);
           share.setEnabled(true);
        }
        else statistic_check_1.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check2(View v){
        if (!statistic_record_2.getText().toString().equals("0")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_2.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check3(View v){
        if (!statistic_record_3.getText().toString().equals("")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_3.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check4(View v){
        if (!statistic_record_4.getText().toString().equals("")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_4.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check5(View v){
        if (!statistic_record_5.getText().toString().equals("")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_5.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check6(View v){
        if (!statistic_record_6.getText().toString().equals("")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_6.setChecked(false);
        EnabledButtons();
    }
    public void Checker_check7(View v){
        if (!statistic_record_7.getText().toString().equals("")){
            vibro();
            delete.setEnabled(true);
            share.setEnabled(true);
        }
        else statistic_check_7.setChecked(false);
        EnabledButtons();
    }




    public void Refresh(){
        Ref1();
        Ref2();
        Ref3();
        Ref4();
        Ref5();
        Ref6();
        Ref7();

       sound= settings.GetSound();
//       music= sp.getBoolean(FILE_BOOLEAN_MUSIC, true);
    }

    public void Ref1(){
        tps1 = String.format("%.1f",(Integer.valueOf(settings.GetRecord1()))/30.0);
        skill1 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord1())/500.0))*100.0);
        statistic_name_1.setText(settings.GetName1());
        statistic_tps_1.setText(tps1);
        statistic_mbt_1.setText(skill1 +"%");
        statistic_record_1.setText(settings.GetRecord1());


        if (statistic_name_1.getText()!=""){
            statistic_tps_1.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_1.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_1.setBackgroundColor(Color.argb(151, 83, 83, 83));
        }
    }
    public void Ref2(){
        tps2 = String.format("%.1f",(Integer.valueOf(settings.GetRecord2()))/30.0);
        skill2 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord2())/500.0))*100.0);
        statistic_name_2.setText(settings.GetName2());
        statistic_tps_2.setText(tps2);
        statistic_mbt_2.setText(skill2 +"%");
        statistic_record_2.setText(settings.GetRecord2());

        if (statistic_name_2.getText()!=""){
            statistic_tps_2.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_2.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_2.setBackgroundColor(Color.argb(151, 83, 83, 83));
        }
    }






    public void Ref3(){

        statistic_name_3.setText(settings.GetName3());

        if (statistic_name_3.getText()!=""){
            statistic_tps_3.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_3.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_3.setBackgroundColor(Color.argb(151, 83, 83, 83));

            tps3 = String.format("%.1f",(Integer.valueOf(settings.GetRecord3()))/30.0);
            skill3 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord3())/500.0))*100.0);
            statistic_tps_3.setText(tps3);
            statistic_mbt_3.setText(skill3 +"%");
            statistic_record_3.setText(settings.GetRecord3());
        }
        else {
            statistic_tps_3.setBackgroundColor(Color.TRANSPARENT);
            statistic_mbt_3.setBackgroundColor(Color.TRANSPARENT);
            statistic_record_3.setBackgroundColor(Color.TRANSPARENT);

            tps3 = String.format("");
            statistic_tps_3.setText("");
            statistic_mbt_3.setText("");
            statistic_record_3.setText("");
        }

    }
    public void Ref4(){
        statistic_name_4.setText(settings.GetName4());

        if (statistic_name_4.getText()!=""){
            statistic_tps_4.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_4.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_4.setBackgroundColor(Color.argb(151, 83, 83, 83));

            tps4 = String.format("%.1f",(Integer.valueOf(settings.GetRecord4()))/30.0);
            skill4 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord4())/500.0))*100.0);
            statistic_tps_4.setText(tps4);
            statistic_mbt_4.setText(skill4 +"%");
            statistic_record_4.setText(settings.GetRecord4());

        }
        else {
            statistic_tps_4.setBackgroundColor(Color.TRANSPARENT);
            statistic_mbt_4.setBackgroundColor(Color.TRANSPARENT);
            statistic_record_4.setBackgroundColor(Color.TRANSPARENT);

            tps4 = String.format("");
            statistic_tps_4.setText("");
            statistic_mbt_4.setText("");
            statistic_record_4.setText("");
        }
    }
    public void Ref5(){
        statistic_name_5.setText(settings.GetName5());

        if (statistic_name_5.getText()!=""){
            statistic_tps_5.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_5.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_5.setBackgroundColor(Color.argb(151, 83, 83, 83));

            tps5 = String.format("%.1f",(Integer.valueOf(settings.GetRecord5()))/30.0);
            skill5 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord5())/500.0))*100.0);
            statistic_tps_5.setText(tps5);
            statistic_mbt_5.setText(skill5 +"%");
            statistic_record_5.setText(settings.GetRecord5());
        }
        else {
            statistic_tps_5.setBackgroundColor(Color.TRANSPARENT);
            statistic_mbt_5.setBackgroundColor(Color.TRANSPARENT);
            statistic_record_5.setBackgroundColor(Color.TRANSPARENT);

            tps5 = String.format("");
            statistic_tps_5.setText("");
            statistic_mbt_5.setText("");
            statistic_record_5.setText("");
        }
    }
    public void Ref6(){
        statistic_name_6.setText(settings.GetName6());

        if (statistic_name_6.getText()!=""){
            statistic_tps_6.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_6.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_6.setBackgroundColor(Color.argb(151, 83, 83, 83));

            tps6 = String.format("%.1f",(Integer.valueOf(settings.GetRecord6()))/30.0);
            skill6 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord6())/500.0))*100.0);
            statistic_tps_6.setText(tps6);
            statistic_mbt_6.setText(skill6 +"%");
            statistic_record_6.setText(settings.GetRecord6());
        }
        else {
            statistic_tps_6.setBackgroundColor(Color.TRANSPARENT);
            statistic_mbt_6.setBackgroundColor(Color.TRANSPARENT);
            statistic_record_6.setBackgroundColor(Color.TRANSPARENT);

            tps6 = String.format("");
            statistic_tps_6.setText("");
            statistic_mbt_6.setText("");
            statistic_record_6.setText("");
        }
    }
    public void Ref7() {
        statistic_name_7.setText(settings.GetName7());

        if (statistic_name_7.getText() != "") {
            statistic_tps_7.setBackgroundColor(Color.argb(147, 181, 181, 181));
            statistic_mbt_7.setBackgroundColor(Color.argb(153, 117, 117, 117));
            statistic_record_7.setBackgroundColor(Color.argb(151, 83, 83, 83));

            tps7 = String.format("%.1f", (Integer.valueOf(settings.GetRecord7())) / 30.0);
            skill7 = String.format("%.1f", (Math.abs(Integer.valueOf(settings.GetRecord7()) / 500.0)) * 100.0);
            statistic_tps_7.setText(tps7);
            statistic_mbt_7.setText(skill7 + "%");
            statistic_record_7.setText(settings.GetRecord7());
        } else {
            statistic_tps_7.setBackgroundColor(Color.TRANSPARENT);
            statistic_mbt_7.setBackgroundColor(Color.TRANSPARENT);
            statistic_record_7.setBackgroundColor(Color.TRANSPARENT);

            tps7 = String.format("");
            statistic_tps_7.setText("");
            statistic_mbt_7.setText("");
            statistic_record_7.setText("");
        }
    }
    public void EnabledButtons(){
        if(!statistic_check_1.isChecked()&!statistic_check_2.isChecked()&!statistic_check_3.isChecked()&
                !statistic_check_4.isChecked()&!statistic_check_5.isChecked()&!statistic_check_6.isChecked()&!statistic_check_7.isChecked()){
            delete.setEnabled(false);
            share.setEnabled(false);
        }
    }
    public void Share(View v){
        Share();
        String share_1="",share_2="",share_3="",share_4="",share_5="",share_6="",share_7="";

        if(statistic_check_1.isChecked())share_1 = String.valueOf("\""+statistic_name_1.getText()+"\""+" - "+"TPS \""+ statistic_tps_1.getText()+"\" Skill \""+statistic_mbt_1.getText()+"\" RECORD \""+statistic_record_1.getText()+"\"\n");
        if(statistic_check_2.isChecked())share_2 = String.valueOf("\""+statistic_name_2.getText()+"\""+" - "+"TPS \""+ statistic_tps_2.getText()+"\" Skill \""+statistic_mbt_2.getText()+"\" RECORD \""+statistic_record_2.getText()+"\"\n");
        if(statistic_check_3.isChecked())share_3 = String.valueOf("\""+statistic_name_3.getText()+"\""+" - "+"TPS \""+ statistic_tps_3.getText()+"\" Skill \""+statistic_mbt_3.getText()+"\" RECORD \""+statistic_record_3.getText()+"\"\n");
        if(statistic_check_4.isChecked())share_4 = String.valueOf("\""+statistic_name_4.getText()+"\""+" - "+"TPS \""+ statistic_tps_4.getText()+"\" Skill \""+statistic_mbt_4.getText()+"\" RECORD \""+statistic_record_4.getText()+"\"\n");
        if(statistic_check_5.isChecked())share_5 = String.valueOf("\""+statistic_name_5.getText()+"\""+" - "+"TPS \""+ statistic_tps_5.getText()+"\" Skill \""+statistic_mbt_5.getText()+"\" RECORD \""+statistic_record_5.getText()+"\"\n");
        if(statistic_check_6.isChecked())share_6 = String.valueOf("\""+statistic_name_6.getText()+"\""+" - "+"TPS \""+ statistic_tps_6.getText()+"\" Skill \""+statistic_mbt_6.getText()+"\" RECORD \""+statistic_record_6.getText()+"\"\n");
        if(statistic_check_7.isChecked())share_7 = String.valueOf("\""+statistic_name_7.getText()+"\""+" - "+"TPS \""+ statistic_tps_7.getText()+"\" Skill \""+statistic_mbt_7.getText()+"\" RECORD \""+statistic_record_7.getText()+"\"\n");

        sharing_text ="eBash POST \n\n#eBash #EnergyBash #newrecord\n\nI have just set a new RECORD! Check this out.\n\n" + share_1 +share_2+share_3+share_4+share_5+share_6+share_7;

     //   File image = new File(R.drawable.baner);
     //   Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baner);




        intent.putExtra(Intent.EXTRA_TEXT, sharing_text);
//        intent = new Intent(Intent.ACTION_, Uri.parse("http://developer.android.com"));
//        startActivity(intent);

     //   File image = new File(String.valueOf(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.baner)));
      //  intent.putExtra(Intent.EXTRA_STREAM,(image));



        try
        {
            startActivity(Intent.createChooser(intent, "Choose your app to Share"));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(getApplicationContext(), "Some error", Toast.LENGTH_SHORT).show();
        }
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
        public void vibro(){
            if(sound==true) v.vibrate(15);
        }
        public void Back() {
            vibro();
            if(sound==true) playSound(sounds[2]);
        }

        public void Delete() {
            vibro();
            if(sound==true)playSound(sounds[1]);
        }

        public void Share() {
            vibro();
            if(sound==true)playSound(sounds[0]);
        }


    }

