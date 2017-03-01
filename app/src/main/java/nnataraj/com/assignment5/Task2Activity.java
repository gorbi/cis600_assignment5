package nnataraj.com.assignment5;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Task2Activity extends AppCompatActivity implements MovieItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new MovieItemFragment())
                .commit();
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onItemSelected(Map<String, ?> item) {

    }

    @Override
    public void onClick(Map<String, ?> item) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, DetailViewFragment.newInstance((HashMap<String, ?>) item))
                .addToBackStack("store")
                .commit();
        ((TextView) findViewById(R.id.title)).setText((String)item.get("name"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((TextView) findViewById(R.id.title)).setText("Movie List");
    }
}
