package com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.detail.DetailTvShowActivity;
import com.apps.ifaldyprayanda.jetpackpro1.fav.MovieFragmentCallback;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowPagedAdapter extends PagedListAdapter<TvShowEntity, TvShowPagedAdapter.TvViewHolder> {

    private MovieFragmentCallback callback;

    protected TvShowPagedAdapter(MovieFragmentCallback diffCalback)
    {
        super(DIFF_CALLBACK);
        this.callback = diffCalback;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        final TvShowEntity tvShow = getItem(position);

        if (tvShow != null)
        {
            holder.tvTitle.setText(tvShow.getTvTitle());
            holder.tvDate.setText(tvShow.getTvDate());
            holder.tvCreator.setText(tvShow.getTvCreator());

            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV, tvShow.getTvId());
                context.startActivity(intent);
            });

            GlideApp.with(holder.itemView.getContext())
                    .load(tvShow.getTvPhoto())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp))
                    .error(R.drawable.ic_error)
                    .into(holder.tvPoster);
        }
    }

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getTvId().equals(newItem.getTvId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    TvShowEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle;
        final TextView tvCreator;
        final TextView tvDate;
        final ImageView tvPoster;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvPoster = itemView.findViewById(R.id.img_poster);
            tvCreator = itemView.findViewById(R.id.tv_item_overview);
        }
    }
}
