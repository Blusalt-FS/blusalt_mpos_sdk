package com.dspread.demoui.blusaltmpos.pos;

import androidx.annotation.Keep;

import java.util.UUID;
/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public class UUIDUtils {
    public static String getUUID32() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
