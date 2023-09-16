package com.blusalt.blusaltmposapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dspread.demoui.activities.PosActivity
import com.dspread.demoui.blusaltmpos.pay.DesirailizeGeneric
import com.dspread.demoui.blusaltmpos.pay.TerminalResponse
import com.dspread.demoui.blusaltmpos.pay.printing.BluSaltPrinter
import com.dspread.demoui.blusaltmpos.pay.printing.MerchantDetails
import com.dspread.demoui.blusaltmpos.pay.printing.PrinterType
import com.dspread.demoui.blusaltmpos.util.Constants

import com.google.gson.Gson
import java.lang.String
import kotlin.Double
import kotlin.Int

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PosActivity.init("test_57566e7a223f98cf6aebfd093c8f295dd77f74a6690cd24672352c7477ebae336cf759516d2a2f500440686eb96d92121663836633811sk")
        // PosActivity.init("test_e5073d66a36fdb84335ae63a317931687eb861bf43d5ab3e0c94cd9230923a90119cb3494c67e599c3501fbae789caac1663868069841sk");
        findViewById<View>(R.id.button_first).setOnClickListener { v: View? ->
            startAccountSelectionActivity(3500.0);
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


    private fun startAccountSelectionActivity(amount: Double) {
        val intent = Intent(this, PosActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_ACCOUNT_TYPE, "10")
        intent.putExtra(Constants.INTENT_EXTRA_AMOUNT_KEY, amount)
        intent.putExtra(Constants.TERMINAL_ID, "2076NA61")
        intent.putExtra(Constants.BLUETOOTH_ADDRESS, "98:27:82:4C:6D:42")
        intent.putExtra(Constants.BLUETOOTH_TITTLE, "MPOS2111050246")
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && data.hasExtra("data")) {
            val result = data.getStringExtra("data")
            val response: TerminalResponse = Gson().fromJson(result, TerminalResponse::class.java)
            AlertDialog.Builder(this)
                .setTitle(String.valueOf(response.responseCode))
                .setMessage(response.responseDescription)
                .setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .show()
        }
    }
}