package io.github.anugrahrochmat.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.activity.MainActivity;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.DetailMoviePresenter;
import io.github.anugrahrochmat.moviecatalogue.view.DetailMovieView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements View.OnClickListener, DetailMovieView {

    public static String MOVIE_TO_DISPLAY_IN_DETAIL = "MOVIE_TO_DISPLAY_IN_DETAIL";

    @BindView(R.id.toolbar)                Toolbar mToolbar;
    @BindView(R.id.img_backdrop)            ImageView imgBackdrop;
    @BindView(R.id.img_detail_movie_poster) ImageView imgPoster;
    @BindView(R.id.tv_detail_movie_title)   TextView tvMovieTitle;
    @BindView(R.id.tv_detail_vote_average)  TextView tvMovieVoted;
    @BindView(R.id.tv_detail_released_date) TextView tvReleasedDate;
    @BindView(R.id.tv_detail_synopsis)      TextView tvMovieSynopsis;

    private DetailMoviePresenter presenter;
    private Bundle data;
    private Movie movie;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
//        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
//        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(this);

        data = getArguments();
        presenter = new DetailMoviePresenter(this);
        presenter.onStartFragment(data);

//        Bundle data = getArguments();
//        if (data != null) {
//
//            Movie movie = data.getParcelable(MOVIE_TO_DISPLAY_IN_DETAIL);
//
//            (getActivity()).setTitle(movie.getOriginalTitle());
//
//            Picasso.with(getContext()).load(getContext().getResources().getString(R.string.tmdb_image_url) + movie.getBackdropPath())
//                    .placeholder(R.drawable.placeholder).into(imgBackdrop);
//            Picasso.with(getContext()).load(getContext().getResources().getString(R.string.tmdb_image_url) + movie.getPosterPath())
//                    .placeholder(R.drawable.placeholder).into(imgPoster);
//
//            tvMovieTitle.setText(movie.getOriginalTitle());
//            tvMovieVoted.setText(movie.getVoteAverage().toString() + " / 10 ");
//            tvReleasedDate.setText(parseDate(movie.getReleaseDate()));
//            tvMovieSynopsis.setText(movie.getOverview());
//        }

    }

    @Override
    public void displayDetailMovie(Bundle data) {
        movie = data.getParcelable(MOVIE_TO_DISPLAY_IN_DETAIL);
        (getActivity()).setTitle(movie.getOriginalTitle());

        Picasso.with(getContext()).load(getContext().getResources().getString(R.string.tmdb_image_url) + movie.getBackdropPath())
                .placeholder(R.drawable.placeholder).into(imgBackdrop);
        Picasso.with(getContext()).load(getContext().getResources().getString(R.string.tmdb_image_url) + movie.getPosterPath())
                .placeholder(R.drawable.placeholder).into(imgPoster);

        tvMovieTitle.setText(movie.getOriginalTitle());
        tvMovieVoted.setText(movie.getVoteAverage().toString() + " / 10 ");
        tvReleasedDate.setText(parseDate(movie.getReleaseDate()));
        tvMovieSynopsis.setText(movie.getOverview());
    }

    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }

    public String parseDate(String time){
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "MMM d, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        try {
            Date date = inputFormat.parse(time);
            String str = outputFormat.format(date);
            return str;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
