package com.mcp1993.elantra.retrofit;

import com.mcp1993.elantra.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class AppClient {
    public static Retrofit mRetrofit;

    public static Retrofit retrofit(){
        if (mRetrofit==null){
            OkHttpClient.Builder builder= new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor =new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置debug log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit=new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
