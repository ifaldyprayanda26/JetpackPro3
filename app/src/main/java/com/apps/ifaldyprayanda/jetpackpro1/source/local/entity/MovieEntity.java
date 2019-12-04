package com.apps.ifaldyprayanda.jetpackpro1.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    private String movieId;

    @ColumnInfo(name = "movieTitle")
    private String movieTitle;

    @ColumnInfo(name = "movieDate")
    private String movieDate;

    @ColumnInfo(name = "moviePhoto")
    private String moviePhoto;

    @ColumnInfo(name = "movieDirector")
    private String movieDirector;

    @ColumnInfo(name = "movieOverview")
    private String movieOverview;

    @ColumnInfo(name = "favourited")
    private boolean favourited = false;


    public MovieEntity(String movieId, String movieTitle, String movieDate, String moviePhoto, String movieDirector, String movieOverview) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDate = movieDate;
        this.moviePhoto = moviePhoto;
        this.movieDirector = movieDirector;
        this.movieOverview = movieOverview;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getMoviePhoto() {
        return moviePhoto;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }
}
