package com.apps.ifaldyprayanda.jetpackpro1.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshow")
public class TvShowEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    private String tvId;

    @ColumnInfo(name = "tvTitle")
    private String tvTitle;

    @ColumnInfo(name = "tvDate")
    private String tvDate;

    @ColumnInfo(name = "tvPhoto")
    private String tvPhoto;

    @ColumnInfo(name = "tvCreator")
    private String tvCreator;

    @ColumnInfo(name = "tvOverview")
    private String tvOverview;

    @ColumnInfo(name = "favourited")
    private boolean favourited = false;

    public TvShowEntity(String tvId, String tvTitle, String tvDate, String tvPhoto, String tvCreator, String tvOverview) {
        this.tvId = tvId;
        this.tvTitle = tvTitle;
        this.tvDate = tvDate;
        this.tvPhoto = tvPhoto;
        this.tvCreator = tvCreator;
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

    public String getTvCreator() {
        return tvCreator;
    }

    public void setTvCreator(String tvCreator) {
        this.tvCreator = tvCreator;
    }

    public String getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(String tvOverview) {
        this.tvOverview = tvOverview;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }

}
