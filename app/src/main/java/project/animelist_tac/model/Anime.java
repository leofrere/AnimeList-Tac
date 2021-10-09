package project.animelist_tac.model;

public class Anime {
    private final int id;
    private String imgUrl;
    private String title;
    private String synopsis;
    private int nbEpisode;
    private String startDate;
    private String endDate;
    private String type;

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

    public String startDate(){
        return startDate;
    }

    public void startDate(String dateDebut){
        this.startDate = dateDebut;
    }

    public String endDate(){
        return endDate;
    }

    public void endDate(String dateFin){
        this.endDate = dateFin;
    }

    public String type(){
        return type;
    }

    public void type(String type){
        this.type = type;
    }

}
