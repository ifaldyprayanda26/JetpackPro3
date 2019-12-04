package com.apps.ifaldyprayanda.jetpackpro1.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponse implements Parcelable {
    private String movieId;
    private String movieTitle;
    private String movieDate;
    private String moviePhoto;
    private String movieLanguage;
    private String movieOverview;

    public MovieResponse(String movieId, String movieTitle, String movieDate, String moviePhoto, String movieLanguage, String movieOverview) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDate = movieDate;
        this.moviePhoto = moviePhoto;
        this.movieLanguage = movieLanguage;
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

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.movieTitle);
        dest.writeString(this.movieDate);
        dest.writeString(this.moviePhoto);
        dest.writeString(this.movieLanguage);
        dest.writeString(this.movieOverview);
    }

    protected MovieResponse(Parcel in) {
        this.movieId = in.readString();
        this.movieTitle = in.readString();
        this.movieDate = in.readString();
        this.moviePhoto = in.readString();
        this.movieLanguage = in.readString();
        this.movieOverview = in.readString();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel source) {
            return new MovieResponse(source);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };
}
