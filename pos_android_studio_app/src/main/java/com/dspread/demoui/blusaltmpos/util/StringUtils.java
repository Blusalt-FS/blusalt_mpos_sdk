package com.dspread.demoui.blusaltmpos.util;

import androidx.annotation.Keep;


import com.dspread.demoui.blusaltmpos.pay.TerminalInfo;

import java.text.NumberFormat;
import java.util.Locale;
/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public class StringUtils {
    public static String getNairaUnitFormat(String amount) {
        try {
            String amounts = amount + "D";
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
            String currency = format.format(Double.parseDouble(amounts));
            return currency.replace("$", "");
        }catch (Exception e){
            return amount;
        }
    }

    public static TerminalInfo getTransactionTesponse(String message, int code) {
        TerminalInfo response = new TerminalInfo();
        response.responseCode = String.valueOf(code);
        response.responseDescription = message;
        return  response;
    }

    public static String maskValue(String pan) {
        StringBuilder builder = new StringBuilder();
        builder.append("**********").append(pan.substring(pan.length() - 2));
      //  builder.append(pan.substring(0, 8)).append("**********");
        return builder.toString();
    }

}
