package com.voitov.movies;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiService apiService;
    private static final String BASE_URL = "https://api.kinopoisk.dev/";

    public static ApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // при помощи чего преобразовывать объекты json формата в экземпляры классов программы
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // чтобы rxjava можно было работать с retrofit
                    .build();

            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }
}
