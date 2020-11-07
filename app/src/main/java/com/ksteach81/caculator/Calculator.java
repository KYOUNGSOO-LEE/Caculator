package com.ksteach81.caculator;

import java.text.DecimalFormat;

public class Calculator {

    private final String CLEAR_INPUT_TEXT = "0";
    private double resultNumber = 0;
    private double lastInputNumber = 0;

    private String operator = "+";
    private String operatorString = "";

    DecimalFormat decimalFormat;

    public Calculator (){
        decimalFormat = new DecimalFormat("###,###.#####");
    }

    public Calculator(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public String getDecimalString(String changeString){
        String setChangeString = changeString.replace(",", "");
        return decimalFormat.format(Double.parseDouble(setChangeString));
    }

    public String getDecimalString(double changeNumber){
        return decimalFormat.format(changeNumber);
    }

    public String getOperatorString() {
        return operatorString;
    }

    public String getClearInputText(){
        return CLEAR_INPUT_TEXT;
    }
    
    public void setAllClear() {
        resultNumber = 0;
        lastInputNumber = 0;
        operator = "+";
        operatorString = "";
    }
    
    public double doubleCalculator(double result, double lastNumber, String operator) {
        switch (operator) {
            case "+":
                result += lastNumber;
                break;
            case "-":
                result -= lastNumber;
                break;
            case "*":
                result *= lastNumber;
                break;
            case "/":
                result /= lastNumber;
                break;
        }
        return result;
    }

    public String getResult(boolean isFirstInput, String getResultString, String lastOperator) {
        if (isFirstInput) {
            if(lastOperator.equals("=")){
                resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
                operatorString = "";
            } else {
                operator = lastOperator;
                if(operatorString.equals("")){
                    operatorString = getResultString + " " + lastOperator;
                } else {
                    operatorString = operatorString.substring(0, operatorString.length() - 1);
                    operatorString = operatorString + lastOperator;
                }
            }
        } else {
            lastInputNumber = Double.parseDouble(getResultString.replace(",",""));
            resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
            if(lastOperator.equals("=")){
                operatorString = "";
            } else {
                operatorString = operatorString + " " + getResultString + " " + lastOperator;
                operator = lastOperator;
            }
        }
        return getDecimalString(resultNumber);
    }
}
