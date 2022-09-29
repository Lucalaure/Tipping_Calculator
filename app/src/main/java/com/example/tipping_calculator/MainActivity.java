package com.example.tipping_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText enterAmount;
    private SeekBar tipSlider;
    private TextView tipPercentage;
    private TextView tipTotal;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipPercentage = (TextView) findViewById(R.id.tipPercentage);
        tipTotal = (TextView) findViewById(R.id.tipTotal);
        totalAmount = (TextView) findViewById(R.id.totalAmount);
        enterAmount = (EditText) findViewById(R.id.enterAmount);
        tipSlider = (SeekBar) findViewById(R.id.tipSlider);

        tipSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tipPercentage.setText("" + progress + "%");
                double prog = progress;
                if (TextUtils.isEmpty(enterAmount.getText().toString()) == false) {
                    double number = Double.parseDouble(enterAmount.getText().toString());
                    double amount = Math.round(number * (prog/100.0+1) * 100.0) / 100.0;
                    totalAmount.setText("$" + amount);
                    tipTotal.setText("$" + Math.round((amount - number)*100.0)/100.0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        enterAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(enterAmount.getText().toString()) == false) {
                    double number = Double.parseDouble(charSequence.toString());
                    double progress = tipSlider.getProgress();
                    double amount = Math.round((number * (progress/100.0+1))*100.0)/100.0;
                    totalAmount.setText("$" + amount);
                    tipTotal.setText("$" + Math.round((amount - number)*100.0)/100.0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}