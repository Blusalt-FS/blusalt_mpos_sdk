package com.dspread.demoui.network;

import static com.dspread.demoui.network.APIConstant.POST_TRANSACTION;

import com.dspread.demoui.blusaltmpos.pay.BlusaltTerminalInfo;
import com.dspread.demoui.blusaltmpos.pay.TerminalResponse;
import com.dspread.demoui.processor.processor_blusalt.BlusaltTerminalInfoProcessor;
import com.dspread.demoui.processor.processor_blusalt.KeyDownloadRequest;
import com.dspread.demoui.processor.processor_blusalt.KeyDownloadResponse;
import com.dspread.demoui.processor.processor_blusalt.param.ParamDownloadResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @POST(POST_TRANSACTION+"/charge")
    Call<BaseData<TerminalResponse>> postTransactionToMiddleWare(@Body BlusaltTerminalInfo blusaltTerminalInfo);

    @POST("/pos/key-exchange")
    Call<BaseData<KeyDownloadResponse>> downloadKeyExchangeFromProcessor(@Body KeyDownloadRequest keyDownloadRequest);

    //    @POST("/processor/v1/transaction")
    @POST(POST_TRANSACTION + "/charge")
    Call<TerminalResponse> postTransactionToProcessor(@Body BlusaltTerminalInfoProcessor blusaltTerminalInfoProcessor);

    @GET("api/v1/devices/parameter-download/{serialNumber}")
    Call<BaseData<ParamDownloadResponse>> downloadTerminalParam(@Path("serialNumber") String serialNumber);

}
