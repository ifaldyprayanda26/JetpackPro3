package com.apps.ifaldyprayanda.jetpackpro1.tvshow;


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
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.viewmodel.ViewModelFactory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements TvCallback{

    private RecyclerView rvTvShow;
    private ProgressBar progressBar;
    private TvShowAdapter tvShowAdapter;

    private TvShowViewModel tvShowViewModel;
    private List<TvShowEntity> tvShows;

    public TvFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance()
    {
        return new TvFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rvTvShow);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onShareClick(TvShowEntity tv) {
        if(getActivity() != null)
        {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Share this Tv Show now.")
                    .setText(String.format("Let's watch TV Show %s in your Favourite Platform Television", tv.getTvTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            tvShowViewModel = obtainViewModel(getActivity());


            tvShowAdapter =  new TvShowAdapter(getActivity(), this);

            tvShowViewModel.getTvShows().observe(this, tvShows -> {
                if (tvShows != null)
                {
                    switch (tvShows.status)
                    {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvShowAdapter.setListTv(tvShows.data);
                            tvShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "There is An Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShow.setHasFixedSize(true);
            rvTvShow.setAdapter(tvShowAdapter);
        }
    }

    private TvShowViewModel obtainViewModel(FragmentActivity activity)
    {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }
}
