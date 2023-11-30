package com.blusalt.blusaltmposapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dspread.demoui.activities.OtherActivity
import com.dspread.demoui.activities.PosActivity
import com.dspread.demoui.blusaltmpos.pay.TerminalResponse
import com.dspread.demoui.blusaltmpos.util.Constants
import com.dspread.demoui.processor.util.TerminalKeyParamDownloadListener
import com.google.gson.Gson
import java.lang.String
import kotlin.Double
import kotlin.Int
import kotlin.toString

class MainActivity : AppCompatActivity() {

    private var mac: EditText? = null
    private var tittle: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PosActivity.clearData(applicationContext);
        PosActivity.init(
            "live_8ec3f5be5a2c2fd38a0777878af87a620f1c1985ac6a9d23a93b854a9ed147115c947588f126e8214a492e63de35e0751690553197588",
            applicationContext,
            listener
        )

        mac = findViewById<View>(R.id.editview_mac) as EditText
        tittle = findViewById<View>(R.id.editview_tittle) as EditText

        // PosActivity.init("test_e5073d66a36fdb84335ae63a317931687eb861bf43d5ab3e0c94cd9230923a90119cb3494c67e599c3501fbae789caac1663868069841sk");
        findViewById<View>(R.id.button_first).setOnClickListener { v: View? ->

            if (mac?.getText().toString().isEmpty()) {
                Toast.makeText(applicationContext, "Input MAC", Toast.LENGTH_SHORT).show()
            } else if (tittle?.getText().toString().isEmpty()) {
                Toast.makeText(applicationContext, "Input TITTLE", Toast.LENGTH_SHORT).show()
            } else {
                startAccountSelectionActivity(36.0);
            }
//            val bluSaltPrinter = BluSaltPrinter()
//            bluSaltPrinter.printerType = PrinterType.PosTransaction
//            val merchantDetails = MerchantDetails()
//            merchantDetails.address = null
//            merchantDetails.name = "Test-Blusalt"
//            bluSaltPrinter.merchantDetails = merchantDetails
//            bluSaltPrinter.transDetail = DesirailizeGeneric(
//                "Paul Totti",
//                "devtotti@gmail.com", "CASH-8AXKT1ERTBE",
//                "Cash Payment", 5000.00, null, "NGN", null,
//                "payment_collection", "cash", "bank_transfer", null, "2023-04-28T18:39:40.489Z",
//                "2023-04-28T18:39:40.489Z"
//            )
//
//
//            val sample =
//                "{\"posResponse\":null,\"merchantDetails\":{\"name\":\"Blusalt Remitance\",\"logo\":null,\"email\":null,\"phoneNumber\":null,\"address\":null,\"state\":null,\"country\":null},\"cashRecord\":null,\"bankTransfer\":{\"PayoutId\":null,\"SplitPaymentId\":null,\"client_reference\":null,\"external_reference\":null,\"reference\":\"WyEL7rYiWmiB7Zv2nt_sw\",\"narration\":\"NIBSS:3969375830:BLUSALT TEST:TEST:090267211206111621704101460847\",\"amount\":1100,\"charges\":300,\"settled_amount\":800,\"currency\":\"NGN\",\"settled\":true,\"settlement_date\":null,\"source\":\"bank_transfer\",\"type\":\"payment_collection\",\"action\":\"credit\",\"status\":\"successful\",\"client_metadata\":{\"agent_ref\":\"AGT-T0EL09J31HN\",\"device_id\":\"98230303971579\"},\"metadata\":{\"checkout_reference\":\"TCH-PI3g-hBpY\",\"agent_ref\":\"AGT-T0EL09J31HN\",\"device_id\":\"98230303971579\",\"source_account\":{\"account_name\":\"JANE JORO\",\"account_number\":\"1111137444\",\"bank_code\":\"dgoPbR\",\"bank_name\":\"First Bank\"}},\"createdAt\":\"2023-05-13T09:13:54.007Z\",\"updatedAt\":\"2023-05-13T09:13:54.007Z\"},\"transDetail\":null,\"resAccTransactionData\":null,\"transactionDate\":\"13 May  2023\",\"transactionSource\":null}"
//            val printPrinterData: BluSaltPrinter =
//                Gson().fromJson(sample, BluSaltPrinter::class.java)

//            PosActivity.prepareForPrinter(this, bluSaltPrinter)

        }
    }

    var listener: TerminalKeyParamDownloadListener = object : TerminalKeyParamDownloadListener {
        override fun onSuccess(message: kotlin.String) {
            Log.e("TAG: ", "Result: $message")
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }

        override fun onFailed(error: kotlin.String) {
            Log.e("TAG: ", "Result: $error")
            Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
        }
    }


    private fun startAccountSelectionActivity(amount: Double) {
        val intent = Intent(this, PosActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_ACCOUNT_TYPE, "10")
        intent.putExtra(Constants.INTENT_EXTRA_AMOUNT_KEY, amount)
//        intent.putExtra(Constants.TERMINAL_ID, "2076NA61") //fizido
//        intent.putExtra(Constants.TERMINAL_ID, "2BLU0066")
//        intent.putExtra(Constants.BLUETOOTH_ADDRESS, mac?.getText().toString())
//        intent.putExtra(Constants.BLUETOOTH_TITTLE, tittle?.getText().toString())
         intent.putExtra(Constants.BLUETOOTH_ADDRESS, "30:3D:51:43:75:20") //not seyi 161
        intent.putExtra(Constants.BLUETOOTH_TITTLE, "MPOS2308140161")
//        intent.putExtra(Constants.BLUETOOTH_ADDRESS, "30:3D:51:43:75:16") //seyi 151
//        intent.putExtra(Constants.BLUETOOTH_TITTLE, "MPOS2308140151")
//        intent.putExtra(Constants.BLUETOOTH_ADDRESS, "30:3D:51:43:75:17") //seyi 152
//        intent.putExtra(Constants.BLUETOOTH_TITTLE, "MPOS2308140152")
//        intent.putExtra(Constants.BLUETOOTH_ADDRESS, "98:27:82:4C:6D:42") //seyi
//        intent.putExtra(Constants.BLUETOOTH_TITTLE, "MPOS2111050246")
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && data.hasExtra("data")) {
            val result = data.getStringExtra("data")
            val response: TerminalResponse = Gson().fromJson(result, TerminalResponse::class.java)
//            val response: TerminalInfoProcessor = Gson().fromJson(result, TerminalInfoProcessor::class.java)
            Log.e("E reach", "E reach " + Gson().toJson(response))

            AlertDialog.Builder(this)
                .setTitle(String.valueOf(response.responseCode))
                .setMessage(response.responseDescription)
                .setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .show()
        }
    }
}