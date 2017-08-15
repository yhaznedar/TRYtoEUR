package example.yunus.doviz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import example.yunus.doviz.model.BizimPara;
import example.yunus.doviz.service.Base;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText paraDegeri;
    TextView sonuc,tarih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paraDegeri = (EditText) findViewById(R.id.price);
        sonuc = (TextView) findViewById(R.id.amount);
        tarih=(TextView) findViewById(R.id.date);

        paraDegeri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                yolla();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }

    public void yolla()
    {
        Base.getInstance().sendTRY().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                JsonObject object = response.body();
                BizimPara ver=new BizimPara();
                ver.tarih=object.get("date").getAsString();
                tarih.setText("Güncelleme tarihi: " + ver.tarih);
                JsonObject rates=object.getAsJsonObject("rates");
                ver.paraBirimi =rates.get("EUR").getAsDouble();
                try  //edittexten herşeyi silince NumberFormatException hatası attığından try-catch bloğuna aldım.

                {
                    sonuc.setText(String.valueOf(Double.parseDouble(paraDegeri.getText().toString()) * ver.paraBirimi));
                }
                catch (NumberFormatException ex)
                {
                    ex.printStackTrace();
                    paraDegeri.setError("Bir para birimi giriniz.");
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                Toast.makeText(MainActivity.this,"Bir şeyler ters gitti.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
