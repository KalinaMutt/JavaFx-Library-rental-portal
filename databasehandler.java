package com.example;

import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databasehandler {
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static Connection myConnection = null;

    public static void initset() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static user logindata(String logginguser) {
        try {
            PreparedStatement tStatement = myConnection.prepareStatement("SELECT* FROM users WHERE username=(?)");
            tStatement.setString(1, logginguser);
            ResultSet testresult = tStatement.executeQuery();
            testresult.next();

            user tempuser = new user(testresult.getString("username"), testresult.getString("fname"),
                    testresult.getString("lname"), testresult.getString("address"), testresult.getInt("password"),
                    testresult.getInt("admin"));
            testresult.close();
            return tempuser;
        } catch (SQLException throwables) {
            return null;
        }

    }

    public static int addcustomer(String email, String fname, String lname, String address) {
        try {
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT MAX(id) FROM customers");
            ResultSet countresult = countstatement.executeQuery();

            int id;
            if (countresult.next()) {
                id = countresult.getInt("MAX(id)") + 1;
            } else {
                id = 0;
            }
            countresult.close();
            email.toLowerCase();

            PreparedStatement usernamecheck = myConnection
                    .prepareStatement("SELECT email FROM customers WHERE email=(?)");
            usernamecheck.setString(1, email);

            ResultSet checkresult = usernamecheck.executeQuery();
            if (checkresult.next()) {
                return 0;
            } else {

                PreparedStatement tempstatement = myConnection
                        .prepareStatement("INSERT INTO customers VALUES (?,?,?,?,?)");
                tempstatement.setString(1, email);
                tempstatement.setString(2, fname);
                tempstatement.setString(3, lname);
                tempstatement.setString(4, address);
                tempstatement.setInt(5, id);
                tempstatement.executeUpdate();

                PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET customers='1'");
                changestatement.executeUpdate();

                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 2;
        }
    }

    public static ArrayList<customer> customerslist() {
        try {
            ArrayList<customer> customerarray = new ArrayList<customer>();
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT COUNT(id) FROM customers");
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int userlength = countresult.getInt("COUNT(id)");
            countresult.close();

            PreparedStatement tempstatement = myConnection.prepareStatement("SELECT* FROM customers");
            ResultSet testresult = tempstatement.executeQuery();

            for (int i = 0; i < userlength; i++) {
                testresult.next();
                customer tempcustomer = new customer(testresult.getString("email"), testresult.getString("firstname"),
                        testresult.getString("lastname"), testresult.getString("address"), testresult.getInt("id"));
                customerarray.add(tempcustomer);
            }
            testresult.close();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET customers='0'");
            changestatement.executeUpdate();

            return customerarray;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static int removecustomer(int remover) {
        try {
            PreparedStatement checkstatement = myConnection
                    .prepareStatement("SELECT id FROM entries WHERE rentedby=(?) OR reservedby=(?)");
            checkstatement.setInt(1, remover);
            checkstatement.setInt(2, remover);
            ResultSet checkresult = checkstatement.executeQuery();

            if (checkresult.next()) {
                return 0;
            }

            PreparedStatement tempstatement = myConnection.prepareStatement("DELETE FROM customers WHERE id=(?)");
            tempstatement.setInt(1, remover);
            tempstatement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET customers='1'");
            changestatement.executeUpdate();

            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 2;
        }
    }

    public static int insertemployee(String username, String fname, String lname, String address, int pword,
            int admin) {
        try {
            username.toLowerCase();

            PreparedStatement usernamecheck = myConnection
                    .prepareStatement("SELECT username FROM users WHERE username=(?)");
            usernamecheck.setString(1, username);
            ResultSet checkresult = usernamecheck.executeQuery();

            if (checkresult.next()) {
                return 0;
            } else {

                // String query="INSERT INTO users VALUES (" + "'" +username + "'," + "'" +fname
                // + "'," + "'" +lname + "'," + "'" +address + "'," + "'" +pword + "'," + "'" +
                // admin + "');";
                PreparedStatement tempstatement = myConnection
                        .prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?)");
                tempstatement.setString(1, username);
                tempstatement.setString(2, fname);
                tempstatement.setString(3, lname);
                tempstatement.setString(4, address);
                tempstatement.setInt(5, pword);
                tempstatement.setInt(6, admin);
                tempstatement.executeUpdate();

                PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET users='1'");
                changestatement.executeUpdate();

                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 2;
        }
    }

    public static ArrayList<user> userslist() {
        try {
            ArrayList<user> userarray = new ArrayList<user>();
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT COUNT(username) FROM users");
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int userlength = countresult.getInt("COUNT(username)");
            countresult.close();

            PreparedStatement tStatement = myConnection.prepareStatement("SELECT* FROM users");
            ResultSet testresult = tStatement.executeQuery();

            for (int i = 0; i < userlength; i++) {
                testresult.next();
                if (Main.getcurrentuser() != null
                        && testresult.getString("username").equals(Main.getcurrentuser().getusername())) {
                    continue;
                }
                user tempuser = new user(testresult.getString("username"), testresult.getString("fname"),
                        testresult.getString("lname"), testresult.getString("address"), testresult.getInt("password"),
                        testresult.getInt("admin"));
                userarray.add(tempuser);
            }
            testresult.close();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET users='0'");
            changestatement.executeUpdate();

            return userarray;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean removeemployee(String remover) {
        try {

            PreparedStatement tStatement = myConnection.prepareStatement("DELETE FROM users WHERE username=(?)");
            tStatement.setString(1, remover);
            tStatement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET users='1'");
            changestatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean insertentry(String name, String type, String edition, double fee, String duration, File file,
            String period) {
        try {
            String forma = "";
            int tperiod = Integer.parseInt(period);
            if (file.getName().matches("(.*).png")) {
                forma = ".png";
            } else {
                forma = ".jpg";
            }
            FileInputStream bytefile = null;
            byte[] filebyte;
            try {
                bytefile = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                return false;
            } finally {
                try {
                    filebyte = bytefile.readAllBytes();
                    bytefile.close();
                } catch (IOException e) {
                    return false;
                }
            }
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT MAX(id) FROM entries");
            ResultSet countresult = countstatement.executeQuery();

            int id;
            if (countresult.next()) {
                id = countresult.getInt("MAX(id)") + 1;
            } else {
                id = 0;
            }
            countresult.close();

            PreparedStatement tStatement = myConnection
                    .prepareStatement("INSERT INTO entries VALUES(?,?,?,?,?,?,NULL,NULL,NULL,NULL,?)");
            tStatement.setInt(1, id);
            tStatement.setString(2, name);
            tStatement.setString(3, type);
            tStatement.setString(4, edition);
            tStatement.setDouble(5, fee);
            tStatement.setString(6, duration);
            tStatement.setString(7, period);
            tStatement.executeUpdate();

            PreparedStatement editstatement = myConnection.prepareStatement(
                    "UPDATE entries SET  fee=(?), duration=(?),period=(?) WHERE name=(?) AND type=(?) AND edition=(?)");
            editstatement.setDouble(1, fee);
            editstatement.setString(2, duration);
            editstatement.setInt(3, tperiod);
            editstatement.setString(4, name);
            editstatement.setString(5, type);
            editstatement.setString(6, edition);

            editstatement.executeUpdate();

            PreparedStatement idsment = myConnection
                    .prepareStatement("SELECT COUNT(id) FROM entries WHERE name=(?) AND edition=(?) AND type=(?)");
            idsment.setString(1, name);
            idsment.setString(2, edition);
            idsment.setString(3, type);
            ResultSet idset = idsment.executeQuery();

            idset.next();

            PreparedStatement sharedstate = myConnection
                    .prepareStatement("SELECT id FROM entries WHERE name=(?) AND edition=(?) AND type=(?)");
            sharedstate.setString(1, name);
            sharedstate.setString(2, edition);
            sharedstate.setString(3, type);
            ResultSet result = sharedstate.executeQuery();

            for (int i = 0; i < idset.getInt("COUNT(id)"); i++) {
                result.next();
                PreparedStatement pstmt = myConnection
                        .prepareStatement("UPDATE photos SET file=?, format=? WHERE id=(?)");
                pstmt.setBlob(1, new SerialBlob(filebyte));
                pstmt.setString(2, forma);
                pstmt.setInt(3, result.getInt("id"));
                pstmt.execute();
            }

            PreparedStatement pstmt = myConnection.prepareStatement("INSERT INTO photos VALUES(?, ?,?)");
            pstmt.setInt(1, id);
            pstmt.setBlob(2, new SerialBlob(filebyte));
            pstmt.setString(3, forma);
            pstmt.execute();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
            changestatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static ArrayList<entry> entrieslist() {
        try {
            ArrayList<entry> entryarray = new ArrayList<entry>();
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT COUNT(id) FROM entries");
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int userlength = countresult.getInt("COUNT(id)");
            countresult.close();

            PreparedStatement tempstatement = myConnection.prepareStatement("SELECT * FROM entries");
            ResultSet testresult = tempstatement.executeQuery();

            for (int i = 0; i < userlength; i++) {
                testresult.next();
                PreparedStatement filestatement = myConnection
                        .prepareStatement("SELECT format FROM photos WHERE id=(?)");
                filestatement.setInt(1, testresult.getInt("id"));
                ResultSet fileresult = filestatement.executeQuery();
                fileresult.next();

                int temprent = 0;
                if (testresult.getObject("rentedby") == null) {
                    temprent = -1;
                } else {
                    temprent = testresult.getInt("rentedby");
                }
                int tempreserve = 0;
                if (testresult.getObject("reservedby") == null) {
                    tempreserve = -1;
                } else {
                    tempreserve = testresult.getInt("reservedby");
                }
                entry tempentry = new entry(testresult.getInt("id"), testresult.getString("name"),
                        testresult.getString("type"), testresult.getString("edition"), testresult.getDouble("fee"),
                        testresult.getString("Duration"), tempreserve, temprent, testresult.getDate("startdate"),
                        testresult.getDate("enddate"), testresult.getInt("id") + "img" + fileresult.getString("format"),
                        testresult.getInt("period"));
                entryarray.add(tempentry);
            }
            testresult.close();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='0'");
            changestatement.executeUpdate();

            return entryarray;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static int getchange(String column) {
        try {
            int changestat = 0;
            PreparedStatement countstatement = myConnection.prepareStatement("SELECT * FROM changes");
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            changestat = countresult.getInt(column);
            countresult.close();
            return changestat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }

    public static InputStream imagefetch(int id) {
        try {
            PreparedStatement filestatement = myConnection.prepareStatement("SELECT * FROM photos WHERE id=(?)");
            filestatement.setInt(1, id);
            ResultSet fileresult = filestatement.executeQuery();
            fileresult.next();

            Blob deblob = fileresult.getBlob("file");
            byte[] debyte = deblob.getBytes(1, (int) deblob.length());
            InputStream tempimg = new ByteArrayInputStream(debyte);
            return tempimg;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream imagefetch(String name, String type, String edition) {
        try {

            PreparedStatement getment = myConnection
                    .prepareStatement("SELECT id FROM entries WHERE name=(?) AND edition=(?) AND type=(?)");
            getment.setString(1, name);
            getment.setString(2, edition);
            getment.setString(3, type);
            ResultSet getset = getment.executeQuery();
            getset.next();

            int id = getset.getInt("id");

            PreparedStatement filestatement = myConnection.prepareStatement("SELECT * FROM photos WHERE id=(?)");
            filestatement.setInt(1, id);
            ResultSet fileresult = filestatement.executeQuery();
            fileresult.next();

            Blob deblob = fileresult.getBlob("file");
            byte[] debyte = deblob.getBytes(1, (int) deblob.length());
            InputStream tempimg = new ByteArrayInputStream(debyte);
            return tempimg;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int removeentry(int remover) {
        try {
            PreparedStatement checkstatement = myConnection.prepareStatement(
                    "SELECT id from entries WHERE rentedby IS NOT NULL AND id=(?) OR reservedby IS NOT NULL AND id=(?)");
            checkstatement.setInt(1, remover);
            checkstatement.setInt(2, remover);
            ResultSet checkresult = checkstatement.executeQuery();

            if (checkresult.next() == true) {
                return checkresult.getInt("id");
            }

            PreparedStatement tempstatement = myConnection.prepareStatement("DELETE FROM entries WHERE id=(?)");
            tempstatement.setInt(1, remover);
            tempstatement.executeUpdate();

            PreparedStatement photostatement = myConnection.prepareStatement("DELETE FROM photos WHERE id=(?)");
            photostatement.setInt(1, remover);
            photostatement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
            changestatement.executeUpdate();
            return -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -2;
        }
    }

    public static boolean changeentry(int id, String name, String type, String edition, double fee, String duration,
            String period) {
        try {
            int tempperiod = Integer.parseInt(period);

            PreparedStatement tempStatement = myConnection.prepareStatement(
                    "UPDATE entries SET name=(?),type=(?),edition=(?),fee=(?),duration=(?),period=(?) WHERE id=(?)");
            tempStatement.setString(1, name);
            tempStatement.setString(2, type);
            tempStatement.setString(3, edition);
            tempStatement.setDouble(4, fee);
            tempStatement.setString(5, duration);
            tempStatement.setInt(6, tempperiod);
            tempStatement.setInt(7, id);
            tempStatement.executeUpdate();

            PreparedStatement editstatement = myConnection.prepareStatement(
                    "UPDATE entries SET  fee=(?), duration=(?),period=(?) WHERE name=(?) AND type=(?) AND edition=(?)");
            editstatement.setDouble(1, fee);
            editstatement.setString(2, duration);
            editstatement.setInt(3, tempperiod);
            editstatement.setString(4, name);
            editstatement.setString(5, type);
            editstatement.setString(6, edition);

            editstatement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
            changestatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean changefile(File file, int id, String name, String edition, String type) {
        try {
            String forma = "";
            if (file.getName().matches("(.*).png")) {
                forma = ".png";
            } else {
                forma = ".jpg";
            }
            FileInputStream bytefile = null;
            byte[] filebyte;
            try {
                bytefile = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                return false;
            } finally {
                try {
                    filebyte = bytefile.readAllBytes();
                    bytefile.close();
                } catch (IOException e) {
                    return false;
                }
            }
            PreparedStatement idsment = myConnection
                    .prepareStatement("SELECT COUNT(id) FROM entries WHERE name=(?) AND edition=(?) AND type=(?)");
            idsment.setString(1, name);
            idsment.setString(2, edition);
            idsment.setString(3, type);

            ResultSet idset = idsment.executeQuery();
            idset.next();
            PreparedStatement sharedstate = myConnection
                    .prepareStatement("SELECT id FROM entries WHERE name=(?) AND edition=(?) AND type=(?)");
            sharedstate.setString(1, name);
            sharedstate.setString(2, edition);
            sharedstate.setString(3, type);
            ResultSet result = sharedstate.executeQuery();

            for (int i = 0; i < idset.getInt("COUNT(id)"); i++) {
                result.next();
                PreparedStatement pstmt = myConnection
                        .prepareStatement("UPDATE photos SET file=?, format=? WHERE id='" + result.getInt("id") + "'");
                pstmt.setBlob(1, new SerialBlob(filebyte));
                pstmt.setString(2, forma);
                pstmt.execute();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeemployee(String username, String fname, String lname, String address, int admin) {
        try {
            username.toLowerCase();

            PreparedStatement tempstatement = myConnection
                    .prepareStatement("UPDATE users SET fname=(?),lname=(?),address=(?),admin=(?) WHERE username=(?)");
            tempstatement.setString(1, fname);
            tempstatement.setString(2, lname);
            tempstatement.setString(3, address);
            tempstatement.setInt(4, admin);
            tempstatement.setString(5, username);

            tempstatement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET users='1'");
            changestatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static int changecustomer(int id, String email, String fname, String lname, String address) {

        try {
            email.toLowerCase();

            PreparedStatement usernamecheck = myConnection
                    .prepareStatement("SELECT email FROM customers WHERE email=(?) AND id!=(?)");
            usernamecheck.setString(1, email);
            usernamecheck.setInt(2, id);

            ResultSet checkresult = usernamecheck.executeQuery();
            if (checkresult.next()) {
                return 0;
            } else {

                PreparedStatement tempstatement = myConnection.prepareStatement(
                        "UPDATE customers SET email=(?),firstname=(?),lastname=(?),address=(?) WHERE id=(?)");
                tempstatement.setString(1, email);
                tempstatement.setString(2, fname);
                tempstatement.setString(3, lname);
                tempstatement.setString(4, address);
                tempstatement.setInt(5, id);
                tempstatement.executeUpdate();

                PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET customers='1'");
                changestatement.executeUpdate();

                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 2;
        }
    }

    public static ArrayList<entry> categorylist(String category) {
        try {
            ArrayList<entry> entryarray = new ArrayList<entry>();
            PreparedStatement countstatement = myConnection
                    .prepareStatement("SELECT COUNT(id) FROM entries WHERE type=(?)");
            countstatement.setString(1, category);
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int userlength = countresult.getInt("COUNT(id)");
            countresult.close();

            PreparedStatement tempstatement = myConnection.prepareStatement("SELECT * FROM entries WHERE type=(?)");
            tempstatement.setString(1, category);
            ResultSet testresult = tempstatement.executeQuery();

            for (int i = 0; i < userlength; i++) {
                boolean exists = false;
                testresult.next();
                for (entry loopentry : entryarray) {
                    if (testresult.getString("name").equals(loopentry.getname())) {
                        exists = true;
                    }
                }
                if (exists) {
                    continue;
                }
                PreparedStatement filestatement = myConnection
                        .prepareStatement("SELECT format FROM photos WHERE id=(?)");
                filestatement.setInt(1, testresult.getInt("id"));
                ResultSet fileresult = filestatement.executeQuery();
                fileresult.next();

                int temprent = 0;
                if (testresult.getObject("rentedby") == null) {
                    temprent = -1;
                } else {
                    temprent = testresult.getInt("rentedby");
                }
                int tempreserve = 0;
                if (testresult.getObject("reservedby") == null) {
                    tempreserve = -1;
                } else {
                    tempreserve = testresult.getInt("reservedby");
                }
                entry tempentry = new entry(testresult.getInt("id"), testresult.getString("name"),
                        testresult.getString("type"), testresult.getString("edition"), testresult.getDouble("fee"),
                        testresult.getString("Duration"), tempreserve, temprent, testresult.getDate("startdate"),
                        testresult.getDate("enddate"), testresult.getInt("id") + "img" + fileresult.getString("format"),
                        testresult.getInt("period"));
                entryarray.add(tempentry);
            }
            testresult.close();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='0'");
            changestatement.executeUpdate();

            return entryarray;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static int getcopies(String type, String name, String edition,boolean all) {
        try {

            PreparedStatement tStatement =null;
            if (all==false) { 
                tStatement=myConnection.prepareStatement(
                    "SELECT COUNT(id) FROM entries WHERE name=(?) AND type=(?) AND edition=(?) AND reservedby IS NULL AND rentedby IS NULL");
            }
            else {
                tStatement=myConnection.prepareStatement(
                    "SELECT COUNT(id) FROM entries WHERE name=(?) AND type=(?) AND edition=(?) ");
            }
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            tStatement.setString(3, edition);
            ResultSet testresult = tStatement.executeQuery();

            testresult.next();
            int tempint = testresult.getInt("COUNT(id)");
            testresult.close();
            return tempint;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public static String getfee(String name, String type, String edition) {
        try {

            PreparedStatement tStatement = myConnection
                    .prepareStatement("SELECT fee,duration FROM entries WHERE name=(?) AND type=(?) AND edition=(?)");
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            tStatement.setString(3, edition);
            ResultSet testresult = tStatement.executeQuery();

            testresult.next();
            String fees = "";
            if (testresult.getString("duration").equals("Daily")) {
                fees = "£ " + Double.toString(testresult.getDouble("fee")) + "/per Day";
            }

            if (testresult.getString("duration").equals("Weekly")) {
                fees = "£ " + Double.toString(testresult.getDouble("fee")) + "/per Week";
            }
            if (testresult.getString("duration").equals("Monthly")) {
                fees = "£ " + Double.toString(testresult.getDouble("fee")) + "/per Month";
            }
            testresult.close();
            return fees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "error";
        }
    }

    public static double getfeesingle(String name, String type, String edition) {
        try {
            PreparedStatement tStatement = myConnection
                    .prepareStatement("SELECT fee,duration FROM entries WHERE name=(?) AND type=(?) AND edition=(?)");
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            tStatement.setString(3, edition);
            ResultSet testresult = tStatement.executeQuery();
            testresult.next();
            double fees = testresult.getDouble("fee");
            testresult.close();
            return fees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0.0;
        }
    }

    public static String getperiod(String name, String type, String edition) {
        try {

            PreparedStatement tStatement = myConnection.prepareStatement(
                    "SELECT period,duration FROM entries WHERE name=(?) AND type=(?) AND edition=(?)");
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            tStatement.setString(3, edition);
            ResultSet testresult = tStatement.executeQuery();
            testresult.next();
            String fees = "";
            if (testresult.getString("duration").equals("Daily")) {
                fees = Integer.toString(testresult.getInt("period")) + " Days";
            }

            if (testresult.getString("duration").equals("Weekly")) {
                fees = Integer.toString(testresult.getInt("period")) + " Weeks";
            }
            if (testresult.getString("duration").equals("Monthly")) {
                fees = Integer.toString(testresult.getInt("period")) + " Months";
            }
            testresult.close();
            return fees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "error";
        }
    }

    public static int getperiodsingle(String name, String type, String edition) {
        try {

            PreparedStatement tStatement = myConnection.prepareStatement(
                    "SELECT period,duration FROM entries WHERE name=(?) AND type=(?) AND edition=(?)");
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            tStatement.setString(3, edition);
            ResultSet testresult = tStatement.executeQuery();
            testresult.next();
            int fees = 0;
            if (testresult.getString("duration").equals("Daily")) {
                fees = testresult.getInt("period");
            }

            if (testresult.getString("duration").equals("Weekly")) {
                fees = testresult.getInt("period") * 7;
            }
            if (testresult.getString("duration").equals("Monthly")) {
                fees = testresult.getInt("period") * 31;
            }
            testresult.close();
            return fees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<String> editions(String name, String type) {
        try {
            ArrayList<String> editions = new ArrayList<String>();

            PreparedStatement tStatement = myConnection
                    .prepareStatement("SELECT DISTINCT edition FROM entries WHERE name=(?) AND type=(?)");
            tStatement.setString(1, name);
            tStatement.setString(2, type);
            ResultSet testresult = tStatement.executeQuery();
            while (testresult.next()) {
                editions.add(testresult.getString("edition"));
            }
            testresult.close();
            return editions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public static customer fetchcustomer(String email) {
        try {
            email.toLowerCase();

            PreparedStatement tempstatement = myConnection.prepareStatement("SELECT * FROM customers WHERE email=(?)");
            tempstatement.setString(1, email);
            ResultSet testresult = tempstatement.executeQuery();

            testresult.next();
            customer tempcustomer = new customer(testresult.getString("email"), testresult.getString("firstname"),
                    testresult.getString("lastname"), testresult.getString("address"), testresult.getInt("id"));

            testresult.close();

            return tempcustomer;

        } catch (SQLException throwables) {
            return null;
        }
    }

    public static int sqlrentreserve(String type, int id, String name, String edition, boolean rent) {
        try {
            PreparedStatement tempStatement;

            PreparedStatement totalcheck = myConnection
                    .prepareStatement("SELECT COUNT(name) FROM entries WHERE rentedby=(?) OR reservedby=(?)");
            totalcheck.setInt(1, id);
            totalcheck.setInt(2, id);
            ResultSet totalset = totalcheck.executeQuery();

            if (totalset.next() && totalset.getInt("COUNT(name)") >= 5) {
                return 6;
            }

            PreparedStatement checkstatement = myConnection
                    .prepareStatement("SELECT name FROM entries WHERE rentedby=(?) AND name=(?) AND type=(?)");
            checkstatement.setInt(1, id);
            checkstatement.setString(2, name);
            checkstatement.setString(3, type);

            ResultSet checkset = checkstatement.executeQuery();

            if ((checkset.next())) {
                return 4;
            }

            PreparedStatement freeStatement = myConnection.prepareStatement(
                    "SELECT edition, id FROM entries WHERE name=(?) AND rentedby IS NULL AND reservedby IS NULL AND edition=(?) AND type=(?)");
            freeStatement.setString(1, name);
            freeStatement.setString(2, edition);
            freeStatement.setString(3, type);
            ResultSet freeresult = freeStatement.executeQuery();

            try {
                freeresult.next();
                freeresult.getString("edition");
            } catch (SQLException f) {

                freeStatement = myConnection.prepareStatement(
                        "SELECT edition, id FROM entries WHERE name=(?) AND rentedby IS NOT NULL AND reservedby IS NULL AND edition=(?) AND type=(?)");
                freeStatement.setString(1, name);
                freeStatement.setString(2, edition);
                freeStatement.setString(3, type);
                freeresult = freeStatement.executeQuery();
                try {
                    freeresult.next();
                    freeresult.getString("edition");
                } catch (SQLException e) {
                    freeStatement = myConnection.prepareStatement(
                            "SELECT edition, id FROM entries WHERE name=(?) AND rentedby IS NULL AND reservedby=(?) AND edition=(?) AND type=(?)");
                    freeStatement.setString(1, name);
                    freeStatement.setInt(2, id);
                    freeStatement.setString(3, edition);
                    freeStatement.setString(4, type);
                    freeresult = freeStatement.executeQuery();
                    try {
                        freeresult.next();
                        freeresult.getString("edition");

                        tempStatement = myConnection
                                .prepareStatement("UPDATE entries SET rentedby=(?),reservedby =NULL WHERE id=(?)");
                        tempStatement.setInt(1, id);
                        tempStatement.setInt(2, freeresult.getInt("id"));
                        tempStatement.executeUpdate();

                        PreparedStatement datement = myConnection
                                .prepareStatement("UPDATE entries SET startdate=(?),enddate=(?)  WHERE id='"
                                        + freeresult.getInt("id") + "'");
                        datement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                        datement.setDate(2, new java.sql.Date(System.currentTimeMillis()
                                + (long) 86400000 * (long) getperiodsingle(name, type, edition)));
                        datement.execute();

                        PreparedStatement changestatement = myConnection
                                .prepareStatement("UPDATE changes SET entries='1'");
                        changestatement.executeUpdate();
                        return 2;

                    } catch (SQLException l) {
                        return 0;
                    }
                }
            }
            PreparedStatement reserveCheckment = myConnection
                    .prepareStatement("SELECT COUNT(name) FROM entries WHERE reservedby=(?)");
            reserveCheckment.setInt(1, id);
            ResultSet reserveset = reserveCheckment.executeQuery();
            reserveset.next();

            if (reserveset.getInt("COUNT(name)") != 0) {
                return 5;
            }

            if (rent == true) {
                tempStatement = myConnection
                        .prepareStatement("UPDATE entries SET rentedby=(?) WHERE id=(?) AND rentedby IS NULL");
                tempStatement.setInt(1, id);
                tempStatement.setInt(2, freeresult.getInt("id"));
                tempStatement.executeUpdate();

                PreparedStatement datement = myConnection.prepareStatement(
                        "UPDATE entries SET startdate=(?),enddate=(?) WHERE id='" + freeresult.getInt("id") + "'");
                datement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                datement.setDate(2, new java.sql.Date(
                        System.currentTimeMillis() + (long) 86400000 * (long) getperiodsingle(name, type, edition)));
                datement.execute();

                PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
                changestatement.executeUpdate();
                freeresult.close();
                return 2;
            } else {
                try {

                    tempStatement = myConnection.prepareStatement("UPDATE entries SET reservedby=(?) WHERE id=(?)");
                    tempStatement.setInt(1, id);
                    tempStatement.setInt(2, freeresult.getInt("id"));
                    tempStatement.executeUpdate();
                } catch (SQLException f) {
                    f.printStackTrace();
                    return 3;
                }

                PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
                changestatement.executeUpdate();
                return 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 3;
        }
    }

    public static String rentinglist(int id, boolean reser) {
        try {
            String tempstring = "";
            String countquery = null;
            if (reser == false) {
                countquery = "SELECT COUNT(id) FROM entries WHERE rentedby=(?)";
            } else {
                countquery = "SELECT COUNT(id) FROM entries WHERE reservedby=(?)";
            }

            PreparedStatement countstatement = myConnection.prepareStatement(countquery);
            countstatement.setInt(1, id);
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int rentlength = countresult.getInt("COUNT(id)");
            countresult.close();

            String query = null;
            if (reser == false) {
                query = "SELECT id FROM entries WHERE rentedby=(?)";
            } else {
                query = "SELECT id FROM entries WHERE reservedby=(?)";
            }
            PreparedStatement rentingStatement = myConnection.prepareStatement(query);
            rentingStatement.setInt(1, id);
            ResultSet rentingresult = rentingStatement.executeQuery();

            if (rentlength != 0) {
                for (int i = 0; i < rentlength; i++) {
                    if (i != 0) {
                        tempstring = tempstring.concat(",");
                    }
                    rentingresult.next();

                    tempstring = tempstring.concat(Integer.toString(rentingresult.getInt("id")));
                }
            }
            return tempstring;

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static ArrayList<String> rentinglistarr(String email, boolean reser) {
        try {
            ArrayList<String> tempstring = new ArrayList<String>();

            email.toLowerCase();

            PreparedStatement emailstatement = myConnection
                    .prepareStatement("Select id FROM customers WHERE email=(?)");
            emailstatement.setString(1, email);
            ResultSet emailresult = emailstatement.executeQuery();
            emailresult.next();
            int id = emailresult.getInt("id");

            String countquery = null;
            if (reser == false) {
                countquery = "SELECT COUNT(id) FROM entries WHERE rentedby=(?)";
            } else {
                countquery = "SELECT COUNT(id) FROM entries WHERE reservedby=(?)";
            }
            PreparedStatement countstatement = myConnection.prepareStatement(countquery);
            countstatement.setInt(1, id);
            ResultSet countresult = countstatement.executeQuery();

            countresult.next();
            int rentlength = countresult.getInt("COUNT(id)");
            countresult.close();

            String query = null;
            if (reser == false) {
                query = "SELECT id FROM entries WHERE rentedby=(?)";
            } else {
                query = "SELECT id FROM entries WHERE reservedby=(?)";
            }
            PreparedStatement rentingStatement = myConnection.prepareStatement(query);
            rentingStatement.setInt(1, id);
            ResultSet rentingresult = rentingStatement.executeQuery();

            if (rentlength != 0) {
                for (int i = 0; i < rentlength; i++) {
                    rentingresult.next();

                    tempstring.add(Integer.toString(rentingresult.getInt("id")));
                }
            }
            return tempstring;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static entry singleentry(int id) {
        try {
            PreparedStatement rentingStatement = myConnection.prepareStatement("SELECT* FROM entries WHERE id=(?)");
            rentingStatement.setInt(1, id);
            ResultSet rentingresult = rentingStatement.executeQuery();

            rentingresult.next();

            PreparedStatement filestatement = myConnection.prepareStatement("SELECT format FROM photos WHERE id=(?)");
            filestatement.setInt(1, rentingresult.getInt("id"));
            ResultSet fileresult = filestatement.executeQuery();
            fileresult.next();
            int temprent = 0;
            if (rentingresult.getObject("rentedby") == null) {
                temprent = -1;
            } else {
                temprent = rentingresult.getInt("rentedby");
            }
            int tempreserve = 0;
            if (rentingresult.getObject("reservedby") == null) {
                tempreserve = -1;
            } else {
                tempreserve = rentingresult.getInt("reservedby");
            }
            entry tempentry = new entry(rentingresult.getInt("id"), rentingresult.getString("name"),
                    rentingresult.getString("type"), rentingresult.getString("edition"), rentingresult.getDouble("fee"),
                    rentingresult.getString("Duration"), tempreserve, temprent, rentingresult.getDate("startdate"),
                    rentingresult.getDate("enddate"),
                    rentingresult.getInt("id") + "img" + fileresult.getString("format"),
                    rentingresult.getInt("period"));

            return tempentry;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean returnitem(int item, int customer, boolean reserve) {
        try {

            String query = null;
            if (reserve == false) {
                query = "UPDATE entries SET rentedby=NULL, startdate=NULL,enddate=NULL WHERE rentedby=(?) AND id=(?)";
            } else {
                query = "UPDATE entries SET reservedby=NULL WHERE reservedby=(?) AND id=(?)";
            }
            PreparedStatement statement = myConnection.prepareStatement(query);
            statement.setInt(1, customer);
            statement.setInt(2, item);
            statement.executeUpdate();

            PreparedStatement changestatement = myConnection.prepareStatement("UPDATE changes SET entries='1'");
            changestatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

}
