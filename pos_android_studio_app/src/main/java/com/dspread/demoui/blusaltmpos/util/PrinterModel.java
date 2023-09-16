package com.dspread.demoui.blusaltmpos.util;

/***
 * Created by Ayodeji
 */

public class PrinterModel {
    public  String merchantName;
    public  String merchantAddress;
    public  String unpredictableNumber;
    public  String merchantTID;
    public  String transactionDate;
    public  String transactionTime;
    public  String transactionAID;
    public  String customerCardScheme;
    public  String customerCardPAN;
    public  String customerCardExpiry;
    public  String customerCardName;
    public  String transactionSTAN;
    public  String transactionAuthID;
    public  String customerAccountType;
    public  String transactionAmount;
    public  String transactionTVR;
    public  String appVersionNameNumber;
    public  String appPoweredBy;
    public  String appOrgUrl;
    public  String appOrgPhoneContact;
    public  boolean status;
    public  String message;

public PrinterModel(){}

public  static PrinterModel getPrinter(){
    PrinterModel printerModel = new PrinterModel();
    printerModel.merchantName = "CHICKEN REPUBLIC SPG JAKANDE LEKKI";
    printerModel.merchantAddress = "Sea Petroleum & Gas Filling Station SPG Jakande Lekki";
    printerModel.unpredictableNumber ="50CDB7DC";
    printerModel.merchantTID = "201182IY";
    printerModel.transactionDate = "22/06/29";
    printerModel.transactionTime = "15:23:24";
    printerModel.transactionAID = "A0000000031010";
    printerModel.customerCardScheme = "VISA DEBIT";
    printerModel.customerCardPAN = "62179380*****7654";
    printerModel.customerCardExpiry = "2210";
    printerModel.customerCardName = "CUSTOMER/INSTANT";
    printerModel.transactionSTAN = "12454";
    printerModel.transactionAuthID  = "558594";
    printerModel.customerAccountType = "Savings";
    printerModel.transactionAmount = "1.800.00";
    printerModel.transactionTVR = "0080008000";
    printerModel.appVersionNameNumber = "Tamslite 9.9.13";
    printerModel.appPoweredBy = "POWERED BY RAVEN";
    printerModel.appOrgUrl = "www.blusalt.com";
    printerModel.appOrgPhoneContact = "0700-2255-4839";
    printerModel.status = true;
    printerModel.message = "card payment successful";
    return  printerModel;

}


}
