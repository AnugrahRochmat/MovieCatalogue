package io.github.anugrahrochmat.moviecatalogue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;

public class FindMoviesAdapter extends RecyclerView.Adapter<FindMoviesAdapter.FindMoviesViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public class FindMoviesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_movie_poster)    ImageView imgMoviePoster;
        @BindView(R.id.tv_movie_title)      TextView tvMovieTitle;
        @BindView(R.id.tv_movie_desc)       TextView tvMovieDesc;
        @BindView(R.id.tv_movie_released)   TextView tvMovieReleased;

        public FindMoviesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
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

        // TODO: Date formatting to Day, Date
        holder.tvMovieReleased.setText(movie.getReleaseDate());
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
}
