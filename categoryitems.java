package com.example;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class categoryitems extends Pages {
    public Scene getScene(Pane root) {

        thisscenenumber = 5;
        thisscenetarget = 2;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene categoryitemsScene = new Scene(root, 600, 600);
        categoryitemsScene.getStylesheets().add("css/categoryitems.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        String currentcategory = Main.getcategory();
        Label categorytitlepane = new Label(currentcategory);
        categorytitlepane.setId("categorytitle");
        categorytitlepane.setPrefSize(300, 40);
        background.getChildren().add(categorytitlepane);

        Pane searchbarpane = new Pane();
        searchbarpane.setPrefSize(300, 30);
        searchbarpane.setTranslateX(150);
        searchbarpane.setTranslateY(90);
        searchbarpane.getStyleClass().add("searchback");

        TextField searchfield = new TextField();
        searchfield.setPromptText("Item name");
        searchfield.setPrefSize(200, 30);
        searchfield.getStyleClass().add("searchfield");
        addtoofocuslist(searchfield);
        searchbarpane.getChildren().add(searchfield);

        Pane searchfiller = new Pane();
        searchfiller.setPrefSize(30, 30);
        searchfiller.getStyleClass().add("searchfiller");
        searchfiller.setTranslateX(270);

        Image searchicon = new Image("imagesicons/search-13-128.png", true);
        ImageView searchiconx = new ImageView(searchicon);
        searchiconx.setFitWidth(20);
        searchiconx.setPreserveRatio(true);
        searchiconx.getStyleClass().add("searchicon");
        searchfiller.getChildren().add(searchiconx);

        ScrollPane entryscroll = new ScrollPane();
        entryscroll.setPrefSize(600, 400);
        entryscroll.getStyleClass().add("entryscroll");

        Pane itemfiller = new Pane();
        itemfiller.getStyleClass().add("itemfiller");
        entryscroll.setContent(itemfiller);

        ArrayList<entry> entries = databasehandler.categorylist(currentcategory);
        itemfiller.setPrefSize(550, (25 + 107) * Math.ceil((double) entries.size() / (double) 4) + 25);
        for (int i = 0; i < entries.size(); i++) {
            Pane loopane = new Pane();
            loopane.setPrefSize(107, 107);
            addtoofocuslist(loopane);
            passentry(loopane, entries.get(i), 16);

            loopane.setTranslateX((25 * ((i % 4) + 1)) + (107 * ((i) % 4)));
            loopane.setTranslateY(25 * (Math.floor(i / 4) + 1) + (107 * (Math.floor(i / 4))));

            loopane.getStyleClass().add("imagesub");

            Image standin = new Image(databasehandler.imagefetch(entries.get(i).getid()), 97, 70, false, false);
            ImagePattern loopattern = new ImagePattern(standin);

            Rectangle looptangle = new Rectangle(0, 0, 97, 70);
            looptangle.setTranslateX(5);
            looptangle.setTranslateY(5);
            looptangle.setArcHeight(55);
            looptangle.setArcWidth(55);
            looptangle.getStyleClass().add("squareimg");
            looptangle.setFill(loopattern);

            loopane.getChildren().add(looptangle);

            Label loopname = new Label(entries.get(i).getname());
            loopname.setPrefSize(107, 20);
            loopname.getStyleClass().add("imgbottom");
            loopane.getChildren().add(loopname);

            itemfiller.getChildren().add(loopane);
        }
        typefiltering(searchfield, searchfield, itemfiller, 1);

        background.getChildren().add(searchbarpane);
        searchbarpane.getChildren().add(searchfiller);

        background.getChildren().add(entryscroll);

        enabletabbing();
        return categoryitemsScene;
    }

}
