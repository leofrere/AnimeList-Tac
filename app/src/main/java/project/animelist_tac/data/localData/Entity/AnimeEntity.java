package project.animelist_tac.data.localData.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AnimeEntity {
    public int getMal_id() {
        return mal_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getType() {
        return type;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    @PrimaryKey
    private int mal_id;

    @ColumnInfo(name = "image_url")
    private String image_url;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "synopsis")
    private String synopsis;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "episodes")
    private Integer episodes;

    @ColumnInfo(name = "start_date")
    private String start_date;

    @ColumnInfo(name = "end_date")
    private String end_date;


    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
