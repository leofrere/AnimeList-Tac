package project.animelist_tac.data.localData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import project.animelist_tac.data.localData.Dao.AnimeDao;
import project.animelist_tac.data.localData.Entity.AnimeEntity;

@Database(entities = AnimeEntity.class, version = 1, exportSchema = false)
public abstract class FavoriteDatabase  extends RoomDatabase {

    public abstract AnimeDao AnimeDao();

    private static volatile FavoriteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService writeExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public ExecutorService getWriteExecutor(){
        return writeExecutor;
    }

    public static FavoriteDatabase getDataBase(Context context) {
        context.deleteDatabase("favoritedatabase");

        if (INSTANCE == null) {
            synchronized (FavoriteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteDatabase.class, "favoritedatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
