package com.example;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import org.apache.commons.lang3.StringUtils;

public class viewentries extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 10;
        thisscenetarget = 8;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene viewentriesScene = new Scene(root, 600, 600);
        viewentriesScene.getStylesheets().add("css/viewentries.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);

        Pane searchbarpane = new Pane();
        searchbarpane.setPrefSize(220, 30);
        searchbarpane.setTranslateX(50);
        searchbarpane.setTranslateY(30);
        searchbarpane.getStyleClass().add("searchback");

        TextField searchfield = new TextField();
        searchfield.setPromptText("Item name");
        searchfield.setPrefSize(180, 30);
        searchfield.getStyleClass().add("searchfield");
        addtoofocuslist(searchfield);
        searchbarpane.getChildren().add(searchfield);

        Pane searchfiller = new Pane();
        searchfiller.setPrefSize(30, 30);
        searchfiller.getStyleClass().add("searchfiller");
        searchfiller.setTranslateX(190);

        Image searchicon = new Image("imagesicons/search-13-128.png", true);
        ImageView searchiconx = new ImageView(searchicon);
        searchiconx.setFitWidth(20);
        searchiconx.setPreserveRatio(true);
        searchiconx.getStyleClass().add("searchicon");
        searchfiller.getChildren().add(searchiconx);

        Pane searchbarpane2 = new Pane();
        searchbarpane2.setPrefSize(220, 30);
        searchbarpane2.setTranslateX(330);
        searchbarpane2.setTranslateY(30);
        searchbarpane2.getStyleClass().add("searchback");

        TextField searchfield2 = new TextField();
        searchfield2.setPromptText("Item id");
        searchfield2.setPrefSize(180, 30);
        searchfield2.getStyleClass().add("searchfield");
        searchbarpane2.getChildren().add(searchfield2);
        addtoofocuslist(searchfield2);

        Pane searchfiller2 = new Pane();
        searchfiller2.setPrefSize(30, 30);
        searchfiller2.getStyleClass().add("searchfiller");
        searchfiller2.setTranslateX(190);

        Image searchicon2 = new Image("imagesicons/search-13-128.png", true);
        // https://www.iconsdb.com/black-icons/search-13-icon.html

        ImageView searchiconx2 = new ImageView(searchicon2);
        searchiconx2.setFitWidth(20);
        searchiconx2.setPreserveRatio(true);
        searchiconx2.getStyleClass().add("searchicon");
        searchfiller2.getChildren().add(searchiconx2);

        ScrollPane entryscroll = new ScrollPane();
        entryscroll.setPrefSize(600, 480);
        entryscroll.getStyleClass().add("customerscroll");

        Pane itemfiller = new Pane();

        itemfiller.getStyleClass().add("itemfiller");
        entryscroll.setContent(itemfiller);
        typefiltering(searchfield, searchfield, searchfield2, itemfiller, 0, 2);
        typefiltering(searchfield2, searchfield2, searchfield, itemfiller, 2, 0);

        ArrayList<entry> entries = databasehandler.entrieslist();
        itemfiller.setPrefSize(550, (25 + 107) * Math.ceil((double) entries.size() / (double) 4) + 25);
        for (int i = 0; i < entries.size(); i++) {
            Pane loopane = new Pane();
            loopane.setPrefSize(107, 107);
            addtoofocuslist(loopane);
            passentry(loopane, entries.get(i), 14);

            loopane.setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
            loopane.setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));

            loopane.getStyleClass().add("scrollsub");

            Label loopname = new Label(entries.get(i).getname());
            loopname.setPrefSize(107, 20);
            loopname.getStyleClass().add("scrolltop");
            loopane.getChildren().add(loopname);

            Label looptype = new Label(entries.get(i).gettype());
            looptype.setPrefSize(107, 20);
            looptype.getStyleClass().add("scrollmid");
            loopane.getChildren().add(looptype);

            Label loopid = new Label(Integer.toString(entries.get(i).getid()));
            loopid.setPrefSize(107, 20);
            loopid.getStyleClass().add("scrollbottom");
            loopane.getChildren().add(loopid);

            itemfiller.getChildren().add(loopane);
        }

        background.getChildren().add(searchbarpane);
        searchbarpane.getChildren().add(searchfiller);

        background.getChildren().add(searchbarpane2);
        searchbarpane2.getChildren().add(searchfiller2);

        background.getChildren().add(entryscroll);

        enabletabbing();

        return viewentriesScene;
    }

    private ArrayList<Pane> itemfiltering(TextField value, TextField two, Pane filternode, int child, int child2) {
        ArrayList<Pane> itemlist = new ArrayList<Pane>();
        for (Node children : filternode.getChildren()) {
            Pane childrenstore = (Pane) children;
            Label usern = (Label) childrenstore.getChildren().get(child);
            Label usern2 = (Label) childrenstore.getChildren().get(child2);
            if (StringUtils.isBlank(two.getText())) {
                if (!(usern.getText().toLowerCase().matches(value.getText().toLowerCase() + "(.*)"))) {
                    childrenstore.setVisible(false);
                } else {
                    childrenstore.setVisible(true);
                    itemlist.add(childrenstore);
                }
            } else if (StringUtils.isBlank(value.getText())) {
                if (!(usern2.getText().toLowerCase().matches(two.getText().toLowerCase() + "(.*)"))) {
                    childrenstore.setVisible(false);
                } else {
                    childrenstore.setVisible(true);
                    itemlist.add(childrenstore);
                }
            }
        }
        return itemlist;
    }

    private void typefiltering(Node button, final TextField v, final TextField v2, final Pane f, final int child,
            final int child2) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<Pane> templist = itemfiltering(v, v2, f, child, child2);
                for (int i = 0; i < templist.size(); i++) {
                    templist.get(i).setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
                    templist.get(i).setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                ArrayList<Pane> templist = itemfiltering(v, v2, f, child, child2);
                for (int i = 0; i < templist.size(); i++) {
                    templist.get(i).setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
                    templist.get(i).setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_RELEASED, keychanger);
    }
}