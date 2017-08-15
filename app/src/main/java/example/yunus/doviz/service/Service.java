package example.yunus.doviz.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by yunus on 12.08.2017.
 */

public interface Service
{

    @GET("/latest?base=TRY")
    Call<JsonObject> sendTRY();
}
