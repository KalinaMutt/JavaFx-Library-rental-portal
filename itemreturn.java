package com.example;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class itemreturn extends Pages {
    private boolean reserve;

    public Scene getScene(Pane root) {
        thisscenenumber = 3;
        thisscenetarget = 1;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene itemreturnScene = new Scene(root, 600, 600);
        itemreturnScene.getStylesheets().add("css/itemreturn.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Pane toppane = new Pane();
        toppane.setPrefSize(600, 50);
        background.getChildren().add(toppane);

        Pane reservefiller = new Pane();
        reservefiller.setPrefSize(200, 30);
        reservefiller.setTranslateY(10);
        reservefiller.getStyleClass().add("reservefiller");
        toppane.getChildren().add(reservefiller);
        addtoofocuslist(reservefiller);

        Label reservelabel = new Label("Reservations");
        reservelabel.setPrefSize(200, 30);
        reservelabel.getStyleClass().add("reservelabel");
        reservefiller.getChildren().add(reservelabel);

        Pane rentfiller = new Pane();
        rentfiller.setPrefSize(200, 30);
        rentfiller.setTranslateY(10);
        rentfiller.getStyleClass().add("rentfiller");
        rentfiller.getStyleClass().add("selectedstatus");
        toppane.getChildren().add(rentfiller);
        addtoofocuslist(rentfiller);

        Label rentlabel = new Label("Rentals");
        rentlabel.setPrefSize(200, 30);
        rentlabel.getStyleClass().add("rentlabel");
        rentfiller.getChildren().add(rentlabel);

        Pane searchbarpane = new Pane();
        searchbarpane.setPrefSize(300, 30);
        searchbarpane.setTranslateX(150);
        searchbarpane.setTranslateY(60);
        searchbarpane.getStyleClass().add("searchback");

        TextField searchfield = new TextField();
        searchfield.setPromptText("Customer email");
        searchfield.setPrefSize(200, 30);
        searchfield.getStyleClass().add("searchfield");
        addtoofocuslist(searchfield);
        searchbarpane.getChildren().add(searchfield);

        Pane searchfiller = new Pane();
        searchfiller.setPrefSize(30, 30);
        searchfiller.getStyleClass().add("searchfiller");
        searchfiller.setTranslateX(270);

        Image searchicon = new Image("imagesicons/search-13-128.png", true);
        // https://www.iconsdb.com/black-icons/search-13-icon.html
        ImageView searchiconx = new ImageView(searchicon);
        searchiconx.setFitWidth(20);
        searchiconx.setPreserveRatio(true);
        searchiconx.getStyleClass().add("searchicon");
        searchfiller.getChildren().add(searchiconx);

        searchbarpane.getChildren().add(searchfiller);
        addtoofocuslist(searchfiller);
        background.getChildren().add(searchbarpane);

        Pane userdetails = new Pane();
        userdetails.setPrefSize(500, 120);
        userdetails.setTranslateX(50);
        userdetails.setTranslateY(110);
        background.getChildren().add(userdetails);

        Pane emailpane = new Pane();

        Pane namepane = new Pane();

        Pane idpane = new Pane();

        Pane rentingpane = new Pane();

        Pane idbuttons = new Pane();
        idbuttons.setPrefSize(500, 70);
        idbuttons.setTranslateX(50);
        idbuttons.setTranslateY(270);
        idbuttons.setId("idbuttons");
        background.getChildren().add(idbuttons);

        // populate with buttons when getting user later, triggered by clicking user in
        // dropdown

        Pane itemdetails = new Pane();
        itemdetails.setPrefSize(500, 120);
        itemdetails.setTranslateX(50);
        itemdetails.setTranslateY(340);
        background.getChildren().add(itemdetails);

        Pane itemnamePane = new Pane();

        Pane itemtypePane = new Pane();

        Pane itemeditionPane = new Pane();

        Pane itemduedatePane = new Pane();

        Pane itemlatedetailPane = new Pane();

        Pane itemlatefeePane = new Pane();

        Pane[] textlabelspane = { emailpane, namepane, idpane, rentingpane, itemnamePane, itemtypePane, itemeditionPane,
                itemduedatePane, itemlatedetailPane, itemlatefeePane };
        String[] texts = { "Email:", "Full Name:", "ID:", "Renting:", "Item name:", "Type:", "Edition:", "Due date:",
                "Fee:", "Late fee:" };
        Label[] labelfillers = { null, null, null, null, null, null, null, null, null, null };

        for (int i = 0; i < textlabelspane.length; i++) {
            textlabelspane[i].setPrefSize(250, 30);
            if (i >= 0 && i <= 2) {
                textlabelspane[i].setTranslateY(i * 50);
            }
            if (i == 3 || i == 8 || i == 7 || i == 9) {
                textlabelspane[i].setTranslateX(250);
                if (i == 3) {
                    textlabelspane[i].setTranslateY(50);
                }
            }
            if (i > 3 && i <= 6) {
                textlabelspane[i].setTranslateY((i - 4) * 50);
            }
            if (i == 7 || i == 8 || i == 9) {
                textlabelspane[i].setTranslateY((i - 7) * 50);
            }
            Label looplabel = new Label(texts[i]);
            looplabel.setPrefSize(80, 40);
            looplabel.getStyleClass().add("looplabel");

            textlabelspane[i].getChildren().add(looplabel);

            Label looptext = new Label();
            looptext.setPrefSize(150, 40);
            looptext.getStyleClass().add("looptext");

            textlabelspane[i].getChildren().add(looptext);
            if (i < 4) {
                userdetails.getChildren().add(textlabelspane[i]);
            } else {
                itemdetails.getChildren().add(textlabelspane[i]);
            }

            labelfillers[i] = looptext;

        }
        emailfetch(searchfiller, searchfield, labelfillers[0], labelfillers[1], labelfillers[2], labelfillers[3], this);
        clearrent(searchfiller, idbuttons, labelfillers[4], labelfillers[5], labelfillers[6], labelfillers[7],
                labelfillers[8], labelfillers[9]);
        returntype(rentfiller, this, toppane, searchfield, idbuttons, labelfillers[0], labelfillers[1], labelfillers[2],
                labelfillers[3], labelfillers[4], labelfillers[5], labelfillers[6], labelfillers[7], labelfillers[8],
                labelfillers[9]);
        returntype(reservefiller, this, toppane, searchfield, idbuttons, labelfillers[0], labelfillers[1],
                labelfillers[2], labelfillers[3], labelfillers[4], labelfillers[5], labelfillers[6], labelfillers[7],
                labelfillers[8], labelfillers[9]);

        Pane returnbutton = new Pane();
        returnbutton.getStyleClass().add("returnbutton");
        returnbutton.setPrefSize(200, 40);
        addtoofocuslist(returnbutton);
        returnitem(returnbutton, searchfield, idbuttons, labelfillers[0], labelfillers[1], labelfillers[2],
                labelfillers[3], labelfillers[4], labelfillers[5], labelfillers[6], labelfillers[7], labelfillers[8],
                labelfillers[9], this);

        Label returnlabel = new Label("Accept return");
        returnlabel.setPrefSize(200, 40);
        returnlabel.getStyleClass().add("returnlabel");
        returnbutton.getChildren().add(returnlabel);

        background.getChildren().add(returnbutton);
        itemsfetch(searchfiller, searchfield, idbuttons, labelfillers[4], labelfillers[5], labelfillers[6],
                labelfillers[7], labelfillers[8], labelfillers[9], this);

        enabletabbing();

        return itemreturnScene;

    }

    public void setreserve(boolean newb) {
        reserve = newb;
    }

    public boolean getreserve() {
        return reserve;
    }

    private void itemsfetch(final Node button, final TextField user, final Pane idpane, final Label name,
            final Label type, final Label edition, final Label due, final Label breakdown, final Label fee,
            final itemreturn page) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                itemsFetchMethod(button, user, idpane, name, type, edition, due, breakdown, fee, page);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    itemsFetchMethod(button, user, idpane, name, type, edition, due, breakdown, fee, page);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void itemsFetchMethod(Node button, TextField user, Pane idpane, Label name, Label type, Label edition,
            Label due, Label breakdown, Label fee, itemreturn page) {
        ArrayList<String> temp = null;
        if (page == null || page.getreserve() == false) {
            temp = databasehandler.rentinglistarr(user.getText(), false);
        } else {
            temp = databasehandler.rentinglistarr(user.getText(), true);
        }
        if (temp != null) {
            for (Node item : idpane.getChildren()) {
                removefromfocuslist(item);
            }
            idpane.getChildren().clear();
            for (String ids : temp) {
                Pane editfiller = new Pane();
                editfiller.setPrefSize(100, 30);
                editfiller.getStyleClass().add("editfiller");
                int tempwidth = temp.size() - 1;
                tempwidth += -5 * Math.floor(temp.indexOf(ids) / 5);
                if (tempwidth > 4) {
                    tempwidth = 4;
                }
                editfiller.setTranslateX(200 + (-60 * tempwidth) + ((120) * ((temp.indexOf(ids)) % 5)));
                editfiller.setTranslateY(40 * Math.floor(temp.indexOf(ids) / 5));
                idpane.getChildren().add(editfiller);

                Label editlabel = new Label(ids);
                editlabel.setPrefSize(100, 30);
                editlabel.getStyleClass().add("editlabel");
                editfiller.getChildren().add(editlabel);

                addtoofocuslist(editfiller, 6 + temp.indexOf(ids));

                useritems(editfiller, idpane, name, type, edition, due, breakdown, fee, page);
            }
        }
    }

    private void useritems(final Node button, final Pane paren, final Label name, final Label type, final Label edition,
            final Label due, final Label breakdown, final Label fee, final itemreturn page) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                userItemsMethod(button, paren, name, type, edition, due, breakdown, fee, page);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    userItemsMethod(button, paren, name, type, edition, due, breakdown, fee, page);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);

    }

    private void userItemsMethod(Node button, Pane paren, Label name, Label type, Label edition, Label due,
            Label breakdown, Label fee, itemreturn page) {
        Pane tempbut = (Pane) button;
        Label templabel = (Label) tempbut.getChildren().get(0);
        entry temp = databasehandler.singleentry(Integer.parseInt(templabel.getText()));
        for (Node child : paren.getChildren()) {
            child.getStyleClass().remove("selectedstatus");
        }
        button.getStyleClass().add("selectedstatus");
        name.setText(temp.getname());
        type.setText(temp.gettype());
        edition.setText(temp.getedition());
        if (page.getreserve()) {
            due.setText("");
            fee.setText("");
        } else {

            Date dued = temp.getendtime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
            due.setText(dateFormat.format(dued));
            long diffInMillies = (System.currentTimeMillis()) - temp.getendtime().getTime();
            double diff = (double) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff < 0.0) {
                diff = 0.0;
            }
            if (temp.getduration().equals("Weekly")) {
                diff = Math.floor(diff / (double) 7);
            }
            if (temp.getduration().equals("Monthly")) {
                diff = Math.floor(diff / (double) 30);
            }
            breakdown.setText(Double.toString(temp.getfee()) + " " + temp.getduration());
            fee.setText(Double.toString(diff * temp.getfee()));
        }
    }

    private void returntype(final Node button, final itemreturn page, final Pane paren, final TextField name,
            final Pane ids, final Label zero, final Label one, final Label two, final Label three, final Label four,
            final Label five, final Label six, final Label seven, final Label eight, final Label nine) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                returnTypeMethod(button, page, paren, name, ids, zero, one, two, three, four, five, six, seven, eight,
                        nine);

            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    returnTypeMethod(button, page, paren, name, ids, zero, one, two, three, four, five, six, seven,
                            eight, nine);

                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);

    }

    private void returnTypeMethod(Node button, itemreturn page, Pane paren, TextField name, Pane ids, Label zero,
            Label one, Label two, Label three, Label four, Label five, Label six, Label seven, Label eight,
            Label nine) {
        for (Node child : paren.getChildren()) {
            child.getStyleClass().remove("selectedstatus");
        }
        button.getStyleClass().add("selectedstatus");
        Pane temppane = (Pane) button;
        Label templabel = (Label) temppane.getChildren().get(0);
        if (templabel.getText().equals("Reservations") && page.getreserve() != true) {
            page.setreserve(true);
            for (Node item : ids.getChildren()) {
                removefromfocuslist(item);
            }
            ids.getChildren().clear();
            name.setText(null);
            zero.setText(null);
            one.setText(null);
            two.setText(null);
            three.setText(null);
            Pane parenx = (Pane) three.getParent();
            Label chil = (Label) parenx.getChildren().get(0);
            chil.setText("Reserving:");
            four.setText(null);
            five.setText(null);
            six.setText(null);
            seven.setText(null);
            eight.setText(null);
            nine.setText(null);

        } else if (templabel.getText().equals("Rentals") && page.getreserve() != false) {
            page.setreserve(false);
            for (Node item : ids.getChildren()) {
                removefromfocuslist(item);
            }
            ids.getChildren().clear();
            name.setText(null);
            zero.setText(null);
            one.setText(null);
            two.setText(null);
            three.setText(null);
            Pane parenx = (Pane) three.getParent();
            Label chil = (Label) parenx.getChildren().get(0);
            chil.setText("Renting:");
            four.setText(null);
            five.setText(null);
            six.setText(null);
            seven.setText(null);
            eight.setText(null);
            nine.setText(null);
        }
    }

    private void returnitem(final Node button, final TextField name, final Pane ids, final Label zero, final Label one,
            final Label two, final Label three, final Label four, final Label five, final Label six, final Label seven,
            final Label eight, final Label nine, final itemreturn page) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                returnItemMethod(button, name, ids, zero, one, two, three, four, five, six, seven, eight, nine, page);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    returnItemMethod(button, name, ids, zero, one, two, three, four, five, six, seven, eight, nine,
                            page);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void returnItemMethod(Node button, TextField name, Pane ids, Label zero, Label one, Label two, Label three,
            Label four, Label five, Label six, Label seven, Label eight, Label nine, itemreturn page) {
        int itemid = -1;
        boolean tempbool = false;
        tempbool = page.getreserve();
        for (Node childs : ids.getChildren()) {
            for (String clas : childs.getStyleClass()) {
                if (clas == "selectedstatus") {
                    Pane temppane = (Pane) childs;
                    Label tempLabel = (Label) temppane.getChildren().get(0);
                    itemid = Integer.parseInt(tempLabel.getText());
                }
            }
        }
        if (itemid != -1 && !(StringUtils.isBlank(two.getText()))) {
            if (databasehandler.returnitem(itemid, Integer.parseInt(two.getText()), tempbool)) {
                for (Node item : ids.getChildren()) {
                    removefromfocuslist(item);
                }
                ids.getChildren().clear();
                name.setText(null);
                zero.setText(null);
                one.setText(null);
                two.setText(null);
                three.setText(null);
                four.setText(null);
                five.setText(null);
                six.setText(null);
                seven.setText(null);
                eight.setText(null);
                nine.setText(null);
                focuscounter = focusList.indexOf(button);
            }
        }
    }

    private void clearrent(final Node button, final Pane ids, final Label one, final Label two, final Label three,
            final Label four, final Label five, final Label six) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (Node item : ids.getChildren()) {
                    removefromfocuslist(item);
                }
                ids.getChildren().clear();
                one.setText(null);
                two.setText(null);
                three.setText(null);
                four.setText(null);
                five.setText(null);
                six.setText(null);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    for (Node item : ids.getChildren()) {
                        removefromfocuslist(item);
                    }
                    ids.getChildren().clear();
                    one.setText(null);
                    two.setText(null);
                    three.setText(null);
                    four.setText(null);
                    five.setText(null);
                    six.setText(null);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }
}
