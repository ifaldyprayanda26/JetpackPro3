package com.apps.ifaldyprayanda.jetpackpro1.utils;

import android.app.Application;

import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.MovieResponse;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.TvShowResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

//    change JSON file to String
    private String parsingFileToString(String fileName)
    {
        try{
            InputStream is = application.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    change JSON File Move String to Array
    public List<MovieResponse> loadMovie()
    {
        ArrayList<MovieResponse> list = new ArrayList<>();

        try {
            JSONObject movieObject = new JSONObject(parsingFileToString("movie.json"));
            JSONArray movieArray = movieObject.getJSONArray("results");
            for (int i = 0; i < movieArray.length(); i++)
            {
                JSONObject movie = movieArray.getJSONObject(i);

                String id = movie.getString("id");
                String title = movie.getString("title");
                String date = movie.getString("release_date");
                String image = movie.getString("poster_path");
                String language = movie.getString("original_language");
                String overview = movie.getString("overview");

                MovieResponse movieResponse = new MovieResponse(id, title, date, image, language, overview);
                list.add(movieResponse);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return list;
    }

//    change JSON Tv Show File String to Array
    public List<TvShowResponse> loadTvShow()
    {
        ArrayList<TvShowResponse> list = new ArrayList<>();

        try{
            JSONObject tvObject = new JSONObject(parsingFileToString("tv.json"));
            JSONArray tvArray = tvObject.getJSONArray("results");
            for (int i = 0; i < tvArray.length(); i++)
            {
                JSONObject tv = tvArray.getJSONObject(i);

                String id = tv.getString("id");
                String title = tv.getString("name");
                String date = tv.getString("first_air_date");
                String image = tv.getString("poster_path");
                String language = tv.getString("original_language");
                String overview = tv.getString("overview");

                TvShowResponse tvShowResponse = new TvShowResponse(id, title, date, image, language, overview);
                list.add(tvShowResponse);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return  list;
    }
}
