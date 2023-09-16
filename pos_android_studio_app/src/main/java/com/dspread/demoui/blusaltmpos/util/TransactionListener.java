package com.dspread.demoui.blusaltmpos.util;

import androidx.annotation.Keep;

import com.dspread.demoui.blusaltmpos.pay.TerminalInfo;


/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public interface TransactionListener {
    public void onProcessingError(RuntimeException message, int errorcode);
    public void onCompleteTransaction(TerminalInfo response);
}
