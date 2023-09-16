package com.blusalt.blusaltmpos.pay;

import androidx.annotation.Keep;

/**
 * Created by AYODEJI on 05/19/2022.
 */

@Keep
public enum TransactionResultCode {

    APPROVED_BY_OFFLINE,
    APPROVED_BY_ONLINE,
    DECLINED_BY_OFFLINE,
    DECLINED_BY_ONLINE,
    DECLINED_BY_TERMINAL_NEED_REVERSE,
    ERROR_TRANSCATION_CANCEL,
    ERROR_TRANSCATION_TIMEOUT,
    ERROR_UNKNOWN,
}
