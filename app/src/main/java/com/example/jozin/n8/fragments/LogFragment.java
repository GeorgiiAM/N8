package com.example.jozin.n8.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.jozin.n8.Defaults;
import com.example.jozin.n8.MainActivity;
import com.example.jozin.n8.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText email;
    private EditText name;
    private EditText pass;
    private Button btn_log;

    private TextView tvRegister;
    private TextView tvRestore;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox checkboxRemember;
    private Button btnFacebook;
    private Button btnGoogle;




    private OnFragmentInteractionListener mListener;

    public LogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogFragment newInstance(String param1, String param2) {
        LogFragment fragment = new LogFragment();
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

        View v=inflater.inflate(R.layout.fragment_log, container, false);



        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp(getActivity().getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        checkAutomaticLogin();

       // setContentView(R.layout.activity_login);
        initViews(v);

        if (Backendless.UserService.CurrentUser() != null)
            Toast.makeText(getActivity().getApplicationContext(), Backendless.UserService.CurrentUser().getUserId(),
                    Toast.LENGTH_SHORT).show();
        return v;
    }



    private void checkAutomaticLogin() {
        Backendless.UserService.isValidLogin(new BackendlessCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean isValidLogin) {
                if (isValidLogin && Backendless.UserService.CurrentUser() == null) {
                    String currentUserId = Backendless.UserService.loggedInUser();
                    if (!currentUserId.equals("")) {
                        Backendless.UserService.findById(currentUserId,
                                new BackendlessCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser currentUser) {
                                        Backendless.UserService.setCurrentUser(currentUser);
                                        loginSuccess();
                                    }
                                });
                    }
                }
            }
        });
    }

    private void initViews(View v) {


        tvRegister = (TextView) v.findViewById(R.id.tvRegister);
        tvRestore = (TextView) v.findViewById(R.id.tvRestore);
        etEmail = (EditText) v.findViewById(R.id.log_mail);
        etPassword = (EditText) v.findViewById(R.id.log_pass);

        btnLogin = (Button) v.findViewById(R.id.log_btn);

        checkboxRemember = (CheckBox) v.findViewById(R.id.checkboxRemember);
        btnFacebook = (Button) v.findViewById(R.id.btnFacebook);
        btnGoogle = (Button) v.findViewById(R.id.btnGoogle);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
                Fragment sfr=new SettingFragment();
                FragmentTransaction trans=getFragmentManager().beginTransaction();
                trans.replace(R.id.container,sfr);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onNavigationItemSelected2(4);
            }
        });


        tvRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(LoginActivity.this, RestorePasswordActivity.class));
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // onLoginWithFacebookButtonClicked();
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginWithGoogleButtonClicked();
            }
        });
    }

    /*  public void onLoginWithFacebookButtonClicked() {
          Map<String, String> fieldMap = new HashMap<>();
          fieldMap.put("name", "name");
          fieldMap.put("gender", "gender");
          fieldMap.put("email", "email");

          List<String> facebookPermissions = new ArrayList<>();
          facebookPermissions.d("email");
  /*
          Backendless.UserService.loginWithFacebook(MainActivity.this, null, fieldMap, facebookPermissions,
                  new AsyncCallback<BackendlessUser>() {
                      @Override
                      public void handleResponse(BackendlessUser response) {
                          Toast.makeText(LoginActivity.this, R.string.fb_login_success,
                                  Toast.LENGTH_SHORT).show();
                          loginSuccess();
                      }

                      @Override
                      public void handleFault(BackendlessFault fault) {
                          Toast.makeText(LoginActivity.this, getString(R.string.login_fail),
                                  Toast.LENGTH_SHORT).show();
                      }
                  });
      }



      private void onLoginWithGoogleButtonClicked() {
          Map<String, String> fieldMap = new HashMap<>();
          fieldMap.put("name", "name");
          fieldMap.put("gender", "gender");
          fieldMap.put("email", "email");

          List<String> googlePermissions = new ArrayList<>();

          Backendless.UserService.loginWithGooglePlus(getActivity().getApplicationContext(), null, fieldMap, googlePermissions,
                  new AsyncCallback<BackendlessUser>() {
                      @Override
                      public void handleResponse(BackendlessUser response) {
                          Toast.makeText(getActivity().getApplicationContext(), R.string.google_login_success,
                                  Toast.LENGTH_SHORT).show();
                          loginSuccess();
                      }

                      @Override
                      public void handleFault(BackendlessFault fault) {
                          Toast.makeText(getActivity().getApplicationContext(), getString(R.string.login_fail),
                                  Toast.LENGTH_SHORT).show();
                      }
                  });
      }
  */
    private void loginSuccess() {
        // TODO: go to home screen
    }
    private void onLoginButtonClicked() {
        Backendless.UserService.login(etEmail.getText().toString(),
                etPassword.getText().toString(),
                new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.email_login_success,
                                Toast.LENGTH_SHORT).show();
                        loginSuccess();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.login_fail) + fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }, checkboxRemember.isChecked());
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*   @Override
      public void onAttach(Context context) {
          super.onAttach(context);
          if (context instanceof OnFragmentInteractionListener) {
              mListener = (OnFragmentInteractionListener) context;
          } else {
              throw new RuntimeException(context.toString()
                      + " must implement OnFragmentInteractionListener");
          }
      }*/

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
