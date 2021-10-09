package project.animelist_tac.data.localData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavoriteAnimeDatabase extends SQLiteOpenHelper {

    private String createDatabase = "create table anime ("
            + "mal_id INTEGER PRIMARY KEY,"
            + "title TEXT NOT NULL,"
            + "synopsis TEXT NOT NULL,"
            + "start_date TEXT NOT NULL,"
            + "end_date TEXT NOT NULL,"
            + "type TEXT NOT NULL,"
            + "img_url TEXT NOT NULL,"
            + "nb_episode INTEGER NOT NULL);";

    public FavoriteAnimeDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
