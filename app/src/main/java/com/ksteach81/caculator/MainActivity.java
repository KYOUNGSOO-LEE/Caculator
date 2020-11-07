package com.ksteach81.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

    Calculator calculator = new Calculator();


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
    }

    private void backSpaceButtonClick(View view) {
    }

    private void clearEntryButtonClick(View view) {
    }

    private void allClearButtonClick(View view) {
        isFirstInput = true;
        resultTextView.setTextColor(0xFF666666);
        resultTextView.setText("0");
    }

    private void operatorButtonClick(View view) {
    }

    private void numberButtonClick(View view) {
        if(isFirstInput) {
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
        } else {
            String getResultText = resultTextView.getText().toString().replace(",","");
            getResultText = getResultText + view.getTag().toString();
            String getDecimalString = calculator.getDecimalString(getResultText);
            resultTextView.setText(getDecimalString);
        }
    }

//    //AC, CE, BE, decimal button click되었을 때 실행되는 메소드
//    public void buttonClick(View view) {
//
//        switch (view.getId()){
//            case R.id.all_clear_button:
//                resultNumber = 0;
//                operator = '+';
//                setClearText(CLEAR_INPUT_TEXT);
//                break;
//
//            case R.id.clear_entry_button:
//                setClearText(CLEAR_INPUT_TEXT);
//                break;
//
//            case R.id.back_space_button:
//                if(resultText.getText().toString().length() > 1){
//                    String getResultText = resultText.getText().toString();
//                    String subString = getResultText.substring(0, getResultText.length()-1);
//                    resultText.setText(subString);
//                } else {
//                    setClearText(CLEAR_INPUT_TEXT);
//                }
//                break;
//
//            case R.id.decimal_button:
//                break;
//        }
//    }
//
//    //입력된 숫자를 클리어 시켜 주는 메소드
//    public void setClearText(String clearText){
//        isFirstInput = true;
//        resultText.setTextColor(0xFF666666);
//        resultText.setText(clearText);
//    }
//
//    // 숫자 버튼이 클릭되었을 때 실행되는 메소드
//    public void numButtonClick(View view){
//        Button getButton = findViewById(view.getId());
//
//        if(isFirstInput){
//            resultText.setTextColor(0xFF000000);
//            resultText.setText(getButton.getText().toString());
//            isFirstInput = false;
//        } else {
//            if(resultText.getText().toString().equals("0")){
//                Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 없습니다.", Toast.LENGTH_SHORT).show();
//                setClearText(CLEAR_INPUT_TEXT);
//            } else {
//                resultText.append(getButton.getText().toString());
//            }
//        }
//    }
//
//    //연산자를 클릭하였을 때 실행되는 메소드
//    public void operatorClick(View view){
//        Button getButton = findViewById(view.getId());
//
//        if(view.getId() == R.id.result_button){
//            if(isFirstInput){
//                resultNumber = 0;
//                operator = '+';
//                setClearText(CLEAR_INPUT_TEXT);
//                // TODO: 2020-11-01 다음에 실수형 계산기 만들 때 윈도우 계산기처럼 =을 두 번 이상 누를 때 실행방법과 같이 구현할 것!
//            } else{
//                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
//                resultText.setText(String.valueOf(resultNumber));
//                isFirstInput = true;
//            }
//        } else{
//            if(isFirstInput){
//                operator = getButton.getText().toString().charAt(0);
//            } else{
//                int lastNum = Integer.parseInt(resultText.getText().toString());
//                resultNumber = intCal(resultNumber, lastNum, operator);
//                operator = getButton.getText().toString().charAt(0);
//                resultText.setText(String.valueOf(resultNumber));
//                isFirstInput = true;
//            }
//        }
//    }
//
//    // 연산자를 계산하는 메소드
//    public int intCal(int result, int lastNum, char operator){
//        if(operator == '+') {
//            result += lastNum;
//        } else if(operator == '-' ){
//            result -= lastNum;
//        } else if(operator == '/'){
//            result /= lastNum;
//        } else if(operator == '*'){
//            result *= lastNum;
//        }
//        return result;
//    }

}






