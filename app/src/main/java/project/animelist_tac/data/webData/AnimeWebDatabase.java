package project.animelist_tac.data.webData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import project.animelist_tac.model.Anime;

public class AnimeWebDatabase {
    private int limit = 50;
    // La majeure parti du code pour créer des requetes provient de https://rapidapi.com/theapiguy/api/jikan1/
    public com.squareup.okhttp.Request requestAllAnime(String searchString){
        com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                .url("https://jikan1.p.rapidapi.com/search/anime?q=" + searchString + "&limit="+limit)
                .get()
                .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c09a52467emsh5bc86d47ca19a3bp14d8bcjsn71c384fcf21f")
                .build();
        return request;
    }

    public List<Anime> getAllAnime(String searchString) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(requestAllAnime(searchString)).execute();
        String responseJson = response.body().string();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Map result = gson.fromJson(responseJson, Map.class);
        ArrayList<Map> resultat = (ArrayList<Map>) result.get("results");
        return listAnime(resultat);
    }

    private  List<Anime> listAnime(ArrayList<Map> resultat) {
        LinkedList<Anime> animeList = new LinkedList<Anime>();
        for (Map map: resultat) {
            Anime anime = new Anime((int) Math.round((double) map.get("mal_id")));
            anime.imgUrl((String) map.get("image_url"));
            String title = (String) map.get("title");
            title = title.replaceAll("\"", "'");
            anime.title(title);
            String sysnospsis = (String) map.get("synopsis");
            sysnospsis = sysnospsis.replaceAll("\"", "'");
            anime.synopsis(sysnospsis);
            anime.nbEpisode((int) Math.round((double) map.get("episodes")));
            String beginDate = (String) map.get("start_date");
            String[] elementOfBeginDate = beginDate.split("T");
            anime.startDate(elementOfBeginDate[0]);
            String endDate = (String) map.get("end_date");
            String[] elementOfEndDate = beginDate.split("T");
            anime.endDate(elementOfEndDate[0]);
            anime.type((String) map.get("type"));
            animeList.add(anime);
        }
        return animeList;
    }


}
