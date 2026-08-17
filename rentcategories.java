package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class rentcategories extends Pages {

    public Scene getScene(Pane root) {
        thisscenenumber = 2;
        thisscenetarget = 1;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene rentcategoriesScene = new Scene(root, 600, 600);
        rentcategoriesScene.getStylesheets().add("css/rentcategories.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Pane VinylPane = new Pane();
        VinylPane.setPrefSize(500, 150);
        VinylPane.getStyleClass().add("hbackpane");
        VinylPane.setTranslateY(25);
        switchcategory("Vinyl", VinylPane, 5);
        addtoofocuslist(VinylPane);
        background.getChildren().add(VinylPane);

        Image VinylImage = new Image("imagesicons/eric-krull-fi3_lDi3qPE-unsplash.jpg", 494, 144, false, false);
        // https://unsplash.com/photos/fi3_lDi3qPE

        ImagePattern pattern = new ImagePattern(VinylImage);
        Rectangle imageround = new Rectangle(0, 0, 494, 144);
        imageround.setArcHeight(55);
        imageround.setArcWidth(55);
        imageround.getStyleClass().add("hpaneimg");
        imageround.setFill(pattern);

        VinylPane.getChildren().add(imageround);

        Label Vinylseethrough = new Label("Vinyl");
        Vinylseethrough.setPrefSize(496, 146);
        Vinylseethrough.getStyleClass().add("hrentext");
        VinylPane.getChildren().add(Vinylseethrough);

        Pane DVDPane = new Pane();
        DVDPane.setPrefSize(500, 150);
        DVDPane.getStyleClass().add("hbackpane");
        addtoofocuslist(DVDPane);
        switchcategory("DVD", DVDPane, 5);
        DVDPane.setTranslateY(200);
        background.getChildren().add(DVDPane);

        Image DVDImage = new Image("imagesicons/michael-dziedzic-0W4XLGITrHg-unsplash.jpg", 494, 144, false, false);
        // https://unsplash.com/photos/0W4XLGITrHg

        ImagePattern DVDpattern = new ImagePattern(DVDImage);
        Rectangle DVDimageround = new Rectangle(0, 0, 494, 144);
        DVDimageround.setArcHeight(55);
        DVDimageround.setArcWidth(55);
        DVDimageround.getStyleClass().add("hpaneimg");
        DVDimageround.setFill(DVDpattern);

        DVDPane.getChildren().add(DVDimageround);

        Label DVDseethrough = new Label("DVD");
        DVDseethrough.setPrefSize(496, 146);
        DVDseethrough.getStyleClass().add("hrentext");
        DVDPane.getChildren().add(DVDseethrough);

        Pane ToolsPane = new Pane();
        ToolsPane.setPrefSize(500, 150);
        ToolsPane.getStyleClass().add("hbackpane");
        addtoofocuslist(ToolsPane);
        switchcategory("Tool", ToolsPane, 5);
        ToolsPane.setTranslateY(375);
        background.getChildren().add(ToolsPane);

        Image ToolsImage = new Image("imagesicons/joanes-andueza-F49x0Vct5Lo-unsplash.jpg", 494, 144, false, false);
        // https://unsplash.com/photos/F49x0Vct5Lo

        ImagePattern Toolspattern = new ImagePattern(ToolsImage);
        Rectangle Toolsimageround = new Rectangle(0, 0, 494, 144);
        Toolsimageround.setArcHeight(55);
        Toolsimageround.setArcWidth(55);
        Toolsimageround.getStyleClass().add("hpaneimg");
        Toolsimageround.setFill(Toolspattern);

        ToolsPane.getChildren().add(Toolsimageround);

        Label Toolsseethrough = new Label("Tool");
        Toolsseethrough.setPrefSize(494, 144);
        Toolsseethrough.getStyleClass().add("hrentext");
        ToolsPane.getChildren().add(Toolsseethrough);

        enabletabbing();

        return rentcategoriesScene;

    }

    private void switchcategory(final String category, final Node thebutton, final int scenenumber) {
        EventHandler<MouseEvent> catchange = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.changecategory(category);
                Main.switchtarget(scenenumber);
            };
        };
        EventHandler<KeyEvent> keycatchange = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Main.changecategory(category);
                    Main.switchtarget(scenenumber);
                }
            }
        };

        thebutton.addEventHandler(MouseEvent.MOUSE_CLICKED, catchange);
        thebutton.addEventHandler(KeyEvent.KEY_PRESSED, keycatchange);

    }
}
