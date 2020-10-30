package com.ksteach81.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button getButton = findViewById(view.getId());

        if(view.getId() == R.id.num_0_button
                || view.getId() == R.id.num_1_button
                || view.getId() == R.id.num_2_button
                || view.getId() == R.id.num_3_button
                || view.getId() == R.id.num_4_button
                || view.getId() == R.id.num_5_button
                || view.getId() == R.id.num_6_button
                || view.getId() == R.id.num_7_button
                || view.getId() == R.id.num_8_button
                || view.getId() == R.id.num_9_button) {
            if(isFirstInput){
                resultText.setTextColor(0xFF000000);
                resultText.setText(getButton.getText().toString());
                isFirstInput = false;
            }else {
                resultText.append(getButton.getText().toString());
            }
        }
    }
}