package com.example;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class viewemployees extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 12;
        thisscenetarget = 8;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene viewemployeesScene = new Scene(root, 600, 600);
        viewemployeesScene.getStylesheets().add("css/viewemployees.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);

        Pane searchbarpane = new Pane();
        searchbarpane.setPrefSize(500, 30);
        searchbarpane.setTranslateX(50);
        searchbarpane.setTranslateY(30);
        searchbarpane.getStyleClass().add("searchback");

        TextField searchfield = new TextField();
        searchfield.setPromptText("employee username");
        searchfield.setPrefSize(460, 30);
        searchfield.getStyleClass().add("searchfield");
        addtoofocuslist(searchfield);
        searchbarpane.getChildren().add(searchfield);

        Pane searchfiller = new Pane();
        searchfiller.setPrefSize(30, 30);
        searchfiller.getStyleClass().add("searchfiller");
        searchfiller.setTranslateX(470);

        Image searchicon = new Image("imagesicons/search-13-128.png", true);
        // https://www.iconsdb.com/black-icons/search-13-icon.html
        ImageView searchiconx = new ImageView(searchicon);
        searchiconx.setFitWidth(20);
        searchiconx.setPreserveRatio(true);
        searchiconx.getStyleClass().add("searchicon");
        searchfiller.getChildren().add(searchiconx);

        ScrollPane entryscroll = new ScrollPane();
        entryscroll.setPrefSize(600, 480);
        entryscroll.getStyleClass().add("customerscroll");

        Pane itemfiller = new Pane();
        itemfiller.getStyleClass().add("itemfiller");
        entryscroll.setContent(itemfiller);
        typefiltering(searchfield, searchfield, itemfiller, 2);

        ArrayList<user> users = databasehandler.userslist();
        itemfiller.setPrefSize(550, (25 + 107) * Math.ceil((double) users.size() / (double) 4) + 25);
        for (int i = 0; i < users.size(); i++) {

            Pane loopane = new Pane();
            loopane.setPrefSize(107, 107);
            addtoofocuslist(loopane);
            passemployee(loopane, users.get(i), 13);

            loopane.setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
            loopane.setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));

            loopane.getStyleClass().add("scrollsub");

            Label loopfname = new Label(users.get(i).getfname());
            loopfname.setPrefSize(107, 20);
            loopfname.getStyleClass().add("scrolltop");
            loopane.getChildren().add(loopfname);

            Label looplname = new Label(users.get(i).getlname());
            looplname.setPrefSize(107, 20);
            looplname.getStyleClass().add("scrollmid");
            loopane.getChildren().add(looplname);

            Label loopusername = new Label(users.get(i).getusername());
            loopusername.setPrefSize(107, 20);
            loopusername.getStyleClass().add("scrollbottom");
            loopane.getChildren().add(loopusername);

            itemfiller.getChildren().add(loopane);
        }

        background.getChildren().add(searchbarpane);
        searchbarpane.getChildren().add(searchfiller);

        background.getChildren().add(entryscroll);

        enabletabbing();
        return viewemployeesScene;
    }

    private void passemployee(Node button, final user info, final int scenenumber) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.setcurrentemployee(info);
                Main.switchtarget(scenenumber);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Main.setcurrentemployee(info);
                    Main.switchtarget(scenenumber);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

}