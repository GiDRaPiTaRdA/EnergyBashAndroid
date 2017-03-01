package com.max.skory.skoryupdate.app;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Max on 08/17/16.
 */
public class Settings extends Activity{


    SharedPreferences sp;
    final String FILE_NAME1 = "YOUR_NAME";
    final String FILE_NAME2 = "FRIENDS_NAME";
    final String FILE_BOOLEAN_SOUND = "SOUND";
    final String FILE_BOOLEAN_MUSIC = "MUSIC";
    final String FILE_INTEGER_THEME = "THEME";


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

    public Settings(SharedPreferences sp){
        this.sp = sp;
    }

    //GET///////////////////////////////////////////////////////////////////////////////////////////
    public boolean GetMusic() {
        return sp.getBoolean(FILE_BOOLEAN_MUSIC, true);
    }
    public boolean GetSound() {
        return sp.getBoolean(FILE_BOOLEAN_SOUND, true);
    }
    public int GetTheme() {
        return sp.getInt(FILE_INTEGER_THEME, 1);
    }

    public String GetName1() {
        return sp.getString(FILE_NAME1, "Player");
    }
    public String GetName2() {
        return sp.getString(FILE_NAME2, "Guest");
    }

    public String GetName3() {
        return sp.getString(FILE_NAME3, "Player");
    }
    public String GetName4() {
        return sp.getString(FILE_NAME4, "Player");
    }
    public String GetName5() {
        return sp.getString(FILE_NAME5, "Player");
    }
    public String GetName6() {
        return sp.getString(FILE_NAME6, "Player");
    }
    public String GetName7() {
        return sp.getString(FILE_NAME7, "Player");
    }

    public String GetRecord1() {
        return sp.getString(FILE_INTEGER_RECORD1, "0");
    }
    public String GetRecord2() {
        return sp.getString(FILE_INTEGER_RECORD2, "0");
    }
    public String GetRecord3() {
        return sp.getString(FILE_INTEGER_RECORD3, "0");
    }
    public String GetRecord4() {
        return sp.getString(FILE_INTEGER_RECORD4, "0");
    }
    public String GetRecord5() {
        return sp.getString(FILE_INTEGER_RECORD5, "0");
    }
    public String GetRecord6() {
        return sp.getString(FILE_INTEGER_RECORD6, "0");
    }
    public String GetRecord7() {
        return sp.getString(FILE_INTEGER_RECORD7, "0");
    }

    //SET///////////////////////////////////////////////////////////////////////////////////////////
    public void SetMusic(boolean music) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FILE_BOOLEAN_MUSIC, sp.getBoolean("Music", music));
        editor.commit();
    }
    public void SetSound(boolean sound) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FILE_BOOLEAN_SOUND, sp.getBoolean("Sound", sound));
        editor.commit();
    }
    public void SetTheme(int theme) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(FILE_INTEGER_THEME, theme);
        editor.commit();
    }

    public void SetName1(String name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME1, name);
        editor.commit();
    }
    public void SetName2(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME2, name);
        editor.commit();
    }

    public void SetName3(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME3, name);
        editor.commit();
    }
    public void SetName4(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME4, name);
        editor.commit();
    }
    public void SetName5(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME5, name);
        editor.commit();
    }
    public void SetName6(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME6, name);
        editor.commit();
    }
    public void SetName7(String  name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_NAME7, name);
        editor.commit();
    }

    public void SetRecord1(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD1, record);
        editor.commit();
    }
    public void SetRecord2(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD2, record);
        editor.commit();
    }
    public void SetRecord3(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD3, record);
        editor.commit();
    }
    public void SetRecord4(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD4, record);
        editor.commit();
    }
    public void SetRecord5(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD5, record);
        editor.commit();
    }
    public void SetRecord6(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD6, record);
        editor.commit();
    }
    public void SetRecord7(String record) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FILE_INTEGER_RECORD7, record);
        editor.commit();
    }
}