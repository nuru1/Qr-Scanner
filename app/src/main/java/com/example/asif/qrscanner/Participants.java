package com.example.asif.qrscanner;

/**
 * Created by asif on 01-Feb-18.
 */

public class Participants {
    String name,college,id;
    boolean scan;

    public Participants() {
    }

    public Participants(String name, String college, String id, boolean scan) {
        this.name = name;
        this.college = college;
        this.id = id;
        this.scan = scan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }
}
