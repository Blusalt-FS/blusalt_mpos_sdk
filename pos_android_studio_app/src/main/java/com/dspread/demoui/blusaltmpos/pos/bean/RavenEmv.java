package com.dspread.demoui.blusaltmpos.pos.bean;

import androidx.annotation.Keep;


import com.blusalt.blusaltmpos.util.TransactionResponse;

import java.io.Serializable;

@Keep
public class RavenEmv implements Serializable {
    public TransactionResponse dataModel;

    public  RavenEmv(){

    }
}
