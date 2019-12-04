package com.apps.ifaldyprayanda.jetpackpro1.fav.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.detail.MovieDetailActivity;
import com.apps.ifaldyprayanda.jetpackpro1.fav.MovieFragmentCallback;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MovieFavPagedAdapter extends PagedListAdapter<MovieEntity, MovieFavPagedAdapter.MovieViewHolder> {

    private MovieFragmentCallback movieFragmentCallback;

    protected MovieFavPagedAdapter(MovieFragmentCallback diffCalback)
    {
        super(DIFF_CALLBACK);
        this.movieFragmentCallback = diffCalback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieEntity movie = getItem(position);

        if (movie != null)
        {
            holder.movieTitle.setText(movie.getMovieTitle());
            holder.movieDate.setText(movie.getMovieDate());
            holder.movieCreator.setText(movie.getMovieDirector());

            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.getMovieId());
                context.startActivity(intent);
            });

            GlideApp.with(holder.itemView.getContext())
                    .load(movie.getMoviePhoto())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp))
                    .error(R.drawable.ic_error)
                    .into(holder.imgPoster);
        }
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId().equals(newItem.getMovieId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    MovieEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        final TextView movieTitle;
        final TextView movieCreator;
        final TextView movieDate;
        final ImageView imgPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.tv_item_title);
            movieDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
            movieCreator = itemView.findViewById(R.id.tv_item_overview);
        }
    }
}
