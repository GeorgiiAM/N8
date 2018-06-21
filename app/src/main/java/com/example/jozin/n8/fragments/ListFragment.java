package com.example.jozin.n8.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.backendless.BackendlessCollection;
import com.example.jozin.n8.MainActivity;
import com.example.jozin.n8.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnFilter;


   // private List<Jump> allJump = new ArrayList<>();
    //private ListView listview;
    //private ProgressBar progressBar;
    /*private BackendlessCollection<Jump> jumps;
    private List<Jump> totalJump = new ArrayList<>();
    private boolean isLoadingItems = false;
    private JumpAdapter adapter;*/

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_list, container, false);

        //Backendless.setUrl( Defaults.SERVER_URL );
        //Backendless.initApp(getActivity().getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onNavigationItemSelected2(5);
            }
        });

        btnFilter = (Button) v.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onNavigationItemSelected2(10);

            }
        });

/*
        listview = (ListView) v.findViewById(R.id.list);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);

        BackendlessDataQuery query = new BackendlessDataQuery();

        QueryOptions queryOptions = new QueryOptions();

        String whereClause = "created";

        queryOptions.addSortByOption(whereClause);

        query.setQueryOptions(queryOptions);

        Backendless.Data.of(Jump.class).find(query, new AsyncCallback<BackendlessCollection<Jump>>() {

            @Override
            public void handleResponse(BackendlessCollection<Jump> eventsListBackendlessCollection) {

                progressBar.setVisibility(View.VISIBLE);
                ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 500);
                animation.setDuration(5000);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.start();

                Jump JumpList;
                for (Jump totalEvents : eventsListBackendlessCollection.getCurrentPage()) {

                    JumpList = new Jump();

                    JumpList.setNumb(totalEvents.getNumb());
                    JumpList.setDate(totalEvents.getDate());
                    JumpList.setDZ(totalEvents.getDZ());
                    JumpList.setAircraft(totalEvents.getAircraft());
                    JumpList.setGear(totalEvents.getGear());
                    JumpList.setTJump(totalEvents.getTJump());
                    JumpList.setExit(totalEvents.getExit());
                    JumpList.setDeploy(totalEvents.getDeploy());
                    JumpList.setDelay(totalEvents.getDelay());
                    JumpList.setNotes(totalEvents.getNotes());


                    allJump.add(JumpList);

                    JumpAdapter adapter = new JumpAdapter(getActivity().getApplicationContext(), allJump);
                    listview.setAdapter(adapter);

                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
            }


        });

        adapter = new JumpAdapter( getActivity().getApplicationContext(), R.layout.one_jump);
        setListAdapter( adapter );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setRelated( Arrays.asList( "locations" ) );

        BackendlessDataQuery query = new BackendlessDataQuery( queryOptions );

        Backendless.Data.of( Restaurant.class ).find( query, new LoadingCallback<BackendlessCollection<Restaurant>>( this, getString( R.string.loading_restaurants ), true )
        {
            @Override
            public void handleResponse( BackendlessCollection<Restaurant> restaurantsBackendlessCollection )
            {
                restaurants = restaurantsBackendlessCollection;

                addMoreItems( restaurantsBackendlessCollection );

                super.handleResponse( restaurantsBackendlessCollection );
            }
        } );

        ListView list = (ListView) findViewById( android.R.id.list );
        list.setOnScrollListener( new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged( AbsListView view, int scrollState )
            {

            }

            @Override
            public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount )
            {
                if( needToLoadItems( firstVisibleItem, visibleItemCount, totalItemCount ) )
                {
                    isLoadingItems = true;

                    restaurants.nextPage( new LoadingCallback<BackendlessCollection<Restaurant>>( RestaurantListingActivity.this )
                    {
                        @Override
                        public void handleResponse( BackendlessCollection<Restaurant> nextPage )
                        {
                            restaurants = nextPage;

                            addMoreItems( nextPage );

                            isLoadingItems = false;
                        }
                    } );
                }
            }
        } );
*/

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
