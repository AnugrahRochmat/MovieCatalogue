package io.github.anugrahrochmat.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable{
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private Double voteAverage;

    /**
     * Movie class constructor
     */
    public Movie(){
//        this.backdropPath = backdropPath;
//        this.id = id;
//        this.originalTitle = originalTitle;
//        this.overview = overview;
//        this.popularity = popularity;
//        this.posterPath = posterPath;
//        this.releaseDate = releaseDate;
//        this.title = title;
//        this.voteAverage = voteAverage;
    }

    /**
     * Getter and Setter
     */
    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }

    public String getOverview(){
        return overview;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }

    public Double getPopularity(){
        return popularity;
    }
    public void setPopularity(Double popularity){
        this.popularity = popularity;
    }

    public String getPosterPath(){
        return posterPath;
    }
    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getReleaseDate(){
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public Double getVoteAverage(){
        return voteAverage;
    }
    public void setVoteAverage(Double voteAverage){
        this.voteAverage = voteAverage;
    }

    /**
     * Parcelling
     */
    public Movie(Parcel in){
        this.backdropPath = in.readString();
        this.id = in.readInt();
        this.originalTitle = in.readString();
        this.overview = in.readString();
        //this.popularity = in.readDouble();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.title = in.readString();
        this.voteAverage = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeInt(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        //dest.writeDouble(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
        dest.writeString(this.title);
        dest.writeDouble(this.voteAverage);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "backdropPath='" + backdropPath + '\'' +
                ", id='" + id + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                //", popularity='" + popularity + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                '}';
    }

}
