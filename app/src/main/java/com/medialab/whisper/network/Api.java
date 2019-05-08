package com.medialab.whisper.network;

import com.medialab.whisper.entities.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://prod.whisper.sh/whispers/";
    String URL_WHISPER_POPULAR = "popular?limit=50";
    String URL_WHISPER_REPLIES = "replies?limit=200&wid={wid}";

    @GET(URL_WHISPER_POPULAR)
    Call<Root> getPopulars();

    @GET(URL_WHISPER_REPLIES)
    Call<Root> getReplies(@Path("wid") String wid);
}
