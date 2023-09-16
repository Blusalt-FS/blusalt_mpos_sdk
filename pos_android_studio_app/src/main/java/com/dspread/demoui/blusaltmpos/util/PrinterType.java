package com.dspread.demoui.blusaltmpos.util;

import androidx.annotation.Keep;

@Keep
public enum PrinterType {
    PosTransaction(10) , BankTransfer(20);

    private  int printerType;
    PrinterType(int printerType) {
        this.printerType = printerType;
    }

    public int getPrinterType() {
        if (printerType == 20) {
            return 20;
        }
        return 10;
    }

    @Override
    public String toString() {
        switch (getPrinterType()){
            case 20:
                return "Bank Transfer";
            default:
                return "Pos Transaction";
        }
    }
}
