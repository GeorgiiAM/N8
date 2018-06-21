package com.example.jozin.n8.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.jozin.n8.MainActivity;
import com.example.jozin.n8.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button btnLogOut;
    private Button btnOtherUser;
    private TextView tv_jump;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        btnLogOut = (Button) v.findViewById(R.id.btnLogOut);
        btnOtherUser = (Button) v.findViewById(R.id.btnOtherUser);
        tv_jump=(TextView) v.findViewById(R.id.tv_jump);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    public void handleResponse(Void response) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "user has been logged out",
                                Toast.LENGTH_SHORT).show();
                        LogOutSuccess();

                    }

                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "something went wrong and logout failed",
                                Toast.LENGTH_SHORT).show();
                        LogOutUnSuccess();
                    }
                });
            }
        });

        btnOtherUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onNavigationItemSelected2(9);
            }
        });


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( "User = antoschenko.gm@gmail.com" );
        Backendless.Data.of( "Jump" ).getObjectCount( queryBuilder,
                new AsyncCallback<Integer>(){
            @Override
            public void handleResponse( Integer integer )
            {
                Log.i( "MYAPP", "total objects in the Order table - " + integer );
                tv_jump.setText(integer.toString());
            }

            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {
                Log.i( "MYAPP", "error - " + backendlessFault.getMessage() );
            }
        } );



        return v;
    }
    private void LogOutSuccess() {
        // TODO: go to home screen
        ((MainActivity)getActivity()).onNavigationItemSelected2(1);
    }

    private void LogOutUnSuccess() {
        // TODO: go to home screen
        ((MainActivity)getActivity()).onNavigationItemSelected2(7);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
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
