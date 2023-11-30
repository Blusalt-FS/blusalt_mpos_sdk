package com.dspread.demoui.processor.util


import com.dspread.demoui.processor.processor_blusalt.MposDownloadReasonCode
import com.dspread.demoui.processor.processor_blusalt.DownloadStatus

class KeyException : Exception {

    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}

    private var downloadStatus: DownloadStatus? = null
    private var mposDownloadReasonCode: MposDownloadReasonCode? = null
    private var errorCode: String? = null
    private var errorDescription: String? = null

    constructor(
        downloadStatus: DownloadStatus,
        mposDownloadReasonCode: MposDownloadReasonCode,
        errorCode: String,
        errorDescription: String,
    ) : super(errorDescription) {
        this.downloadStatus = downloadStatus
        this.mposDownloadReasonCode = mposDownloadReasonCode
        this.errorCode = errorCode
        this.errorDescription = errorDescription
    }

    constructor(
        downloadStatus: DownloadStatus,
        mposDownloadReasonCode: MposDownloadReasonCode,
        errorCode: String,
        errorDescription: String,
        throwable: Throwable?,
    ) : super(errorDescription, throwable) {
        this.downloadStatus = downloadStatus
        this.mposDownloadReasonCode = mposDownloadReasonCode
        this.errorCode = errorCode
        this.errorDescription = errorDescription
    }

    fun getDownloadStatus(): DownloadStatus? {
        return downloadStatus
    }

    fun getDownloadReasonCode(): MposDownloadReasonCode? {
        return mposDownloadReasonCode
    }

    fun getErrorCode(): String? {
        return errorCode
    }

    fun getErrorDescription(): String? {
        return errorDescription
    }


}