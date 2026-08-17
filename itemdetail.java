package com.example;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class itemdetail extends Pages {

    private boolean rent = false;

    public Scene getScene(Pane root) {
        thisscenenumber = 16;
        thisscenetarget = 5;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene itemdetailScene = new Scene(root, 600, 600);
        itemdetailScene.getStylesheets().add("css/itemdetail.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Pane subheader = new Pane();
        subheader.setPrefSize(600, 80);
        subheader.getStyleClass().add("subheader");
        background.getChildren().add(subheader);

        Pane reservefiller = new Pane();
        reservefiller.setPrefSize(225, 50);
        reservefiller.getStyleClass().add("reservefiller");
        subheader.getChildren().add(reservefiller);
        addtoofocuslist(reservefiller);

        Label reservelabel = new Label("Reserve/Redeem reservation");
        reservelabel.setPrefSize(225, 50);
        reservelabel.getStyleClass().add("reservelabel");
        reservefiller.getChildren().add(reservelabel);

        Pane rentfiller = new Pane();
        rentfiller.setPrefSize(225, 50);
        rentfiller.getStyleClass().add("rentfiller");
        subheader.getChildren().add(rentfiller);
        addtoofocuslist(rentfiller);

        Label rentlabel = new Label("Rent");
        rentlabel.setPrefSize(225, 50);
        rentlabel.getStyleClass().add("rentlabel");
        rentfiller.getChildren().add(rentlabel);

        Pane detailspane = new Pane();
        detailspane.setPrefSize(600, 470);
        detailspane.setTranslateY(80);
        background.getChildren().add(detailspane);

        if (Main.getcurrententry() != null) {
            entry currententry = Main.getcurrententry();
            Image standin = new Image(databasehandler.imagefetch(currententry.getid()), 130, 130, false, false);
            ImagePattern loopattern = new ImagePattern(standin);

            Rectangle looptangle = new Rectangle(0, 0, 130, 130);
            looptangle.setArcHeight(55);
            looptangle.setArcWidth(55);
            looptangle.getStyleClass().add("squareimg");
            looptangle.setFill(loopattern);
            detailspane.getChildren().add(looptangle);

            HBox centertext = new HBox();
            centertext.setPrefSize(600, 10);
            centertext.setAlignment(Pos.CENTER);
            centertext.setTranslateY(190);
            detailspane.getChildren().add(centertext);

            Label itemname = new Label(currententry.getname());
            itemname.setPrefHeight(10);
            itemname.getStyleClass().add("itemname");
            centertext.getChildren().add(itemname);

            Label editionlabel = new Label("Editions");
            editionlabel.setPrefSize(100, 20);
            editionlabel.getStyleClass().add("editionlabel");
            detailspane.getChildren().add(editionlabel);

            Pane editionpane = new Pane();
            editionpane.setPrefSize(500, 70);
            editionpane.getStyleClass().add("editionpane");
            detailspane.getChildren().add(editionpane);

            Label lnumber = new Label();

            Label period = new Label();
            int amount = databasehandler.getcopies(currententry.gettype(), currententry.getname(),
                    currententry.getedition(),false);
            if (amount == 0) {
                rentfiller.setVisible(false);
                reservefiller.setVisible(true);
            } else {
                reservefiller.setVisible(false);
                rentfiller.setVisible(true);
            }

            Label stockcount = new Label(Integer.toString(amount));

            ArrayList<String> editloop = databasehandler.editions(currententry.getname(), currententry.gettype());
            for (String child : editloop) {
                Pane editfiller = new Pane();
                editfiller.setPrefSize(100, 30);
                editfiller.getStyleClass().add("editfiller");
                if (child.equals(currententry.getedition())) {
                    editfiller.getStyleClass().add("selectedstatus");
                }
                int tempwidth = editloop.size() - 1;
                tempwidth += -5 * Math.floor(editloop.indexOf(child) / 5);
                if (tempwidth > 4) {
                    tempwidth = 4;
                }
                editfiller.setTranslateX(200 + (-60 * tempwidth) + ((120) * ((editloop.indexOf(child)) % 5)));
                editfiller.setTranslateY(40 * Math.floor(editloop.indexOf(child) / 5));
                addtoofocuslist(editfiller);
                swapedition(editfiller, editionpane, itemname, lnumber, period, stockcount, currententry.gettype(),
                        looptangle, reservefiller, rentfiller);
                editionpane.getChildren().add(editfiller);

                Label editlabel = new Label(child);
                editlabel.setPrefSize(100, 30);
                editlabel.getStyleClass().add("editlabel");
                editfiller.getChildren().add(editlabel);
            }

            Label lfee = new Label("Late fee");
            lfee.setPrefSize(100, 30);
            lfee.getStyleClass().add("latefee");
            detailspane.getChildren().add(lfee);

            if (currententry.getduration().equals("Daily")) {
                lnumber.setText("£" + Double.toString(currententry.getfee()) + "/per Day");
            } else if (currententry.getduration().equals("Weekly")) {
                lnumber.setText("£" + Double.toString(currententry.getfee()) + "/per Week");
            } else if (currententry.getduration().equals("Monthly")) {
                lnumber.setText("£" + Double.toString(currententry.getfee()) + "/per Month");
            }
            lnumber.setPrefSize(100, 30);
            lnumber.getStyleClass().add("lnumber");
            detailspane.getChildren().add(lnumber);

            Label periodlab = new Label("Rental period");
            periodlab.setPrefSize(100, 30);
            periodlab.getStyleClass().add("periodlab");
            detailspane.getChildren().add(periodlab);

            if (currententry.getduration().equals("Daily")) {
                period.setText(Integer.toString(currententry.getperiod()) + " Days");
            } else if (currententry.getduration().equals("Weekly")) {
                period.setText(Integer.toString(currententry.getperiod()) + " Weeks");
            } else if (currententry.getduration().equals("Monthly")) {
                period.setText(Integer.toString(currententry.getperiod()) + " Months");
            }
            period.setPrefSize(100, 30);
            period.getStyleClass().add("periodfill");
            detailspane.getChildren().add(period);

            Label labelstock = new Label("Total Stock");
            labelstock.setPrefSize(100, 30);
            labelstock.getStyleClass().add("labelstock");
            detailspane.getChildren().add(labelstock);

            stockcount.setPrefSize(50, 30);
            stockcount.getStyleClass().add("stockcount");
            detailspane.getChildren().add(stockcount);

            Pane rentpopup = new Pane();
            rentpopup.setPrefSize(600, 500);
            rentpopup.setTranslateY(80);
            rentpopup.getStyleClass().add("popup");
            rentpopup.setVisible(false);
            background.getChildren().add(rentpopup);

            openpopup(reservefiller, rentpopup, detailspane, this);
            openpopup(rentfiller, rentpopup, detailspane, this);

            Pane searchbarpane = new Pane();
            searchbarpane.setPrefSize(500, 30);
            searchbarpane.setTranslateX(50);
            searchbarpane.setTranslateY(30);
            searchbarpane.getStyleClass().add("searchback");
            rentpopup.getChildren().add(searchbarpane);

            TextField searchfield = new TextField();
            searchfield.setPromptText("Customer email");
            searchfield.setPrefSize(460, 30);
            searchfield.getStyleClass().add("searchfield");
            addtoofocuslist(searchfield);
            searchbarpane.getChildren().add(searchfield);

            Pane searchfiller = new Pane();
            searchfiller.setPrefSize(30, 30);
            searchfiller.getStyleClass().add("searchfiller");
            searchfiller.setTranslateX(470);
            addtoofocuslist(searchfiller);
            searchbarpane.getChildren().add(searchfiller);

            Image searchicon = new Image("imagesicons/search-13-128.png", true);
            // https://www.iconsdb.com/black-icons/search-13-icon.html
            ImageView searchiconx = new ImageView(searchicon);
            searchiconx.setFitWidth(20);
            searchiconx.setPreserveRatio(true);
            searchiconx.getStyleClass().add("searchicon");
            searchfiller.getChildren().add(searchiconx);

            Label popuperror = new Label();
            popuperror.setPrefSize(500, 20);
            popuperror.getStyleClass().add("popuperror");
            rentpopup.getChildren().add(popuperror);

            Label popupemail = new Label("email");
            popupemail.setPrefSize(100, 50);
            popupemail.getStyleClass().add("popuplabel");
            popupemail.setTranslateY(100);
            rentpopup.getChildren().add(popupemail);

            Label popupemailfield = new Label();
            popupemailfield.setPrefSize(200, 50);
            popupemailfield.getStyleClass().add("popupfield");
            popupemailfield.setTranslateY(100);
            rentpopup.getChildren().add(popupemailfield);

            Label popupfullname = new Label("full name");
            popupfullname.setPrefSize(100, 50);
            popupfullname.getStyleClass().add("popuplabel");
            popupfullname.setTranslateY(170);
            rentpopup.getChildren().add(popupfullname);

            Label popupfullnamefield = new Label();
            popupfullnamefield.setPrefSize(200, 50);
            popupfullnamefield.getStyleClass().add("popupfield");
            popupfullnamefield.setTranslateY(170);
            rentpopup.getChildren().add(popupfullnamefield);

            Label popupid = new Label("id");
            popupid.setPrefSize(100, 50);
            popupid.getStyleClass().add("popuplabel");
            popupid.setTranslateY(240);
            rentpopup.getChildren().add(popupid);

            Label popupidfield = new Label();
            popupidfield.setPrefSize(200, 50);
            popupidfield.getStyleClass().add("popupfield");
            popupidfield.setTranslateY(240);
            rentpopup.getChildren().add(popupidfield);

            Label popupitems = new Label("renting");
            popupitems.setPrefSize(100, 50);
            popupitems.getStyleClass().add("popuplabel");
            popupitems.setTranslateY(310);
            rentpopup.getChildren().add(popupitems);

            Label popupitemsfield = new Label();
            popupitemsfield.setPrefSize(200, 50);
            popupitemsfield.getStyleClass().add("popupfield");
            popupitemsfield.setTranslateY(310);
            rentpopup.getChildren().add(popupitemsfield);

            Pane closeback = new Pane();
            closeback.setPrefSize(100, 50);
            closeback.setTranslateY(390);
            closeback.setTranslateX(100);
            closeback.getStyleClass().add("closeback");
            rentpopup.getChildren().add(closeback);
            addtoofocuslist(closeback);
            closepopup(closeback, rentpopup, detailspane, popupemailfield, popupfullnamefield, popupidfield,
                    popupitemsfield, searchfield, popuperror);

            Label closelabel = new Label("Close");
            closelabel.setPrefSize(100, 50);
            closelabel.getStyleClass().add("closelabel");
            closeback.getChildren().add(closelabel);

            Pane confirmback = new Pane();
            confirmback.setPrefSize(100, 50);
            confirmback.setTranslateY(390);
            confirmback.setTranslateX(400);
            confirmback.getStyleClass().add("confirmback");
            rentpopup.getChildren().add(confirmback);
            addtoofocuslist(confirmback);
            rentreserve(confirmback, itemname, editionpane, popupidfield, this, popupemailfield, popupfullnamefield,
                    popupitemsfield, stockcount, currententry.gettype(), reservefiller, rentfiller, searchfield,
                    popuperror);

            Label confirmlabel = new Label("Confirm");
            confirmlabel.setPrefSize(100, 50);
            confirmlabel.getStyleClass().add("confirmlabel");
            confirmback.getChildren().add(confirmlabel);

            emailfetch(searchfiller, searchfield, popupemailfield, popupfullnamefield, popupidfield, popupitemsfield,
                    null);
        }

        enabletabbing();
        return itemdetailScene;
    }

    public boolean getrent() {
        return rent;
    }

    public void setrent(boolean stat) {
        rent = stat;
    }

    private void closepopup(Node button, final Pane pop, final Pane details, final Label email, final Label name,
            final Label id, final Label renting, final TextField fie, final Label error) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setVisible(false);
                details.setVisible(true);
                email.setText("");
                name.setText("");
                id.setText("");
                renting.setText("");
                error.setText("");
                fie.setText(null);
                focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
                onpagentercheck();
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    pop.setVisible(false);
                    details.setVisible(true);
                    email.setText("");
                    name.setText("");
                    id.setText("");
                    renting.setText("");
                    error.setText("");
                    fie.setText(null);
                    focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
                    onpagentercheck();
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void openpopup(final Node button, final Pane pop, final Pane details, final itemdetail page) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setVisible(true);
                details.setVisible(false);
                for (String style : button.getStyleClass()) {
                    if (style.equals("reservefiller")) {
                        page.setrent(false);
                    }
                    if (style.equals("rentfiller")) {
                        page.setrent(true);
                    }
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    pop.setVisible(true);
                    details.setVisible(false);
                    for (String style : button.getStyleClass()) {
                        if (style == "reservefiller") {
                            page.setrent(false);
                        }
                        if (style.equals("rentfiller")) {
                            page.setrent(true);
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void swapedition(final Node button, final Pane paren, final Label name, final Label fee, final Label period,
            final Label stock, final String type, final Rectangle pic, final Pane reservelabel, final Pane rentlabel) {
        EventHandler<MouseEvent> getfield = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                swapEditionMethod(button, paren, name, fee, period, stock, type, pic, reservelabel, rentlabel);
            }
        };

        EventHandler<KeyEvent> keygetfield = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    swapEditionMethod(button, paren, name, fee, period, stock, type, pic, reservelabel, rentlabel);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, getfield);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keygetfield);
    }

    private void swapEditionMethod(Node button, Pane paren, Label name, Label fee, Label period, Label stock,
            String type, Rectangle pic, Pane reservelabel, Pane rentlabel) {
        for (Node child : paren.getChildren()) {
            child.getStyleClass().remove("selectedstatus");
        }
        button.getStyleClass().add("selectedstatus");
        Pane tempane = (Pane) button;
        Label templabel = (Label) tempane.getChildren().get(0);
        int amount = databasehandler.getcopies(type, name.getText(), templabel.getText(),false);
        stock.setText(Integer.toString(amount));
        if (amount == 0) {
            rentlabel.setVisible(false);
            reservelabel.setVisible(true);
        } else {
            reservelabel.setVisible(false);
            rentlabel.setVisible(true);
        }
        fee.setText(databasehandler.getfee(name.getText(), type, templabel.getText()));
        period.setText(databasehandler.getperiod(name.getText(), type, templabel.getText()));
        Image tempim = new Image(databasehandler.imagefetch(name.getText(), type, templabel.getText()), 130, 130, false,
                false);
        ImagePattern temppat = new ImagePattern(tempim);
        pic.setFill(temppat);
    }

    private void rentreserve(Node button, final Label item, final Pane editionpane, final Label id,
            final itemdetail page, final Label email, final Label name, final Label renting, final Label stock,
            final String type, final Pane reservefiller, final Pane rentfiller, final TextField fie,
            final Label error) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rentReseveMethod(item, editionpane, id, page, email, name, renting, stock, type, reservefiller,
                        rentfiller, fie, error);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    rentReseveMethod(item, editionpane, id, page, email, name, renting, stock, type, reservefiller,
                            rentfiller, fie, error);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void rentReseveMethod(Label item, Pane editionpane, Label id, itemdetail page, Label email, Label name,
            Label renting, Label stock, String type, Pane reservefiller, Pane rentfiller, TextField fie, Label error) {
        String edition = "";
        for (Node child : editionpane.getChildren()) {
            Pane temppane = (Pane) child;
            for (String cla : child.getStyleClass()) {
                if (cla.equals("selectedstatus")) {
                    Label templabel = (Label) temppane.getChildren().get(0);
                    edition = templabel.getText();
                }
            }
        }
        int updatenumber = databasehandler.sqlrentreserve(type, Integer.parseInt(id.getText()), item.getText(), edition,
                page.getrent());

        if (updatenumber == 0) {
            error.setText("all copies reserved");
        } else if (updatenumber == 1) {
            email.getParent().setVisible(false);
            editionpane.getParent().setVisible(true);
            email.setText("");
            name.setText("");
            id.setText("");
            renting.setText("");
            fie.setText("");
            error.setText("");
            focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
            onpagentercheck();
        } else if (updatenumber == 2) {
            email.getParent().setVisible(false);
            editionpane.getParent().setVisible(true);
            email.setText("");
            name.setText("");
            id.setText("");
            renting.setText("");
            fie.setText("");
            error.setText("");
            focusList.get(focuscounter).getStyleClass().remove("tabbablepane");
            onpagentercheck();
            int amount = databasehandler.getcopies(type, item.getText(), edition,false);
            stock.setText(Integer.toString(amount));
            if (amount == 0) {
                rentfiller.setVisible(false);
                reservefiller.setVisible(true);
            } else {
                reservefiller.setVisible(false);
                rentfiller.setVisible(true);
            }
        } else if (updatenumber == 3) {
            System.out.println("system error");
        } else if (updatenumber == 4) {
            error.setText("already renting this item type");
        } else if (updatenumber == 5) {
            error.setText("already reserving this item type");
        } else if (updatenumber == 6) {
            error.setText("already renting/reserving 5 items");
        }
    }
}
