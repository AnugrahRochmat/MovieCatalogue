package io.github.anugrahrochmat.favouritemovielist.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.anugrahrochmat.favouritemovielist.R;

import static io.github.anugrahrochmat.favouritemovielist.database.MovieContract.MovieEntry.COLUMN_MOVIE_TITLE;
import static io.github.anugrahrochmat.favouritemovielist.database.MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW;
import static io.github.anugrahrochmat.favouritemovielist.database.MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE;
import static io.github.anugrahrochmat.favouritemovielist.database.MovieContract.MovieEntry.COLUMN_MOVIE_POSTER;
import static io.github.anugrahrochmat.favouritemovielist.database.MovieContract.getColumnString;

public class MovieListAdapter extends CursorAdapter{

    public MovieListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_item_list, parent, false);
        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            ImageView imgPoster = (ImageView) view.findViewById(R.id.img_movie_poster);
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_movie_title);
            TextView tvDesc = (TextView)view.findViewById(R.id.tv_movie_desc);
            TextView tvDate = (TextView)view.findViewById(R.id.tv_movie_released);

            Picasso.with(context).load(context.getResources().getString(R.string.tmdb_image_url) + getColumnString(cursor, COLUMN_MOVIE_POSTER)).into(imgPoster);
            tvTitle.setText(getColumnString(cursor, COLUMN_MOVIE_TITLE));
            tvDesc.setText(getColumnString(cursor, COLUMN_MOVIE_OVERVIEW));
            tvDate.setText(getColumnString(cursor, COLUMN_MOVIE_RELEASE_DATE));
        }
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }
}
