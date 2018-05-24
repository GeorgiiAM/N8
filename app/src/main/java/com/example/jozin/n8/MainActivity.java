package com.example.jozin.n8;

import android.app.FragmentTransaction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.app.Fragment;
import com.example.jozin.n8.fragments.LogFragment;
import com.example.jozin.n8.fragments.MainFragment;
import com.example.jozin.n8.fragments.RegFragment;
import com.example.jozin.n8.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener {


    MainFragment mfr;
    SettingFragment sfr;
    LogFragment lfr;
    RegFragment rfr;



    private TextView txt,txt2;

    private SensorManager sensorManager;
    private Sensor pressureSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        FragmentTransaction ftrans=getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container,mfr);
        txt2=(TextView)findViewById(R.id.txt_sensor);
        txt = (TextView)findViewById(R.id.attention);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
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
        if (id == R.id.imageView) {
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

        } else if (id == R.id.nav_send) {
            findViewById(R.id.attention).setVisibility(View.GONE);

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
            ftrans.replace(R.id.container,lfr);
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_share) {
            findViewById(R.id.attention).setVisibility(View.GONE);

        } else if (id == R.id.nav_send) {
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
