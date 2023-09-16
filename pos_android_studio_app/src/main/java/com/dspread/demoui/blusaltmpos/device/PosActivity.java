//package com.blusalt.blusaltmpos.device;
//
//import static com.dspread.demoui.network.APIConstant.ERROR;
//import static com.dspread.demoui.network.APIConstant.initializationError;
//import static com.dspread.demoui.network.APIConstant.incompleteParameters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.res.AssetManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import androidx.annotation.Keep;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.lifecycle.MutableLiveData;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.blusalt.blusaltmpos.R;
//import com.blusalt.blusaltmpos.network.BaseData;
//import com.blusalt.blusaltmpos.network.MemoryManager;
//import com.blusalt.blusaltmpos.network.RetrofitClientInstance;
//import com.blusalt.blusaltmpos.pay.BlusaltTerminalInfo;
//import com.blusalt.blusaltmpos.pay.CreditCard;
//import com.blusalt.blusaltmpos.pay.TerminalInfo;
//import com.blusalt.blusaltmpos.pay.TerminalResponse;
//import com.blusalt.blusaltmpos.pos.AppLog;
//import com.blusalt.blusaltmpos.util.Constants;
//import com.blusalt.blusaltmpos.util.KSNUtilities;
//import com.blusalt.blusaltmpos.util.StringUtils;
//import com.blusalt.blusaltmpos.util.TransactionListener;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Type;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Objects;
//
//import pl.droidsonroids.gif.GifImageView;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//@Keep
//public class PosActivity extends BaseActivity implements TransactionListener {
//    private Double totalAmount = 0.0;
//    public static Double totalAmountPrint = 0.0;
//    private static String TAG = PosActivity.class.getName();
//    private String cPin = "";
//    private boolean isSupport;
//    private String accountType;
//    private TextView posViewUpdate;
//    private GifImageView spinKit;
//    private MutableLiveData<String> mutableLiveData;
//    private String terminalId;
//    private String ksn;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pos_loader_view);
//
//        posViewUpdate = findViewById(R.id.pos_view_update);
//        spinKit = findViewById(R.id.imageView);
//        spinKit.setImageResource(R.drawable.card);
//        Intent intent = getIntent();
//        mutableLiveData = new MutableLiveData();
//
//         if(isSecretKeyAdded()){
//             if (intent != null) {
//                 totalAmount = intent.getDoubleExtra(Constants.INTENT_EXTRA_AMOUNT_KEY, 0.0);
//                 accountType = intent.getStringExtra(Constants.INTENT_EXTRA_ACCOUNT_TYPE);
//                 terminalId = intent.getStringExtra(Constants.TERMINAL_ID);
//                 totalAmountPrint = totalAmount;
//                 if(!TextUtils.isEmpty(intent.getStringExtra(Constants.TERMINAL_ID))
//                         && !TextUtils.isEmpty(String.valueOf(intent.getDoubleExtra(Constants.INTENT_EXTRA_AMOUNT_KEY, 0.0)))
//                         && !TextUtils.isEmpty(intent.getStringExtra(Constants.INTENT_EXTRA_ACCOUNT_TYPE))
//                 ){
//                     totalAmount = intent.getDoubleExtra(Constants.INTENT_EXTRA_AMOUNT_KEY, 0.0);
//                     accountType = intent.getStringExtra(Constants.INTENT_EXTRA_ACCOUNT_TYPE);
//                     terminalId = intent.getStringExtra(Constants.TERMINAL_ID);
//                     totalAmountPrint = totalAmount;
//                 }else {
//                     incompleteParameters();
//                 }
//             } else {
//                 incompleteParameters();
//                 return;
//             }
//         }
//         else{
//             initializationError();
//         }
////        try {
////            printer = DeviceHelper.getPrinter();
////            mEmvL2 = DeviceHelper.getEmvHandler();
////            isSupport = mEmvL2.isSupport();
////        } catch (RemoteException e) {
////          //  String errp = e.getLocalizedMessage();
////            AppLog.e("RemoteException", e.getMessage());
////            e.printStackTrace();
////        }
//         viewObserver();
//        proceedToPayment();
//    }
//
//    public static  boolean checkDeviceStatus() {
//        boolean isValid = false;
////        try {
////            mEmvL2 = DeviceHelper.getEmvHandler();
////            isValid = mEmvL2.isSupport();
////         } catch (Exception e) {
////            AppLog.e("checkDeviceStatus", e.getMessage());
////        }
//       return  isValid;
//    }
//
//
//    public static void init(String secretKey) {
//        if (!TextUtils.isEmpty(secretKey)) {
//            try {
//                MemoryManager.getInstance().putUserSecretKey(secretKey);
//            } catch (Exception e) {
//                AppLog.e("prepareForPrinter", e.getMessage());
//            }
//        } else {
//            AppLog.e("init", "Secret Key is Empty");
//        }
//    }
//
//
//    private Boolean isSecretKeyAdded(){
//        return MemoryManager.getInstance().isSecretActivated();
//    }
//
//    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
//        Bitmap image = null;
//        AssetManager am = context.getResources().getAssets();
//        try {
//            InputStream is = am.open(fileName);
//            image = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        AppLog.d(TAG, "bitMap  =" + image);
//        return image;
//    }
//
//
//    private void viewObserver() {
//        mutableLiveData.observe(this, s -> {
//            if (!TextUtils.isEmpty(s)) {
//                showResult(posViewUpdate, s);
//            }
//        });
//    }
//
//    @Override
//    public void onProcessingError(RuntimeException message, int errorcode) {
//        try {
//            if (!isFinishing()) {
//                onCompleteTransaction(StringUtils.getTransactionTesponse(message.getMessage(), errorcode));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void proceedToPayment() {
//        if (!isSupport) {
//            posViewUpdate.setText(R.string.err_not_support_api);
//            FailedTransaction();
//            return;
//        }
//        try {
////            payProcessor.pay(totalAmount.longValue(), processorListener, mutableLiveData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public  static String getDeviceMac() {
////        try {
////            Bundle devInfo = DeviceHelper.getSysHandle().getDeviceInfo();
////            return devInfo.getString(SysConst.DeviceInfo.DEVICE_SN);
////        } catch (RemoteException e) {
////            Log.e("getDeviceMac", e.getLocalizedMessage());
////        }
//        return "";
//    }
//
//    public static String getDeviceModel() {
////        try {
////            Bundle devInfo = DeviceHelper.getSysHandle().getDeviceInfo();
////            return devInfo.getString(SysConst.DeviceInfo.DEVICE_MODEL);
////        } catch (RemoteException e) {
////            Log.e("getDeviceMac", e.getLocalizedMessage());
////        }
//        return "";
//    }
//
//    private void proceedToExChangeData(String responseCode, CreditCard creditCard) {
//        if (creditCard.getPIN() == null) {
//            FailedTransaction();
//            return;
//        }
//        TerminalInfo response = showTerminalEmvTransResult(accountType, totalAmount.longValue(), creditCard.getPIN(), responseCode, getDeviceMac());
//        KSNUtilities ksnUtilitites = new KSNUtilities();
//        //  String workingKey = ksnUtilitites.getWorkingKey("3F2216D8297BCE9C", "000002DDDDE00002");
//
//
//        String workingKey = ksnUtilitites.getWorkingKey("3F2216D8297BCE9C", getInitialKSN());
//        String pinBlock = ksnUtilitites.DesEncryptDukpt(workingKey, response.pan, cPin);
//
//
//        ksn = ksnUtilitites.getLatestKsn();
//        response.ksn = ksn;
//        response.pinBlock = pinBlock;
//        response.terminalId = terminalId;
//        // response.cardOwner = creditCard.getHolderName();
//        stopEmvProcess(response);
//    }
//
//    public static TerminalInfo showTerminalEmvTransResult(String accountType, long amountTotal, String _PinBlock, String _responseCode,String deviceMacAddress) {
//        TerminalInfo terminalInfo = getDefaultTerminalInfo();
////        terminalInfo.fromAccount = "Default";// getAccountTypeString(accountType);
////        terminalInfo.responseCode = _responseCode;
////        terminalInfo.responseDescription = "Data collected successfully";
////        terminalInfo.TerminalName = "horizonpay";
////        TlvDataList tlvDataList = null;
////        String tlv = null;
////        try {
////            tlv = DeviceHelper.getEmvHandler().getTlvByTags(EmvUtil.tags);
////            tlvDataList = TlvDataList.fromBinary(tlv);
////            if (tlvDataList.getTLV(EmvTags.EMV_TAG_IC_CHNAME) != null) {
////                String name = EmvUtil.readCardHolder();
////                terminalInfo.cardOwner =  ConvertUtils.formatHexString(name);
////            } else {
////                terminalInfo.cardOwner = "CUSTOMER / INSTANT";
////            }
////            Log.d(TAG,"ICC Data: " + "\n" + tlv);
////            terminalInfo.DedicatedFileName = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_DFNAME).getValue();
////            terminalInfo.CvmResults = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_CVMRESULT).getValue();
////            terminalInfo.ApplicationInterchangeProfile = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_AIP).getValue();
////            terminalInfo.TerminalVerificationResult = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_TVR).getValue();
////            terminalInfo.TransactionDate = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_TRANSDATE).getValue();
////            terminalInfo.CryptogramInformationData = "80";
////            terminalInfo.Cryptogram = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_AC).getValue();
////            terminalInfo.TerminalCapabilities = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_CAP).getValue();
////            terminalInfo.cardSequenceNumber = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_PANSN).getValue();
////            terminalInfo.atc = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_ATC).getValue();
////            terminalInfo.iad = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_ISSAPPDATA).getValue();
////            terminalInfo.track2 = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_TRACK2DATA).getValue();
////            String strTrack2 = terminalInfo.track2.split("F")[0];
////            String pan = strTrack2.split("D")[0];
////            String expiry = strTrack2.split("D")[1].substring(0, 4);
////            terminalInfo.pan = pan;
////            terminalInfo.expiryYear = expiry.substring(0,2);
////            terminalInfo.expiryMonth = expiry.substring(2);
////            terminalInfo.AmountAuthorized = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_AUTHAMNTN).getValue();
////            terminalInfo.UnpredictableNumber = tlvDataList.getTLV(EmvTags.EMV_TAG_TM_UNPNUM).getValue();
////            if(tlvDataList.getTLV(EmvTags.EMV_TAG_IC_APNAME) != null){
////                //terminalInfo.CardType = tlvDataList.getTLV(EmvTags.EMV_TAG_IC_APNAME).getGBKValue();
////            }
////            String reult = new Gson().toJson(terminalInfo);
////
////        } catch (RemoteException e) {
////            e.printStackTrace();
////        }
//        return terminalInfo;
//    }
//
//    private static TerminalInfo getDefaultTerminalInfo(){
//        TerminalInfo   terminalInfo = new TerminalInfo();
//        terminalInfo.batteryInformation = "100";
//        terminalInfo.languageInfo = "EN";
//        terminalInfo.posConditionCode = "00";
//        terminalInfo.printerStatus = "1";
//        //  terminalInfo.minorAmount = "000000000001";
//        terminalInfo.TransactionType = "00";
//        terminalInfo.posEntryMode = "051";
//        terminalInfo.posDataCode = "510101511344101";
//        terminalInfo.posGeoCode = "00234000000000566";
//        terminalInfo.pinType = "Dukpt";
//        terminalInfo.stan = getNextStan();
//        terminalInfo.AmountOther = "000000000000";
//        terminalInfo.TransactionCurrencyCode = "0566";
//        terminalInfo.TerminalCountryCode = "566";
//        terminalInfo.TerminalType  = "22";
//        return terminalInfo;
//    }
//
//    public static String getNextStan(){
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        return df.format(new Date()).substring(8);
//    }
//
//    private void getClearPin(String data) {
//        char[] ary = data.toCharArray();
//        StringBuilder cardPins = new StringBuilder();
//        for (int i = 0; i < ary.length; i++) {
//            if (i % 2 == 1) {
//                cardPins.append(ary[i]);
//            }
//        }
//        String result = cardPins.toString();
//        cPin = result;
//        Log.d("result card ", result);
//    }
//
//    private String getInitialKSN() {
//        SharedPreferences sharedPref = getSharedPreferences("KSNCOUNTER", Context.MODE_PRIVATE);
//        int ksn = sharedPref.getInt("KSN", 00001);
//        if (ksn > 9999) {
//            ksn = 00000;
//        }
//        int latestKSN = ksn + 1;
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt("KSN", latestKSN);
//        editor.apply();
//        return "0000000002DDDDE" + String.format("%05d", latestKSN);
//    }
//
//    private void stopEmvProcess(TerminalInfo response) {
//        AppLog.d("Processing", "Transaction" + "Please wait");
//        showResult(posViewUpdate, "Processing Transaction....");
//        String mTerminal = new Gson().toJson(response);
//        BlusaltTerminalInfo blusaltTerminalInfo = new Gson().fromJson(mTerminal, BlusaltTerminalInfo.class);
//        blusaltTerminalInfo.deviceOs = "Android";
//        blusaltTerminalInfo.serialNumber = getDeviceMac();
//        blusaltTerminalInfo.device = "Horizon " + getDeviceModel();
//        blusaltTerminalInfo.currency = "NGN";
//        blusaltTerminalInfo.currencyCode = "566";
//        response.currencyCode = "566";
//        response.currency = "NGN";
//        response.deviceOs = "Android";
//        response.serialNumber = getDeviceMac();
//        response.device = "Horizon " + getDeviceModel();
//        String rtt = new Gson().toJson(blusaltTerminalInfo);
//        ProcessTransaction(blusaltTerminalInfo);
////        onCompleteTransaction(response);
//    }
//
//
//
//    private void ProcessTransaction(BlusaltTerminalInfo blusaltTerminalInfo) {
//        RetrofitClientInstance.getInstance().getDataService().postTransactionToMiddleWare(blusaltTerminalInfo).enqueue(new Callback<BaseData<TerminalResponse>>() {
//            @Override
//            public void onResponse(@NonNull Call<BaseData<TerminalResponse>> call, @NonNull Response<BaseData<TerminalResponse>> response) {
//                AppLog.d("ProcessTransaction", "ProcessTransaction" + response);
//                TerminalResponse terminalResponse = new TerminalResponse("card payment failed", "01", "Unable to process transaction");
//                if (response.isSuccessful()) {
//                    if (response.body().getMessage().contains("Access denied! invalid apiKey passed")) {
//                        terminalResponse.responseCode = "01";
//                        terminalResponse.responseDescription = "card payment failed";
//                        apiResponseCall(terminalResponse);
//                    } else {
//                        terminalResponse = Objects.requireNonNull(response.body()).getData();
//                        terminalResponse.responseCode = "00";
//                        terminalResponse.responseDescription = "card payment successful";
//                        apiResponseCall(terminalResponse);
//                    }
//                } else {
//                    try {
//                        Gson gson = new Gson();
//                        Type type = new TypeToken<TerminalResponse>() {}.getType();
//
//                        terminalResponse = gson.fromJson(response.errorBody().charStream(), type);
//                        Log.e("ProcessTransaction", "ProcessTransaction err" + new Gson().toJson(terminalResponse));
//
//                        terminalResponse.responseCode = terminalResponse.data.posResponseCode;
//                        terminalResponse.responseDescription = terminalResponse.data.message;
//
//                        Log.e("ProcessTransaction", "ProcessTransaction err" + new Gson().toJson(terminalResponse));
//
//                        apiResponseCall(terminalResponse);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.e("ProcessTransaction", "ProcessTransaction tra" + e.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<BaseData<TerminalResponse>> call, @NonNull Throwable t) {
//                TerminalResponse terminalResponse = new TerminalResponse();
//                AppLog.d("ProcessTransaction", "ProcessTransaction onFailure" + t.getMessage());
//                terminalResponse.status = false;
//                terminalResponse.message = t.getMessage();
//                terminalResponse.responseCode = "02";
//                terminalResponse.responseDescription = "Unable to connect to the server";
//                apiResponseCall(terminalResponse);
//            }
//        });
//    }
//
//    private void FailedTransaction() {
//        TerminalResponse terminalResponse = new TerminalResponse();
//        AppLog.d("FailedTransaction", "FailedTransaction" + "Unable to process transaction");
//        terminalResponse.status = false;
//        terminalResponse.message = "Unable to process transaction";
//        terminalResponse.responseCode = "03";
//        terminalResponse.responseDescription = "Card Malfunction";
//        apiResponseCall(terminalResponse);
//    }
//
//    @Override
//    public void onCompleteTransaction(TerminalInfo response) {
//        try {
//            String fullPay = new Gson().toJson(response);
//            showResult(posViewUpdate, "");
//            Log.e("TRANS DONE", new Gson().toJson(response));
//            Intent intent = new Intent();
//            //intent.putExtra(getString(R.string.data), response);
//            intent.putExtra(getString(R.string.data), fullPay);
//            setResult(Activity.RESULT_OK, intent);
//            finish();
//        /*
//            Call<Object> userCall = mApiService.performTransaction("98220514989004", "Horizonpay", "K11", "1.0.0", msg);
//            userCall.enqueue(new Callback<Object>() {
//
//                @Override
//                public void onResponse(Call<Object> call, Response<Object> res) {
//                    if (res.code() == 200) {
//                        Intent intent = new Intent();
//                        intent.putExtra(getString(R.string.data), response);
//                        setResult(Activity.RESULT_OK, intent);
//                        finish();
////                        SharedPreferencesUtils.getInstance().setValue(SharedPreferencesUtils.KEYS, new Gson().toJson(response.body()));
//                    }
//                }
//                @Override
//                public void onFailure(Call<Object> call, Throwable t) {
//                    Intent intent = new Intent();
//                    intent.putExtra(getString(R.string.data), response);
//                    setResult(Activity.RESULT_OK, intent);
//                    finish();
//                    t.printStackTrace();
//                }
//            });
//         */
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        onBackPressed();
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void apiResponseCall(TerminalResponse terminalResponse) {
//        Intent intent = new Intent();
//        String fullPay = new Gson().toJson(terminalResponse);
//        intent.putExtra(getString(R.string.data), fullPay);
//        // prepareForPrinters(PosActivity.this,terminalResponse);
//        finishTransaction(intent);
//    }
//
//    private void incompleteParameters() {
//        Intent intent = new Intent();
//        TerminalResponse response = new TerminalResponse();
//        response.responseCode = incompleteParameters;
//        response.responseDescription = "Incomplete Parameter";
//        String fullPay = new Gson().toJson(response);
//        intent.putExtra(getString(R.string.data), fullPay);
//        finishTransaction(intent);
//    }
//
//    private void initializationError() {
//        Intent intent = new Intent();
//        TerminalInfo response = new TerminalInfo();
//        response.responseCode = initializationError;
//        response.responseDescription = ERROR;
//        String fullPay = new Gson().toJson(response);
//        intent.putExtra(getString(R.string.data), fullPay);
//        finishTransaction(intent);
//    }
//
//    private void finishTransaction(Intent intent){
//        setResult(Activity.RESULT_OK, intent);
//        finish();
//    }
//
//    public static TerminalInfo getOnBackPressResponse(Double totalAmount) {
//        TerminalInfo response = new TerminalInfo();
//        response.responseCode = "407";
//        response.responseDescription = "Transaction could not complete";
//        response.amount = String.valueOf(totalAmount);
//        return response;
//    }
//
//
//
//    @Override
//    public void onBackPressed() {
//        onCompleteTransaction(getOnBackPressResponse(totalAmount));
//    }
//
//}
