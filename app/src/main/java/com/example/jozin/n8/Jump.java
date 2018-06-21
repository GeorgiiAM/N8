package com.example.jozin.n8;

/**
 * Created by Jozin on 07.06.2018.
 */

public class Jump {

    private int zn_numb;
    private String zn_showD;
    private String zn_dz;
    private String zn_aircraft;
    private String zn_gear;
    private String zn_jumptype;
    private float zn_ground;
    private double zn_exit;
    private double zn_deploy;
    private double zn_delay;
    private String zn_notes;


    public int getNumb() {
        return this.zn_numb;
    }

    public void setNumb(int zn_numb) {
        this.zn_numb = zn_numb;
    }

    public String getDate() {
        return this.zn_showD;
    }

    public void setDate(String zn_showD) {
        this.zn_showD = zn_showD;
    }

    public String getDZ() {
        return this.zn_dz;
    }

    public void setDZ(String zn_dz) {
        this.zn_dz = zn_dz;
    }

    public String getAircraft() {
        return this.zn_aircraft;
    }

    public void setAircraft(String zn_aircraft) {
        this.zn_aircraft = zn_aircraft;
    }

    public String getGear() {
        return this.zn_gear;
    }

    public void setGear(String zn_gear) {
        this.zn_gear = zn_gear;
    }

    public String getTJump() {
        return this.zn_jumptype;
    }

    public void setTJump(String zn_jumptype) {
        this.zn_jumptype = zn_jumptype;
    }

    public double getExit() {
        return this.zn_exit;
    }

    public void setExit(double zn_exit) {
        this.zn_exit = zn_exit;
    }

    public double getGround() {
        return this.zn_ground;
    }

    public void setGround(float zn_ground) {
        this.zn_ground = zn_ground;
    }

    public double getDeploy() {
        return this.zn_deploy;
    }

    public void setDeploy(double zn_deploy) {
        this.zn_deploy = zn_deploy;
    }

    public double getDelay() {
        return this.zn_delay;
    }

    public void setDelay(double zn_delay) {
        this.zn_delay = zn_delay;
    }

    public String getNotes() {
        return this.zn_notes;
    }

    public void setNotes(String eventsDJ5) {
        this.zn_notes = zn_notes;
    }
}

