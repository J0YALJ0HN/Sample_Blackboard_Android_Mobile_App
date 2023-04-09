package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mUserNameTv, mResultTv, mSolutionTv;
    private MaterialButton mButtonC, mButtonBrackOpen, mButtonBrackClose;
    private MaterialButton mButtonDivide, mButtonMultiply, mButtonPlus, mButtonMinus, mButtonEquals;
    private MaterialButton mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9;
    private MaterialButton mButtonAC, mButtonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Hooks
        mUserNameTv = findViewById(R.id.set_username);
        mResultTv = findViewById(R.id.mresult_tv);
        mSolutionTv = findViewById(R.id.msolution_tv);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USERNAME");
        String loggedInAs = "Logged in as: " + userName;
        mUserNameTv.setText(loggedInAs);

        mButtonC = findViewById(R.id.button_c);
        mButtonBrackOpen = findViewById(R.id.button_open_bracket);
        mButtonBrackClose = findViewById(R.id.button_close_bracket);
        mButtonDivide = findViewById(R.id.button_divide);
        mButtonMultiply = findViewById(R.id.button_multiply);
        mButtonPlus = findViewById(R.id.button_plus);
        mButtonMinus = findViewById(R.id.button_minus);
        mButtonEquals = findViewById(R.id.button_equals);
        mButton0 = findViewById(R.id.button_0);
        mButton1 = findViewById(R.id.button_1);
        mButton2 = findViewById(R.id.button_2);
        mButton3 = findViewById(R.id.button_3);
        mButton4 = findViewById(R.id.button_4);
        mButton5 = findViewById(R.id.button_5);
        mButton6 = findViewById(R.id.button_6);
        mButton7 = findViewById(R.id.button_7);
        mButton8 = findViewById(R.id.button_8);
        mButton9 = findViewById(R.id.button_9);
        mButtonAC = findViewById(R.id.button_ac);
        mButtonDot = findViewById(R.id.button_dot);

        mButtonC.setOnClickListener(this);
        mButtonBrackOpen.setOnClickListener(this);
        mButtonBrackClose.setOnClickListener(this);
        mButtonDivide.setOnClickListener(this);
        mButtonMultiply.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);
        mButtonEquals.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButtonAC.setOnClickListener(this);
        mButtonDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String buttonText = ((MaterialButton) view).getText().toString();
        String dataToCalculate = mSolutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            mSolutionTv.setText("");
            mResultTv.setText("0");
            return;
        }

// if the button pressed is equals "="
        if (buttonText.equals("=")) {
// set the text of the solution text view to the text of the result text view
            mSolutionTv.setText(mResultTv.getText());
            return;
        }

// if the button pressed is "C"
        if (buttonText.equals("C")) {
// if there is data to calculate and the data is not equal to "0"
            if (dataToCalculate.length() > 0 && !dataToCalculate.equals("0")) {
// remove the last character from the data to calculate
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
// otherwise, concatenate the button text to the data to calculate
            dataToCalculate = dataToCalculate + buttonText;
        }
// set the solution text view text to the updated data to calculate string
        mSolutionTv.setText(dataToCalculate);

// get the final result from the given data to calculate string
        String finalResult = Result(dataToCalculate);

// if the final result is not "Err"
        if (!finalResult.equals("Err")) {
// set the text of the result text view to the final result
            mResultTv.setText(finalResult);
        }
    }

    // evaluate the given data string and return the result
    String Result(String data) {
        try {
// create a new context
            Context context = Context.enter();
// disable optimization for the context
            context.setOptimizationLevel(-1);
// initialize a new scriptable
            Scriptable scriptable = context.initStandardObjects();
// evaluate the data string using the context and scriptable, and get the resulting string
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
// if the final result ends with ".0", remove it
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
// return the final result
            return finalResult;
        } catch (Exception e) {
// if there is an error, return "Err"
            return "Err";
        }
    }
}