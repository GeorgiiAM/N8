package com.example.jozin.n8;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.app.Fragment;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.example.jozin.n8.fragments.FilterFragment;
import com.example.jozin.n8.fragments.ListFragment;
import com.example.jozin.n8.fragments.LogFragment;
import com.example.jozin.n8.fragments.MainFragment;
import com.example.jozin.n8.fragments.NewJumpFragment;
import com.example.jozin.n8.fragments.OtherUserFragment;
import com.example.jozin.n8.fragments.ProfileFragment;
import com.example.jozin.n8.fragments.RegFragment;
import com.example.jozin.n8.fragments.RestorePassword;
import com.example.jozin.n8.fragments.SettingFragment;

import java.util.logging.FileHandler;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener {


    MainFragment mfr;
    SettingFragment sfr;
    LogFragment lfr;
    RegFragment rfr;
    NewJumpFragment njfr;
    ListFragment lstfr;
    RestorePassword restfr;
    ProfileFragment pfr;
    OtherUserFragment oufr;
    FilterFragment ffr;



    private TextView txt,txt2;
    private SensorManager sensorManager;
    private Sensor pressureSensor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mfr=new MainFragment();
        sfr=new SettingFragment();
        lfr=new LogFragment();
        rfr=new RegFragment();
        njfr= new NewJumpFragment();
        lstfr=new ListFragment();
        restfr=new RestorePassword();
        pfr= new ProfileFragment();
        oufr=new OtherUserFragment();
        ffr=new FilterFragment();


        FragmentTransaction ftrans=getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container,mfr);
        txt2=(TextView)findViewById(R.id.txt_sensor);
        txt = (TextView)findViewById(R.id.attention);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        TextView profilename = (TextView) headerview.findViewById(R.id.t1);
        profilename.setText("your name");

        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.profile);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String userToken = UserTokenStorageFactory.instance().getStorage().get();
                if( userToken != null && !userToken.equals( "" ) )
                {  onNavigationItemSelected2(7);
                } else{
                    onNavigationItemSelected2(7);
                }

            }
        });


        /*ImageView iv = (ImageView)findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationItemSelected2(1);
            }
        });
        TextView t1 = (TextView) findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationItemSelected2(1);
            }
        });
        TextView t2 = (TextView)findViewById(R.id.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationItemSelected2(1);
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans=getFragmentManager().beginTransaction();
        if (id == R.id.iv) {
            ftrans.replace(R.id.container,lfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_camera) {
            ftrans.replace(R.id.container,mfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_gallery) {
            ftrans.replace(R.id.container,sfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_slideshow) {
            ftrans.replace(R.id.container,rfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_manage) {
            ftrans.replace(R.id.container,lfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_share) {

            findViewById(R.id.attention).setVisibility(View.GONE);
            ftrans.replace(R.id.container,lstfr);
        } else if (id == R.id.nav_send) {

            findViewById(R.id.attention).setVisibility(View.GONE);

        }else if (id == R.id.t1) {

            findViewById(R.id.attention).setVisibility(View.GONE);
            ftrans.replace(R.id.container,lfr);
        }else if (id == R.id.t2) {

            findViewById(R.id.attention).setVisibility(View.GONE);
            ftrans.replace(R.id.container,lfr);
        }ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public boolean onNavigationItemSelected2(int id) {
        // Handle navigation view item clicks here.

        FragmentTransaction ftrans=getFragmentManager().beginTransaction();
        if (id == 1) {
            ftrans.replace(R.id.container,lfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 2) {
            ftrans.replace(R.id.container,mfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 3) {
            ftrans.replace(R.id.container,sfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 4) {
            ftrans.replace(R.id.container,rfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 5) {
            ftrans.replace(R.id.container,njfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 6) {
            ftrans.replace(R.id.container,restfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 7) {
            ftrans.replace(R.id.container,pfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == 8) {
            ftrans.replace(R.id.container,lstfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        }else if (id == 9) {
            ftrans.replace(R.id.container,oufr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        }else if (id == 10) {
            ftrans.replace(R.id.container,ffr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        }ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values=event.values;
        txt.setText(String.format("%.3f mbar",values[0]));


    }

    private void speed(float[] values) {



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
