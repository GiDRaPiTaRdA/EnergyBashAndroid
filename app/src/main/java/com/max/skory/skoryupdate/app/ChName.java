package com.max.skory.skoryupdate.app;


import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ChName extends DialogFragment {

   // final String LOG_TAG = "myLogs";
    Button b1,b2;
    LinearLayout background;
    EditText name_field;
    Singleplayer singleplayer;
    General general;
//    public interface onSomeEventListener {
//        public void someEvent(String s);
//    }

//    onSomeEventListener someEventListener;

    SharedPreferences sp;
    final String FILE_INTEGER_THEME = "THEME";
    final String FILE_NAME3 = "FIRST_NAME";
    final String FILE_NAME4 = "SECOND_NAME";
    final String FILE_NAME5 = "THIRD_NAME";
    final String FILE_NAME6 = "FOURTH_NAME";
    final String FILE_NAME7 = "FIFTH_NAME";
    final String FILE_INTEGER_RECORD3 = "RECORD2";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View lay = inflater.inflate(R.layout.fragment_change, null);


//////////////////////////////////////////////////////////////////////////////////
//       LayoutInflater inflater1;
//       inflater1 = getActivity().getLayoutInflater();
//        View view = inflater1.inflate (R.layout.singleplayer, null);
//
//        myTextView = (TextView)view.findViewById(R.id.name);
//        myTextView.setBackgroundColor(Color.WHITE);


        if (getActivity() != null) {
             singleplayer = (Singleplayer) getActivity();
        }


/////////////////////////////////////////////////////////////////////////////////////


      //  View lay = inflater.inflate(R.layout.fragment_change, container, false);
//        lay.findViewById(R.id.apply).setOnClickListener(this);
//        lay.findViewById(R.id.cancel).setOnClickListener(this);
        background = (LinearLayout)lay.findViewById(R.id.background);
        b1 = (Button)lay.findViewById(R.id.apply);
        b1.setOnClickListener(new mycl1());
        b2 = (Button)lay.findViewById(R.id.cancel);
        b2.setOnClickListener(new mycl2());

        name_field = (EditText)lay.findViewById(R.id.add_field);
        name_field.setText("");
        getDialog().setTitle("Add a new player");
        Load();


        return lay;
    }

    public void Load(){
         sp = getActivity().getSharedPreferences("settings",0);

        int LTheme = sp.getInt(FILE_INTEGER_THEME, 1);
        if (LTheme==0){
            background.setBackgroundColor(Color.WHITE);
        }
        else if (LTheme==1){
            background.setBackgroundColor(Color.rgb(246, 225, 0));
        }
        else{
            background.setBackgroundColor(Color.rgb(220,20,60));
        }

    }

//    public void onClick(View v) {
//        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
//
//        dismiss();
//    }
//
//    public void onDismiss(DialogInterface dialog) {
//        super.onDismiss(dialog);
//        Log.d(LOG_TAG, "Dialog 1: onDismiss");
//    }
//
//    public void onCancel(DialogInterface dialog) {
//        super.onCancel(dialog);
//        Log.d(LOG_TAG, "Dialog 1: onCancel");
//    }




    class mycl1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {

          //  singleplayer.ex((name_field.getText()).toString());
            String FN3 = sp.getString(FILE_NAME3, "");
            String FN4 = sp.getString(FILE_NAME4, "");
            String FN5 = sp.getString(FILE_NAME5, "");
            String FN6 = sp.getString(FILE_NAME6, "");
            String FN7 = sp.getString(FILE_NAME7, "");
            sp = getActivity().getSharedPreferences("settings",0);
            SharedPreferences.Editor editor = sp.edit();
            if (name_field.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Extremely short name!", Toast.LENGTH_SHORT).show();
            }
        else{
              if(name_field.getText().toString().equals(sp.getString(FILE_NAME3,"")))editor.putString(FILE_NAME3, name_field.getText().toString());
              else if(name_field.getText().toString().equals(sp.getString(FILE_NAME4,"")))editor.putString(FILE_NAME4, name_field.getText().toString());
              else if(name_field.getText().toString().equals(sp.getString(FILE_NAME5,"")))editor.putString(FILE_NAME5, name_field.getText().toString());
              else if(name_field.getText().toString().equals(sp.getString(FILE_NAME6,"")))editor.putString(FILE_NAME6, name_field.getText().toString());
              else if(name_field.getText().toString().equals(sp.getString(FILE_NAME7,"")))editor.putString(FILE_NAME7, name_field.getText().toString());
                else {
                  if (FN3 == "") {
                      editor.putString(FILE_NAME3, name_field.getText().toString());
                      editor.commit();
                      Toast.makeText(getActivity(), "Created account: \"" + sp.getString(FILE_NAME3, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  } else if (FN4 == "") {
                      editor.putString(FILE_NAME4, name_field.getText().toString());
                      editor.commit();
                      Toast.makeText(getActivity(), "Created account: as \"" + sp.getString(FILE_NAME4, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  } else if (FN5 == "") {
                      editor.putString(FILE_NAME5, name_field.getText().toString());
                      editor.commit();
                      Toast.makeText(getActivity(), "Created account: as \"" + sp.getString(FILE_NAME5, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  } else if (FN6 == "") {
                      editor.putString(FILE_NAME6, name_field.getText().toString());
                      editor.commit();
                      Toast.makeText(getActivity(), "Created account: as \"" + sp.getString(FILE_NAME6, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  } else if (FN7 == "") {
                      editor.putString(FILE_NAME7, name_field.getText().toString());
                      editor.commit();
                      Toast.makeText(getActivity(), "Created account: as \"" + sp.getString(FILE_NAME7, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(getActivity(), "There is no space for a new name,\n the first name will be ReWritten", Toast.LENGTH_LONG).show();
                      editor.putString(FILE_NAME3, name_field.getText().toString());
                      editor.putString(FILE_INTEGER_RECORD3, "0");
                      editor.commit();
                      Toast.makeText(getActivity(), "ReCreated, account: \"" + sp.getString(FILE_NAME3, "ERROR") + "\"", Toast.LENGTH_SHORT).show();
                  }
              }
                singleplayer.ex(name_field.getText().toString());
               // editor.commit();

                name_field.setText("");
              getDialog().dismiss();
            }
        }
    }

    class mycl2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
        getDialog().dismiss();
        }
    }
}