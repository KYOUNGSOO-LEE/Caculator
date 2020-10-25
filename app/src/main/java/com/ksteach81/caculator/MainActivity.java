package com.ksteach81.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; // 입력값이 처음 입력되는가를 체크
    int resultNumber = 0; // 계산된 결과 값을 저장하는 변수
    char operator = '+'; // 입력된 연산자를 저장하는 변수

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText=findViewById(R.id.result_text);
    }

    public void buttonClick(View view) {
        if(view.getId() == R.id.all_clear_button){
            isFirstInput = true;
            resultNumber = 0;
            operator = '+';
            resultText.setTextColor(0xFF666666);
            resultText.setText(String.valueOf(resultNumber));
        }

        if(view.getId() == R.id.num_1_button){
            if(isFirstInput){
                resultText.setTextColor(0xFF000000);
                resultText.setText("1");
                isFirstInput = false;
            }else {
                resultText.append("1");
            }

        }else {

        }
    }
}