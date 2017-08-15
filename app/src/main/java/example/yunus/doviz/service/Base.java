package example.yunus.doviz.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yunus on 12.08.2017.
 */

public class Base
{
    private static final String BASE_URL = "http://api.fixer.io/";

    private static Service services;

    public static Service getInstance() {

        if (services == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient().newBuilder().build())
                    .build();
            services = retrofit.create(Service.class);
            return services;
        } else {
            return services;
        }
    }
}
