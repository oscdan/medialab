package com.medialab.whisper.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medialab.whisper.entities.Root;
import com.medialab.whisper.entities.Whisper;
import com.medialab.whisper.network.Api;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WhispersViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Whisper>> whisperList;

    public LiveData<List<Whisper>> getWhispers() {
        //if the list is null
        if (whisperList == null) {
            whisperList = new MutableLiveData<List<Whisper>>();
            //we will load it asynchronously from server in this method
            loadWhispers();
        }

        //finally we will return the list
        return whisperList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadWhispers() {

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        Call<Root> call = api.getPopulars();


        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> root) {
                whisperList.setValue(root.body().getPopular());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e("Whisper", t.toString());
            }
        });
    }
}
