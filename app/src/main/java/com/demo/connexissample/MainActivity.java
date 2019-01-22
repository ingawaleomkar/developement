package com.demo.connexissample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.connexissample.ApiService.ApiClient;
import com.demo.connexissample.ApiService.ApiListener;
import com.demo.connexissample.model.MasterPojo;
import com.demo.connexissample.model.ResponseList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="MainActivity";

    private final static String API_KEY = "2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makerequest();
    }



    private void makerequest(){

         ApiListener apiService =
                ApiClient.getClient().create(ApiListener.class);

        Call<MasterPojo> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MasterPojo>() {
            @Override
            public void onResponse(Call<MasterPojo>call, Response<MasterPojo> response) {

                if(response!=null){
                    List<ResponseList> movies=response.body().getResponseList();
                    Log.e(TAG, String.valueOf(response.body().getResponseList()));
                }



            }

            @Override
            public void onFailure(Call<MasterPojo>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
