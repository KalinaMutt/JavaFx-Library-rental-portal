package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class standardoptions extends Pages {

    public Scene getScene(Pane root) {
        thisscenenumber = 1;
        thisscenetarget = 0;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene standardoptionsScene = new Scene(root, 600, 600);
        standardoptionsScene.getStylesheets().add("css/standardoptions.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, false);

        Pane rentPane = new Pane();
        rentPane.setPrefSize(166, 450);
        rentPane.getStyleClass().add("backpane");
        rentPane.setTranslateX(25);
        addtoofocuslist(rentPane);
        scenechange(rentPane, 2);
        background.getChildren().add(rentPane);

        Image rentImage = new Image("imagesicons/guzel-maksutova-B30XL_m3fso-unsplash.jpg", 160, 444, false, false);
        // https://unsplash.com/photos/B30XL_m3fso

        ImagePattern pattern = new ImagePattern(rentImage);
        Rectangle imageround = new Rectangle(0, 0, 160, 444);
        imageround.setArcHeight(55);
        imageround.setArcWidth(55);
        imageround.getStyleClass().add("paneimg");
        imageround.setFill(pattern);

        rentPane.getChildren().add(imageround);

        Label rentseethrough = new Label("Rent");
        rentseethrough.setPrefSize(160, 444);
        rentseethrough.getStyleClass().add("rentext");
        rentPane.getChildren().add(rentseethrough);

        Pane ReturnPane = new Pane();
        ReturnPane.setPrefSize(166, 450);
        ReturnPane.getStyleClass().add("backpane");
        addtoofocuslist(ReturnPane);
        scenechange(ReturnPane, 3);
        ReturnPane.setTranslateX(216);
        background.getChildren().add(ReturnPane);

        Image ReturnImage = new Image("imagesicons/milo-bauman-36kkkG28oN0-unsplash.jpg", 160, 444, false, false);
        // https://unsplash.com/photos/36kkkG28oN0

        ImagePattern returnpattern = new ImagePattern(ReturnImage);
        Rectangle returnimageround = new Rectangle(0, 0, 160, 444);
        returnimageround.setArcHeight(55);
        returnimageround.setArcWidth(55);
        returnimageround.getStyleClass().add("paneimg");
        returnimageround.setFill(returnpattern);

        ReturnPane.getChildren().add(returnimageround);

        Label Returnseethrough = new Label("Return");
        Returnseethrough.setPrefSize(160, 444);
        Returnseethrough.getStyleClass().add("rentext");
        ReturnPane.getChildren().add(Returnseethrough);

        Pane CustomersPane = new Pane();
        CustomersPane.setPrefSize(166, 450);
        CustomersPane.getStyleClass().add("backpane");
        addtoofocuslist(CustomersPane);
        scenechange(CustomersPane, 4);
        CustomersPane.setTranslateX(407);
        background.getChildren().add(CustomersPane);

        Image CustomersImage = new Image("imagesicons/francesco-lo-giudice-6gFWa9nXwCg-unsplash.jpg", 160, 444, false,
                false);
        // https://unsplash.com/photos/6gFWa9nXwCg

        ImagePattern Customerspattern = new ImagePattern(CustomersImage);
        Rectangle Customersimageround = new Rectangle(0, 0, 160, 444);
        Customersimageround.setArcHeight(55);
        Customersimageround.setArcWidth(55);
        Customersimageround.getStyleClass().add("paneimg");
        Customersimageround.setFill(Customerspattern);

        CustomersPane.getChildren().add(Customersimageround);

        Label Customersseethrough = new Label("Customers");
        Customersseethrough.setPrefSize(160, 444);
        Customersseethrough.getStyleClass().add("rentext");
        CustomersPane.getChildren().add(Customersseethrough);

        enabletabbing();

        return standardoptionsScene;
    }

}
