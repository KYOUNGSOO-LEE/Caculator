package com.ksteach81.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; // 입력값이 처음 입력되는가를 체크

    ScrollView scrollView;
    TextView resultOperatorTextView;
    TextView resultTextView;

    ImageButton allClearButton;
    ImageButton clearEntryButton;
    ImageButton backSpaceButton;
    ImageButton decimalButton;
    ImageButton[] numberButton = new ImageButton[10];
    ImageButton[] operatorButton = new ImageButton[5];

    Calculator calculator = new Calculator(new DecimalFormat("###,###.##########"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scroll_view);
        resultOperatorTextView = findViewById(R.id.result_operator_text_view);
        resultTextView = findViewById(R.id.result_text_view);

        allClearButton = findViewById(R.id.all_clear_button);
        clearEntryButton = findViewById(R.id.clear_entry_button);
        backSpaceButton = findViewById(R.id.back_space_button);
        decimalButton = findViewById(R.id.decimal_button);

        for (int i = 0; i < numberButton.length; i++) {
            numberButton[i] = findViewById(R.id.num_button_0 + i);
        }

        for (int i = 0; i < operatorButton.length ; i++) {
            operatorButton[i] = findViewById(R.id.operator_button_1 + i);
        }

        for(ImageButton imageButton : numberButton) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberButtonClick(view);
                }
            });
        }

        for(ImageButton imageButton : operatorButton) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operatorButtonClick(view);
                }
            });
        }

        allClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allClearButtonClick(view);
            }
        });

        clearEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEntryButtonClick(view);
            }
        });

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSpaceButtonClick(view);
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decimalButtonClick(view);
            }
        });
    }

    private void decimalButtonClick(View view) {
        if(isFirstInput){
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText("0.");
            isFirstInput = false;
        } else {
            if(resultTextView.getText().toString().contains(".")) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            } else {
                resultTextView.append(".");
            }
        }
    }

    private void backSpaceButtonClick(View view) {
        if (isFirstInput && !calculator.getOperatorString().equals("")) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
        } else {
            if(resultTextView.getText().toString().length() > 1) {
                String getResultString = resultTextView.getText().toString().replace(",", "");
                String subString = getResultString.substring(0, getResultString.length() - 1);
                String decimalString = calculator.getDecimalString(subString);
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,getStringSize(decimalString));
                }
                resultTextView.setText(decimalString);
            } else {
                clearText();
            }
        }
    }

    private void clearEntryButtonClick(View view) {
        clearText();
    }

    private void allClearButtonClick(View view) {
        calculator.setAllClear();
        resultOperatorTextView.setText(calculator.getOperatorString());
        clearText();
    }

    private void clearText() {
        isFirstInput = true;
        resultTextView.setTextColor(0xFF666666);
        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        resultTextView.setText(calculator.getClearInputText());
    }

    private void operatorButtonClick(View view) {
        String getResultString = resultTextView.getText().toString();
        String operator = view.getTag().toString();
        String getResult = calculator.getResult(isFirstInput, getResultString, operator);
        resultTextView.setText(getResult);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,getStringSize(getResult));
        }
        resultOperatorTextView.setText(calculator.getOperatorString());
        isFirstInput = true;
    }

    private void numberButtonClick(View view) {
        if(isFirstInput) {
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
        } else {
            String getResultText = resultTextView.getText().toString().replace(",","");
            if(getResultText.length() > 15){
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            } else {
                getResultText = getResultText + view.getTag().toString();
                String getDecimalString = calculator.getDecimalString(getResultText);
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,getStringSize(getDecimalString));
                }
                resultTextView.setText(getDecimalString);
            }
        }
    }

    private int getStringSize(String getDecimalString) {
        if(getDecimalString.length() > 30) {
            return 25;
        }
        else if(getDecimalString.length() > 25) {
            return 30;
        } else if(getDecimalString.length() > 20) {
            return 35;
        } else if(getDecimalString.length() > 15) {
            return 40;
        }
        return 50;
    }
}






