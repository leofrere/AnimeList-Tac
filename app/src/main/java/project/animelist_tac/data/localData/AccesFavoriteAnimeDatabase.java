package project.animelist_tac.data.localData;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

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
                + "values (" + anime.id() + ",\"" + anime.title() + "\",\"" + anime.synopsis() + "\",\"" + anime.startDate()
                + "\",\"" + anime.endDate() + "\",\"" + anime.type() + "\",\"" + anime.imgUrl() + "\"," + anime.nbEpisode() + ")";
        sqLiteDatabase.execSQL(request);
    }

    public void deleteFavoriteAnime(int mal_id){
        sqLiteDatabase = database.getWritableDatabase();
        String request = "delete from anime where mal_id=" + mal_id;
        sqLiteDatabase.execSQL(request);
    }

    public Anime getFavoriteAnime(int mal_id){
        sqLiteDatabase = database.getReadableDatabase();
        Anime anime = null;
        String request = "select * from anime where mal_id=" + mal_id;
        Cursor cursor = sqLiteDatabase.rawQuery(request, null);
        cursor.moveToFirst();
        if (cursor.isFirst()){
            anime = createAnime(cursor);
        }
        return anime;
    }

    public List<Anime> getAllFavoriteAnime(){
        LinkedList<Anime> favoriteAnimeList = new LinkedList<Anime>();
        sqLiteDatabase = database.getReadableDatabase();
        String request = "select * from anime";
        Cursor cursor = sqLiteDatabase.rawQuery(request, null);
        cursor.moveToFirst();
        if (cursor.isFirst()){
            while (!cursor.isAfterLast()){
                favoriteAnimeList.add(createAnime(cursor));
                cursor.moveToNext();
            }
        }
        return favoriteAnimeList;
    }

    private Anime createAnime(Cursor cursor) {
        Anime anime;
        anime = new Anime(cursor.getInt(0));
        anime.title(cursor.getString(1));
        anime.synopsis(cursor.getString(2));
        anime.startDate(cursor.getString(3));
        anime.endDate(cursor.getString(4));
        anime.type(cursor.getString(5));
        anime.imgUrl(cursor.getString(6));
        anime.nbEpisode(cursor.getInt(7));
        return anime;
    }

}
