package com.dspread.demoui.blusaltmpos.util;

import androidx.annotation.Keep;

/**
 * Created by AYODEJI on 05/19/2022.
 */
@Keep
public enum AccountType {

    Savings(10) , Current(20), Credit(30), Default(00);

    private  int accountType;
    AccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        switch (accountType){
            case 0:
                return "00";
            case 10:
                return "10";
            case 20:
                return "20";
            case 30:
                return "30";
            default:
                return "00";
        }
    }

    @Override
    public String toString() {
        switch (getAccountType()){
            case "10":
                return "Savings Account";
            case "20":
                return "Current Account";
            case "30":
                return "Credit Account";
            case "00":
                return "Default Account";
            default:
                return "Unknown Account";
        }
    }
}