package com.example;

public class customer {
    private String email;
    private String fname;
    private String lname;
    private String address;
    private int id;

    public customer(String nemail, String nfname, String nlname, String naddress, int nid) {
        this.email = nemail;
        this.fname = nfname;
        this.lname = nlname;
        this.address = naddress;
        this.id = nid;
    }

    public String getemail() {
        return email;
    }

    public String getfname() {
        return fname;
    }

    public String getlname() {
        return lname;
    }

    public String getaddress() {
        return address;
    }

    public int getid() {
        return id;
    }

}
