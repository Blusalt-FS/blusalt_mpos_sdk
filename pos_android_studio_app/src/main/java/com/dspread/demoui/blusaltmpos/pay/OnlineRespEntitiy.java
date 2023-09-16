package com.dspread.demoui.blusaltmpos.pay;

import androidx.annotation.Keep;

/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public class OnlineRespEntitiy {
    String respCode;
    String iccData;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getIccData() {
        return iccData;
    }

    public void setIccData(String iccData) {
        this.iccData = iccData;
    }
}
