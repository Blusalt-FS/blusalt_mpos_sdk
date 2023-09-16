package com.dspread.demoui.network;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.dspread.demoui.network.APIConstant.BASE_LIVE_URL;

import android.util.Log;

import com.dspread.demoui.MemoryManager;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AYODEJI on 10/10/2020.
 *
 */
public class RetrofitClientInstance {
    private static RetrofitClientInstance mInstance;
    private Retrofit mRetrofit;


    private RetrofitClientInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        authorization = MemoryManager.getInstance().getSecretKey() != null ? MemoryManager.getInstance().getSecretKey(): "";
        authorization = "test_8c3f614d42530912fbb5a76b72626f8861410cee382948c0e14090ea6f749798db8fd109bed75759129165a2037f6a371691150896997sk";
        Log.d("AAA",authorization);
        String authValue = authorization;
        Interceptor authInterceptor = chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("x-api-key", authValue)
                    .addHeader("content-type", "application/json")
                    .build();
            return chain.proceed(newRequest);
        };

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.readTimeout(3, TimeUnit.MINUTES);
        okHttpBuilder.connectTimeout(3, TimeUnit.MINUTES);
        okHttpBuilder.addInterceptor(authInterceptor);
        okHttpBuilder.addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_LIVE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClientInstance getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientInstance();
        }
        return mInstance;
    }

    public GetDataService getDataService() {
        return mRetrofit.create(GetDataService.class);
    }

    private String authorization;

    public void reset() {
        mInstance = null;
        getInstance();
    }
}
