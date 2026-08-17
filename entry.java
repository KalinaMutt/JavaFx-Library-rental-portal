package com.example;

import java.sql.Date;

public class entry {
    private int id;
    private String name;
    private String type;
    private String edition;
    private double fee;
    private String duration;
    private int reservedby;
    private int rentedby;
    private Date starttime;
    private Date endtime;
    private String path;
    private int period;

    public entry(int nid, String nname, String ntype, String nedition, double nfee, String nduration, int nreservedby,
            int nrentedby, Date nstarttime, Date nendtime, String npath, int nperiod) {
        this.id = nid;
        this.name = nname;
        this.type = ntype;
        this.edition = nedition;
        this.fee = nfee;
        this.duration = nduration;
        this.reservedby = nreservedby;
        this.rentedby = nrentedby;
        this.starttime = nstarttime;
        this.endtime = nendtime;
        this.path = npath;
        this.period = nperiod;
    }

    public int getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String gettype() {
        return type;
    }

    public String getedition() {
        return edition;
    }

    public double getfee() {
        return fee;
    }

    public String getduration() {
        return duration;
    }

    public int getreservedby() {
        return reservedby;
    }

    public int getrentedby() {
        return rentedby;
    }

    public Date getstarttime() {
        return starttime;
    }

    public Date getendtime() {
        return endtime;
    }

    public String getpath() {
        return path;
    }

    public int getperiod() {
        return period;
    }
}
