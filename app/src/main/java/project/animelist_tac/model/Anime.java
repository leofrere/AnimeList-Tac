package project.animelist_tac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("end_date")
    @Expose
    public String end_date;

}

