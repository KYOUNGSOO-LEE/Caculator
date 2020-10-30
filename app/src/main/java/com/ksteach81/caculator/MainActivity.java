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

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText=findViewById(R.id.result_text);
    }

    public void buttonClick(View view) {

        Button getButton = findViewById(view.getId());

        Log.e("buttonClick", "buttonClick "+getButton.getText().toString() + "버튼이 클릭되었습니다.");
        Log.e("buttonClick", "resultNumber = "+ resultNumber);

        switch (view.getId()){
            case R.id.all_clear_button:
                isFirstInput = true;
                resultNumber = 0;
                operator = '+';
                resultText.setTextColor(0xFF666666);
                resultText.setText(String.valueOf(resultNumber));
                break;

            case R.id.addition_button:
                int lastNum = Integer.parseInt(resultText.getText().toString());
                if(operator == '+') {
                    resultNumber = resultNumber + lastNum;
                } else if(operator == '-' ){
                    resultNumber = resultNumber - lastNum;
                } else if(operator == '/'){
                    resultNumber = resultNumber / lastNum;
                } else if(operator == '*'){
                    resultNumber = resultNumber * lastNum;
                }
                operator = '+';
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
                break;

            case R.id.num_0_button:
            case R.id.num_1_button:
            case R.id.num_2_button:
            case R.id.num_3_button:
            case R.id.num_4_button:
            case R.id.num_5_button:
            case R.id.num_6_button:
            case R.id.num_7_button:
            case R.id.num_8_button:
            case R.id.num_9_button:
                if(isFirstInput){
                    resultText.setTextColor(0xFF000000);
                    resultText.setText(getButton.getText().toString());
                    isFirstInput = false;
                } else {
                    resultText.append(getButton.getText().toString());
                }
                break;

            default:
//                Toast.makeText(getApplicationContext(), getButton.getText().toString() + "버튼이 클릭되었습니다.", Toast.LENGTH_LONG).show();
                Log.e("buttonClick", "default "+getButton.getText().toString() + "버튼이 클릭되었습니다.");
                break;
        }
    }
}














