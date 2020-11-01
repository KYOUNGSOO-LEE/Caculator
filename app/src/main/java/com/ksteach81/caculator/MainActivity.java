package com.ksteach81.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; // 입력값이 처음 입력되는가를 체크
    int resultNumber = 0; // 계산된 결과 값을 저장하는 변수
    char operator = '+'; // 입력된 연산자를 저장하는 변수

    final String CLEAR_INPUT_TEXT = "0";

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText=findViewById(R.id.result_text);
    }

    //AC, CE, BE, decimal button click되었을 때 실행되는 메소드
    public void buttonClick(View view) {

        switch (view.getId()){
            case R.id.all_clear_button:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if(resultText.getText().toString().length() > 1){
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length()-1);
                    resultText.setText(subString);
                } else {
                    setClearText(CLEAR_INPUT_TEXT);
                }
                break;

            case R.id.decimal_button:
                break;
        }
    }

    //입력된 숫자를 클리어 시켜 주는 메소드
    public void setClearText(String clearText){
        isFirstInput = true;
        resultText.setTextColor(0xFF666666);
        resultText.setText(clearText);
    }

    // 숫자 버튼이 클릭되었을 때 실행되는 메소드
    public void numButtonClick(View view){
        Button getButton = findViewById(view.getId());

        if(isFirstInput){
            resultText.setTextColor(0xFF000000);
            resultText.setText(getButton.getText().toString());
            isFirstInput = false;
        } else {
            if(resultText.getText().toString().equals("0")){
                Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 없습니다.", Toast.LENGTH_SHORT).show();
                setClearText(CLEAR_INPUT_TEXT);
            } else {
                resultText.append(getButton.getText().toString());
            }
        }
    }

    //연산자를 클릭하였을 때 실행되는 메소드
    public void operatorClick(View view){
        Button getButton = findViewById(view.getId());

        if(view.getId() == R.id.result_button){
            if(isFirstInput){
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                // TODO: 2020-11-01 다음에 실수형 계산기 만들 때 윈도우 계산기처럼 =을 두 번 이상 누를 때 실행방법과 같이 구현할 것!
            } else{
                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }
        } else{
            if(isFirstInput){
                operator = getButton.getText().toString().charAt(0);
            } else{
                int lastNum = Integer.parseInt(resultText.getText().toString());
                resultNumber = intCal(resultNumber, lastNum, operator);
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }
        }
    }

    // 연산자를 계산하는 메소드
    public int intCal(int result, int lastNum, char operator){
        if(operator == '+') {
            result += lastNum;
        } else if(operator == '-' ){
            result -= lastNum;
        } else if(operator == '/'){
            result /= lastNum;
        } else if(operator == '*'){
            result *= lastNum;
        }
        return result;
    }

}






