package project.animelist_tac.data.localData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import project.animelist_tac.model.Anime;

public class AccesFavoriteAnimeDatabase {
    private String baseName = "favoriteAnime.sqlite";
    private int version = 1;
    private FavoriteAnimeDatabase database;
    private SQLiteDatabase sqLiteDatabase;

    public AccesFavoriteAnimeDatabase(Context context){
        database = new FavoriteAnimeDatabase(context, baseName, null, version);
    }

    public void addFavoriteAnime(Anime anime){
        sqLiteDatabase = database.getWritableDatabase();
        String request = "insert into anime (mal_id, title, synopsis, date_debut, date_fin, type, img_url, nb_episode)"
                + "values (" + anime.id() + ",\"" + anime.title() + "\",\"" + anime.synopsis() + "\",\"" + anime.dateDebut()
                + "\",\"" + anime.dateFin() + "\",\"" + anime.type() + "\",\"" + anime.imgUrl() + "\"," + anime.nbEpisode() + ")";
        sqLiteDatabase.execSQL(request);
    }

    public void deleteFavoriteAnime(int mal_id){
        sqLiteDatabase = database.getWritableDatabase();
        String request = "delete from anime where mal_id=" + mal_id;
        sqLiteDatabase.execSQL(request);
    }

}
