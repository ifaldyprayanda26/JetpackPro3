package com.apps.ifaldyprayanda.jetpackpro1.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.detail.DetailTvShowActivity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {

    private final Activity activity;
    private List<TvShowEntity> tEntity = new ArrayList<>();
    private final TvCallback tvCallback;

    public TvShowAdapter(Activity activity, TvCallback callback) {
        this.activity = activity;
        this.tvCallback = callback;
    }

    private List<TvShowEntity> getListTv()
    {
        return tEntity;
    }

    void setListTv(List<TvShowEntity> listTv)
    {
        if (listTv == null) return;
        this.tEntity.clear();
        this.tEntity.addAll(listTv);
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tvshow, parent, false);
        return  new TvViewHolder(tView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        TvShowEntity tv = tEntity.get(position);

        holder.tvTitle.setText(getListTv().get(position).getTvTitle());
        holder.tvCreator.setText(getListTv().get(position).getTvCreator());
        holder.tvDate.setText(getListTv().get(position).getTvDate());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_TV, tv.getTvId());
            activity.startActivity(intent);
        });

        holder.imgShare.setOnClickListener(v -> tvCallback.onShareClick(tEntity.get(holder.getAdapterPosition())));

        GlideApp.with(holder.itemView.getContext())
                .load(getListTv().get(position).getTvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListTv().size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvCreator;
        final TextView tvDate;
        final ImageView imgPoster;
        final ImageButton imgShare;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCreator = itemView.findViewById(R.id.tvCreator);
            imgPoster = itemView.findViewById(R.id.img_poster);
            imgShare = itemView.findViewById(R.id.img_share);
        }
    }
}
