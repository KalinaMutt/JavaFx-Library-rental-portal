package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class customermenu extends Pages {
    public Scene getScene(Pane root) {
        thisscenenumber = 4;
        thisscenetarget = 1;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene customermenuScene = new Scene(root, 600, 600);
        customermenuScene.getStylesheets().add("css/customermenu.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Pane AddcPane = new Pane();
        AddcPane.setPrefSize(200, 450);
        AddcPane.getStyleClass().add("backpane");
        addtoofocuslist(AddcPane);
        scenechange(AddcPane, 6);
        AddcPane.setTranslateX(66);
        background.getChildren().add(AddcPane);

        Image AddcImage = new Image("imagesicons/van-tay-media-TFFn3BYLc5s-unsplash.jpg", 194, 444, false, false);
        // https://unsplash.com/photos/TFFn3BYLc5s

        ImagePattern Addcpattern = new ImagePattern(AddcImage);
        Rectangle Addcimageround = new Rectangle(0, 0, 194, 444);
        Addcimageround.setArcHeight(55);
        Addcimageround.setArcWidth(55);
        Addcimageround.getStyleClass().add("paneimg");
        Addcimageround.setFill(Addcpattern);

        AddcPane.getChildren().add(Addcimageround);

        Label Addcseethrough = new Label("Add customer");
        Addcseethrough.setPrefSize(194, 444);
        Addcseethrough.getStyleClass().add("rentext");
        AddcPane.getChildren().add(Addcseethrough);

        Pane viewcPane = new Pane();
        viewcPane.setPrefSize(200, 450);
        viewcPane.getStyleClass().add("backpane");
        addtoofocuslist(viewcPane);
        scenechange(viewcPane, 7);
        viewcPane.setTranslateX(332);
        background.getChildren().add(viewcPane);

        Image viewcImage = new Image("imagesicons/hal-gatewood-Nzb4LBsctyQ-unsplash.jpg", 194, 444, false, false);
        // https://unsplash.com/photos/Nzb4LBsctyQ

        ImagePattern viewcpattern = new ImagePattern(viewcImage);
        Rectangle viewcimageround = new Rectangle(0, 0, 194, 444);
        viewcimageround.setArcHeight(55);
        viewcimageround.setArcWidth(55);
        viewcimageround.getStyleClass().add("paneimg");
        viewcimageround.setFill(viewcpattern);

        viewcPane.getChildren().add(viewcimageround);

        Label viewcseethrough = new Label("View customers");
        viewcseethrough.setPrefSize(194, 444);
        viewcseethrough.getStyleClass().add("rentext");
        viewcPane.getChildren().add(viewcseethrough);

        enabletabbing();

        return customermenuScene;

    }

}
