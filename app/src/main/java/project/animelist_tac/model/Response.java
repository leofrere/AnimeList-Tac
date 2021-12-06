package project.animelist_tac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represent the class for the response
 * @author Leo Frere, Jeremy Curoux
 * */
public class Response {

    @SerializedName("results")
    @Expose
    public List<Anime> results = null;

}

