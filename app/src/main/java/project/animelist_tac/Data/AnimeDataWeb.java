package project.animelist_tac.Data;

import android.app.DownloadManager;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AnimeDataWeb {
    private int limit = 20;
    // La majeure parti du code pour créer des requête provient de https://rapidapi.com/theapiguy/api/jikan1/
    public com.squareup.okhttp.Request requestAllAnime(String searchString){
        com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                .url("https://jikan1.p.rapidapi.com/search/anime?q=" + searchString + "&limit="+limit)
                .get()
                .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c09a52467emsh5bc86d47ca19a3bp14d8bcjsn71c384fcf21f")
                .build();
        return request;
    }
}
