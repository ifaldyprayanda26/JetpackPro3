package com.apps.ifaldyprayanda.jetpackpro1.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowResponse implements Parcelable {

    private String tvId;
    private String tvTitle;
    private String tvDate;
    private String tvPhoto;
    private String tvLanguage;
    private String tvOverview;

    public TvShowResponse(String tvId, String tvTitle, String tvDate, String tvPhoto, String tvLanguage, String tvOverview) {
        this.tvId = tvId;
        this.tvTitle = tvTitle;
        this.tvDate = tvDate;
        this.tvPhoto = tvPhoto;
        this.tvLanguage = tvLanguage;
        this.tvOverview = tvOverview;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public String getTvPhoto() {
        return tvPhoto;
    }

    public void setTvPhoto(String tvPhoto) {
        this.tvPhoto = tvPhoto;
    }

    public String getTvLanguage() {
        return tvLanguage;
    }

    public void setTvLanguage(String tvLanguage) {
        this.tvLanguage = tvLanguage;
    }

    public String getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(String tvOverview) {
        this.tvOverview = tvOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tvId);
        dest.writeString(this.tvTitle);
        dest.writeString(this.tvDate);
        dest.writeString(this.tvPhoto);
        dest.writeString(this.tvLanguage);
        dest.writeString(this.tvOverview);
    }

    protected TvShowResponse(Parcel in) {
        this.tvId = in.readString();
        this.tvTitle = in.readString();
        this.tvDate = in.readString();
        this.tvPhoto = in.readString();
        this.tvLanguage = in.readString();
        this.tvOverview = in.readString();
    }

    public static final Creator<TvShowResponse> CREATOR = new Creator<TvShowResponse>() {
        @Override
        public TvShowResponse createFromParcel(Parcel source) {
            return new TvShowResponse(source);
        }

        @Override
        public TvShowResponse[] newArray(int size) {
            return new TvShowResponse[size];
        }
    };
}
