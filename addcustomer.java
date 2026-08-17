package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import org.apache.commons.lang3.StringUtils;

import javafx.event.EventHandler;

public class addcustomer extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 6;
        thisscenetarget = 4;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene addcustomerScene = new Scene(root, 600, 600);
        addcustomerScene.getStylesheets().add("css/addcustomer.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Label customertitle = new Label("New customer details");
        customertitle.setPrefSize(400, 50);
        customertitle.getStyleClass().add("addtitle");
        background.getChildren().add(customertitle);

        Label labelemail = new Label("Email");
        labelemail.setPrefSize(100, 50);
        labelemail.getStyleClass().add("addinglabel");
        labelemail.setTranslateY(90);
        background.getChildren().add(labelemail);

        TextField textfieldemail = new TextField();
        textfieldemail.setPrefSize(350, 50);
        textfieldemail.getStyleClass().add("addfield");
        textfieldemail.setTranslateY(90);
        addtoofocuslist(textfieldemail);
        background.getChildren().add(textfieldemail);

        Label labelfirst = new Label("First name");
        labelfirst.setPrefSize(100, 50);
        labelfirst.getStyleClass().add("addinglabel");
        labelfirst.setTranslateY(160);
        background.getChildren().add(labelfirst);

        TextField textfieldfirst = new TextField();
        textfieldfirst.setPrefSize(350, 50);
        textfieldfirst.getStyleClass().add("addfield");
        textfieldfirst.setTranslateY(160);
        addtoofocuslist(textfieldfirst);
        background.getChildren().add(textfieldfirst);

        Label labellast = new Label("Last name");
        labellast.setPrefSize(100, 50);
        labellast.getStyleClass().add("addinglabel");
        labellast.setTranslateY(230);
        background.getChildren().add(labellast);

        TextField textfieldlast = new TextField();
        textfieldlast.setPrefSize(350, 50);
        textfieldlast.getStyleClass().add("addfield");
        textfieldlast.setTranslateY(230);
        addtoofocuslist(textfieldlast);
        background.getChildren().add(textfieldlast);

        Label labeladdress = new Label("Address");
        labeladdress.setPrefSize(100, 100);
        labeladdress.getStyleClass().add("addinglabel");
        labeladdress.setTranslateY(300);
        background.getChildren().add(labeladdress);

        TextArea TextAreaaddress = new TextArea();
        TextAreaaddress.setPrefSize(350, 100);
        TextAreaaddress.getStyleClass().add("addfield");
        TextAreaaddress.setTranslateY(300);
        addtoofocuslist(TextAreaaddress);
        background.getChildren().add(TextAreaaddress);

        Pane addpane = new Pane();
        addpane.setPrefSize(200, 50);
        addpane.getStyleClass().add("addpane");
        addtoofocuslist(addpane);
        addcustomertrigger(addpane, textfieldemail, textfieldfirst, textfieldlast, TextAreaaddress);
        background.getChildren().add(addpane);

        Label addLabel = new Label("Add customer");
        addLabel.setPrefSize(200, 50);
        addLabel.getStyleClass().add("addlabel");
        addpane.getChildren().add(addLabel);

        enabletabbing();

        return addcustomerScene;
    }

    private void addcustomertrigger(final Node button, final TextField email, final TextField fname,
            final TextField lname, final TextArea address) {
        EventHandler<MouseEvent> getfield = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addCustomerTriggerMethod(button, email, fname, lname, address);
            }
        };
        EventHandler<KeyEvent> keyfield = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    addCustomerTriggerMethod(button, email, fname, lname, address);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, getfield);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keyfield);
    }

    private void addCustomerTriggerMethod(Node button, TextField email, TextField fname, TextField lname,
            TextArea address) {
        Pane bPane = (Pane) button;
        Label bLabel = (Label) bPane.getChildren().get(0);
        bLabel.setText("Add customer");
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
            int dnum = databasehandler.addcustomer(email.getText(), fname.getText(), lname.getText(),
                    address.getText());
            if (dnum == 1) {
                email.setText("");
                fname.setText("");
                lname.setText("");
                address.setText("");
            }
            if (dnum == 0) {
                bLabel.setText("email in use");
                email.getStyleClass().add("errorborder");
            }
        }
    }
}