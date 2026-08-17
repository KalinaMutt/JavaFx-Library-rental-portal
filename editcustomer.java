package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

import org.apache.commons.lang3.StringUtils;

public class editcustomer extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 15;
        thisscenetarget = 7;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene editcustomerscene = new Scene(root, 600, 600);
        editcustomerscene.getStylesheets().add("css/editcustomer.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        customer currentcustomer = Main.getcurrentcustomer();
        if (currentcustomer != null) {
            Label labelemail = new Label("email");
            labelemail.setPrefSize(100, 50);
            labelemail.getStyleClass().add("addinglabel");
            labelemail.setTranslateY(20);
            background.getChildren().add(labelemail);

            TextField textfieldemail = new TextField(currentcustomer.getemail());
            textfieldemail.setPrefSize(350, 50);
            textfieldemail.getStyleClass().add("addfield");
            textfieldemail.setTranslateY(20);
            addtoofocuslist(textfieldemail);
            background.getChildren().add(textfieldemail);

            Label labelfirst = new Label("First name");
            labelfirst.setPrefSize(100, 50);
            labelfirst.getStyleClass().add("addinglabel");
            labelfirst.setTranslateY(90);
            background.getChildren().add(labelfirst);

            TextField textfieldfirst = new TextField(currentcustomer.getfname());
            textfieldfirst.setPrefSize(350, 50);
            textfieldfirst.getStyleClass().add("addfield");
            textfieldfirst.setTranslateY(90);
            addtoofocuslist(textfieldfirst);
            background.getChildren().add(textfieldfirst);

            Label labellast = new Label("Last name");
            labellast.setPrefSize(100, 50);
            labellast.getStyleClass().add("addinglabel");
            labellast.setTranslateY(160);
            background.getChildren().add(labellast);

            TextField textfieldlast = new TextField(currentcustomer.getlname());
            textfieldlast.setPrefSize(350, 50);
            textfieldlast.getStyleClass().add("addfield");
            textfieldlast.setTranslateY(160);
            addtoofocuslist(textfieldlast);
            background.getChildren().add(textfieldlast);

            Label labeladdress = new Label("Address");
            labeladdress.setPrefSize(100, 50);
            labeladdress.getStyleClass().add("addinglabel");
            labeladdress.setTranslateY(230);
            background.getChildren().add(labeladdress);

            TextArea TextAreaaddress = new TextArea(currentcustomer.getaddress());
            TextAreaaddress.setPrefSize(350, 50);
            TextAreaaddress.getStyleClass().add("addfield");
            TextAreaaddress.setTranslateY(230);
            addtoofocuslist(TextAreaaddress);
            background.getChildren().add(TextAreaaddress);

            Label labelrenting = new Label("renting");
            labelrenting.setPrefSize(100, 50);
            labelrenting.getStyleClass().add("addinglabel");
            labelrenting.setTranslateY(300);
            background.getChildren().add(labelrenting);

            Label Label2renting = new Label((databasehandler.rentinglist(currentcustomer.getid(), false)));
            Label2renting.setPrefSize(350, 50);
            Label2renting.getStyleClass().add("addfield");
            Label2renting.setTranslateY(300);
            background.getChildren().add(Label2renting);

            Label labelreserving = new Label("reserving");
            labelreserving.setPrefSize(100, 50);
            labelreserving.getStyleClass().add("addinglabel");
            labelreserving.setTranslateY(370);
            background.getChildren().add(labelreserving);

            Label Label2reserving = new Label((databasehandler.rentinglist(currentcustomer.getid(), true)));
            Label2reserving.setPrefSize(350, 50);
            Label2reserving.getStyleClass().add("addfield");
            Label2reserving.setTranslateY(370);
            background.getChildren().add(Label2reserving);

            Pane changepane = new Pane();
            changepane.setPrefSize(200, 40);
            changepane.getStyleClass().add("changepane");
            addtoofocuslist(changepane);
            background.getChildren().add(changepane);
            customerchange(changepane, currentcustomer.getid(), textfieldemail, textfieldfirst, textfieldlast,
                    TextAreaaddress);

            Label changelabel = new Label("Confirm changes");
            changelabel.setPrefSize(200, 40);
            changelabel.getStyleClass().add("changelabel");
            changepane.getChildren().add(changelabel);

            Pane deletepane = new Pane();
            deletepane.setPrefSize(400, 50);
            deletepane.getStyleClass().add("deletepane");
            addtoofocuslist(deletepane);
            customerremove(deletepane, currentcustomer, 7);
            background.getChildren().add(deletepane);

            Label deletelabel = new Label("Delete customer");
            deletelabel.setPrefSize(400, 50);
            deletelabel.getStyleClass().add("deletelabel");
            deletepane.getChildren().add(deletelabel);

        }

        enabletabbing();
        return editcustomerscene;
    }

    private void customerremove(final Node button, final customer info, final int scenenumber) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane tempPane = (Pane) button;
                Label tempLabel = (Label) tempPane.getChildren().get(0);
                if (tempLabel.getText().equals("Delete customer")) {
                    tempLabel.setText("Confirm?");

                } else if (tempLabel.getText().equals("Confirm?")) {
                    if (databasehandler.removecustomer(info.getid()) == 1) {
                        Main.switchtarget(scenenumber);
                    } else if (databasehandler.removecustomer(info.getid()) == 0) {
                        tempLabel.setText("Customer renting/reserving");
                    }
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Pane tempPane = (Pane) button;
                    Label tempLabel = (Label) tempPane.getChildren().get(0);
                    if (tempLabel.getText().equals("Delete customer")) {
                        tempLabel.setText("Confirm?");

                    } else if (tempLabel.getText().equals("Confirm?")) {
                        int temper = databasehandler.removecustomer(info.getid());
                        if (temper == 1) {
                            Main.switchtarget(scenenumber);
                        } else if (temper == 0) {
                            tempLabel.setText("Customer renting/reserving");
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void customerchange(final Node button, final int id, final TextField email, final TextField fname,
            final TextField lname, final TextArea address) {
        EventHandler<MouseEvent> getfield = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane dPane = (Pane) button;
                Label dLabel = (Label) dPane.getChildren().get(0);
                dLabel.setText("Confirm changes");

                email.getStyleClass().remove("errorborder");
                fname.getStyleClass().remove("errorborder");
                lname.getStyleClass().remove("errorborder");
                address.getStyleClass().remove("errorborder");

                if (!(email.getText().matches("(.*)@(.*)"))) {
                    email.getStyleClass().add("errorborder");
                } else if (StringUtils.isBlank(fname.getText())) {

                    fname.getStyleClass().add("errorborder");
                } else if (StringUtils.isBlank(lname.getText())) {

                    lname.getStyleClass().add("errorborder");
                } else if (StringUtils.isBlank(address.getText())) {

                    address.getStyleClass().add("errorborder");
                } else {
                    int dNum = databasehandler.changecustomer(id, email.getText(), fname.getText(), lname.getText(),
                            address.getText());
                    if (dNum == 1) {
                        Main.switchtarget(7);
                    }
                    if (dNum == 0) {
                        email.getStyleClass().add("errorborder");
                        dLabel.setText("email in use");
                    }
                }
            }
        };

        EventHandler<KeyEvent> keygetfield = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Pane dPane = (Pane) button;
                    Label dLabel = (Label) dPane.getChildren().get(0);
                    dLabel.getStyleClass().remove("errorborder");

                    email.getStyleClass().remove("errorborder");
                    fname.getStyleClass().remove("errorborder");
                    lname.getStyleClass().remove("errorborder");
                    address.getStyleClass().remove("errorborder");

                    if (!(email.getText().matches("(.*)@(.*)"))) {
                        email.getStyleClass().add("errorborder");
                    } else if (StringUtils.isBlank(fname.getText())) {
                        fname.getStyleClass().add("errorborder");
                    } else if (StringUtils.isBlank(lname.getText())) {

                        lname.getStyleClass().add("errorborder");
                    } else if (StringUtils.isBlank(address.getText())) {
                        address.getStyleClass().add("errorborder");
                    } else {
                        int dNum = databasehandler.changecustomer(id, email.getText(), fname.getText(), lname.getText(),
                                address.getText());
                        if (dNum == 1) {
                            Main.switchtarget(7);
                        }
                        if (dNum == 0) {
                            email.getStyleClass().add("errorborder");
                            dLabel.getStyleClass().add("errorborder");
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, getfield);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keygetfield);
    }
}
