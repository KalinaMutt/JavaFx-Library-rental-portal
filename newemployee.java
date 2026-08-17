package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

import org.apache.commons.lang3.StringUtils;

public class newemployee extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 11;
        thisscenetarget = 8;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene newemployeeScene = new Scene(root, 600, 600);
        newemployeeScene.getStylesheets().add("css/newemployee.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);

        Label customertitle = new Label("New employee details");
        customertitle.setPrefSize(400, 50);
        customertitle.getStyleClass().add("addtitle");
        background.getChildren().add(customertitle);

        Label labelusername = new Label("Username");
        labelusername.setPrefSize(100, 50);
        labelusername.getStyleClass().add("addinglabel");
        labelusername.setTranslateY(90);
        background.getChildren().add(labelusername);

        TextField textfieldusername = new TextField();
        textfieldusername.setPrefSize(350, 50);
        textfieldusername.getStyleClass().add("addfield");
        textfieldusername.setTranslateY(90);
        addtoofocuslist(textfieldusername);
        background.getChildren().add(textfieldusername);

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
        labeladdress.setPrefSize(100, 50);
        labeladdress.getStyleClass().add("addinglabel");
        labeladdress.setTranslateY(300);
        background.getChildren().add(labeladdress);

        TextArea TextAreaaddress = new TextArea();
        TextAreaaddress.setPrefSize(350, 50);
        TextAreaaddress.getStyleClass().add("addfield");
        TextAreaaddress.setTranslateY(300);
        addtoofocuslist(TextAreaaddress);
        background.getChildren().add(TextAreaaddress);

        Label labelpassword = new Label("Password");
        labelpassword.setPrefSize(100, 50);
        labelpassword.getStyleClass().add("addinglabel");
        labelpassword.setTranslateY(370);
        background.getChildren().add(labelpassword);

        PasswordField fieldpassword = new PasswordField();
        fieldpassword.setPrefSize(350, 50);
        fieldpassword.getStyleClass().add("addfield");
        fieldpassword.setTranslateY(370);
        addtoofocuslist(fieldpassword);
        background.getChildren().add(fieldpassword);

        Label labeladmin = new Label("Admin");
        labeladmin.setPrefSize(100, 40);
        labeladmin.getStyleClass().add("addinglabel");
        labeladmin.setTranslateY(440);
        background.getChildren().add(labeladmin);

        Pane Paneyes = new Pane();
        Paneyes.setPrefSize(140, 40);
        Paneyes.setTranslateY(440);
        Paneyes.setTranslateX(200);
        Paneyes.getStyleClass().add("adminbutton");
        addtoofocuslist(Paneyes);
        background.getChildren().add(Paneyes);

        Label labelyes = new Label("Yes");
        labelyes.setPrefSize(140, 40);
        labelyes.getStyleClass().add("adminlabel");
        Paneyes.getChildren().add(labelyes);

        Pane Paneno = new Pane();
        Paneno.setPrefSize(140, 40);
        Paneno.setTranslateY(440);
        Paneno.setTranslateX(360);
        Paneno.getStyleClass().add("selectedstatus");
        Paneno.getStyleClass().add("adminbutton");
        addtoofocuslist(Paneno);
        background.getChildren().add(Paneno);

        adminassignment(Paneno, Paneyes);
        adminassignment(Paneyes, Paneno);

        Label labelno = new Label("No");
        labelno.setPrefSize(140, 40);
        labelno.getStyleClass().add("adminlabel");
        Paneno.getChildren().add(labelno);

        Pane addpane = new Pane();
        addpane.setPrefSize(200, 40);
        addpane.getStyleClass().add("addpane");
        addtoofocuslist(addpane);
        sendnewuser(addpane, textfieldusername, textfieldfirst, textfieldlast, TextAreaaddress, fieldpassword, Paneyes,
                Paneno);
        background.getChildren().add(addpane);

        Label addLabel = new Label("Add employee");
        addLabel.setPrefSize(200, 40);
        addLabel.getStyleClass().add("addlabel");
        addpane.getChildren().add(addLabel);

        enabletabbing();
        return newemployeeScene;
    }

    private void sendnewuser(final Node clicker, final TextField username, final TextField fname, final TextField lname,
            final TextArea Address, final PasswordField pword, final Pane yes, final Pane no) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sendNewUserMethod(clicker, username, fname, lname, Address, pword, yes, no);
            }
        };

        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    sendNewUserMethod(clicker, username, fname, lname, Address, pword, yes, no);
                }
            }
        };

        clicker.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        clicker.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void sendNewUserMethod(Node button, TextField username, TextField fname, TextField lname, TextArea Address,
            PasswordField pword, Pane yes, Pane no) {
        Pane dPane = (Pane) button;
        Label bLabel = (Label) dPane.getChildren().get(0);
        bLabel.setText("Confirm changes");
        username.getStyleClass().remove("errorborder");
        fname.getStyleClass().remove("errorborder");
        lname.getStyleClass().remove("errorborder");
        Address.getStyleClass().remove("errorborder");
        pword.getStyleClass().remove("errorborder");
        int hasher = (pword.getText()).hashCode();
        int admin = 0;
        for (String style : yes.getStyleClass()) {
            if (style.matches("selectedstatus")) {
                admin = 1;
            }
        }
        for (String style : no.getStyleClass()) {
            if (style.matches("selectedstatus")) {
                admin = 0;
            }
        }
        if (StringUtils.isBlank(username.getText())) {
            username.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(fname.getText())) {
            fname.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(lname.getText())) {
            lname.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(Address.getText())) {
            Address.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(pword.getText())) {
            pword.getStyleClass().add("errorborder");
        } else {
            int dnum = databasehandler.insertemployee(username.getText(), fname.getText(), lname.getText(),
                    Address.getText(), hasher, admin);
            if (dnum == 1) {
                username.setText("");
                fname.setText("");
                lname.setText("");
                Address.setText("");
                pword.setText("");
                yes.getStyleClass().remove("selectedstatus");
                no.getStyleClass().remove("selectedstatus");
                no.getStyleClass().add("selectedstatus");
            }
            if (dnum == 0) {
                bLabel.setText("username in use");
                username.getStyleClass().add("errorborder");
            }
        }
    }
}
