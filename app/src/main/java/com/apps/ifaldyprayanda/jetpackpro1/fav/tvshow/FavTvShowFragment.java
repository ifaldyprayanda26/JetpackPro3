package com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.fav.MovieFragmentCallback;
import com.apps.ifaldyprayanda.jetpackpro1.fav.movie.FavMovieFragment;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavTvShowFragment extends Fragment implements MovieFragmentCallback {

    private RecyclerView rvFavTvShow;
    private ProgressBar progressBar;
    private TvShowPagedAdapter tvShowPagedAdapter;
    FavTvShowViewModel favTvShowViewModel;


    public FavTvShowFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance()
    {
        return new FavMovieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavTvShow = view.findViewById(R.id.rv_FavTv);
        progressBar = view.findViewById(R.id.progressFavTv);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null)
        {
            favTvShowViewModel = obtainViewModel(getActivity());
            tvShowPagedAdapter = new TvShowPagedAdapter(this);

            itemTouchHelper.attachToRecyclerView(rvFavTvShow);

            favTvShowViewModel.getFavTvShow().observe(this, tvShows -> {
                if (tvShows != null)
                {
                    switch (tvShows.status)
                    {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvShowPagedAdapter.submitList(tvShows.data);
                            tvShowPagedAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "There is an Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvFavTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavTvShow.setHasFixedSize(true);
            rvFavTvShow.setAdapter(tvShowPagedAdapter);
        }
    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null)
            {
                int swipePos = viewHolder.getAdapterPosition();
                TvShowEntity tvShowEntity = tvShowPagedAdapter.getItemById(swipePos);
                favTvShowViewModel.setFavTvShows(tvShowEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> favTvShowViewModel.setFavTvShows(tvShowEntity));
                snackbar.show();
            }
        }
    });

    @NonNull
    private static FavTvShowViewModel obtainViewModel(FragmentActivity activity)
    {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavTvShowViewModel.class);
    }

}
