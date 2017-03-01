package nnataraj.com.assignment5;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailViewFragment extends Fragment {

    private static final String ARG_MOVIE = "movie";
    private HashMap<String, ?> movieData;
    private int total = 0;
    ShareActionProvider mShareActionProvider;

    public DetailViewFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(HashMap<String, ?> movieData) {
        Fragment fragment = new DetailViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movieData);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieData = (HashMap<String, ?>) getArguments().getSerializable(ARG_MOVIE);
        }
        if (savedInstanceState != null)
            total = savedInstanceState.getInt("total");
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        outstate.putInt("total", total);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailmenu,menu);
        MenuItem shareItem=menu.findItem(R.id.action_share);
        mShareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intentShare =new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT,(String)movieData.get("name"));
        mShareActionProvider.setShareIntent(intentShare);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_view, container, false);
        final TextView name = (TextView) rootView.findViewById(R.id.title);
        final TextView year = (TextView) rootView.findViewById(R.id.year);
        final TextView length = (TextView) rootView.findViewById(R.id.length);
        final RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        final TextView director = (TextView) rootView.findViewById(R.id.director);
        final TextView cast = (TextView) rootView.findViewById(R.id.cast);
        final TextView description = (TextView) rootView.findViewById(R.id.description);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        setHasOptionsMenu(true);

        name.setText((String) movieData.get("name"));
        year.setText((String) movieData.get("year"));
        length.setText((String) movieData.get("length"));
        director.setText((String) movieData.get("director"));
        cast.setText((String) movieData.get("stars"));
        description.setText((String) movieData.get("description"));
        imageView.setImageResource((int) (Object) movieData.get("image"));
        ratingBar.setRating(((float) (double) (Object) movieData.get("rating")) / 2f);

        return rootView;
    }

}
