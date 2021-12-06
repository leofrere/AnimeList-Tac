package project.animelist_tac.data.webData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Represent the class to get anime with API Jinkan
 * @author Leo Frere, Jeremy Curoux*/

public class JinkanClient {

    public static String BASE_URL = "https://jikan1.p.rapidapi.com/search/anime/";

    private static Retrofit retrofit;

    private static JinkanClient instance = null;

    private JinkanInterface myAPI;

    public JinkanClient (){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        myAPI = retrofit.create(JinkanInterface.class);
    }

    public static JinkanClient getInstance() {
        if (instance == null) {
            instance = new JinkanClient();
        }
        return instance;
    }

    public JinkanInterface getMyApi() {
        return myAPI;
    }

}
