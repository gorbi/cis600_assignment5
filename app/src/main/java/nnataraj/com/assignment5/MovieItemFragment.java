package nnataraj.com.assignment5;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

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
    LinearLayoutManager linearLayoutManager;
    MyMovieItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private static MovieData movieData = new MovieData();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieItemFragment() {
    }

    public void cloneMovie(int position) {
        movieData.moviesList.add(position+1, (Map) movieData.getItem(position).clone());
        itemRecyclerViewAdapter.notifyItemInserted(position + 1);
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
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
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

        /*Button selectAll = (Button) view.findViewById(R.id.select_all_button);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", true);
                }
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button clearAll = (Button) view.findViewById(R.id.clear_all_button);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", false);
                }
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button delete = (Button) view.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                int index = 0;
                while (!(count <= 0)) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(index);
                    if (item.get("selection")) {
                        movieData.removeItem(index);
                        itemRecyclerViewAdapter.notifyItemRemoved(index);
                    } else {
                        index++;
                    }
                    count--;
                }
            }
        });

        class MovieComparator implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                Integer obj1Value = Integer.parseInt((String) ((HashMap) o1).get("year"));
                Integer obj2Value = Integer.parseInt((String) ((HashMap) o2).get("year"));
                return obj1Value.compareTo(obj2Value);
            }
        }

        Button sort = (Button) view.findViewById(R.id.sort_button);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(movieData.getMoviesList(), new MovieComparator());
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });*/
        setHasOptionsMenu(true);

        return view;
    }

    class MovieComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Integer obj1Value = Integer.parseInt((String) ((HashMap) o1).get("year"));
            Integer obj2Value = Integer.parseInt((String) ((HashMap) o2).get("year"));
            return obj1Value.compareTo(obj2Value);
        }
    }

    class MovieComparator2 implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            String obj1Value = (String) ((HashMap) o1).get("name");
            String obj2Value = (String) ((HashMap) o2).get("name");
            return obj1Value.compareToIgnoreCase(obj2Value);
        }
    }

    public void sortByYear() {
        Collections.sort(movieData.getMoviesList(), new MovieComparator());
        itemRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void sortByName() {
        Collections.sort(movieData.getMoviesList(), new MovieComparator2());
        itemRecyclerViewAdapter.notifyDataSetChanged();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.findItem(R.id.action_search) == null)
            inflater.inflate(R.menu.menu_search_view, menu);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    int position = movieData.find(query);
                    if (position >= 0)
                        linearLayoutManager.scrollToPositionWithOffset(position, 20);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    return false;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
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
