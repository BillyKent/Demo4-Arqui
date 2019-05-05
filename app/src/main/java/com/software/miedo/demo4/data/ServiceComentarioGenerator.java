package com.software.miedo.demo4.data;

import android.util.Log;

import com.software.miedo.demo4.model.ComentarioResponse;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceComentarioGenerator {

    public static final String API_BASE_URL = "http://169.62.31.213:8082/";


    public static ComentariosRequestAPI getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new BasicAuthInterceptor("admin", "admin"))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        retrofitBuilder.baseUrl(API_BASE_URL);

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());

        retrofitBuilder.client(client);

        Retrofit retrofit = retrofitBuilder.build();

        ComentariosRequestAPI userManagerService = retrofit.create(ComentariosRequestAPI.class);

        return userManagerService;
    }

    public static retrofit2.Callback<ComentarioResponse> callback = new Callback<ComentarioResponse>() {
        @Override
        public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {
            Log.i("JOJOXD", "Yea nena " + response.code());

        }

        @Override
        public void onFailure(Call<ComentarioResponse> call, Throwable t) {

            Log.i("JOJOXD", "Falla la kzm");

        }
    };


}
