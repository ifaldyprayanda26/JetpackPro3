package com.apps.ifaldyprayanda.jetpackpro1.fav.movie;


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
import com.apps.ifaldyprayanda.jetpackpro1.detail.MovieDetailViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.fav.MovieFragmentCallback;
import com.apps.ifaldyprayanda.jetpackpro1.movie.MovieViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment implements MovieFragmentCallback {

    private RecyclerView rvFavMovie;
    private ProgressBar progressBar;
    private MovieFavPagedAdapter movieFavPagedAdapter;
    FavMovieViewModel favMovieViewModel;


    public FavMovieFragment() {
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
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMovie = view.findViewById(R.id.rv_FavMovie);
        progressBar = view.findViewById(R.id.progressFav);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null)
        {
            favMovieViewModel = obtainViewModel(getActivity());
            movieFavPagedAdapter = new MovieFavPagedAdapter(this);

            itemTouchHelper.attachToRecyclerView(rvFavMovie);

            favMovieViewModel.getFavMovies().observe(this, movies -> {
                if (movies != null)
                {
                    switch (movies.status)
                    {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            movieFavPagedAdapter.submitList(movies.data);
                            movieFavPagedAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "There is an Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavMovie.setHasFixedSize(true);
            rvFavMovie.setAdapter(movieFavPagedAdapter);
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
                MovieEntity movieEntity = movieFavPagedAdapter.getItemById(swipePos);
                favMovieViewModel.setFavMovie(movieEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> favMovieViewModel.setFavMovie(movieEntity));
                snackbar.show();
            }
        }
    });

    @NonNull
    private static FavMovieViewModel obtainViewModel(FragmentActivity activity)
    {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavMovieViewModel.class);
    }
}
