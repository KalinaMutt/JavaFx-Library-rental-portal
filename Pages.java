package com.example;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public abstract class Pages {

    protected int thisscenenumber = 0;
    protected int thisscenetarget = 0;
    protected Node adminable;
    protected boolean adminpage;

    protected boolean onadmin;
    protected ArrayList<Node> focusList = new ArrayList<Node>();
    protected int focuscounter = 0;

    public abstract Scene getScene(Pane root);

    protected Pane createbackgrounds(Pane root, int current, int target, boolean admin) {
        Pane background = new Pane();
        background.setPrefSize(600, 550);
        if (admin) {
            background.getStyleClass().add("adminbackground");
        } else {
            background.getStyleClass().add("standardbackground");
        }
        root.getChildren().add(background);

        Pane topbar = new Pane();
        topbar.getStyleClass().add("topbar");
        topbar.setPrefSize(600, 50);
        root.getChildren().add(topbar);

        // logo images created on https://looka.com/logo-maker/

        Image smallogo = new Image("/imagesicons/ppicon.png");
        ImageView smallogox = new ImageView(smallogo);
        smallogox.setFitWidth(50);
        smallogox.setPreserveRatio(true);
        smallogox.getStyleClass().add("smallogo");
        topbar.getChildren().add(smallogox);

        Image longlogo = new Image("/imagesicons/onelinepp.png");
        ImageView longlogox = new ImageView(longlogo);
        longlogox.setFitWidth(300);
        longlogox.setPreserveRatio(true);
        longlogox.getStyleClass().add("longlogo");
        topbar.getChildren().add(longlogox);

        Pane adminswitcher = new Pane();
        adminswitcher.setPrefSize(50, 30);
        adminswitcher.getStyleClass().add("adminswitcher");
        adminswitcher.getStyleClass().add("hoverable");
        adminable = adminswitcher;
        topbar.getChildren().add(adminswitcher);
        addtoofocuslist(adminswitcher);
        if (admin) {
            scenechange(adminswitcher, 1);
            adminswitcher.setStyle("-fx-background-color:#77FF94");
        } else {
            scenechange(adminswitcher, 8);
            adminswitcher.setStyle("-fx-background-color:#558564");
        }

        Pane adminfiller = new Pane();
        adminfiller.setPrefSize(50, 30);
        adminfiller.getStyleClass().add("adminfiller");
        adminswitcher.getChildren().add(adminfiller);

        Image computericon = new Image("/imagesicons/computer-128.png");
        // https://www.iconsdb.com/black-icons/computer-icon.html
        ImageView computericonx = new ImageView(computericon);
        computericonx.setFitWidth(20);
        computericonx.setPreserveRatio(true);
        computericonx.getStyleClass().add("computericon");
        adminfiller.getChildren().add(computericonx);

        Pane backbutton = new Pane();
        backbutton.setPrefSize(140, 30);
        backbutton.getStyleClass().add("backbutton");
        backbutton.getStyleClass().add("hoverable");

        topbar.getChildren().add(backbutton);

        addtoofocuslist(backbutton);

        Label backbuttonLabel = new Label();
        backbuttonLabel.setPrefSize(140, 30);
        backbuttonLabel.getStyleClass().add("backbuttonlabel");
        if (current == 1 || current == 8) {
            backbuttonLabel.setText("Logout");
            properlogout(backbutton, target);
        } else {
            backbuttonLabel.setText("Back");
            scenechange(backbutton, target);
        }
        backbutton.getChildren().add(backbuttonLabel);

        return background;
    }

    protected int getthisscenenumber() {
        return thisscenenumber;
    }

    protected void triggeradminable(boolean status) {
        if (status) {
            adminable.setVisible(true);
        } else {
            adminable.setVisible(false);
        }
    }

    protected void tabindexing() {
        try {
            focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
            do {

                if (focuscounter == focusList.size() - 1) {
                    focuscounter = -1;
                }
                focuscounter += 1;
            }

            while (focusList.get(focuscounter).isVisible() == false
                    || focusList.get(focuscounter).getParent().isVisible() == false
                    || focusList.get(focuscounter).getParent().getParent().isVisible() == false);
        } catch (NullPointerException e) {

        } finally {
            focusList.get(focuscounter).getStyleClass().add("tabbablepane");
            focusList.get(focuscounter).requestFocus();
        }
    }

    protected void backtabindexing() {
        try {
            focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
            do {
                if (focuscounter == 0) {
                    focuscounter = focusList.size();
                }
                focuscounter -= 1;
            } while (focusList.get(focuscounter).isVisible() == false
                    || focusList.get(focuscounter).getParent().isVisible() == false
                    || focusList.get(focuscounter).getParent().getParent().isVisible() == false);

        } catch (NullPointerException e) {

        } finally {
            focusList.get(focuscounter).getStyleClass().add("tabbablepane");
            focusList.get(focuscounter).requestFocus();
        }
    }

    /**
     * *
     * Adds the specified node to the foucs list, it seems as though this should be
     * done in the order the node is added to the page, and enables focus moving for
     * the elements
     * 
     * @param newnode the node to be added
     */
    protected void addtoofocuslist(Node newnode) {
        focusList.add(newnode);
        singleenable(newnode);
    }

    /**
     * Adds the specified node to the foucs list, in the specified index and enables
     * focus moving for the elements
     * 
     * @param newnode  the node to be added
     * @param position the position to be added at
     */
    protected void addtoofocuslist(Node newnode, int position) {
        focusList.add(position, newnode);
        singleenable(newnode);
    }

    /**
     * Removes the specified node from the focus list
     * 
     * @param oldnode the node to remove
     */
    protected void removefromfocuslist(Node oldnode) {
        focusList.remove(oldnode);
    }

    /**
     * A method that returns the list of items in the focus list
     * 
     * @return arraylist of nodes
     */
    protected ArrayList<Node> getfocuslist() {
        return focusList;
    }

    /**
     * A method to alter the admin status of the page which affects whether admin
     * elements appear
     * 
     * @param newbool whether admin is true or not
     */
    protected void setonadmin(boolean newbool) {
        onadmin = newbool;
    }

    /**
     * A function used to enable focus traversing using buttons on an element,
     * however does not add to the focus list
     * 
     * @param newnode node to enable traversal upon.
     */
    protected void singleenable(final Node newnode) {
        final KeyCodeCombination shiftab = new KeyCodeCombination(KeyCode.TAB, KeyCombination.SHIFT_DOWN);
        EventHandler<KeyEvent> tabindexing = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.UP
                        || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.ENTER) {
                    focusList.get(focuscounter).requestFocus();
                }
                if (shiftab.match(event)) {
                    for (String style : focusList.get(focuscounter).getStyleClass()) {
                        if (style.equals("poundfield")) {
                            TextField tempfield = (TextField) focusList.get(focuscounter);
                            formatdecimal(tempfield);
                        }
                    }
                    backtabindexing();
                    while (focusList.get(focuscounter).isFocused() == false) {
                        focusList.get(focuscounter).requestFocus();
                    }

                }

                else if (event.getCode() == KeyCode.TAB) {
                    for (String style : focusList.get(focuscounter).getStyleClass()) {
                        if (style.equals("poundfield")) {
                            TextField tempfield = (TextField) focusList.get(focuscounter);
                            formatdecimal(tempfield);
                        }
                    }
                    tabindexing();
                    while (focusList.get(focuscounter).isFocused() == false) {
                        focusList.get(focuscounter).requestFocus();
                    }

                }
                if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.UP
                        || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.ENTER) {
                    focusList.get(focuscounter).requestFocus();
                }

            }

        };

        EventHandler<MouseEvent> quickindex = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (String style : focusList.get(focuscounter).getStyleClass()) {
                    if (style.equals("poundfield")) {
                        TextField tempfield = (TextField) focusList.get(focuscounter);
                        formatdecimal(tempfield);
                    }
                }
                focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
                focuscounter = focusList.indexOf(event.getSource());
                focusList.get(focuscounter).getStyleClass().add("tabbablepane");
                focusList.get(focuscounter).requestFocus();
                onpagentercheck();

            }
        };

        newnode.setFocusTraversable(true);
        newnode.addEventHandler(KeyEvent.KEY_PRESSED, tabindexing);
        newnode.addEventHandler(MouseEvent.MOUSE_CLICKED, quickindex);
        if (newnode.getClass() == (Pane.class)) {
            newnode.getStyleClass().add("hoverable");
        }
    }

    protected void enabletabbing() {
        getfocuslist().get(0).requestFocus();
    }

    protected void scenechange(Node button, final int number) {
        EventHandler<MouseEvent> performlogout = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.switchtarget(number);
            }
        };
        EventHandler<KeyEvent> keylogout = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Main.switchtarget(number);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, performlogout);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keylogout);
    }

    protected boolean getonadmin() {
        return onadmin;
    }

    protected void onpagentercheck() {
        if (focusList.get(focuscounter).getStyleClass().remove("tabbablepane")) {
        }
        focusList.get(focuscounter).getStyleClass().add("tabbablepane");
        try {
            while (focusList.get(focuscounter).isVisible() == false
                    || focusList.get(focuscounter).getParent().isVisible() == false
                    || focusList.get(focuscounter).getParent().getParent().isVisible() == false) {
                focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
                if (focuscounter == focusList.size() - 1) {
                    focuscounter = -1;
                }
                focuscounter += 1;
                focusList.get(focuscounter).getStyleClass().add("tabbablepane");
                focusList.get(focuscounter).requestFocus();
            }
        } catch (NullPointerException e) {

        } finally {

        }
    }

    protected void adminassignment(final Node newer, final Node older) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newer.getStyleClass().remove("selectedstatus");
                newer.getStyleClass().add("selectedstatus");
                older.getStyleClass().remove("selectedstatus");
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    newer.getStyleClass().remove("selectedstatus");
                    newer.getStyleClass().add("selectedstatus");
                    older.getStyleClass().remove("selectedstatus");
                }
            }
        };
        newer.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        newer.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    protected void properlogout(Node button, final int target) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.alteradmin(null);
                Main.switchtarget(target);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Main.alteradmin(null);
                    Main.switchtarget(target);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    protected ArrayList<Pane> itemfiltering(TextField value, Pane filternode, int child) {
        ArrayList<Pane> itemlist = new ArrayList<Pane>();
        for (Node children : filternode.getChildren()) {
            Pane childrenstore = (Pane) children;
            Label usern = (Label) childrenstore.getChildren().get(child);
            if (!(usern.getText().toLowerCase().matches(value.getText().toLowerCase() + "(.*)"))) {
                childrenstore.setVisible(false);
            } else {
                childrenstore.setVisible(true);
                itemlist.add(childrenstore);
            }

        }
        return itemlist;
    }

    protected void typefiltering(Node button, final TextField v, final Pane f, final int child) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<Pane> templist = itemfiltering(v, f, child);
                for (int i = 0; i < templist.size(); i++) {
                    templist.get(i).setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
                    templist.get(i).setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                ArrayList<Pane> templist = itemfiltering(v, f, child);
                for (int i = 0; i < templist.size(); i++) {
                    templist.get(i).setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
                    templist.get(i).setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_RELEASED, keychanger);
    }

    protected void typeassignment(final Node newer, final Node older, final Node oldertwo, final Label period) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                typeAssignmentMethod(newer, older, oldertwo, period);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    typeAssignmentMethod(newer, older, oldertwo, period);
                }
            }
        };
        newer.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        newer.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    protected void typeAssignmentMethod(Node newer, Node older, Node oldertwo, Label period) {
        Pane temppane = (Pane) newer;
        Label templabel = (Label) temppane.getChildren().get(0);
        if (period != null) {
            if (templabel.getText().equals("Monthly")) {
                period.setText("Months");
            } else if (templabel.getText().equals("Weekly")) {
                period.setText("Weeks");
            } else if (templabel.getText().equals("Daily")) {
                period.setText("Days");
            }
        }
        newer.getStyleClass().remove("selectedstatus");
        newer.getStyleClass().add("selectedstatus");
        older.getStyleClass().remove("selectedstatus");
        oldertwo.getStyleClass().remove("selectedstatus");
    }

    protected void formatdecimal(final TextField field) {
        try {
            DecimalFormat formatter = new DecimalFormat("0.00");
            if ((Double.parseDouble(field.getText())) > 99.99) {
                field.setText("99.99");
            } else if ((Double.parseDouble(field.getText())) < 0.00) {
                field.setText("0.00");
            } else {
                field.setText(formatter.format(Double.parseDouble(field.getText())));
            }
        } catch (java.lang.NumberFormatException e) {
            field.setText("0.00");
        }
    }

    protected void passentry(Node button, final entry info, final int scenenumber) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.setcurrententry(info);
                Main.switchtarget(scenenumber);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Main.setcurrententry(info);
                    Main.switchtarget(scenenumber);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    protected void emailfetch(Node button, final TextField field, final Label email, final Label full, final Label id,
            final Label renting, final itemreturn page) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                customer temp = databasehandler.fetchcustomer(field.getText().toLowerCase());
                if (temp == null) {
                    field.getStyleClass().add("errorborder");
                    return;
                }
                field.getStyleClass().remove("errorborder");
                email.setText(temp.getemail());
                full.setText(temp.getfname() + " " + temp.getlname());
                id.setText(Integer.toString(temp.getid()));
                if (page == null || page.getreserve() == false) {
                    renting.setText(databasehandler.rentinglist(temp.getid(), false));
                } else {
                    renting.setText(databasehandler.rentinglist(temp.getid(), true));
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    customer temp = databasehandler.fetchcustomer(field.getText().toLowerCase());
                    if (temp == null) {
                        field.getStyleClass().add("errorborder");
                        return;
                    }
                    field.getStyleClass().remove("errorborder");
                    email.setText(temp.getemail());
                    full.setText(temp.getfname() + temp.getlname());
                    id.setText(Integer.toString(temp.getid()));
                    if (page == null || page.getreserve() == false) {
                        renting.setText(databasehandler.rentinglist(temp.getid(), false));
                    } else {
                        renting.setText(databasehandler.rentinglist(temp.getid(), true));
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    // rent code, edition label, user id, currentitem id, start date, end date

}
