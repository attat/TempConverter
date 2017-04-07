package com.tat.tempconverter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import org.w3c.dom.Text;


public class TempConverterActivity extends AppCompatActivity implements OnEditorActionListener {


    private EditText FahrenHeitEditText;
    private TextView mCelciusTV;
    private SharedPreferences savedValues;
    String fahrenheitString;
    float celcius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);



        FahrenHeitEditText = (EditText) findViewById(R.id.FahrenHeitEditText);
        mCelciusTV = (TextView) findViewById(R.id.mCelciusTV);
        //set listener
        FahrenHeitEditText.setOnEditorActionListener(this);

        //get shared preferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //implement listener
        //String fahrenheitString;
        //float celcius;

        fahrenheitString = FahrenHeitEditText.getText().toString();
        if(fahrenheitString == (""))
        {
            celcius = 0;
        }
        else
        {
            celcius = Float.parseFloat(fahrenheitString);
        }
        celcius = (celcius-32) * 5/9;

        if(actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
        {

            mCelciusTV.setText(String.valueOf(celcius));
        }
        return false;
    }

    public void onPause()
    {
        //save instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("fahrenheitString", fahrenheitString);
        editor.putFloat(fahrenheitString, celcius);
        editor.commit();

        super.onPause();
    }

    public void onResume()
    {
        super.onResume();

        //get instance variables
        fahrenheitString = savedValues.getString("fahrenheitString", "");
        celcius = savedValues.getFloat("fahrenheitString", celcius);
    }
}
