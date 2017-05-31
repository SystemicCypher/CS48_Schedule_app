package com.example.quintinhill.scheduled;

/**
 * Created by quintinhill on 5/19/17.
 */

public class Pair<String,Boolean> {
    private String l;
    private Boolean r;
    public Pair(String l, Boolean r){
        this.l = l;
        this.r = r;
    }
    public String getString(){ return l; }
    public Boolean getBoolean(){ return r; }
    public void setString(String l){ this.l = l; }
    public void setBoolean(Boolean r){ this.r = r; }
}

