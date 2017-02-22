package nnataraj.com.assignment5;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieItemFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    MyMovieItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private static MovieData movieData = new MovieData();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieItemFragment() {
    }

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 20;
            }
            outRect.bottom = 20;
            outRect.left = 20;
            outRect.right = 20;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movieitem_list, container, false);

        // Set the adapter
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_activity_main_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        itemRecyclerViewAdapter = new MyMovieItemRecyclerViewAdapter(mListener, movieData.getMoviesList());

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(itemRecyclerViewAdapter);
        alphaAdapter.setInterpolator(new OvershootInterpolator(1f));
        alphaAdapter.setDuration(1000);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleInAnimationAdapter.setDuration(1000);
        recyclerView.setAdapter(scaleInAnimationAdapter);

        FlipInTopXAnimator flipInTopXAnimator = new FlipInTopXAnimator(new OvershootInterpolator(1f));
        flipInTopXAnimator.setAddDuration(500);
        flipInTopXAnimator.setRemoveDuration(500);
        recyclerView.setItemAnimator(flipInTopXAnimator);

        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration());


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onClick(Map<String, ?> item);

        void onLongClick(int position);

        void onItemSelected(Map<String, ?> item);
    }
}
