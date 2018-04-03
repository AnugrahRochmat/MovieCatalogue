package io.github.anugrahrochmat.moviecatalogue.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.activity.MainActivity;
import io.github.anugrahrochmat.moviecatalogue.fragment.MovieDetailFragment;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;

public class FindMoviesAdapter extends RecyclerView.Adapter<FindMoviesAdapter.FindMoviesViewHolder> {

    private final String SEND_MOVIE_TO_DETAIL_FRAGMENT = "SEND_MOVIE_TO_DETAIL_FRAGMENT";

    private List<Movie> movieList;
    private Context context;

    public class FindMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_movie_poster)    ImageView imgMoviePoster;
        @BindView(R.id.tv_movie_title)      TextView tvMovieTitle;
        @BindView(R.id.tv_movie_desc)       TextView tvMovieDesc;
        @BindView(R.id.tv_movie_released)   TextView tvMovieReleased;

        public FindMoviesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = movieList.get(getAdapterPosition());

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(MovieDetailFragment.MOVIE_TO_DISPLAY_IN_DETAIL, movie);
            movieDetailFragment.setArguments(bundle);

            FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frame_container, movieDetailFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public FindMoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public FindMoviesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutInflater = R.layout.find_movies_item_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutInflater, viewGroup, false);
        return new FindMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindMoviesViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.tvMovieTitle.setText(movie.getOriginalTitle());
        holder.tvMovieDesc.setText(movie.getOverview());
        Picasso.with(context).load(context.getResources().getString(R.string.tmdb_image_url) + movie.getPosterPath())
                .placeholder(R.drawable.placeholder).into(holder.imgMoviePoster);
        holder.tvMovieReleased.setText(parseDate(movie.getReleaseDate()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public String parseDate(String time){
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "EEEE, MMM d, yyyy";
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
