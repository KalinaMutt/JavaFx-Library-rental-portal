package com.example;


public class user {
    private String username;
    private String fname;
    private String lname;
    private String address;
    private int password;
    private int admin;

    public user(String nusername, String nfname, String nlname, String naddress, int npassword, int nadmin) {
        this.username = nusername;
        this.fname = nfname;
        this.lname = nlname;
        this.address = naddress;
        this.password = npassword;
        this.admin = nadmin;
    }

    public String getusername() {
        return username;
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

    public int getpassword() {
        return password;
    }

    public int getadmin() {
        return admin;
    }

}
