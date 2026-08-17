package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import org.apache.commons.lang3.StringUtils;

public class editemployee extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 13;
        thisscenetarget = 12;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene editemployeeScene = new Scene(root, 600, 600);
        editemployeeScene.getStylesheets().add("css/editemployee.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);
        user currentemployee = Main.getcurrentemployee();
        if (currentemployee != null) {
            Label customertitle = new Label(currentemployee.getusername());
            customertitle.setPrefSize(400, 50);
            customertitle.getStyleClass().add("addtitle");
            background.getChildren().add(customertitle);

            Label labelfirst = new Label("First name");
            labelfirst.setPrefSize(100, 50);
            labelfirst.getStyleClass().add("addinglabel");
            labelfirst.setTranslateY(90);
            background.getChildren().add(labelfirst);

            TextField textfieldfirst = new TextField(currentemployee.getfname());
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

            TextField textfieldlast = new TextField(currentemployee.getlname());
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

            TextArea TextAreaaddress = new TextArea(currentemployee.getaddress());
            TextAreaaddress.setPrefSize(350, 50);
            TextAreaaddress.getStyleClass().add("addfield");
            TextAreaaddress.setTranslateY(230);
            addtoofocuslist(TextAreaaddress);
            background.getChildren().add(TextAreaaddress);

            Label labeladmin = new Label("Admin");
            labeladmin.setPrefSize(100, 40);
            labeladmin.getStyleClass().add("addinglabel");
            labeladmin.setTranslateY(300);
            background.getChildren().add(labeladmin);

            Pane Paneyes = new Pane();
            Paneyes.setPrefSize(140, 40);
            Paneyes.setTranslateY(300);
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
            Paneno.setTranslateY(300);
            Paneno.setTranslateX(360);
            Paneno.getStyleClass().add("adminbutton");
            addtoofocuslist(Paneno);
            background.getChildren().add(Paneno);

            Label labelno = new Label("No");
            labelno.setPrefSize(140, 40);
            labelno.getStyleClass().add("adminlabel");
            Paneno.getChildren().add(labelno);

            adminassignment(Paneno, Paneyes);
            adminassignment(Paneyes, Paneno);
            if (currentemployee.getadmin() == 1) {
                Paneyes.getStyleClass().add("selectedstatus");
            } else {
                Paneno.getStyleClass().add("selectedstatus");
            }

            Pane changepane = new Pane();
            changepane.setPrefSize(200, 40);
            changepane.getStyleClass().add("changepane");
            addtoofocuslist(changepane);
            background.getChildren().add(changepane);
            employeeedit(changepane, currentemployee.getusername(), textfieldfirst, textfieldlast, TextAreaaddress,
                    Paneyes, Paneno);

            Label changelabel = new Label("Confirm changes");
            changelabel.setPrefSize(200, 40);
            changelabel.getStyleClass().add("changelabel");
            changepane.getChildren().add(changelabel);

            Pane deletepane = new Pane();
            deletepane.setPrefSize(300, 80);
            deletepane.getStyleClass().add("deletepane");
            addtoofocuslist(deletepane);
            employeeremove(deletepane, currentemployee, 12);
            background.getChildren().add(deletepane);

            Label deletelabel = new Label("Delete employee");
            deletelabel.setPrefSize(300, 80);
            deletelabel.getStyleClass().add("deletelabel");
            deletepane.getChildren().add(deletelabel);

        }
        enabletabbing();
        return editemployeeScene;
    }

    private void employeeremove(final Node button, final user info, final int scenenumber) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane tempPane = (Pane) button;
                Label tempLabel = (Label) tempPane.getChildren().get(0);
                if (tempLabel.getText().equals("Delete employee")) {
                    tempLabel.setText("Confirm?");

                } else if (tempLabel.getText().equals("Confirm?")) {
                    if (databasehandler.removeemployee(info.getusername()) == true) {
                        Main.switchtarget(scenenumber);
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
                    if (tempLabel.getText().equals("Delete employee")) {
                        tempLabel.setText("Confirm?");

                    } else if (tempLabel.getText().equals("Confirm?")) {
                        if (databasehandler.removeemployee(info.getusername()) == true) {
                            Main.switchtarget(scenenumber);
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void employeeedit(Node clicker, final String username, final TextField fname, final TextField lname,
            final TextArea Address, final Pane yes, final Pane no) {
        EventHandler<MouseEvent> getfield = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                employeeEditMethod(username, fname, lname, Address, yes, no);
            }
        };
        EventHandler<KeyEvent> keygetfield = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    employeeEditMethod(username, fname, lname, Address, yes, no);
                }
            }
        };
        clicker.addEventHandler(MouseEvent.MOUSE_CLICKED, getfield);
        clicker.addEventHandler(KeyEvent.KEY_PRESSED, keygetfield);

    }

    private void employeeEditMethod(String username, TextField fname, TextField lname, TextArea Address, Pane yes,
            Pane no) {
        int admin = 0;
        fname.getStyleClass().remove("errorborder");
        lname.getStyleClass().remove("errorborder");

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
        if (StringUtils.isBlank(fname.getText())) {
            fname.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(lname.getText())) {

            lname.getStyleClass().add("errorborder");
        } else if (StringUtils.isBlank(Address.getText())) {
            Address.getStyleClass().add("errorborder");
        } else if (databasehandler.changeemployee(username, fname.getText(), lname.getText(), Address.getText(),
                admin) == true) {
            Main.switchtarget(12);
        }
    }
}
