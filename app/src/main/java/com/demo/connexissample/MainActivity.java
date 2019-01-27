package com.demo.connexissample;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.demo.connexissample.ApiService.ApiClient;
import com.demo.connexissample.ApiService.ApiListener;
import com.demo.connexissample.DAO.UserDatabase;
import com.demo.connexissample.adapter.ResponseListAdapter;
import com.demo.connexissample.model.MasterPojo;
import com.demo.connexissample.model.ResponseList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "user_db";
    private UserDatabase userDatabase;
    private final static String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    private final static String API_KEY = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        makerequest();
    }

    /**
     * method to initializing views
     */
    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Getting data");
        progressDialog.setCancelable(false);

        userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, DATABASE_NAME)
                .build();
    }


    // get response from server
    private void makerequest() {

        progressDialog.show();
        ApiListener apiService =
                ApiClient.getClient().create(ApiListener.class);

        Call<MasterPojo> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MasterPojo>() {
            @Override
            public void onResponse(Call<MasterPojo> call, Response<MasterPojo> response) {

                progressDialog.dismiss();
                List<ResponseList> movies = response.body().getResponseList();
                insertData(movies);
               /* if (movies.size() > 0) {
                    recyclerView.setAdapter(new ResponseListAdapter(MainActivity.this, movies));
                }*/
               fetchDataFromDb();
            }

            @Override
            public void onFailure(Call<MasterPojo> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void insertData(final List<ResponseList> responseLists) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDatabase.daoAccess().insertTask(responseLists);
            }
        }).start();
    }

    private void fetchDataFromDb(){
        LiveData<List<ResponseList>> universityLiveData = userDatabase.daoAccess().fetchAllRecords();
        universityLiveData.observe(this, new Observer<List<ResponseList>>() {
            @Override
            public void onChanged(@Nullable List<ResponseList> universities) {
                //Update your UI here.
                if (universities.size() > 0) {
                    recyclerView.setAdapter(new ResponseListAdapter(MainActivity.this, universities));
                }
            }
        });
    }
}
