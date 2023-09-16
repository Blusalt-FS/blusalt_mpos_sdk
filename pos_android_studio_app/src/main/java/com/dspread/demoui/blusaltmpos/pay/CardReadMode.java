package com.dspread.demoui.blusaltmpos.pay;

import androidx.annotation.Keep;

/**
 * Created by AYODEJI on 05/19/2022.
 */

@Keep
public enum  CardReadMode {
    MANUAL ,
    SWIPE ,
    FALLBACK_SWIPE ,
    CONTACT ,
    CONTACTLESS,
    CONTACTLESS_MSD,
}
