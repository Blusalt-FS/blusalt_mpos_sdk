package com.dspread.demoui.blusaltmpos.pay.printing;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class BankTransfer implements Serializable {
    public String reference;
    public int amount;
    public String status;
    public String currency;
    public Boolean customer_pays_charges;
    public MetaData metadata;



}
