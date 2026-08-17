package com.example;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class loginPage extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 0;

        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene loginScene = new Scene(root, 600, 600);
        loginScene.getStylesheets().add("css/loginScene.css");

        Pane standardform = new Pane();
        standardform.setPrefSize(600, 550);
        standardform.setId("standardform");
        root.getChildren().add(standardform);

        Pane standardback = new Pane();
        standardback.setPrefSize(300, 50);
        standardback.setId("standardback");
        standardback.getStyleClass().add("hoverable");

        addtoofocuslist(standardback);

        root.getChildren().add(standardback);

        Label standardtab = new Label("Standard Login");
        standardtab.setPrefSize(300, 50);
        standardtab.setId("standardtab");
        standardback.getChildren().add(standardtab);

        Pane adminback = new Pane();
        adminback.setPrefSize(300, 50);
        adminback.setId("adminback");

        addtoofocuslist(adminback);

        root.getChildren().add(adminback);

        Label admintab = new Label("Admin Login");
        admintab.setPrefSize(300, 50);
        admintab.setId("admintab");
        adminback.getChildren().add(admintab);

        Image logo = new Image("/imagesicons/papers+p+tran.png", true);
        ImageView logox = new ImageView(logo);
        logox.setFitWidth(400);
        logox.setPreserveRatio(true);
        logox.setId("logo");
        logox.setStyle("visibility:hidden");
        standardform.getChildren().add(logox);

        Image logo2 = new Image("/imagesicons/papers+p+tran+black.png", true);
        ImageView logox2 = new ImageView(logo2);
        logox2.setFitWidth(400);
        logox2.setPreserveRatio(true);
        logox2.setId("logo2");
        standardform.getChildren().add(logox2);

        Label admin = new Label("ADMIn");
        admin.setPrefSize(300, 50);
        admin.setId("admintext");
        standardform.getChildren().add(admin);

        Pane formbox = new Pane();
        formbox.setPrefSize(400, 400);
        formbox.setId("formbox");
        root.getChildren().add(formbox);

        Pane userbox = new Pane();
        userbox.setPrefSize(50, 50);
        userbox.setLayoutY(50);
        userbox.getStyleClass().add("iconbox");
        formbox.getChildren().add(userbox);

        Image user1 = new Image("/imagesicons/user-4-128.png", true);
        // https://www.iconsdb.com/black-icons/user-4-icon.html
        ImageView user1x = new ImageView(user1);
        user1x.setFitHeight(30);
        user1x.setFitWidth(30);
        user1x.setPreserveRatio(true);
        user1x.getStyleClass().add("loginuser");
        userbox.getChildren().add(user1x);

        Image user2 = new Image("/imagesicons/user-4-128 (1).png", true);
        ImageView user2x = new ImageView(user2);
        user2x.setFitHeight(30);
        user2x.setFitWidth(30);
        user2x.setPreserveRatio(true);
        user2x.getStyleClass().add("loginuser");
        user2x.setStyle("visibility:hidden");
        userbox.getChildren().add(user2x);

        Pane divider1 = new Pane();
        divider1.getStyleClass().add("logindivider");
        divider1.setPrefSize(5, 30);
        userbox.getChildren().add(divider1);

        TextField userfield = new TextField();
        userfield.setPromptText("Username");
        userfield.setPrefSize(250, 50);
        userfield.setLayoutY(50);
        userfield.getStyleClass().add("loginfield");

        addtoofocuslist(userfield);

        formbox.getChildren().add(userfield);

        Pane passbox = new Pane();
        passbox.setPrefSize(50, 50);
        passbox.setLayoutY(150);
        passbox.getStyleClass().add("iconbox");
        formbox.getChildren().add(passbox);

        Image pass1 = new Image("/imagesicons/lock-7-128.png", true);
        // https://www.iconsdb.com/black-icons/lock-7-icon.html
        ImageView pass1x = new ImageView(pass1);
        pass1x.setFitHeight(30);
        pass1x.setFitWidth(30);
        pass1x.setPreserveRatio(true);
        pass1x.getStyleClass().add("loginuser");
        passbox.getChildren().add(pass1x);

        Image pass2 = new Image("/imagesicons/lock-7-128 (1).png", true);
        ImageView pass2x = new ImageView(pass2);
        pass2x.setFitHeight(30);
        pass2x.setFitWidth(30);
        pass2x.setPreserveRatio(true);
        pass2x.getStyleClass().add("loginuser");
        pass2x.setStyle("visibility:hidden");
        passbox.getChildren().add(pass2x);

        Pane divider2 = new Pane();
        divider2.getStyleClass().add("logindivider");
        divider2.setPrefSize(5, 30);
        passbox.getChildren().add(divider2);

        PasswordField passfield = new PasswordField();
        passfield.setPromptText("Password");
        passfield.setPrefSize(250, 50);
        passfield.setLayoutY(150);
        passfield.getStyleClass().add("loginfield");

        addtoofocuslist(passfield);

        formbox.getChildren().add(passfield);

        Pane loginback = new Pane();
        loginback.setPrefSize(200, 80);
        loginback.setId("loginback");

        addtoofocuslist(loginback);

        root.getChildren().add(loginback);

        Label loginlabel = new Label("Login");
        loginlabel.setPrefSize(200, 80);
        loginlabel.setId("loginlabel");

        loginback.getChildren().add(loginlabel);

        enabletabbing();
        getfocuslist().get(0).requestFocus();

        loginoswitch(standardback, standardform, admin, loginlabel, loginback, divider1, divider2, pass1x, user1x,
                pass2x, user2x, logox, logox2);
        loginoswitch(adminback, standardform, admin, loginlabel, loginback, divider1, divider2, pass1x, user1x, pass2x,
                user2x, logox, logox2);

        loggingin(loginback, userfield, passfield);

        return loginScene;

    }

    private void loginoswitch(final Node thebutton, final Node standardform, final Node admin, final Node loginlabel,
            final Node loginback, final Node divider1, final Node divider2, final Node pass1x, final Node user1x,
            final Node pass2x, final Node user2x, final Node logo1, final Node logo2) {
        EventHandler<MouseEvent> standardswitch = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchknowledge(thebutton, standardform, admin, loginlabel, loginback, divider1, divider2, pass1x,
                        user1x, pass2x, user2x, logo1, logo2);
            }
        };
        EventHandler<KeyEvent> keystandardswitch = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    switchknowledge(thebutton, standardform, admin, loginlabel, loginback, divider1, divider2, pass1x,
                            user1x, pass2x, user2x, logo1, logo2);
                }
            }
        };

        thebutton.addEventHandler(MouseEvent.MOUSE_CLICKED, standardswitch);
        thebutton.addEventHandler(KeyEvent.KEY_PRESSED, keystandardswitch);
    }

    private void switchknowledge(Node thebutton, Node standardform, Node admin, Node loginlabel, Node loginback,
            Node divider1, Node divider2, Node pass1x, Node user1x, Node pass2x, Node user2x, Node logo1, Node logo2) {
        if (thebutton.getId().equals("standardback")) {
            standardform.setStyle("-fx-background-color:#77FF94");
            admin.setStyle("visibility:hidden;");
            loginlabel.setStyle("-fx-text-fill:#77FF94;");
            loginback.setStyle("-fx-background-color:#558564;");
            divider1.setStyle("-fx-background-color:#77FF94");
            divider2.setStyle("-fx-background-color:#77FF94");

            pass1x.setStyle("visibility:visible;");
            user1x.setStyle("visibility:visible;");

            logo1.setStyle("visibility:hidden");
            logo2.setStyle("visibility:visible");

            pass2x.setStyle("visibility:hidden;");
            user2x.setStyle("visibility:hidden;");

            setonadmin(false);
        } else {
            standardform.setStyle("-fx-background-color:#558564");
            admin.setStyle("visibility:visible;");
            loginlabel.setStyle("-fx-text-fill:#558564;");
            loginback.setStyle("-fx-background-color:#77FF94");
            divider1.setStyle("-fx-background-color:#558564");
            divider2.setStyle("-fx-background-color:#558564");

            pass1x.setStyle("visibility:hidden;");
            user1x.setStyle("visibility:hidden;");

            logo1.setStyle("visibility:visible");
            logo2.setStyle("visibility:hidden");

            pass2x.setStyle("visibility:visible;");
            user2x.setStyle("visibility:visible;");
            setonadmin(true);
        }
    }

    private void loggingin(Node button, final TextField userfield, final PasswordField passfield) {
        EventHandler<MouseEvent> performlogin = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logginInmethod(userfield, passfield);
            }
        };
        EventHandler<KeyEvent> keylogin = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    logginInmethod(userfield, passfield);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, performlogin);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keylogin);
    }

    private void logginInmethod(TextField userfield, PasswordField passfield) {
        userfield.getStyleClass().remove("errorborder");
        passfield.getStyleClass().remove("errorborder");
        user tempnum = databasehandler.logindata(userfield.getText());
        if (tempnum == null) {

            userfield.getStyleClass().add("errorborder");
            return;
        } else {

            if (tempnum.getpassword() == (passfield.getText()).hashCode()) {
                Main.alteradmin(tempnum);
                Main.switchtarget(1);
                userfield.setText("");
                passfield.setText("");
            } else {
                passfield.getStyleClass().add("errorborder");
            }
        }
    }

}