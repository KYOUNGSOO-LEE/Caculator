package com.ksteach81.caculator;

import java.text.DecimalFormat;

public class Calculator {

    DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");

    public String getDecimalString(String changeString){
        String setChangeString = changeString.replace(",", "");
        return decimalFormat.format(Double.parseDouble(setChangeString));
    }
}
