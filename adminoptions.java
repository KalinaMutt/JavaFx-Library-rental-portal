package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class adminoptions extends Pages {

    public Scene getScene(Pane root) {
        thisscenenumber = 8;
        thisscenetarget = 0;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene adminoptionsScene = new Scene(root, 600, 600);
        adminoptionsScene.getStylesheets().add("css/adminoptions.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);

        for (int i = 0; i < 4; i++) {
            Pane loopane = new Pane();
            loopane.setPrefSize(200, 225);
            loopane.getStyleClass().add("squarepane");
            if (i == 0 || i == 2) {
                loopane.setTranslateX(50);
            } else {
                loopane.setTranslateX(325);
            }

            if (i == 0 || i == 1) {
                loopane.setTranslateY(50);
            } else {
                loopane.setTranslateY(300);
            }
            background.getChildren().add(loopane);

            addtoofocuslist(loopane);
            scenechange(loopane, (i + 9));

            Image loopImage = new Image("imagesicons/user-4-128.png");
            if (i == 0) {
                loopImage = new Image("imagesicons/maksym-kaharlytskyi-Q9y3LRuuxmg-unsplash.jpg", 194, 219, false,
                        false);
                // https://unsplash.com/photos/Q9y3LRuuxmg
            } else if (i == 1) {
                loopImage = new Image("imagesicons/inaki-del-olmo-NIJuEQw0RKg-unsplash.jpg", 194, 219, false, false);
                // https://unsplash.com/photos/NIJuEQw0RKg
            } else if (i == 2) {
                loopImage = new Image("imagesicons/sincerely-media-EtyBBUByPSQ-unsplash.jpg", 194, 219, false, false);
                // https://unsplash.com/photos/EtyBBUByPSQ
            } else if (i == 3) {
                loopImage = new Image("imagesicons/towfiqu-barbhuiya-FnA5pAzqhMM-unsplash.jpg", 194, 219, false, false);
                // https://unsplash.com/photos/FnA5pAzqhMM
            }

            ImagePattern loopattern = new ImagePattern(loopImage);

            Rectangle looptangle = new Rectangle(0, 0, 194, 219);
            looptangle.setArcHeight(55);
            looptangle.setArcWidth(55);
            looptangle.getStyleClass().add("squareimg");
            looptangle.setFill(loopattern);

            loopane.getChildren().add(looptangle);

            Label loopseethrough = new Label();
            if (i == 0) {
                loopseethrough.setText("New Item");
            } else if (i == 1) {
                loopseethrough.setText("View Items");
            } else if (i == 2) {
                loopseethrough.setText("New Employee");
            } else if (i == 3) {
                loopseethrough.setText("View Employees");
            }
            loopseethrough.setPrefSize(194, 219);
            loopseethrough.getStyleClass().add("squaretext");
            loopane.getChildren().add(loopseethrough);

        }
        enabletabbing();

        return adminoptionsScene;
    }
}
