package com.apps.ifaldyprayanda.jetpackpro1.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.detail.MovieDetailActivity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Activity activity;
    private List<MovieEntity> mEntity = new ArrayList<>();
    private final MovieFragmentCallback callback;

    public MovieAdapter(Activity activity, MovieFragmentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    private  List<MovieEntity> getListMovie()
    {
        return mEntity;
    }

    void setListMovie(List<MovieEntity> listMovie)
    {
        if (listMovie == null) return;
        this.mEntity.clear();
        this.mEntity.addAll(listMovie);
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        MovieEntity movie = mEntity.get(position);

        holder.movieTitle.setText(getListMovie().get(position).getMovieTitle());
        holder.movieCreator.setText(getListMovie().get(position).getMovieDirector());
        holder.movieDate.setText(getListMovie().get(position).getMovieDate());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.getMovieId());
            activity.startActivity(intent);
        });

        holder.imgShare.setOnClickListener(v -> callback.onShareClick(mEntity.get(holder.getAdapterPosition())));

        GlideApp.with(holder.itemView.getContext())
                .load(getListMovie().get(position).getMoviePhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView movieTitle;
        final TextView movieCreator;
        final TextView movieDate;
        final ImageView imgPoster;
        final ImageButton imgShare;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.tv_item_title);
            movieDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
            movieCreator = itemView.findViewById(R.id.tv_item_overview);
            imgShare = itemView.findViewById(R.id.img_share);
        }
    }
}
