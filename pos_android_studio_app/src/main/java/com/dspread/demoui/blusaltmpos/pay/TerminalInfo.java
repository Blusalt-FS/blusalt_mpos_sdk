package com.dspread.demoui.blusaltmpos.pay;
import androidx.annotation.Keep;

import com.dspread.demoui.processor.processor_blusalt.CardData;
import com.dspread.demoui.processor.processor_blusalt.EmvData;
import com.dspread.demoui.processor.processor_blusalt.TerminalInformation;

import java.io.Serializable;
@Keep
public class TerminalInfo   implements Serializable {

    public String rrn;
    public String transactionDateTime;
    public String transactionTime;
    public String expiryDate;
    public String serviceCode;
    public String iccData;
    public String pinData;

    public String merchantCategoryCode;
    public String terminalMerchantID;
    public String merchantNameAndLocation;

    public String processingCode;
    public String sessionKey;
    public String transactionDate;
    public String otherAmount;
    public String de62;
    public String de63;
    public CardData cardData;
    public EmvData emvData;
    public TerminalInformation terminalInformation;

    public String iccdata;

    public String  deviceOs;
    public String serialNumber;
    public String device;
    public String currency;

    public String cardOwner;
    public  String TerminalName;
    public  String responseCode;
    public  String responseDescription;
   // public  String CardType;


    public String amount;
    public  String batteryInformation;
    public  String currencyCode;
    public  String languageInfo;
    public  String posConditionCode;
    public  String printerStatus;
    public  String terminalType;
    public  String transmissionDate;
    public  String ApplicationInterchangeProfile;
    public  String CvmResults;
    public  String TransactionCurrencyCode;
    public  String TerminalCountryCode;
    public  String TerminalType;
    public  String TransactionType;
    public  String stan;
    public  String minorAmount;
    public  String ksnd;
    public  String surcharge;
    public  String extendedTransactionType;
    public  String posEntryMode;
    public  String cardSequenceNumber;
    public  String posDataCode;
    public  String posGeoCode;
    public  String atc;
    public  String TerminalVerificationResult;
    public  String iad;
    public  String TerminalCapabilities;
    public  String keyLabel;
    public  String receivingInstitutionId;
    public  String destinationAccountNumber;
    public  String retrievalReferenceNumber;
    public  String pinType;
    public  String terminalId;
    public  String expiryYear;
    public  String expiryMonth;
    public  String pan;
    public  String track2;
    public  String AmountAuthorized;
    public  String AmountOther;
    public  String TransactionDate;
    public  String CryptogramInformationData;
    public  String fromAccount;
    public  String ksn;
    public  String pinBlock;
    public  String Cryptogram;
    public  String UnpredictableNumber;
    public  String DedicatedFileName;
}
