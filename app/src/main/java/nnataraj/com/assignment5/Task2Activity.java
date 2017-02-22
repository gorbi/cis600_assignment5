package nnataraj.com.assignment5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Map;

public class Task2Activity extends AppCompatActivity implements MovieItemFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,new MovieItemFragment())
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

    }
}
