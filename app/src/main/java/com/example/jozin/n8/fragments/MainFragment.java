package com.example.jozin.n8.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jozin.n8.Jump;
import com.example.jozin.n8.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements SensorEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txt_sensor;
    private TextView tv_status;
    private TextView tvHeight;
    private TextView tvSpeed;
    private Button start_btn;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private float pressure;
    private double p0=1013.25;
    private double ph;
    private double e =  Math.E;
    private double height;
    Jump j;
    int q=1;
    int mill=1000;
    long[] t = new long[2];
    float[] bar = new float[2];
    float[] h = new float[2];
    boolean flag=true;
    boolean button=true;
    float speed;
    int i=0;



    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View v= inflater.inflate(R.layout.fragment_main, container, false);
        txt_sensor=(TextView) v.findViewById(R.id.txt_sensor);
        tvHeight=(TextView) v.findViewById(R.id.tvHeight);

        start_btn=(Button) v.findViewById(R.id.start_btn);
        sensorManager=(SensorManager)  getActivity().getSystemService(SENSOR_SERVICE);
        pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        tvSpeed=(TextView) v.findViewById(R.id.tvSpeed);
        tv_status = (TextView) v.findViewById(R.id.tv_status);

        /*Thread t = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                try {
                    while (true) {
                        Thread.sleep(1000);

                                tvSpeed.setText(String.format("%.3f mps", speed));

                        }
                    } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        });
        t.start();*/
        speed(1);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               p0=pressure;
               speed(2);

               if (button==false){
                   start_btn.setText("START");
                   button=true;
               }
               else {
                   start_btn.setText("STOP");
                   button=false;
                   start(p0);
               }
            }
        });



        return v;
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
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values=event.values;
        pressure=values[0];
        txt_sensor.setText(String.format("%.3f mbar",pressure));
        height = 18400*Math.log10(p0/values[0]);
        tvHeight.setText(String.format("%.3f m",height));
        tvSpeed.setText(String.format("%.3f mps", speed));

        if (speed>0.01) tvSpeed.setText(String.format("FLY"));


        //j.setGround(values[0]);

        //fly(j);

    }

    public void speed(final int status){


        if (status==1) mill=1000;
        if (status==2) mill=100;
        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                while(true) {
                    if (flag) {
                        bar[0] = (float) height;
                        t[0] = time();
                        flag = false;
                    } else {
                        try {
                            Thread.sleep(mill);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        bar[1] = (float) height;
                        t[1] = time();
                        //speed =q++;
                        //speed = (bar[1] - bar[0]) / (t[1] - t[0]);

                        if (status==1) speed = (bar[1] - bar[0]);
                        else speed = (bar[1] - bar[0])*10;

                        flag = true;

                    }

                    }
            }
        });
        myThready.start();



    }

    public void start(final double status){


        if (status==1) mill=1000;
        if (status==2) mill=100;
        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                while(true) {
                    if (flag) {
                        bar[0] = (float) height;
                        t[0] = time();
                        flag = false;
                    } else {
                        try {
                            Thread.sleep(mill);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        bar[1] = (float) height;
                        t[1] = time();
                        //speed =q++;
                        //speed = (bar[1] - bar[0]) / (t[1] - t[0]);

                        if (status==1) speed = (bar[1] - bar[0]);
                        else speed = (bar[1] - bar[0])*10;

                        flag = true;

                    }

                }
            }
        });
        myThready.start();



    }

    public void fly(Jump j){

    }
    public long time(){
        Date date = new Date();
        long millis = date.getTime();
        return millis/1000;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
