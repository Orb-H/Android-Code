package com.yeonlee.calculator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static boolean programInit = true;
    static boolean digitBefore = false;
    static boolean calculBefore = false;
    static double previousNumber = 0;
    static char previouscalculator = '\0';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.one).setOnClickListener(btnClickListener);
        findViewById(R.id.two).setOnClickListener(btnClickListener);
        findViewById(R.id.three).setOnClickListener(btnClickListener);
        findViewById(R.id.four).setOnClickListener(btnClickListener);
        findViewById(R.id.five).setOnClickListener(btnClickListener);
        findViewById(R.id.six).setOnClickListener(btnClickListener);
        findViewById(R.id.seven).setOnClickListener(btnClickListener);
        findViewById(R.id.eight).setOnClickListener(btnClickListener);
        findViewById(R.id.nine).setOnClickListener(btnClickListener);
        findViewById(R.id.zero).setOnClickListener(btnClickListener);
        findViewById(R.id.plus).setOnClickListener(btnClickListener);
        findViewById(R.id.minus).setOnClickListener(btnClickListener);
        findViewById(R.id.multi).setOnClickListener(btnClickListener);
        findViewById(R.id.divide).setOnClickListener(btnClickListener);
        findViewById(R.id.answer).setOnClickListener(btnClickListener);
        findViewById(R.id.Reset).setOnClickListener(btnClickListener);
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int callId = v.getId();
            String buttonValue = ((Button)v).getText().toString();
            TextView textView = (TextView)findViewById(R.id.panel);
            TextView historyTextView = (TextView)findViewById(R.id.history);
            Boolean isDigit = false;

            if(callId == R.id.Reset)
            {
                textView.setText("0");
                historyTextView.setText("");
                programInit = true;
                digitBefore = false;
                calculBefore = false;
                previousNumber = 0;
                previouscalculator = '\0';
                return;
            }


            switch(callId) {
                case R.id.one:
                case R.id.two:
                case R.id.three:
                case R.id.four:
                case R.id.five:
                case R.id.six:
                case R.id.seven:
                case R.id.eight:
                case R.id.nine:
                case R.id.zero:
                    isDigit = true;
                    break;
            }

            if(programInit)
            {
                if(isDigit)
                {
                    textView.setText(buttonValue);
                    programInit = false;
                    digitBefore = true;
                }
                else
                    Toast.makeText(getApplicationContext(),"숫자를 입력해주세요!",Toast.LENGTH_LONG).show();
            }
            else
            {
                if(isDigit)
                {
                    if(digitBefore)
                    {
                        String temp = textView.getText().toString();
                        textView.setText(temp+buttonValue);
                    }
                    else
                    {
                        textView.setText(buttonValue);
                        digitBefore = true;
                    }
                }
                else
                {
                    if(digitBefore) {
                        if (!calculBefore) {
                            previousNumber = Double.parseDouble(textView.getText().toString());
                            previouscalculator = buttonValue.charAt(0);
                            calculBefore = true;
                        } else {
                            double temp = 0;
                            switch (previouscalculator) {
                                case '+':
                                    temp = previousNumber + Double.parseDouble(textView.getText().toString());
                                    break;
                                case '-':
                                    temp = previousNumber - Double.parseDouble(textView.getText().toString());
                                    break;
                                case '*':
                                    temp = previousNumber * Double.parseDouble(textView.getText().toString());
                                    break;
                                case '/':
                                    temp = previousNumber / Double.parseDouble(textView.getText().toString());
                                    break;
                            }

                            textView.setText(Double.toString(temp));
                            if (callId == R.id.answer) {
                                textView.setText(Double.toString(temp));
                                historyTextView.setText(Double.toString(temp));
                                calculBefore = false;
                                previousNumber = temp;
                                return;
                            }
                            previousNumber = temp;
                            previouscalculator = buttonValue.charAt(0);
                        }
                        digitBefore = false;
                    }
                    else
                        Toast.makeText(getApplicationContext(),"숫자를 입력하세요!",Toast.LENGTH_LONG).show();
                }
            }

            historyTextView.setText(historyTextView.getText().toString()+buttonValue);
        }
    };
}
