package com.dspread.demoui.blusaltmpos.pos;
/**
 * Created by AYODEJI on 05/19/2022.
 */
import android.text.TextUtils;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class StringUitls {

    private String transformAmount(String amount) {
        try {
            long lAmount = Long.parseLong(amount);
            amount = String.valueOf(lAmount * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public static <T> ArrayList<T> createArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<T>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    public static String getServiceCodeFromTrack2(String track2) {
        String ServiceCode24 = "";
        if (TextUtils.isEmpty(track2)){
            return "";
        }
        for (int i = 0; i < track2.length(); i++) {
            if (track2.charAt(i) == '=' || track2.charAt(i) == 'd' || track2.charAt(i) == 'D') {
                ServiceCode24 = track2.substring(i + 5, i + 5 + 3);
                break;
            }
        }
        return ServiceCode24;
    }
    public static String maskString(String s, int x) {
        int n = s.length()/x;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (n >= 1 && (i < n || i >= (s.length() - n))) {
                sb.append(s.charAt(i));
            }
            else {
                sb.append("*");
            }
        }
        return sb.toString();
    }
}
