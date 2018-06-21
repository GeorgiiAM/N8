package com.example.jozin.n8.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.view.View.OnClickListener;

import android.widget.Toast;


import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.jozin.n8.MainActivity;
import com.example.jozin.n8.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewJumpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewJumpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewJumpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "NewJumpFragment";

    private EditText numb;
    private EditText showD;
    private EditText dz;
    private EditText aircraft;
    private EditText gear;
    private EditText jumptype;
    private EditText exit;
    private EditText deploy;
    private EditText delay;
    private EditText notes;

    private int zn_numb;
    private String zn_showD;
    private String zn_dz;
    private String zn_aircraft;
    private String zn_gear;
    private String zn_jumptype;
    private double zn_exit;
    private double zn_deploy;
    private double zn_delay;
    private String zn_notes;
    private Date data;
    private java.sql.Date data1;


    private int year;
    private int month;
    private int day;
    private int year1;
    private int month1;
    private int day1;


    private OnFragmentInteractionListener mListener;

    public NewJumpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewJumpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewJumpFragment newInstance(String param1, String param2) {
        NewJumpFragment fragment = new NewJumpFragment();
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

       View v= inflater.inflate(R.layout.fragment_new_jump, container, false);

        showD=(EditText)v.findViewById(R.id.tvDate);
        numb=(EditText) v.findViewById(R.id.tvNumb);
        dz=(EditText) v.findViewById(R.id.tvDZ);
        aircraft=(EditText) v.findViewById(R.id.tvAircraft);
        gear=(EditText) v.findViewById(R.id.tvGear);
        jumptype=(EditText) v.findViewById(R.id.tvJumpType);
        exit=(EditText) v.findViewById(R.id.tvExit);
        deploy=(EditText) v.findViewById(R.id.tvDeploy);
        delay=(EditText) v.findViewById(R.id.tvDelay);
        notes=(EditText) v.findViewById(R.id.tvNotes);

        Calendar c1=Calendar.getInstance();
        Date myD=new Date();
        c1.setTime(myD);
        year=c1.get(Calendar.YEAR);
        month=c1.get(Calendar.MONTH);
        day=c1.get(Calendar.DAY_OF_MONTH);
        year1=c1.get(Calendar.YEAR);
        month1=c1.get(Calendar.MONTH);
        day1=c1.get(Calendar.DAY_OF_MONTH);

        showD.setText(day+"-"+(month+1)+"-"+year);
        showD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog my_datePickerDialog=new DatePickerDialog(v.getContext(),
                        Datelistener,year,month,day);
                my_datePickerDialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "BUTTON",
                        Toast.LENGTH_SHORT).show();
                    Save(view);

            }
        });


        return v;
    }

    private void Save(View v)  {

        zn_numb=Integer.parseInt(numb.getText().toString());;
        zn_showD=showD.getText().toString();
        zn_dz=dz.getText().toString();
        zn_aircraft=aircraft.getText().toString();;
        zn_gear=gear.getText().toString();;
        zn_jumptype=jumptype.getText().toString();;
        zn_exit=Double.parseDouble(exit.getText().toString());
        zn_deploy=Double.parseDouble(deploy.getText().toString());
        zn_delay=Double.parseDouble(delay.getText().toString());
        zn_notes=notes.getText().toString();

        //SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c1=Calendar.getInstance();

        //java.sql.Date myD= new java.sql.Date();
        ////c1.setTime(myD);
       // data1 = myD;


           // parsingDate = ft.parse(zn_showD);
        Toast.makeText(getActivity().getApplicationContext(), zn_showD,
                Toast.LENGTH_SHORT).show();




        HashMap jump = new HashMap<>();
        jump.put( "User", "antoschenko.gm@gmail.com");
        jump.put( "Numb", zn_numb);
        jump.put( "Data", zn_showD);
        jump.put( "DropeZone", zn_dz);
        jump.put( "Aircraft", zn_aircraft);
        jump.put( "Gear", zn_gear);
        jump.put( "JumpType", zn_jumptype);
        jump.put( "ExitAt", zn_exit);
        jump.put( "DeployAt", zn_deploy);
        jump.put( "Delay", zn_delay);
        jump.put( "Notes",zn_notes);

        Backendless.Data.of( "Jump" ).save(jump, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                Toast.makeText(getActivity().getApplicationContext(), "reg",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });


    }



    private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int d) {
            if(y>year1||y==year1&&m>month1||y==year1&&m==month1&&d>day1)
            {
                showD.setText("future date :)");
            }
            else {
                year = y;
                month = m;
                day = d;
                updateDate();
            }
        }
    };
    private void updateDate()
    {
        showD.setText(day+"-"+(month+1)+"-"+year);
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

