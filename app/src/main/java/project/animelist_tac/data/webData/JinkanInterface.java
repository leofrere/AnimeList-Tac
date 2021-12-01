package project.animelist_tac.data.webData;

import io.reactivex.rxjava3.core.Single;
import project.animelist_tac.model.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JinkanInterface {
    @GET("?rapidapi-host=jikan1.p.rapidapi.com&rapidapi-key=c09a52467emsh5bc86d47ca19a3bp14d8bcjsn71c384fcf21f")
    Single<Response> getAnimes(@Query("q") String search, @Query("limit") int numberOfAnime);
}
