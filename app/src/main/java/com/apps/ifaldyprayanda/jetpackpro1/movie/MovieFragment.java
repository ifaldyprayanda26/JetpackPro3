package com.apps.ifaldyprayanda.jetpackpro1.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.viewmodel.ViewModelFactory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieFragmentCallback{

    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;

    private MovieViewModel movieViewModel;
    private List<MovieEntity> movies;

    public MovieFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance()
    {
        return new MovieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

//    untuk menghubungkan fragment dengan recycler view
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onShareClick(MovieEntity movie) {
        if (getActivity() != null)
        {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Share this Movie now.")
                    .setText(String.format("Let's watch Movie %s in your Favourite Cinema", movie.getMovieTitle()))
                    .startChooser();
        }
    }

//    relation between RecyclerView dengan Adapter

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity()!= null)
        {
            progressBar.setVisibility(View.VISIBLE);
            movieViewModel = obtainViewModel(getActivity());

            movieAdapter = new MovieAdapter(getActivity(), this);

            movieViewModel.getMovies().observe(this, movies -> {
                if (movies != null)
                {
                    switch (movies.status)
                    {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            movieAdapter.setListMovie(movies.data);
                            movieAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "There is as Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });


            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(movieAdapter);
        }
    }

    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }


}
