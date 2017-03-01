package nnataraj.com.assignment5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Task3Activity extends AppCompatActivity implements MovieItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        final MovieItemFragment movieItemFragment = new MovieItemFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, movieItemFragment)
                .commit();

        Button sortByName =  (Button) findViewById(R.id.sort_by_name);
        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieItemFragment.sortByName();
            }
        });

        Button sortByYear =  (Button) findViewById(R.id.sort_by_year);
        sortByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieItemFragment.sortByYear();
            }
        });
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onItemSelected(Map<String, ?> item) {

    }

    @Override
    public void onClick(Map<String, ?> item) {

    }
}
