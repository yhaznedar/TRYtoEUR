package example.yunus.doviz.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yunus on 12.08.2017.
 */

public class BizimPara
{
    @SerializedName("base")
    public String deger;

    @SerializedName("date")
    public String tarih;


    @SerializedName("rates")
    public double paraBirimi;

}
