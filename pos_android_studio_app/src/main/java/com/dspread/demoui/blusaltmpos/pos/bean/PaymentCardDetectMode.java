package com.dspread.demoui.blusaltmpos.pos.bean;

import androidx.annotation.Keep;

/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public class PaymentCardDetectMode {
    public static final int UNKNOWN = 0;   // unknown
    public static final int MANUAL = 1;    // manually input
    public static final int MAG = 2;       // mag card
    public static final int ICC = 3;       // IC card
    public static final int CTLS = 4;      // Contactless card
    public static final int CTLS_MSD = 5;  // contactless card MSD
}
