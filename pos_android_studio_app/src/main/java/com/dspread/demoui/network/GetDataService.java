package com.dspread.demoui.network;

import com.dspread.demoui.blusaltmpos.pay.BlusaltTerminalInfo;
import com.dspread.demoui.blusaltmpos.pay.TerminalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST(APIConstant.POST_TRANSACTION+"/charge")
    Call<BaseData<TerminalResponse>> postTransactionToMiddleWare(@Body BlusaltTerminalInfo blusaltTerminalInfo);


}
