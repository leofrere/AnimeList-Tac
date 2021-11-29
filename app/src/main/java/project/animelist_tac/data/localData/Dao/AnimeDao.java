package project.animelist_tac.data.localData.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.core.Single;
import project.animelist_tac.data.localData.Entity.AnimeEntity;

@Dao
public interface AnimeDao {

    @Insert
    void insert(AnimeEntity animeEntity);

    @Delete
    void delete(AnimeEntity animeEntity);

    @Query("SELECT * FROM AnimeEntity")
    Single<List<AnimeEntity>> getAllAnime();

    @Query("Select * FROM AnimeEntity WHERE mal_id = :mal_id")
    Maybe<AnimeEntity> getAnimeEntity(int mal_id);

}
