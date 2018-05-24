package com.example.jozin.n8.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.BackendlessUser;
import com.example.jozin.n8.Defaults;
import com.example.jozin.n8.MainActivity;
import com.example.jozin.n8.R;

import java.util.HashMap;
import java.util.Map;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private TextView gotolog;

    private EditText email;
    private EditText pass;
    private EditText pass2;
    private EditText login;
    private Button btnRegister;



    private OnFragmentInteractionListener mListener;

    public RegFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegFragment newInstance(String param1, String param2) {
        RegFragment fragment = new RegFragment();
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
        View v=inflater.inflate(R.layout.fragment_reg, container, false);



        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp(getActivity().getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        HashMap testObject = new HashMap<>();
        testObject.put( "a", "zzz");
        testObject.put( "b", "333");
        Backendless.Data.of( "TestTable3" ).save(testObject, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });

        initUI(v);

        return v;
    }

    private void initUI(View v) {

        email = (EditText)v.findViewById(R.id.email);
        login=(EditText)v.findViewById(R.id.login);
        pass = (EditText) v.findViewById(R.id.pass);
        pass2 = (EditText) v.findViewById(R.id.pass2);
        gotolog= (TextView) v.findViewById(R.id.gotolog);
        btnRegister = (Button) v.findViewById(R.id.reg_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onRegisterButtonClicked(v);
                ((MainActivity)getActivity()).onNavigationItemSelected2(3);
                //v.findViewById(R.id.attention).setVisibility(v.GONE);
            }
        });

        gotolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onNavigationItemSelected2(1);
            }
        });


    }

    private void onRegisterButtonClicked(View view) {

        if (email.getText().toString().equals("")) {
            email.setError(getString(R.string.error_email));
            return;
        }
        if (pass.getText().toString().equals("")) {
            email.setError(getString(R.string.error_pass));
            return;
        }

        BackendlessUser newUser = new BackendlessUser();
        newUser.setEmail(email.getText().toString());
        newUser.setProperty("name",login.getText().toString());
        newUser.setPassword(pass.getText().toString());

        Backendless.UserService.register(newUser, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.register_success,
                        Toast.LENGTH_SHORT).show();


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.register_fail,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*  @Override
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
