//package com.oze.blusalthorizonpos.utils.processor.service
//
//import android.content.Context
//import android.os.RemoteException
//import android.util.Log
//import com.horizonpay.smartpossdk.aidl.pinpad.IAidlPinpad
//import com.horizonpay.smartpossdk.data.PinpadConst
//import com.oze.blusalthorizonpos.utils.DeviceHelper
//import com.oze.blusalthorizonpos.utils.processor.util.ByteUtil.hexToBytes
//import com.oze.blusalthorizonpos.utils.processor.util.StringUtill.hexStringToByte
//
//class SecurityKeyManager(context: Context?) : SecurityKeyService() {
//
//    private val TAG = "PinPad3DesActivity"
//    private lateinit var innerpinpad: IAidlPinpad
//    private var isSupport = false
//    private val KEK_KEY_INDEX = 0
//    private val MASTER_KEY_INDEX = 0
//    private val WORK_KEY_INDEX = 0
//
//    var sessionKeyInit = false
//
//    fun saveTMK(tmk: String, clearTmk: String): Int {
//        innerpinpad = DeviceHelper.getPinpad()
//
//        var ret = 1
//
////        if (!isSupport) {
////            Log.e(TAG,"KEY INJECTION API NOT SUPPORTED")
////            return ret
////        }
//
//        return try {
//            val verifyKcv = encryptWithDES(clearTmk, "0000000000000000")
//            Log.e(TAG, "TMK KCV check: ClearTMK = [{$clearTmk}]")
//            Log.e(TAG, "TMK KCV check: VerifiedKCV = [{$verifyKcv}]")
//            try {
//
//                val masterKey = hexToBytes(tmk.substring(0, 32))
//                var ekcv = hexStringToByte(verifyKcv)
//                Log.e(TAG, "TMK KEY: " + tmk.substring(0, 32))
//                Log.e(TAG, "TMK Verify KVC: $verifyKcv")
////                ekcv = ByteArray(4)
//                val result = innerpinpad.injectSecureTMK(KEK_KEY_INDEX,
//                    MASTER_KEY_INDEX,
//                    masterKey,
//                    ekcv
//                )
//                Log.e(TAG, "Clear TMK Inject: $result")
//
//                val clearmasterKey = hexToBytes(clearTmk.substring(0, 32))
//                var clearekcv = hexStringToByte(verifyKcv)
//                Log.e(TAG, "TMK KEY: " + clearTmk.substring(0, 32))
//                Log.e(TAG, "TMK Verify KVC: $verifyKcv")
////                ekcv = ByteArray(4)
//                val ClearResult = innerpinpad.injectClearTMK(MASTER_KEY_INDEX,
//                    clearmasterKey,
//                    clearekcv
//                )
//
//                ret = if (ClearResult){
//                    0
//                }else{
//                    1
//                }
////                pinpadManager?.loadTEK(mMainKeyIndex, masterKey, null);
//                Log.e(TAG, "Clear TMK Inject: $ClearResult")
//            } catch (e: RemoteException) {
//                e.printStackTrace()
////                e.message?.let { log(it) }
//            }
////            if (ret == 0) {
////                localData.tmk = clearTmk
////                Log.e(TAG, "TMK injection result: $ret")
////                ret
////            }
//            ret
//        } catch (rex: Exception) {
//            rex.printStackTrace()
//            ret
//        }
//    }
//
//    fun saveTSK(clearTsk: String): Int {
//        innerpinpad = DeviceHelper.getPinpad()
//
//        var ret = 1
//
////        if (!isSupport) {
////            Log.e(TAG, "KEY INJECTION API NOT SUPPORTED")
////            return ret
////        }
//
//        return try {
//            val verifyKcv = encryptWithDES(clearTsk, "0000000000000000")
//            Log.e(TAG, "TSK KCV check: ClearTSK = [{$clearTsk}]")
//            Log.e(TAG, "TSK KCV check: VerifiedKCV = [{$verifyKcv}]")
//            // SK:DEK
//            var ret = 1 // Acquirer.getInstance().addAcquireKey(key02);
//            try {
//                //Encrypted Mac key
//                val emackey = hexToBytes(clearTsk.substring(0, 32))
//                var ekcv = hexStringToByte(verifyKcv)
////                ekcv = ByteArray(4)
//                val result =  innerpinpad.injectWorkKey(WORK_KEY_INDEX,
//                    PinpadConst.PinPadKeyType.TMACK,
//                    emackey,
//                    ByteArray(4)
//                )
//                ret = if (result){
//                    0
//                }else{
//                    1
//                }
//                Log.e(TAG, "TSK Inject (WorkKey): $result")
//
//            } catch (e: Exception) {
//                e.printStackTrace()
////                e.message?.let { log(it) }
//            }
//            if (ret != 0) {
//                ret
//            } else {
////                log("TSK injection result: $ret")
//                ret
//            }
//        } catch (rex: Exception) {
//            rex.printStackTrace()
//            ret
//        }
//    }
//
//    fun saveTPK(clearTpk: String): Int {
//        innerpinpad = DeviceHelper.getPinpad()
//        var ret = 1
//
////        if (!isSupport) {
////            Log.e(TAG, "KEY INJECTION API NOT SUPPORTED")
////            return ret
////        }
//        return try {
//            val verifyKcv = encryptWithDES(clearTpk, "0000000000000000")
//            var ekcv = hexStringToByte(verifyKcv)
//
//            Log.e(TAG,"TPK KCV check: ClearTPK = [{$clearTpk}]")
//            Log.e(TAG,"TPK KCV check: VerifiedKCV = [{$verifyKcv}]")
//            // SK:PEK
//            ret = 1 // Acquirer.getInstance().addAcquireKey(key03);
//            try {
//                //Encrypted PIN key
//                val epinKey = hexStringToByte(clearTpk)
//                var ekcv = hexStringToByte(verifyKcv)
////                ekcv = ByteArray(4)
//                val result = innerpinpad.injectWorkKey(WORK_KEY_INDEX,
//                    PinpadConst.PinPadKeyType.TPINK,
//                    epinKey,
//                    ByteArray(4)
//                )
//
//                ret = if (result){
//                    0
//                }else{
//                    1
//                }
////                epinKey?.let {
////                    pinpadManager?.loadTWK(
////                        PinpadConstant.KeyType.KEYTYPE_PEK,
////                        mMainKey,
////                        mWorkKey,
////                        epinKey,
////                        null
////                    );
////                }
//                Log.e(TAG,"TPK Inject (WorkKey): $result")
//            } catch (e: RemoteException) {
////                e.message?.let { log(it) }
//            }
//            if (ret != 0) {
//                ret
//            } else {
////                log("TPK injection result: $ret")
//                ret
//            }
//        } catch (rex: Exception) {
//            rex.printStackTrace()
//            ret
//        }
//    }
//
//
//    companion object {
//        private const val LOG_TAG = "POSKEYSERVICE"
//        const val ZMK_KEY_ID = 1
//        const val TMK_KEY_ID = 2
//        const val TSK_KEY_ID = 3
//        const val TPK_KEY_ID = 4
//        const val PACKAGE_NAME = "com.pos"
//        private const val verifyCheckDigit = true
//    }
//
//    init {
//        this.context = context
//        object : Thread() {
//            override fun run() {
//                try {
//                    innerpinpad = DeviceHelper.getPinpad()
//                    isSupport = innerpinpad.isSupport
//                    innerpinpad.setKeyAlgorithm(PinpadConst.KeyAlgorithm.DES)
//                } catch (e: RemoteException) {
//                    e.printStackTrace()
//                }
//            }
//        }.start()
//    }
//}
