package com.example.animelist.model;

public class Anime {
    private final int id;
    private String imgUrl;
    private String title;
    private String synopsis;
    private int nbEpisode;
    private String dateDebut;
    private String dateFin;
    private String type;

    //ajouter date_debut, date_fin, type

    public Anime(int id){
        this.id = id;
    }

    public int id(){
        return id;
    }

    public String imgUrl(){
        return imgUrl;
    }

    public void imgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String title(){
        return title;
    }

    public void title(String title){
        this.title = title;
    }

    public String synopsis(){
        return synopsis;
    }

    public void synopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public int nbEpisode(){
        return nbEpisode;
    }

    public void nbEpisode(int nbEpisode){
        this.nbEpisode = nbEpisode;
    }

    public String dateDebut(){
        return dateDebut;
    }

    public void dateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    public String dateFin(){
        return dateFin;
    }

    public void dateFin(String dateFin){
        this.dateFin = dateFin;
    }

    public String type(){
        return type;
    }

    public void type(String type){
        this.type = type;
    }

}
