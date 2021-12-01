package project.animelist_tac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import project.animelist_tac.data.localData.Entity.AnimeEntity;

public class Anime {
    @SerializedName("mal_id")
    @Expose
    public int id;

    @SerializedName("image_url")
    @Expose
    public String image_url;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("synopsis")
    @Expose
    public String synopsis;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("episodes")
    @Expose
    public Integer episodes;

    @SerializedName("start_date")
    @Expose
    public String start_date;

    public String getStart_date(){
        if(start_date == null){
            return "inconnue";
        }
        return start_date.split("T")[0];
    }

    @SerializedName("end_date")
    @Expose
    public String end_date;

    public String getEnd_date(){
        if(end_date == null){
            return "inconnue";
        }
        return end_date.split("T")[0];
    }

    public AnimeEntity asAnimeEntity(){
        AnimeEntity animeEntity = new AnimeEntity();
        animeEntity.setMal_id(id);
        animeEntity.setImage_url(image_url);
        animeEntity.setSynopsis(synopsis);
        animeEntity.setEpisodes(episodes);
        animeEntity.setStart_date(this.getStart_date());
        animeEntity.setEnd_date(this.getEnd_date());
        animeEntity.setTitle(title);
        animeEntity.setType(type);
        return animeEntity;
    }

}

